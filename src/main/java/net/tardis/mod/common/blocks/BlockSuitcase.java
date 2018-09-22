package net.tardis.mod.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tardis.mod.common.items.TItems;

public class BlockSuitcase extends BlockBase {

	private static final ArrayList<ItemStack[]> clothes = new ArrayList<>();
	private static final AxisAlignedBB BB = new AxisAlignedBB(0.0625, 0, 0.0625, 0.9375, 0.4375, 0.875);
	private Random rand = new Random();
	
	public BlockSuitcase() {
		clothes.add(new ItemStack[] {new ItemStack(TItems.space_chest), new ItemStack(TItems.space_helm), new ItemStack(TItems.space_legs)});
		clothes.add(new ItemStack[]{new ItemStack(TItems.fez), new ItemStack(TItems.bowtie)});
		clothes.add(new ItemStack[] {new ItemStack(TItems.first_cane), new ItemStack(TItems.fourth_hat)});
		clothes.add(new ItemStack[] {new ItemStack(TItems.void_specs), new ItemStack(TItems.fob_watch)});
		clothes.add(new ItemStack[] {new ItemStack(TItems.thirteen_coat)});
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ItemStack[] stacks = clothes.get(rand.nextInt(clothes.size()));
		for(ItemStack stack : stacks) {
			drops.add(stack);
		}
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return BB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
