package net.tardis.mod.common.blocks;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMegalos extends BlockBase {
	
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 14);
	
	public BlockMegalos(){}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TYPE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {TYPE});
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(TYPE, placer.getHeldItem(hand).getMetadata());
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(state.getBlock(), 1, state.getValue(TYPE));
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(int i=0; i<=14; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return state.getValue(TYPE) == 14 ? new AxisAlignedBB(0,0,0,1,0.5,1) : super.getBoundingBox(state, source, pos);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return blockState.getValue(TYPE) == 14 ? new AxisAlignedBB(0,0,0,1,0.5,1) : super.getCollisionBoundingBox(blockState, worldIn, pos);
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return state.getValue(TYPE) == 14 ? false : true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return state.getValue(TYPE) == 14 ? false : true;
	}

}
