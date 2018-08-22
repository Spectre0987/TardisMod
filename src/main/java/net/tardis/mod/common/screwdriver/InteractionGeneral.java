package net.tardis.mod.common.screwdriver;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.tardis.mod.Tardis;
import net.tardis.mod.util.helpers.PlayerHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Mod.EventBusSubscriber(modid = Tardis.MODID)
public class InteractionGeneral implements IScrew {

    Method dispense = ReflectionHelper.findMethod(BlockDispenser.class, "dispense", "func_176439_d", World.class, BlockPos.class);

    @Override
    public void performAction(World world, EntityPlayer player, EnumHand hand) {

    }

    @Override
    public void blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (world.isRemote) return;

        Block block = state.getBlock();

        //It doesn't work on wood.
        if (block.isWood(world,pos) || block.getRegistryName().toString().contains("wood") || state.getMaterial().equals(Material.WOOD)) {
            PlayerHelper.sendMessage(player, "screw.fail.itswood", true);
            return;
        }

        //TNT
        if (block instanceof BlockTNT) {
            BlockTNT tnt = (BlockTNT) block;
            tnt.explode(world, pos, state.withProperty(BlockTNT.EXPLODE, true), player);
            world.setBlockToAir(pos);
            coolDown(player);
            return;
        }

        //Open trap doors
        // TO-DO bring across the trap doors sounds, cycling the property and making it open is cool and all
        // But it's boring without sounds
        if (block instanceof BlockTrapDoor) {
            IBlockState newState = state.cycleProperty(BlockTrapDoor.OPEN);
            markUpdate(world, pos, newState);
            coolDown(player);
            return;
        }

        //Open doors
        if (block instanceof BlockDoor) {
            int meta = block.getMetaFromState(state);
            boolean isTop = (meta & 8) != 0;

            if (isTop) {
                PlayerHelper.sendMessage(player, "screw.fail.bottom-door", true);
            } else {
                IBlockState newState = state.cycleProperty(BlockDoor.OPEN);
                markUpdate(world, pos, newState);
                coolDown(player);
            }
            return;
        }


        if (block instanceof BlockDispenser) {
            if (!player.isSneaking()) return;
            try {
                dispense.invoke(block, world, pos);
                coolDown(player);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void entityInteraction(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

    }

    private void markUpdate(World world, BlockPos pos, IBlockState state) {
        world.setBlockState(pos, state, 10);
        world.markBlockRangeForRenderUpdate(pos, pos);
    }

    private void coolDown(EntityPlayer player) {
        Item stack = player.getHeldItem(player.getActiveHand()).getItem();
        player.getCooldownTracker().setCooldown(stack, 50);
    }

    @Override
    public String getName() {
        return "screw.general";
    }


}
