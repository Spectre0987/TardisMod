package net.tardis.mod.client.renderers.consoles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.consoles.ModelConsole05;
import net.tardis.mod.client.models.consoles.ModelControls05;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis05;

public class RenderConsole05 extends TileEntitySpecialRenderer<TileEntityTardis05> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/consoles/console05.png");
	public static final ResourceLocation CONTROL_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/consoles/controls05.png");
	public static ModelConsole05 model = new ModelConsole05();
	public static ModelControls05 controlModel = new ModelControls05();
	
	@Override
	public void render(TileEntityTardis05 te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
		GlStateManager.rotate(180, 0, 0, 1);
		this.bindTexture(TEXTURE);
		model.render(null, 0, 0, te.getTimeLeft(), 0, 0, 0.0625F);
		this.bindTexture(CONTROL_TEXTURE);
		GlStateManager.translate(0, -1, 0);
		controlModel.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected void bindTexture(ResourceLocation location) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}

}
