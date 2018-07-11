package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelRay;
import net.tardis.mod.common.entities.EntityRayCyberman;

public class RenderCyberRay extends Render<EntityRayCyberman> {

	Minecraft mc;
	public ModelRay model = new ModelRay();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/ray.png");
	
	public RenderCyberRay() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRayCyberman entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityRayCyberman entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		GlStateManager.rotate(-entity.rotationPitch, 0, 0, 1);
		GlStateManager.color(1F, 0F, -1F);
		model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
