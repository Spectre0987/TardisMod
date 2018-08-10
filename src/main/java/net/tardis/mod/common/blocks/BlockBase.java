package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tardis.mod.Tardis;

public class BlockBase extends Block {
	
	public ItemBlock item = new ItemBlock(this);
	
	public BlockBase() {
		super(Material.IRON);
		this.setHardness(1F);
		this.setResistance(99999F);
		this.setCreativeTab(Tardis.tab);
		this.item.setCreativeTab(Tardis.tab);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(this));
	}
}
