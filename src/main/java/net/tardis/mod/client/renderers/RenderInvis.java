package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderInvis extends Render {
	
	public RenderInvis() {
		super(Minecraft.getMinecraft().getRenderManager());
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {}
	
	@Override
	public void doRenderShadowAndFire(Entity entityIn, double x, double y, double z, float yaw, float partialTicks) {}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	
}
