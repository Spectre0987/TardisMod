package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;

public class BlockGallifreyDirt extends Block {

    public BlockGallifreyDirt(Boolean isTop) {

        super(Material.GROUND);
        this.setHardness(1F);
        this.setResistance(1F);
        setCreativeTab(TardisTabs.GALLIFREY_BLOCKS);


        if (isTop) {

            this.setSoundType(SoundType.PLANT);
        }
        else{

            this.setSoundType(SoundType.GROUND);
        }

    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (!world.isRemote) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this));
            world.spawnEntity(item);
        }
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.clear();
    }

}