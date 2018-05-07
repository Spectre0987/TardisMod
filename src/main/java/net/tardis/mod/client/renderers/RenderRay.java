package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelRay;

public class RenderRay extends Render {
	
	Minecraft mc;
	public ModelRay model = new ModelRay();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/ray.png");
	
	public RenderRay() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.1, z);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		GlStateManager.rotate(-entity.rotationPitch, 1, 0, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.disableFog();
		GlStateManager.disableLighting();
		model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.enableFog();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
	
}
