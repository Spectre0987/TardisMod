package net.tardis.mod.common.items;

import net.minecraft.item.Item;
import net.tardis.mod.client.renderers.TEISRVictem;

public class ItemPlayerVictim extends Item {
	
	public ItemPlayerVictim() {
		this.setMaxStackSize(1);
		this.setTileEntityItemStackRenderer(new TEISRVictem());
	}

}
