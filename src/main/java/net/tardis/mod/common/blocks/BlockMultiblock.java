package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityMultiblock;
import net.tardis.mod.common.tileentity.TileEntityMultiblockMaster;

public class BlockMultiblock extends BlockContainer {

	public BlockMultiblock(Material material) {
		super(material);
		this.hasTileEntity = true;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		super.onBlockHarvested(worldIn, pos, state, player);
		TileEntityMultiblock tile = (TileEntityMultiblock) worldIn.getTileEntity(pos);
		if(tile != null) {
			TileEntityMultiblockMaster master = (TileEntityMultiblockMaster) worldIn.getTileEntity(tile.getMasterPos());
			if(master != null) {
				for(BlockPos child : master.getChildren()) {
					worldIn.setBlockToAir(child);
				}
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMultiblock();
	}

	public static class ItemMultiblock extends ItemBlock{

		public ItemMultiblock(Block block) {
			super(block);
		}

		@Override
		public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
			boolean place = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
			if(place) {
				for(EnumFacing face : EnumFacing.VALUES) {
					if(face != side.getOpposite()) {
						BlockPos setPos = pos.offset(face);
						world.setBlockState(setPos, TBlocks.multiblock.getDefaultState());
						TileEntityMultiblock block = (TileEntityMultiblock) world.getTileEntity(setPos);
						if(block != null)
							block.setMasterPos(pos);
					}
					((TileEntityMultiblockMaster) world.getTileEntity(pos)).addChildren(pos);
				}
			}
			return place;
		}
		
	}
}
