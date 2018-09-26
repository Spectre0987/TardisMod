package net.tardis.mod.util.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {

    public static NBTTagCompound nbtToStack(NBTTagCompound tag, ItemStack itemstack) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        if (!itemstack.isEmpty()) {
            itemstack.writeToNBT(nbttagcompound);
        }

        if (!nbttagcompound.isEmpty()) {
            tag.setTag("saved_items", nbttagcompound);
        }

        return tag;
    }

    public static ItemStack loadStack(NBTTagCompound tag) {
        return new ItemStack(tag.getCompoundTag("saved_items"));
    }
    
}
