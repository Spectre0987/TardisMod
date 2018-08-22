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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TTabs;

public class BlockPanel extends BlockBase {
	
	public static final PropertyInteger TYPE = PropertyInteger.create("type", 0, 2);
	public ItemBlock item = new ItemBlock(this);
	
	public BlockPanel() {
		this.setLightOpacity(0);
		item.setCreativeTab(TTabs.tabTardis);
	}
	
	public static void setType(IBlockState state, int i) {
		state.withProperty(TYPE, i);
	}
	
	public static int getType(IBlockState state) {
		return state.getValue(TYPE);
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
		return this.getDefaultState();
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (int i = 0; i < 3; ++i) {
			items.add(new ItemStack(new ItemBlock(this), 1, i));
		}
	}
	
}
