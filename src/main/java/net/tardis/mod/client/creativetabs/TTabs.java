package net.tardis.mod.client.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tardis.mod.common.blocks.TBlocks;

public class TTabs {

	public static CreativeTabs tabTardis = new CreativeTabs("tardis") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(TBlocks.tardis_top);
		}
	};

}
