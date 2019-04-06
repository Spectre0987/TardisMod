package net.tardis.mod.common.screwdriver;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockRedstoneLight;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.tardis.mod.Tardis;
import net.tardis.mod.util.common.helpers.PlayerHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.minecraft.init.Blocks.REDSTONE_LAMP;

@Mod.EventBusSubscriber(modid = Tardis.MODID)
public class InteractionGeneral implements IScrew {
	
	private Method dispense = ReflectionHelper.findMethod(BlockDispenser.class, "dispense", "func_176439_d", World.class, BlockPos.class);
	
	@Override
	public EnumActionResult performAction(World world, EntityPlayer player, EnumHand hand) {
		return EnumActionResult.FAIL;
	}
	
	@Override
	public EnumActionResult blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (world.isRemote) return EnumActionResult.FAIL;
		
		Block block = state.getBlock();
		
		//It doesn't work on wood.
		if (block.isWood(world, pos) || block.getRegistryName().toString().contains("wood") || state.getMaterial().equals(Material.WOOD)) {
			PlayerHelper.sendMessage(player, "screw.fail.itswood", true);
			return EnumActionResult.FAIL;
		}
		
		//TNT
		if (block instanceof BlockTNT) {
			BlockTNT tnt = (BlockTNT) block;
			tnt.explode(world, pos, state.withProperty(BlockTNT.EXPLODE, true), player);
			world.setBlockToAir(pos);
			return EnumActionResult.SUCCESS;
		}
		
		if (block instanceof BlockTrapDoor) {
			IBlockState newState = state.cycleProperty(BlockTrapDoor.OPEN);
			markUpdate(world, pos, newState);
			
			if (state.getValue(BlockTrapDoor.OPEN)) {
				int i = state.getMaterial() == Material.IRON ? 1037 : 1007;
				world.playEvent(player, i, pos, 0);
			} else {
				int j = state.getMaterial() == Material.IRON ? 1036 : 1013;
				world.playEvent(player, j, pos, 0);
			}
			
			return EnumActionResult.SUCCESS;
		}
		
		if (block instanceof BlockRedstoneLight) {
			if (block == REDSTONE_LAMP) {
				world.setBlockState(pos, Blocks.LIT_REDSTONE_LAMP.getDefaultState(), 2);
				return EnumActionResult.SUCCESS;
			} else {
				world.setBlockState(pos, Blocks.REDSTONE_LAMP.getDefaultState(), 2);
				return EnumActionResult.SUCCESS;
			}
		}
		
		
		//Open doors
		if (block instanceof BlockDoor) {
			int meta = block.getMetaFromState(state);
			boolean isTop = (meta & 8) != 0;
			
			if (isTop) {
				IBlockState down = world.getBlockState(pos.down());
				IBlockState newState = down.cycleProperty(BlockDoor.OPEN);
				markUpdate(world, pos.down(), newState);
				world.playEvent(player, state.getValue(BlockDoor.OPEN) ? state.getMaterial() == Material.IRON ? 1005 : 1006 : state.getMaterial() == Material.IRON ? 1011 : 1012, pos, 0);
			} else {
				IBlockState newState = state.cycleProperty(BlockDoor.OPEN);
				markUpdate(world, pos, newState);
				world.playEvent(player, state.getValue(BlockDoor.OPEN) ? state.getMaterial() == Material.IRON ? 1005 : 1006 : state.getMaterial() == Material.IRON ? 1011 : 1012, pos, 0);
			}
			return EnumActionResult.SUCCESS;
		}
		
		
		if (block instanceof BlockDispenser) {
			if (!player.isSneaking()) return EnumActionResult.FAIL;
			try {
				dispense.invoke(block, world, pos);
				
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
	
	@Override
	public boolean entityInteraction(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		return false;
	}
	
	private void markUpdate(World world, BlockPos pos, IBlockState state) {
		world.setBlockState(pos, state, 10);
		world.markBlockRangeForRenderUpdate(pos, pos);
	}
	
	@Override
	public String getName() {
		return "screw.general";
	}
	
	@Override
	public int getCoolDownAmount() {
		return 50;
	}
	
	@Override
	public boolean causesCoolDown() {
		return true;
	}
	
	@Override
	public int energyRequired() {
		return 5;
	}
	
	
}
