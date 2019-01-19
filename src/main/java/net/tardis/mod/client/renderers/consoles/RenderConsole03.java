package net.tardis.mod.client.renderers.consoles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.consoles.ModelConsoleToyota;
import net.tardis.mod.client.models.consoles.ModelControlsToyota;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis03;

public class RenderConsole03 extends TileEntitySpecialRenderer<TileEntityTardis03> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/consoles/toyota.png");
	public static final ResourceLocation CONTROLS = new ResourceLocation(Tardis.MODID, "textures/consoles/controls_toyota.png");
	private static final double[] frames = {0, 0.02, 0.04, 0.06, 0.08, 0.1, 0.12, 0.14, 0.16, 0.18, 0.2, 0.22, 0.24, 0.26, 0.28, 0.3, 0.32, 0.34, 0.36, 0.38, 0.4, 0.42, 0.44, 0.46, 0.48, 0.5, 0.48, 0.46, 0.44, 0.42, 0.4, 0.38, 0.36, 0.34, 0.32, 0.3, 0.28, 0.26, 0.24, 0.22, 0.2, 0.18, 0.16, 0.14, 0.12, 0.1, 0.08, 0.06, 0.04, 0.02, 0};
	public ModelConsoleToyota model = new ModelConsoleToyota();
	public ModelControlsToyota controls = new ModelControlsToyota();

	@Override
	public void render(TileEntityTardis03 te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.53125, z + 0.5);
		GlStateManager.rotate(180, 0, 0, 1);
		this.bindTexture(new ResourceLocation(Tardis.MODID, "textures/consoles/toyota.png"));
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.pushMatrix();
		double prev = -frames[wrap(te.frame - 1, frames.length - 1)];
		GlStateManager.translate(0, prev - ((prev + frames[te.frame]) * partialTicks), 0);
		model.renderRotor(0.0625F);
		GlStateManager.popMatrix();
		this.bindTexture(CONTROLS);
		GlStateManager.translate(0, -1, 0);
		controls.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected void bindTexture(ResourceLocation location) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}

	public int wrap(int d0, int d1) {
		return d0 > d1 || d0 < 0 ? 0 : d0;
	}

}
