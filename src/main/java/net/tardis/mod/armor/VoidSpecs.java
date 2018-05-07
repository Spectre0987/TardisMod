package net.tardis.mod.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.tardis.mod.Tardis;

public class VoidSpecs extends ItemArmor {
	
	public VoidSpecs() {
		super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.HEAD);
		this.setCreativeTab(Tardis.tab);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Tardis.MODID + ":textures/armor/void_specs.png";
	}
	
}
