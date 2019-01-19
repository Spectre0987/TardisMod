package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class GuiARS extends GuiScreen {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/ars.png");
	public int gui_width = 248, gui_height = 166;
	Minecraft mc;

	public GuiARS() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE);
		ScaledResolution res = new ScaledResolution(mc);
		this.drawTexturedModalRect(res.getScaledWidth() / 2 - gui_width / 2, res.getScaledHeight() / 2 - gui_height / 2, 0, 0, gui_width, gui_height);

		GlStateManager.popMatrix();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		ScaledResolution res = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRenderer;
		String hall = "Hallway";
		this.addButton(new GuiButton(0, (res.getScaledWidth() / 2 - gui_width / 2) - fr.getStringWidth(hall), (res.getScaledHeight() / 2 - gui_height / 2) - fr.FONT_HEIGHT, fr.getStringWidth(hall) * 2, fr.FONT_HEIGHT * 2, hall));
	}
}
