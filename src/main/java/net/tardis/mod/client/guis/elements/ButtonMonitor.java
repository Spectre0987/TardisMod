package net.tardis.mod.client.guis.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.tardis.mod.client.guis.elements.ButtonText.IAction;

public class ButtonMonitor extends GuiButton{

	private int color = 0xFFFFFF;
	IAction action;
	
	public ButtonMonitor(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, Minecraft.getMinecraft().fontRenderer.getStringWidth(buttonText),
				Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT,buttonText);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		mc.fontRenderer.drawString(this.displayString, this.x, this.y, color);
	}
	
	public void addAction(IAction action) {
		this.action = action;
	}
	
	public void doAction() {
		if(action != null)
			action.call();
	}

}
