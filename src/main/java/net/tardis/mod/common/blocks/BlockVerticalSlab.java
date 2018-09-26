package net.tardis.mod.common.blocks;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVerticalSlab extends BlockBase {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final AxisAlignedBB NORTH = new AxisAlignedBB(0, 0, 0, 1, 1, 0.5);
	public static final AxisAlignedBB EAST = new AxisAlignedBB(0.5, 0, 0, 1, 1, 1);
	public static final AxisAlignedBB SOUTH = new AxisAlignedBB(0, 0, 0.5, 1, 1, 1);
	public static final AxisAlignedBB WEST = new AxisAlignedBB(0, 0, 0, 0.5, 1, 1);
	
	public BlockVerticalSlab() {
		
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.isSneaking() && (facing == EnumFacing.UP || facing == EnumFacing.DOWN) ? facing.getOpposite() : placer.getHorizontalFacing());
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = state.getValue(FACING);
		if(face == EnumFacing.NORTH)
			return NORTH;
		else if(face == EnumFacing.EAST)
			return EAST;
		else if(face == EnumFacing.SOUTH)
			return SOUTH;
		else if(face == EnumFacing.WEST)
			return WEST;
		else if(face == EnumFacing.UP)
			return new AxisAlignedBB(0, 0.5, 0, 1, 1, 1);
		else
			return new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return this.getBoundingBox(blockState, worldIn, pos);
	}

}
