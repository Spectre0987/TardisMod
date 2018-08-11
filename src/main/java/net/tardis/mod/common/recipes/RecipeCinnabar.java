package net.tardis.mod.common.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.tardis.mod.common.items.TItems;

public class RecipeCinnabar implements IRecipe {

	private ResourceLocation loc;
	
	public RecipeCinnabar(String name) {
		loc = new ResourceLocation(name);
	}

	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		this.loc = name;
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
		boolean hasPick = false;
		boolean hasOre = false;
		for(ItemStack cin : OreDictionary.getOres("oreCinnabar")) {
			for(int i = 0; i < inv.getSizeInventory(); ++i) {
				if(inv.getStackInSlot(i).getItem() instanceof ItemPickaxe) hasPick = true;
				if(inv.getStackInSlot(i).isItemEqual(cin)) hasOre = true;
			}
		}
		return hasPick && hasOre;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return new ItemStack(TItems.crushedCinnabar);
	}

	@Override
	public boolean canFit(int width, int height) {
		return width <= 3 && height <= 3;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(TItems.crushedCinnabar);
	}
	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> list = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
		for(int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack stack = inv.getStackInSlot(i);
			if(stack.getItem() instanceof ItemPickaxe) {
				stack.setItemDamage(stack.getItemDamage() + 1);
				list.set(i, stack.copy());
				return list;
			}
		}
		return list;
	}
}
