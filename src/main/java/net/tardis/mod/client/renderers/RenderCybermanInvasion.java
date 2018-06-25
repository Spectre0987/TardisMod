package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelCybermanInvasion;
import net.tardis.mod.common.entities.EntityCybermanInvasion;

public class RenderCybermanInvasion extends Render<EntityCybermanInvasion> {

	Minecraft mc;
	public ModelCybermanInvasion model = new ModelCybermanInvasion();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/cyberman_invasion.png");
	
	public RenderCybermanInvasion() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCybermanInvasion entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityCybermanInvasion entity, double x, double y, double z, float entityYaw,float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180,0,0,1);
		GlStateManager.rotate(180, 0, 1, 0);
		model.render(entity, entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted,entity.getRotationYawHead(), entity.rotationPitch, 0.0625F);
		GlStateManager.popMatrix();
		
	}

}
