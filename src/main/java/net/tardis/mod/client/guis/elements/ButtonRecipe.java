package net.tardis.mod.client.guis.elements;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
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
	
	public IBlockState getBlock() {
		if(this.stackToRender.getItem() instanceof ItemBlock) {
			return ((ItemBlock)this.stackToRender.getItem()).getBlock().getDefaultState();
		}
		return Blocks.AIR.getDefaultState();
	}
}
