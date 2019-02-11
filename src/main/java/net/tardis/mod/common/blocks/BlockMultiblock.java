package net.tardis.mod.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityMultiblock;
import net.tardis.mod.common.tileentity.TileEntityMultiblockMaster;

public class BlockMultiblock extends BlockContainer {

	public BlockMultiblock(Material material) {
		super(material);
		this.hasTileEntity = true;
	}

	public static IBlockState getMasterState(World world, BlockPos pos) {
		TileEntityMultiblock multi = (TileEntityMultiblock) world.getTileEntity(pos);
		if (multi != null)
			return world.getBlockState(multi.getMasterPos());
		return BlockMultiblockMaster.DUMMY.getDefaultState();
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
		TileEntityMultiblock tile = (TileEntityMultiblock) worldIn.getTileEntity(pos);
		if (tile != null) {
			TileEntityMultiblockMaster master = (TileEntityMultiblockMaster) worldIn.getTileEntity(tile.getMasterPos());
			if (master != null) {
				for (BlockPos child : master.getChildren()) {
					worldIn.getBlockState(child).getBlock().harvestBlock(worldIn, player, pos, state, worldIn.getTileEntity(child), player.getHeldItemMainhand());
					worldIn.setBlockToAir(child);
				}
			}
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityMultiblock mBlock = (TileEntityMultiblock) worldIn.getTileEntity(pos);
		if (mBlock == null || mBlock.getMasterPos() == null) return false;
		TileEntityMultiblockMaster master = (TileEntityMultiblockMaster) worldIn.getTileEntity(mBlock.getMasterPos());
		if (master != null && master.getChildren() != null) {
			for (BlockPos child : master.getChildren()) {
				if (child == null) {
				IBlockState childState = worldIn.getBlockState(child);
				childState.getBlock().onBlockActivated(worldIn, child, childState, playerIn, hand, facing, hitX, hitY, hitZ);
			}
		}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMultiblock();
	}

	@Override
	public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
		IBlockState master = getMasterState(worldIn, pos);
		return master.getBlockHardness(worldIn, pos);
	}

	@Override
	public EnumPushReaction getPushReaction(IBlockState state) {
		return EnumPushReaction.IGNORE;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(getMasterState(world, pos).getBlock());
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
		return getMasterState(worldIn, pos).getBlockHardness(worldIn, pos);
	}
}
