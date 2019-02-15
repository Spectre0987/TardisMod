package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tardis.mod.client.renderers.CadibooClientUtil;
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
		renderEntityStackWithAlpha(entity.getItem(), entity.world, entity.getAlpha(), 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.popMatrix();

	}

	private void renderEntityStackWithAlpha(final ItemStack stack, final World world, final float alpha, final float alphaInputStartCorrect) {

		if (stack.isEmpty()) {
			return;
		}

		final float alphaAdjusted = (float) CadibooClientUtil.map(alpha, alphaInputStartCorrect, 1F, 0F, 1F);

		// Full color is 0x2B_AA_FF in RGB format or 0xFF_2B_AA_FF in ARGB format
		final int color = CadibooClientUtil.colorf(
				0x2B / 255F * alphaAdjusted,
				0xAA / 255F * alphaAdjusted,
				0xFF / 255F * alphaAdjusted
		);

		CadibooClientUtil.renderStackWithColor(stack, world, color);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityItemMaterializer entity) {
		return null;
	}

}
