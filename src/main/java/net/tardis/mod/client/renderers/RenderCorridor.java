package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelToyotaCorridor;
import net.tardis.mod.common.entities.EntityCorridor;

public class RenderCorridor extends Render<EntityCorridor> {

	Minecraft mc;
	ModelToyotaCorridor model = new ModelToyotaCorridor();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/corridor_toyota.png");
	
	public RenderCorridor(RenderManager manager) {
		super(manager);
		mc = Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCorridor entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityCorridor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		if(!entity.isOpen())model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
		else model.renderOpen(0.0625F);
		GlStateManager.popMatrix();
	}

}
