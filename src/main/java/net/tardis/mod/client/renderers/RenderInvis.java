package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.renderers.controls.RenderControl;
import net.tardis.mod.common.items.TItems;

public class RenderInvis extends Render<Entity> {
	
	public RenderInvis(RenderManager manager) {
		super(manager);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.player.getHeldItemMainhand().getItem() == TItems.manual) {
			Entity look = mc.objectMouseOver.entityHit;
			if(look != null && look == entity) {
				GlStateManager.pushMatrix();
		          RenderControl.renderText(entity, entity.getDisplayName().getUnformattedText());
		         GlStateManager.popMatrix();
			}
		}
	}
	
	@Override
	public void doRenderShadowAndFire(Entity entityIn, double x, double y, double z, float yaw, float partialTicks) {}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	
}
