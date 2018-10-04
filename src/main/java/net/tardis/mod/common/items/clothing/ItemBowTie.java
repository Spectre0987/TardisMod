package net.tardis.mod.common.items.clothing;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;

public class ItemBowTie extends ItemArmor {
	
	public ItemBowTie() {
		super(ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST);
        setCreativeTab(Tardis.tab);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return Tardis.MODID + ":textures/clothing/bowtie.png";
	}
	
}
