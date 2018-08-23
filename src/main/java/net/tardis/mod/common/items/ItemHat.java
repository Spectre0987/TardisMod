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
import net.tardis.mod.client.EnumClothes;
import net.tardis.mod.common.items.clothing.ItemSpaceSuit;

public class ItemHat extends ItemArmor {

	private EnumClothes clothType;

	public ItemHat(EnumClothes clothType) {
		super(ItemSpaceSuit.material, 0, EntityEquipmentSlot.HEAD);
		this.setCreativeTab(Tardis.tab);
		this.clothType = clothType;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return clothType.getTexture().toString();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,ModelBiped _default) {
		if (clothType.getModel() != null) {
			return clothType.getModel();
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

}
