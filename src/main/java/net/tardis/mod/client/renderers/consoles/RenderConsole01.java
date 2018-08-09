package net.tardis.mod.client.renderers.consoles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.consoles.ModelConsole01;
import net.tardis.mod.client.models.consoles.ModelControls01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;

public class RenderConsole01 extends TileEntitySpecialRenderer<TileEntityTardis01> {

	Minecraft mc;
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/consoles/console01.png");
	public static final ResourceLocation TEXTURE_CONTROLS = new ResourceLocation(Tardis.MODID, "textures/consoles/controls01.png");
	public ModelControls01 controls = new ModelControls01();
	public ModelConsole01 console = new ModelConsole01();
	
	public RenderConsole01() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntityTardis01 te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180, 1, 0, 0);
		console.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.pushMatrix();
		if(te.isInFlight())GlStateManager.rotate((te.getWorld().getTotalWorldTime() % 180) * 2, 0, 1, 0);
		console.renderRotor(0.0625F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE_CONTROLS);
		GlStateManager.translate(0, -1, 0);
		controls.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}

}
