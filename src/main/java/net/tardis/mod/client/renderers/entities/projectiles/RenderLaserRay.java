package net.tardis.mod.client.renderers.entities.projectiles;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.common.entities.EntityLaserRay;
import net.tardis.mod.util.client.RenderHelper;

public class RenderLaserRay extends Render<EntityLaserRay> {

	public RenderLaserRay(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRenderShadowAndFire(Entity entityIn, double x, double y, double z, float yaw, float partialTicks) {

	}

	@Override
	public void doRender(EntityLaserRay entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		RenderHelper.setupRenderLightning();

		Vec3d vec1 = new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);
		Vec3d vec2 = new Vec3d(entity.posX, entity.posY, entity.posZ);
		vec1 = vec2.subtract(vec1);
		vec2 = vec2.subtract(vec2);
		vec1 = vec1.normalize();

		double x_ = vec2.x - vec1.x;
		double y_ = vec2.y - vec1.y;
		double z_ = vec2.z - vec1.z;
		double diff = MathHelper.sqrt(x_ * x_ + z_ * z_);
		float yaw = (float) (Math.atan2(z_, x_) * 180.0D / 3.141592653589793D) - 90.0F;
		float pitch = (float) -(Math.atan2(y_, diff) * 180.0D / 3.141592653589793D);
		GlStateManager.rotate(-yaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);

		RenderHelper.drawGlowingLine(Vec3d.ZERO, new Vec3d(0, 0, 1), 0.5F, entity.color);
		RenderHelper.finishRenderLightning();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLaserRay entity) {
		return null;
	}

}
