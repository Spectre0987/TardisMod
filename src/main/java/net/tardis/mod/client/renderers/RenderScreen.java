package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.console.contols.ModelScreen;
import net.tardis.mod.common.entities.controls.ControlScreen;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

import java.awt.*;

public class RenderScreen extends Render {
	
	public ModelScreen model = new ModelScreen();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/screen.png");
	public static final int COLOR = Color.WHITE.getRGB();
	Minecraft mc;
	FontRenderer fr;
	int line = 0;

    public RenderScreen(RenderManager manager) {
        super(manager);
		mc = Minecraft.getMinecraft();
		fr = mc.fontRenderer;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y - 2.5, z);
		GlStateManager.scale(1.5, 1.5, 1.5);
		GlStateManager.rotate(90, 0, 1, 0);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.scale(1.5, 1.5, 1.5);
		model.render(entity, 0.0625F);
		TileEntityTardis tardis = ((TileEntityTardis) mc.world.getTileEntity(((ControlScreen) entity).getConsolePos()));
		if (tardis != null) {
			GlStateManager.disableLighting();
			mc.entityRenderer.disableLightmap();
			line = 0;
			GlStateManager.translate(0.375, 1.4, 0.06);
			GlStateManager.rotate(180, 0, 0, 1);
			GlStateManager.scale(0.005, 0.005, 0.005);
			drawString("TARDIS Location: " + Helper.formatBlockPos(tardis.getLocation()));
			if (tardis.currentDimName != null && !tardis.currentDimName.equals(""))
				drawString("TARDIS Dimension: [" + tardis.currentDimName.replace('_', ' ') + "]");
			else
				drawString("TARDIS Dimension: [" + tardis.dimension + "]");
			drawString("Nav-Com Target: " + Helper.formatBlockPos(tardis.getDestination()));
			if (tardis.targetDimName != null && !tardis.targetDimName.equals(""))
				drawString("Dimension Target: [" + tardis.targetDimName.replace('_', ' ') + "]");
			else
				drawString("Dimension Target: [" + tardis.getTargetDim() + "]");
			String fuelS = tardis.fuel * 100 + "";
			drawString("Artron Banks: " + fuelS.substring(0, fuelS.indexOf(".")) + "%");
			if (tardis.totalTimeToTravel > 0 && tardis.getTicks() > 0) drawString("Time Left: " + tardis.getTicks() / 20);
			mc.entityRenderer.enableLightmap();
			GlStateManager.enableLighting();
		}
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
