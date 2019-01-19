package net.tardis.mod.common.items.components;

import net.tardis.mod.common.items.ItemBase;

/**
 * All TARDIS Components must extend this class
 *
 * @author Spectre
 */
public class ItemComponent extends ItemBase {

	public ItemComponent() {
		this.setMaxDamage(100);
		this.setMaxStackSize(1);
	}

}
