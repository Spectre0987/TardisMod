package net.tardis.mod.common.recipes;

import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class TemporalRecipe {
	
	public static Map<Integer, TemporalRecipe> recipes = new HashMap<Integer, TemporalRecipe>();
	
	ItemStack result;
	int timeToMake;
	
	public static int id = 0;
	
	public TemporalRecipe(ItemStack result, int timeToMake) {
		this.result = result;
		this.timeToMake = timeToMake;
	}
	
	public ItemStack getResult() {
		return this.result;
	}
	
	public int getTime() {
		return this.timeToMake;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return arg0 instanceof TemporalRecipe && result.isItemEqual(((TemporalRecipe) arg0).result) && timeToMake == ((TemporalRecipe) arg0).timeToMake;
	}
	
	@Override
	public String toString() {
		return "Result: " + result + ", Time to create: " + timeToMake;
	}
	
	public static void register(TemporalRecipe rec) {
		recipes.put(++id, rec);
	}
	
	public static Map<Integer, TemporalRecipe> getRegisteredRecipes() {
		return recipes;
	}
	
}
