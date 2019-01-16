package net.tardis.mod.client.guis.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class MonitorButton extends GuiButton {
	
	ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor_ui.png");
	int BUTTON_WIDTH = 75;
	int BUTTON_HEIGHT = 32;
	int colour = 0x00938F;
	float scale;
	
	public MonitorButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, 75, 32, buttonText);
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(visible) {
			FontRenderer fontRenderer = mc.fontRenderer;
			if(fontRenderer.getStringWidth(this.displayString) > 70) {
				scale = (float) 70 / fontRenderer.getStringWidth(this.displayString);
			} else {
				scale = 1;
			}
			mc.getTextureManager().bindTexture(TEXTURE);
			if(mouseX >= this.x && mouseX <= this.x + BUTTON_WIDTH && mouseY >= this.y && mouseY <= this.y + BUTTON_HEIGHT) {
				if(enabled) {
					hovered = true;
					colour = 0xCE8F23;					
				}
			} else {
				hovered = false;
				colour = 0x00938F;
			}
			
			if(hovered){
				this.drawTexturedModalRect(this.x, this.y, 108, 209, BUTTON_WIDTH, BUTTON_HEIGHT);
			} else {
				this.drawTexturedModalRect(this.x, this.y, 7, 209, BUTTON_WIDTH, BUTTON_HEIGHT);
			}
			GlStateManager.pushMatrix();
			GlStateManager.scale(scale, scale, 1);
			this.drawCenteredString(fontRenderer, this.displayString, (int) ((1 / scale) * (this.x + 37)), (int) ((1 / scale) * (this.y + 12)), colour);
			GlStateManager.color(1, 1, 1);
			GlStateManager.scale(1, 1, 1);
			GlStateManager.popMatrix();
		}
	}
	
}
