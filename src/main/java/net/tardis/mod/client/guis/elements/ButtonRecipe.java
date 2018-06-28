package net.tardis.mod.client.guis.elements;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class ButtonRecipe extends GuiButton {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/item_button.png");
	ItemStack stackToRender;
	TransformType type = TransformType.GUI;
	int time;
	
	public ButtonRecipe(int buttonId, int x, int y, ItemStack stack, int ticks) {
		super(buttonId, x, y, 18, 18, "Item");
		this.stackToRender = stack.copy();
		this.time = ticks;
	}
	
	public ButtonRecipe(int buttonId, int x, int y, ItemStack stack, int ticks, TransformType type) {
		this(buttonId, x, y, stack, ticks);
		this.type = type;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		mc.getRenderItem().renderItemIntoGUI(stackToRender, this.x + 1, this.y + 1);
		if(time < 0) {
			GlStateManager.pushMatrix();
			GlStateManager.translate((this.x / 2) + 10, (this.y / 2), 0);
			GlStateManager.scale(0.5, 0.5, 0.5);
			this.drawCenteredString(mc.fontRenderer, time / 20 + "", x, y, Color.WHITE.getRGB());
			GlStateManager.popMatrix();
		}
		
	}
	
	@Override
	public boolean isMouseOver() {
		return super.isMouseOver();
	}
	
}
