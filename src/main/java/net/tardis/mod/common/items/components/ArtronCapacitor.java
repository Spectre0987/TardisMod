package net.tardis.mod.common.items.components;

import net.minecraft.item.ItemStack;
import net.tardis.mod.info.CrashType;

public class ArtronCapacitor extends ItemComponent {
	
	public ArtronCapacitor() {
		
	}

	@Override
	public ItemStack damageItem(CrashType type, ItemStack stack) {
		stack.damageItem(1, null);
		return stack;
	}

}
