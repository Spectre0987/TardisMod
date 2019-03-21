package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;

public class GuiToggleHum extends GuiScreen {

	BlockPos pos;
	int dimID = 0;
	
	public static ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/monitor.png");
	
	public GuiToggleHum(BlockPos pos, int dimID) {
		this.pos = pos;
		this.dimID = dimID;
	}
	
	@Override
	protected void actionPerformed(GuiButton p_actionPerformed_1_) throws IOException {
		super.actionPerformed(p_actionPerformed_1_);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
		super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(width / 2 - 242 / 2, height / 2 - 132 / 2, 0, 0, 242, 132);
	}

	@Override
	public void initGui() {
		super.initGui();
	}

}
