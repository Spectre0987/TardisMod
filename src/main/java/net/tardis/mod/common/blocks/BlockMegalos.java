package net.tardis.mod.common.blocks;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.blocks.interfaces.INeedItem;

public class BlockMegalos extends BlockBase implements INeedItem{
	
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 15);
	public ItemBlock item = new ItemBlock(this);
	
	public BlockMegalos() {
		this.setLightOpacity(0);
        this.setCreativeTab(TardisTabs.MAIN);
        item.setCreativeTab(TardisTabs.MAIN);
		item.setHasSubtypes(true);
	}
	
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
        return new BlockStateContainer(this, TYPE);
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		int type = placer.getHeldItem(hand).getMetadata();
		if (type == 14) return facing == EnumFacing.DOWN ? this.getDefaultState().withProperty(TYPE, 15) : this.getDefaultState().withProperty(TYPE, 14);
		return this.getDefaultState().withProperty(TYPE, type);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(state.getBlock(), 1, state.getValue(TYPE));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (int i = 0; i <= 14; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = state.getValue(TYPE);
		if (meta == 14)
			return new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
		else if (meta == 15) return new AxisAlignedBB(0, 0.5, 0, 1, 1, 1);
		return super.getBoundingBox(state, source, pos);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return blockState.getValue(TYPE) == 14 ? new AxisAlignedBB(0, 0, 0, 1, 0.5, 1) : super.getCollisionBoundingBox(blockState, worldIn, pos);
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
        return state.getValue(TYPE) < 14;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
        return state.getValue(TYPE) < 14;
	}
	
	@Override
	public boolean causesSuffocation(IBlockState state) {
		int meta = state.getValue(TYPE);
        return meta != 14 && meta != 15;
	}

	@Override
	public ItemBlock getItem() {
		return item;
	}
	
}
