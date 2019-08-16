package net.tardis.mod.common.items;

import java.util.UUID;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.tardis.mod.client.renderers.TEISRVictim;

public class ItemPlayerVictim extends Item {
	
	public ItemPlayerVictim() {
		this.setMaxStackSize(1);
	}
	
	public static UUID getUUID(ItemStack stack) {
		if(!stack.hasTagCompound() || stack.getTagCompound().hasKey("player_id"))
			return null;
		return stack.getTagCompound().getUniqueId("player_id");
	}
	
	public static void setUUID(UUID id, ItemStack stack) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setUniqueId("player_id", id);
	}


}
