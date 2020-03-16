package net.tardis.mod.common.blocks;

import com.google.common.base.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityMultiblock;
import net.tardis.mod.common.tileentity.decoration.TileEntityToyotaSpin;

public class BlockToyotaSpin extends BlockMultiblockMaster {

	Supplier<TileEntity> sup;
	ItemBlock item = new ItemToyotaSpin(this);

	public BlockToyotaSpin(Supplier<TileEntity> supplier) {
		super(Material.IRON);
		this.setHardness(5F);
		this.setLightLevel(1F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityToyotaSpin();
	}

	@Override
	public ItemBlock getItem() {
		return item;
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.clear();
		drops.add(new ItemStack(this));
		super.getDrops(drops, world, pos, state, fortune);
	}

	public static class ItemToyotaSpin extends ItemBlock {

		public ItemToyotaSpin(Block block) {
			super(block);
		}

		@Override
		public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
			if (side != EnumFacing.DOWN)
				return false;
			boolean place = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
			for (BlockPos nPos : BlockPos.getAllInBox(pos.offset(EnumFacing.NORTH).offset(EnumFacing.EAST), pos.offset(EnumFacing.SOUTH).offset(EnumFacing.WEST))) {
				if (!nPos.equals(pos)) {
					world.setBlockState(nPos, TBlocks.multiblock.getDefaultState());
					TileEntityMultiblock multi = (TileEntityMultiblock) world.getTileEntity(nPos);
					if (multi != null)
						multi.setMaster(pos);
				}
			}
			((TileEntityToyotaSpin) world.getTileEntity(pos)).addChildren(pos);
			world.setBlockState(pos.down(), TBlocks.multiblock.getDefaultState());
			((TileEntityMultiblock) world.getTileEntity(pos.down())).setMaster(pos);
			return place;
		}

		@Override
		public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
			if(side != EnumFacing.DOWN)
				return false;
			pos = pos.down();
			for (BlockPos nPos : BlockPos.getAllInBox(pos.offset(EnumFacing.NORTH).offset(EnumFacing.EAST), pos.offset(EnumFacing.SOUTH).offset(EnumFacing.WEST))) {
				if(!worldIn.getBlockState(nPos).getMaterial().isReplaceable()) {
					return false;
				}
			}
			
			return true;
		}

	}
}
