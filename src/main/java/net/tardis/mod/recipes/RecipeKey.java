package net.tardis.mod.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.items.TItems;

public class RecipeKey implements IRecipe {
	
	private ResourceLocation loc;
	
	public RecipeKey(String s) {
		this.setRegistryName(new ResourceLocation(Tardis.MODID, s));
	}
	
	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		loc = name;
		return this;
	}
	
	@Override
	public ResourceLocation getRegistryName() {
		return loc;
	}
	
	@Override
	public Class<IRecipe> getRegistryType() {
		return null;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		List<Item> list = getList(inv);
		return list.contains(Items.IRON_INGOT) && list.contains(TItems.key);
	}
	
	public List<Item> getList(InventoryCrafting inv) {
		List<Item> list = new ArrayList<Item>();
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			if (!inv.getStackInSlot(i).isEmpty()) list.add(inv.getStackInSlot(i).getItem());
		}
		return list;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack stack = this.getRecipeOutput();
		ItemStack base = getItem(inv, TItems.key);
		ItemKey.setPos(stack, ItemKey.getPos(base));
		return stack;
	}
	
	@Override
	public boolean canFit(int width, int height) {
		return width <= 3 && height <= 3;
	}
	
	public static ItemStack getItem(InventoryCrafting inv, Item item) {
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			if (inv.getStackInSlot(i).getItem() == item) return inv.getStackInSlot(i);
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(TItems.key);
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> list = NonNullList.create().withSize(9, ItemStack.EMPTY);
		list.set(0, getItem(inv, TItems.key).copy());
		return list;
	}
	
}
