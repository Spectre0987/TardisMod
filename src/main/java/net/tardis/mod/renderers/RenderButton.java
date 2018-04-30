package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.controls.ControlSTCLoad;
import net.tardis.mod.controls.EntityControl;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.models.console.console.ModelButton;

public class RenderButton extends Render{
	
	public ModelButton model=new ModelButton();
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/controls/button.png");
	public static final ResourceLocation TEXTURE_LOADING=new ResourceLocation(Tardis.MODID,"textures/controls/button_loading.png");

	public RenderButton() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x-0.03175, y, z-0.03175);
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.rotate(entity.rotationYaw,0,1,0);
		GlStateManager.rotate(entity.rotationPitch,1,0,0);
		{
			//Apply Animations
			{
				EntityControl c=(EntityControl)entity;
				ResourceLocation loc=TEXTURE;
				if(entity instanceof ControlSTCLoad&&((ControlSTCLoad)entity).getLoading())loc=TEXTURE_LOADING;
				Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
				GlStateManager.translate(0, c.ticks>0?Helper.normalizeVec3d(0, 0.5, 0).y:0, 0);
			}
			model.render(entity, 0,0,0,0,0,0.0625F);
		}
		GlStateManager.popMatrix();
	}

}
