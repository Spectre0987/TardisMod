package net.tardis.mod.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.tardis.mod.Tardis;
import net.tardis.mod.models.ModelFez;

public class Fez extends ItemArmor {

	public Fez() {
		super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.HEAD);
		this.setCreativeTab(Tardis.tab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Tardis.MODID+":textures/armor/fez.png";
	}

	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,ModelBiped _default) {
		return new ModelFez(_default);
	}

}
