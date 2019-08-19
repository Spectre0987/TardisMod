package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;

public class BlockSlab extends Block {

	public static final AxisAlignedBB TOP = new AxisAlignedBB(0, 0.5, 0, 1, 1, 1);
	public static final AxisAlignedBB BOTTOM = new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
	public static PropertyBool TOP_HALF = PropertyBool.create("top");
	
	public BlockSlab(Material materialIn) {
		super(materialIn);
		this.setCreativeTab(TardisTabs.BLOCKS);
	}
	
	@Override
	public boolean isTranslucent(IBlockState state) {
		// TODO Auto-generated method stub
		return super.isTranslucent(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(TOP_HALF, meta == 0 ? true : false);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TOP_HALF) ? 0 : 1;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, TOP_HALF);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(TOP_HALF, hitY > 0.5 ? true : false);
	}

}
