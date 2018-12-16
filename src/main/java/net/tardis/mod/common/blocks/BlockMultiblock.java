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
		return EnumBlockRenderType.INVISIBLE;
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

	
	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
		//if(worldIn != null && worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileEntityMultiblock && !(worldIn.getTileEntity(pos) instanceof TileEntityMultiblockMaster))
			//return worldIn.getBlockState(((TileEntityMultiblock)worldIn.getTileEntity(pos)).getMasterPos()).getBlockHardness(blockState, worldIn, pos);
		return super.getBlockHardness(blockState, worldIn, pos);
	}
}
