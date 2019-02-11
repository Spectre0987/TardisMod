package net.tardis.mod.client.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.items.TItems;

public class TardisTabs {

	public static CreativeTabs ITEMS = new CreativeTabs("tardis_items") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(TItems.fourth_hat);
		}
	};

	public static CreativeTabs BLOCKS = new CreativeTabs("tardis_blocks") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(TBlocks.tardis_top);
		}
	};

	public static CreativeTabs GALLIFREY_BLOCKS = new CreativeTabs("tardis_galliblocks") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(TBlocks.gallifreyan_grass);
		}
	};

}
