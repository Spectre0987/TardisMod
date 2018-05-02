package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.controls.EntityControl;
import net.tardis.mod.models.console.contols.ModelLeverDemat;

public class RenderLever extends Render {

	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/controls/demat_lever.png");
	public ModelLeverDemat model=new ModelLeverDemat();
	
	public RenderLever() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		GlStateManager.rotate(entity.rotationPitch,1,0,0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		int rot=((EntityControl)entity).ticks;
		model.render(null, rot==0?30:rot, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
