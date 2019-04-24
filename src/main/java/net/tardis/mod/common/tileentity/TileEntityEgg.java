package net.tardis.mod.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEgg extends TileEntity{

	public static List<ItemStack> ITEMS = new ArrayList<>();
	
	public static void register(ItemStack stack) {
		ITEMS.add(stack);
	}
}
