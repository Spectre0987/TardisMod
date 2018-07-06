package net.tardis.mod.client.guis.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class ButtonRecipe extends GuiButton {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/item_button.png");
	ItemStack stackToRender = ItemStack.EMPTY;
	TransformType type = TransformType.GUI;
	
	public ButtonRecipe(int buttonId, int x, int y, ItemStack stack) {
		super(buttonId, x, y, 18, 18, "Item");
		this.stackToRender = stack.copy();
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		mc.getRenderItem().renderItemIntoGUI(stackToRender, this.x + 1, this.y + 1);
	}
}
