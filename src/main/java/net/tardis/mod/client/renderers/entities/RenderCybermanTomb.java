package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.entity.cybermen.ModelCybermanTomb;
import net.tardis.mod.common.entities.EntityCybermanTomb;

public class RenderCybermanTomb extends Render<EntityCybermanTomb> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/cyberman_tomb.png");
	Minecraft mc;
	ModelCybermanTomb model = new ModelCybermanTomb();

	public RenderCybermanTomb(RenderManager manager) {
		super(manager);
		mc = Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCybermanTomb entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityCybermanTomb entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(entity, entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted, entity.rotationYawHead, entity.rotationPitch, 0.0625F);
		GlStateManager.popMatrix();
	}

}
