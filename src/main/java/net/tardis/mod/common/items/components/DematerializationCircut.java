package net.tardis.mod.common.items.components;

import net.minecraft.item.ItemStack;
import net.tardis.mod.client.renderers.RendererItemDemat;
import net.tardis.mod.info.CrashType;

public class DematerializationCircut extends ItemComponent {
	
	public DematerializationCircut() {
		this.setTileEntityItemStackRenderer(new RendererItemDemat());
	}
	
	@Override
	public ItemStack damageItem(CrashType type, ItemStack stack) {
		return stack;
	}
	
}
