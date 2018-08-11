package net.tardis.mod.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
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
				
				 float offset = MathHelper.cos(entity.ticksExisted * 0.1F) * -0.09F;
				
				GL11.glPushMatrix();
				GL11.glTranslatef(0, 1.4F, 0);
				GL11.glScalef(0.1F, 0.1F, 0.1F);
            	this.renderLivingLabel(entity, entity.getDisplayName().getUnformattedText(), x,y + 0.4 + offset, z, 46);
				GL11.glPopMatrix();
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
