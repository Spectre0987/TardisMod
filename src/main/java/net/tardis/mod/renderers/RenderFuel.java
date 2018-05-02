package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.controls.ControlFuel;
import net.tardis.mod.models.console.contols.ModelFuelLever;

public class RenderFuel extends Render {

	Minecraft mc;
	public ModelFuelLever model=new ModelFuelLever();
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/controls/fuel_lever.png");
	
	public RenderFuel() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc=Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x-0.1, y+0.1, z-0.1);
		GlStateManager.rotate(entity.rotationYaw,0,1,0);
		GlStateManager.rotate(entity.rotationPitch,1,0,0);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.translate(0, 0, ((ControlFuel)entity).ticks*0.002);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
