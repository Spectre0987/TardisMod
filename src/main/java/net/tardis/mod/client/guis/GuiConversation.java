package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonText;

import java.io.IOException;

public class GuiConversation extends GuiScreen {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/conv.png");
	int entityId;
	Minecraft mc;

	int gw = 254;
	int gh = 166;

	public GuiConversation() {
	}

	public GuiConversation(int id) {
		mc = Minecraft.getMinecraft();
		entityId = id;
	}

	@Override
	public void initGui() {
		this.buttonList.clear();
		int centerX = width / 2 - gw / 2;
		int centerY = height / 2 - gh / 2;
		this.addButton(new ButtonText(0, width / 2, height / 2, mc.fontRenderer, "Hello"));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(width / 2 - gw / 2, height / 2 - gh / 2, 0, 0, gw, gh);
		for (GuiButton b : this.buttonList) {
			b.drawButton(mc, mouseX, mouseY, partialTicks);
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
