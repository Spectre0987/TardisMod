package net.tardis.mod.integrations.jei;

import org.lwjgl.opengl.GL11;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.GuiComponentRepair;

public class JEIRepairCategory implements IRecipeCategory {

	public static final String id = "jei.tardis.category.repair";
	private static DrawRecipeScreen gui = new DrawRecipeScreen();
	
	@Override
	public String getUid() {
		return id;
	}

	@Override
	public String getTitle() {
		return id;
	}

	@Override
	public String getModName() {
		return Tardis.MODID;
	}

	@Override
	public IDrawable getBackground() {
		return gui;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		recipeLayout.getItemStacks().init(0, true, 50, 50);
	}
	
	public static class DrawRecipeScreen implements IDrawable{

		@Override
		public int getWidth() {
			return 100;
		}

		@Override
		public int getHeight() {
			return 100;
		}

		@Override
		public void draw(Minecraft minecraft, int xOffset, int yOffset) {
			minecraft.getTextureManager().bindTexture(GuiComponentRepair.TEXTURE);
			int width = 100, height = 100;
			BufferBuilder bb = Tessellator.getInstance().getBuffer();
			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			bb.pos(xOffset, 0, yOffset).tex(0, 0).endVertex();
			bb.pos(xOffset + width, 0, yOffset).tex(1, 0).endVertex();
			bb.pos(xOffset + width, 0, yOffset + height).tex(1, 1).endVertex();
			bb.pos(0, 0, yOffset + height).tex(0, 1).endVertex();
			Tessellator.getInstance().draw();
		}
		
	}

}
