package net.tardis.mod.common.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class ItemBase extends Item {
	
	public ItemBase(String name) {
		ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
		this.setCreativeTab(Tardis.tab);
		this.setUnlocalizedName("tardis." + name);
		this.setRegistryName(rl);
		TItems.items.add(this);
	}

}
