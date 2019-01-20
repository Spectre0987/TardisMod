package net.tardis.mod.common.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.items.ItemRemote;
import net.tardis.mod.common.items.TItems;

import java.util.ArrayList;
import java.util.List;

public class RecipeRemote implements IRecipe {

	private ResourceLocation rl;

	public RecipeRemote(String name) {
		rl = new ResourceLocation(Tardis.MODID, name);
	}

	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		rl = name;
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return rl;
	}

	@Override
	public Class<IRecipe> getRegistryType() {
		return null;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		List<Item> items = new ArrayList<>();
		boolean hasKey = false;
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			items.add(inv.getStackInSlot(i).getItem());
			if (inv.getStackInSlot(i).getItem() instanceof ItemRemote) {
				hasKey = true;
			}
		}
		return items.contains(TItems.stattenheim_remote) && hasKey;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack keyStack = ItemStack.EMPTY;
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack stack = inv.getStackInSlot(i);
			if (stack.getItem() instanceof ItemKey) {
				keyStack = stack;
			}
		}
		ItemStack remoteStack = new ItemStack(TItems.stattenheim_remote);
		BlockPos pos = ItemKey.getPos(keyStack);
		if (pos != null && !pos.equals(BlockPos.ORIGIN)) ItemRemote.setConsolePos(remoteStack, pos);
		return remoteStack;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(TItems.stattenheim_remote);
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height > 2;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> list = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			if (inv.getStackInSlot(i).getItem() != TItems.stattenheim_remote)
				list.set(i, inv.getStackInSlot(i).copy());
		}
		return list;
	}
}
