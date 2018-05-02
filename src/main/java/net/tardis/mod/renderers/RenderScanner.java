package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.models.console.contols.ModelScanner;

public class RenderScanner extends Render {
	
	public ModelScanner model=new ModelScanner();
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/controls/scanner.png");

	public RenderScanner() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entity.rotationYaw,0,1,0);
		GlStateManager.rotate(entity.rotationPitch, 0, 0, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
