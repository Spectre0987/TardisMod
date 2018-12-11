package net.tardis.mod.client.renderers.decorations;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.decoration.ModelToyotaSpinnyThing;
import net.tardis.mod.common.tileentity.decoration.TileEntityToyotaSpin;

public class RenderToyotaSpin extends TileEntitySpecialRenderer<TileEntityToyotaSpin> {

	ModelToyotaSpinnyThing model = new ModelToyotaSpinnyThing();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/toyota/spinny_thingamajig.png");
	
	@Override
	public void render(TileEntityToyotaSpin te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		this.bindTexture(TEXTURE);
		model.renderAnimated(0.0625F, te.isInFlight());
		GlStateManager.popMatrix();
	}

	@Override
	protected void bindTexture(ResourceLocation location) {
		super.bindTexture(location);
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}

}
