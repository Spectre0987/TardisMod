package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonMonitor;
import net.tardis.mod.common.sounds.InteriorHum;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageSwitchHum;

import java.io.IOException;

public class GuiToggleHum extends GuiScreen {

	private BlockPos pos;
	private int dimID; // Useless right ?
	
	public static ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor.png");
	
	public GuiToggleHum(BlockPos pos, int dimID) {
		this.pos = pos;
		this.dimID = dimID;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		NetworkHandler.NETWORK.sendToServer(new MessageSwitchHum(button.id, pos.toLong()));
		super.actionPerformed(button);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(width / 2 - 242 / 2, height / 2 - 132 / 2, 0, 0, 242, 132);
		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
		int titleWidth = fr.getStringWidth("Hum Selection");
		fr.drawString("Hum Selection", width / 2 - titleWidth / 2, height / 2 - 50, 0xFFFFFF);
		for(GuiButton button : this.buttonList) {
			button.height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
			button.drawButton(Minecraft.getMinecraft(), mouseX, mouseY, partialTicks);
		}
	}

	@Override
	public void initGui() {
		this.buttonList.clear();
		int index = 0;
		for(InteriorHum hum : InteriorHum.hums) {
			this.addButton(new ButtonMonitor(index, width / 2 - 110, (height / 2 + 50) - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * index  + index * - 3, "> " + hum.getTranslatedName()));
			++index;
		}
	}

}
