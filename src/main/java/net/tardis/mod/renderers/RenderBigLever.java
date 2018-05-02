package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.models.console.contols.ModelFlightLever;

public class RenderBigLever extends Render {

	Minecraft mc;
	public ModelFlightLever model=new ModelFlightLever();
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/controls/flight_lever.png");
	
	public RenderBigLever() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc=Minecraft.getMinecraft();
	}
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x-0.03125, y, z-0.03125);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}

}
