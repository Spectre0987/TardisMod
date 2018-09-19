package net.tardis.mod.common.recipes;

import java.util.HashMap;

import net.minecraft.item.Item;

public class RepairRecipes {
	
	public static HashMap<Item, Item> recipes = new HashMap<Item, Item>();
	
	public static void registerRecipe(Item componenet, Item repair) {
		recipes.put(componenet, repair);
	}
	
	public static Item getRepairItem(Item item) {
		return recipes.get(item);
	}
}
