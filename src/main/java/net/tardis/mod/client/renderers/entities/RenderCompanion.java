package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.entities.EntityCompanion;

public class RenderCompanion extends RenderLiving<EntityCompanion> {


	public static ModelPlayer STEVE = new ModelPlayer(0.0625F, false);
	public static ModelPlayer ALEX = new ModelPlayer(0.0625F, true);

	public RenderCompanion(RenderManager rendermanagerIn) {
		super(rendermanagerIn, STEVE, 0.3F);
		STEVE.isChild = false;
		ALEX.isChild = false;
		addLayer(new LayerHeldItem(this));
	}

	@Override
	public ResourceLocation getEntityTexture(EntityCompanion entity) {
		return entity.getType().getTexture();
	}

	@Override
	protected void renderModel(EntityCompanion comp, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		this.bindEntityTexture(comp);
		comp.getType().getModel().render(comp, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	}

}
