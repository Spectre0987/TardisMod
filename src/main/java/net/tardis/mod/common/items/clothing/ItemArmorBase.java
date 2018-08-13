package net.tardis.mod.common.items.clothing;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.TItems;

public class ItemArmorBase extends ItemArmor {

    public ItemArmorBase(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String name) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
        this.setCreativeTab(Tardis.tab);
        this.setUnlocalizedName("tardis." + name);
        this.setRegistryName(rl);
        TItems.items.add(this);
    }
}
