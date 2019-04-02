package net.tardis.mod.client.guis.elements;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class ButtonText extends GuiButton {

	FontRenderer fr;
	IAction func;
	
	public ButtonText(int buttonId, int x, int y, FontRenderer fr, String buttonText) {
		super(buttonId, x - fr.getStringWidth(buttonText) / 2, y, fr.getStringWidth(buttonText), fr.FONT_HEIGHT, buttonText);
		this.fr = fr;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		this.drawString(fr, displayString, this.x, this.y, Color.WHITE.getRGB());
	}
	
	public void addAction(IAction call) {
		this.func = call;
	}
	
	public IAction getAction() {
		return this.func;
	}
	
	public static interface IAction{
		
		void call();
	}
}
