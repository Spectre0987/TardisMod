package net.tardis.mod.client.renderers.entities.vehicles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.entities.EntityTardis;

public class RenderTardisEntity extends Render<EntityTardis>{

	public RenderTardisEntity(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTardis entity) {
		return TextureMap.LOCATION_MISSING_TEXTURE;
	}

	@Override
	public void doRender(EntityTardis entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		EnumExterior exterior = entity.getExteriorEnum();
		Minecraft.getMinecraft().getTextureManager().bindTexture(exterior.tex);
		exterior.model.renderClosed(0.0625F);
		GlStateManager.popMatrix();
	}

}
