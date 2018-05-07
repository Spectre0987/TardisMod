package net.tardis.mod.client.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tardis.mod.blocks.TBlocks;

public class TardisTab extends CreativeTabs {
	
	public TardisTab() {
		super("tardis");
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(TBlocks.tardis);
	}
	
}
