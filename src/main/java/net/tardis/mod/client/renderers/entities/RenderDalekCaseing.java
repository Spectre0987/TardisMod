package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.models.entity.dalek.ModelDalek;
import net.tardis.mod.common.entities.EntityDalekCasing;

public class RenderDalekCaseing extends Render<EntityDalekCasing> {

	public static final ResourceLocation TEXTURE = ModelDalek.TEXTURE;
	public ModelDalek model = new ModelDalek();
	Minecraft mc;
	
	public RenderDalekCaseing(RenderManager manager) {
		super(manager);
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void doRender(EntityDalekCasing entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDalekCasing entity) {
		return TEXTURE;
	}

}
