package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.controls.EntityControl;

public abstract class RenderControl extends Render {
	
	private Minecraft mc;
	public static final ResourceLocation CONTROL_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/control_sheet.png");
	
	public RenderControl() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(y, y, z);
		renderControl((EntityControl) entity, entityYaw, partialTicks);
		GlStateManager.popMatrix();
	}
	
	public abstract void renderControl(EntityControl entity, float entityYaw, float partialTicks);
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return CONTROL_TEXTURE;
	}
	
}
