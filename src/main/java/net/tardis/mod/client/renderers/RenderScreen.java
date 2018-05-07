package net.tardis.mod.client.renderers;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.controls.ControlScreen;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.client.models.console.contols.ModelScreen;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class RenderScreen extends Render {
	
	public ModelScreen model = new ModelScreen();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/screen.png");
	public static final int COLOR = Color.WHITE.getRGB();
	Minecraft mc;
	FontRenderer fr;
	int line = 0;
	TileEntityTardis tardis;
	
	public RenderScreen() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
		fr = mc.fontRenderer;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y - 1.5, z);
		GlStateManager.rotate(90, 0, 1, 0);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.scale(1.5, 1.5, 1.5);
		model.render(entity, 0.0625F);
		if (tardis != null) {
			line = 0;
			GlStateManager.translate(0.375, 1.4, 0);
			GlStateManager.rotate(180, 0, 0, 1);
			GlStateManager.scale(0.005, 0.005, 0.005);
			drawString("TARDIS Location: " + Helper.formatBlockPos(tardis.getLocation()));
			drawString("TARDIS Dimension: [" + tardis.dimension + "]");
			drawString("Nav-Com Target: " + Helper.formatBlockPos(tardis.getDestination()));
			drawString("Dimension Target: [" + tardis.getTargetDim() + "]");
			String fuelS = tardis.fuel * 100 + "";
			drawString("Artron Banks: " + fuelS.substring(0, fuelS.indexOf(".")) + "%");
		}
		tardis = ((TileEntityTardis) mc.world.getTileEntity(((ControlScreen) entity).getConsolePos()));
		GlStateManager.popMatrix();
	}
	
	public void drawString(String s) {
		fr.drawString(s, 0, line * fr.FONT_HEIGHT, COLOR);
		++line;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
	
}
