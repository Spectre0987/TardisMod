package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.renderers.CadibooClientUtil;
import net.tardis.mod.common.entities.EntityItemMaterializer;

public class RenderItemMaterializer extends Render<EntityItemMaterializer> {

	public RenderItemMaterializer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityItemMaterializer entity, double x, double y, double z, float entityYaw, float partialTicks) {
		CadibooClientUtil.enableMaxLighting();
		GlStateManager.enableLighting();

		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, z);

		GlStateManager.enableBlend();

		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

		final int color = CadibooClientUtil.color(0, 0, 0xFF /*- (int) (entity.world.getTotalWorldTime() % 0xFF)*/);
		final ItemStack stack = entity.getItem();
		if (!stack.isEmpty()) {
			CadibooClientUtil.renderStackWithColor(stack, entity.world, color);
		}

		GlStateManager.disableBlend();
		GlStateManager.popMatrix();

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityItemMaterializer entity) {
		return null;
	}

}
