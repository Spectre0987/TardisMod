package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.entities.EntityItemMaterializer;

public class RenderItemMaterializer extends Render<EntityItemMaterializer> {

	public RenderItemMaterializer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityItemMaterializer entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.5, z);
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();

		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.DST_COLOR);
		//renderEntityStackWithAlpha(entity.getItem(), entity.world, entity.getAlpha(), 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.popMatrix();

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityItemMaterializer entity) {
		return null;
	}

}
