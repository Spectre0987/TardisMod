package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class GuiTardis extends GuiScreen {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/tardis_coords.png");
	Minecraft mc;
	TileEntityTardis tardis;
	int pWidth = 248;
	int pHeight = 166;
	int line = 0;
	
	public GuiTardis(TileEntityTardis t) {
		mc = Minecraft.getMinecraft();
		tardis = t;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		int cX = (width / 2) - (pWidth / 2);
		int cY = (height / 2) - (pHeight / 2);
		this.drawTexturedModalRect(cX, cY, 0, 0, pWidth, pHeight);
		line = 0;
		if (tardis != null) {
			this.drawText("TARDIS' location: " + Helper.formatBlockPos(tardis.getLocation()));
			this.drawText("Navcom Coords: " + Helper.formatBlockPos(tardis.getDestination()));
			this.drawText("TARDIS' Target Dimension: " + tardis.getTargetDim());
			this.drawText("TARDIS' Current Dimension: " + tardis.dimension);
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public void drawText(String s) {
		FontRenderer fr = mc.fontRenderer;
		this.drawCenteredString(fr, s, (width / 2), ((height / 2) - (fr.FONT_HEIGHT * 4 / 2)) + (line * fr.FONT_HEIGHT), 000);
		++line;
	}
	
}
