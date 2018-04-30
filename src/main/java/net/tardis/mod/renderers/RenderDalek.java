package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.models.ModelDalek;

public class RenderDalek extends Render {

	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/entity/mob/dalek.png");
	public ModelDalek model=new ModelDalek();
	Minecraft mc;
	
	public RenderDalek() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc=Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y+1.5, z);
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.rotate(((EntityMob)entity).rotationYawHead, 0, 1, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(entity, entity.rotationPitch, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
