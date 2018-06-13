package net.tardis.mod.common.items.components;

import net.minecraft.item.ItemStack;
import net.tardis.mod.common.info.CrashType;

public class DematerializationCircut extends ItemComponent {
	
	public DematerializationCircut() {
	}
	
	@Override
	public ItemStack damageItem(CrashType type, ItemStack stack) {
		return stack;
	}
	
}
