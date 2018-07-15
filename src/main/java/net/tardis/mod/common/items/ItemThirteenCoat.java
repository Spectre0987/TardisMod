package net.tardis.mod.common.items;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.clothing.ModelThirteenCoat;
import net.tardis.mod.common.items.clothing.ItemSpaceSuit;

public class ItemThirteenCoat extends ItemArmor {

	public ItemThirteenCoat() {
		super(ItemSpaceSuit.material, 0, EntityEquipmentSlot.CHEST);
		this.setCreativeTab(Tardis.tab);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return ModelThirteenCoat.TEXTURE.toString();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,ModelBiped _default) {
		return new ModelThirteenCoat();
	}

}
