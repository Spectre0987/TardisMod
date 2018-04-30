package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.controls.EntityControl;
import net.tardis.mod.models.console.console.ModelWheel;

public class RenderDirectionalControl extends Render{

	public ModelWheel model=new ModelWheel();
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/controls/wheel.png");
	
	public RenderDirectionalControl() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y+0.25, z);
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.rotate(entity.rotationYaw,0,1,0);
		GlStateManager.rotate(entity.rotationPitch,0,0,1);
		GlStateManager.scale(0.5, 0.5, 0.5);
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			GlStateManager.rotate(90,0,0,1);
			{
				EntityControl ec=(EntityControl)entity;
				float degree=(ec.direction*ec.ticks)*5;
				GlStateManager.rotate(degree,0,1,0);
			}
			model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		}
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
