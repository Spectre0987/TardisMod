package net.tardis.mod.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.tardis.mod.Tardis;

public class BowTie extends ItemArmor {
	
	public BowTie() {
		super(ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST);
		this.setCreativeTab(Tardis.tab);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Tardis.MODID + ":textures/armor/bowtie.png";
	}
	
}
