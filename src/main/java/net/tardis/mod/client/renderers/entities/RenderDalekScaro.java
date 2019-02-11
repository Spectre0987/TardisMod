package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.entity.dalek.ModelDalekScaro;
import net.tardis.mod.common.entities.EntityDalekSkaro;

public class RenderDalekScaro extends RenderLiving<EntityDalekSkaro> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/dalek_scaro.png");

	public RenderDalekScaro(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelDalekScaro(), 0.03F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDalekSkaro entity) {
		return TEXTURE;
	}

}
