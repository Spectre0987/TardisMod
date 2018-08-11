package net.tardis.mod.client.renderers.consoles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.consoles.ModelConsole01;
import net.tardis.mod.client.models.consoles.ModelControls01;
import net.tardis.mod.client.models.decoration.ModelHellbentHexagon;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class RenderConsole02 extends TileEntitySpecialRenderer<TileEntityTardis> {

	Minecraft mc;
	public ModelConsole01 model = new ModelConsole01();
	public ModelControls01 model_controls = new ModelControls01();

	public ModelHellbentHexagon hex = new ModelHellbentHexagon();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/consoles/console02.png");
	public static final ResourceLocation TEXTURE_CONTROLS = new ResourceLocation(Tardis.MODID, "textures/consoles/controls01.png");
	public static final ResourceLocation TEXTURE_HEX = new ResourceLocation(Tardis.MODID, "textures/blocks/hellbent/hex.png");
	
	public RenderConsole02() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntityTardis te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180, 1, 0, 0);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.pushMatrix();
		if(te.isInFlight())GlStateManager.rotate((te.getWorld().getTotalWorldTime() % 180) * 2, 0, 1, 0);
		model.renderRotor(0.0625F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE_CONTROLS);
		GlStateManager.translate(0, -1, 0);
		model_controls.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE_HEX);
		GlStateManager.translate(0, -2, 0);
		hex.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}
}
