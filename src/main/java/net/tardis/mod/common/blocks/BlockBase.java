package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.blocks.interfaces.IARSBlock;
import net.tardis.mod.common.tileentity.TileEntityEgg;

public class BlockBase extends Block implements IARSBlock {

	public BlockBase() {
		super(Material.IRON);
		this.setHardness(5F);
		setCreativeTab(TardisTabs.BLOCKS);
		//TileEntityEgg.register(new ItemStack(this, 64));
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(this));
	}

	@Override
	public Item getItemARS() {
		return Item.getByNameOrId(getRegistryName().getNamespace() + ":" + getRegistryName().getPath());
	}
}
