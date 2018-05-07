package net.tardis.mod.common.items.clothing;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.tardis.mod.Tardis;

public class ItemVoidSpecs extends ItemArmor {
	
	public ItemVoidSpecs() {
		super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.HEAD);
		this.setCreativeTab(Tardis.tab);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Tardis.MODID + ":textures/clothing/void_specs.png";
	}
	
}
