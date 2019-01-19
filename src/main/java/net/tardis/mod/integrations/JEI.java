package net.tardis.mod.integrations;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.recipes.RepairRecipes;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.integrations.jei.JEIRepairCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@JEIPlugin
public class JEI implements IModPlugin {

	Collection<HashMap<Item, Item>> recipes = new ArrayList<HashMap<Item, Item>>();

	public JEI() {
		recipes.add(RepairRecipes.recipes);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new JEIRepairCategory());
		IModPlugin.super.registerCategories(registry);
	}

	@Override
	public void register(IModRegistry registry) {
		registry.addIngredientInfo(new ItemStack(TItems.manual), ItemStack.class, TStrings.JEI.MANUAL);

		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.console));
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.console_01));
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.console_02));

		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.tardis_top));
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.tardis_top_01));
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.tardis_top_02));
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.tardis_top_03));
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.tardis_top_cc));
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.sonic_blaster));
		registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(TBlocks.tardis));

		registry.addRecipeCatalyst(new ItemStack(TBlocks.circuit_repair), JEIRepairCategory.id);

		registry.addRecipes(recipes, JEIRepairCategory.id);
		IModPlugin.super.register(registry);
	}

}
