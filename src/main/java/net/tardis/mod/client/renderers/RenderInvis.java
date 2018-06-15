package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.items.ItemManual;

public class RenderInvis extends Render {
	
	public RenderInvis() {
		super(Minecraft.getMinecraft().getRenderManager());
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		Entity look = Minecraft.getMinecraft().objectMouseOver.entityHit;
		if (look != null && look == entity && Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemManual	) {
			GlStateManager.pushMatrix();
			renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, y, z, 45);
			GlStateManager.popMatrix();
		}
	}
	
	@Override
	public void doRenderShadowAndFire(Entity entityIn, double x, double y, double z, float yaw, float partialTicks) {}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	
}
