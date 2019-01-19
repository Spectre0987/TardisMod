package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.items.TItems;
import org.lwjgl.opengl.GL11;

public class RenderInvis extends Render<Entity> {

	public RenderInvis(RenderManager manager) {
		super(manager);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.player.getHeldItemMainhand().getItem() == TItems.manual) {
			Entity look = mc.objectMouseOver.entityHit;

			String name;
			if (entity instanceof EntityControl) {
				name = ((EntityControl) entity).getControlName();
			} else {
				name = entity.getDisplayName().getUnformattedText();
			}

			if (look != null && look == entity) {

				float offset = MathHelper.cos(entity.ticksExisted * 0.1F) * -0.09F;

				GL11.glPushMatrix();
				GL11.glTranslatef(0, 0.4F, 0);
				GL11.glScalef(0.60F, 0.60F, 0.60F);
				this.renderLivingLabel(entity, name, x, y + 0.4 + offset, z, 46);
				GL11.glPopMatrix();
			}
		}
	}

	@Override
	public void doRenderShadowAndFire(Entity entityIn, double x, double y, double z, float yaw, float partialTicks) {
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
