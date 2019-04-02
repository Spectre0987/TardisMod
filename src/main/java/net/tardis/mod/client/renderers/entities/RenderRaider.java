package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.entities.EntityRaider;



public class RenderRaider extends RenderLiving<EntityRaider> {

	public static ModelPlayer STEVE = new ModelPlayer(0F, false);
	public static ModelPlayer ALEX = new ModelPlayer(0F, true);

	public RenderRaider(RenderManager rendermanagerIn) {
		super(rendermanagerIn, STEVE, 0.3F);
		STEVE.isChild = false;
		ALEX.isChild = false;
		addLayer(new LayerHeldItem(this));
	}


	@Override
	public ResourceLocation getEntityTexture(EntityRaider entity) {
		return entity.getType().getTexture();
	}

	@Override
	protected void renderModel(EntityRaider comp, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		this.bindEntityTexture(comp);
		comp.getType().getModel().render(comp, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	}

}