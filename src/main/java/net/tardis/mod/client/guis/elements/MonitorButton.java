package net.tardis.mod.client.guis.elements;

import java.util.List;

import com.google.common.collect.Lists;

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
	private FontRenderer fontRenderer;
    private final List<String> listLines = Lists.<String>newArrayList();
	
	public MonitorButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, 75, 32, buttonText);
		this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
		this.listLines.addAll(fontRenderer.listFormattedStringToWidth(buttonText, BUTTON_WIDTH - 5));
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if(visible) {
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
	        int i = 0;
	        for (String s : this.listLines) {
	            this.drawCenteredString(this.fontRenderer, s, this.x + this.width / 2, this.y  + this.height / 4 + i, colour);
	            i += this.fontRenderer.FONT_HEIGHT;
	        }
			GlStateManager.color(1, 1, 1);
			GlStateManager.popMatrix();
		}
	}
	
}
