package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.entity.cybermen.ModelCybermanTomb;
import net.tardis.mod.common.entities.EntityCybermanInvasion;

public class RenderCybermanInvasion extends RenderLiving<EntityCybermanInvasion> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/cyberman_tomb.png");
	public static ModelCybermanTomb model = new ModelCybermanTomb();

	public RenderCybermanInvasion(RenderManager manager) {
		super(manager, model, 0.03F);
		addLayer(new LayerHeldItem(this));
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityCybermanInvasion entity) {
		return TEXTURE;
	}

	@Override
	protected void renderModel(EntityCybermanInvasion entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		super.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	}

}
