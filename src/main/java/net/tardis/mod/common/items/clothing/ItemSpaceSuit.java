package net.tardis.mod.common.items.clothing;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.clothing.ModelSpaceChest;
import net.tardis.mod.client.models.clothing.ModelSpaceHelm;
import net.tardis.mod.client.models.clothing.ModelSpaceLegs;

public class ItemSpaceSuit extends ItemArmor {
	
	public static final ArmorMaterial material = EnumHelper.addArmorMaterial("space_suit", "spacesuit", 0,new int[] {0,0,0,0},0,SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,0F);
	
	public ItemSpaceSuit(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(material, renderIndexIn, equipmentSlotIn);

    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Tardis.MODID+":textures/clothing/space_suit.png";
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		switch(armorSlot) {
			case CHEST: return new ModelSpaceChest();
			case HEAD: return new ModelSpaceHelm();
			case LEGS: return new ModelSpaceLegs();
			default: return new ModelSpaceHelm();
		}
	}
	
}
