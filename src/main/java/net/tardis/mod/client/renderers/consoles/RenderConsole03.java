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
	public ModelConsoleToyota model = new ModelConsoleToyota();
	public ModelControlsToyota controls = new ModelControlsToyota();
	
	@Override
	public void render(TileEntityTardis03 te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1.53125, z + 0.5);
		GlStateManager.rotate(180, 0, 0, 1);
		this.bindTexture(new ResourceLocation(Tardis.MODID, "textures/consoles/toyota.png"));
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		this.bindTexture(CONTROLS);
		GlStateManager.translate(0, -1, 0);
		controls.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected void bindTexture(ResourceLocation location) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}

}
