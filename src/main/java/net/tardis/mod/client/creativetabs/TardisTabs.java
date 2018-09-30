package net.tardis.mod.client.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.tardis.mod.common.blocks.TBlocks;

public class TardisTabs {

	public static final ItemStack ICON = new ItemStack(TBlocks.tardis_top);

    public static CreativeTabs MAIN = new CreativeTabs("tardis") {
        @Override
        public ItemStack createIcon() {
            return ICON;
        }
    };

	
}
