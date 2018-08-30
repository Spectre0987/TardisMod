package net.tardis.mod.client.renderers.entities.vehicles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.vehicles.ModelBessie;
import net.tardis.mod.common.entities.vehicles.EntityBessie;

public class RenderBessie extends Render<EntityBessie>{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/vehicles/bessie.png");
	ModelBessie model = new ModelBessie();
	
	public RenderBessie(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBessie entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityBessie entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(entity.prevRotationYaw - ((entity.prevRotationYaw - entity.rotationYaw) * partialTicks), 0, 1, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
