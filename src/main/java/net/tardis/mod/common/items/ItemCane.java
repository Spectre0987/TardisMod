package net.tardis.mod.common.items;

import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ItemCane extends ItemBase {
	
	public static final UUID caneWeapon = UUID.randomUUID();

	public ItemCane(String name) {
		super(name);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);
		if(slot == EntityEquipmentSlot.MAINHAND) {
			map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(caneWeapon, "cane_damage", 2.0D, 0));
		}
		return map;
	}

}
