package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.renderer.GlStateManager;
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

		//disable this if you want the entity to not always be full-bright
		CadibooClientUtil.enableMaxLighting();
		GlStateManager.enableLighting();

		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, z);

		GlStateManager.enableBlend();

		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

		final float alpha = entity.getAlpha();

		final ItemStack stack = entity.getItem();
		final World world = entity.world;

		//wait 1/16th of the time before starting to render it
		if (alpha > 0.0625F) {
			renderEntityStackWithAlpha(stack, world, alpha, 0.0625F);
		}

		//render it a 2nd time if time is half done to make it even more opaque
		if (alpha > 0.5F) {
			renderEntityStackWithAlpha(stack, world, alpha, 0.5F);
		}

		//render it a 3rd time if time is 3/4 done to make it even more opaque
		if (alpha > 0.75F) {
			renderEntityStackWithAlpha(stack, world, alpha, 0.75F);
		}

		GlStateManager.disableBlend();
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
