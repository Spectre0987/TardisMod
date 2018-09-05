package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.entities.EntityCompanion;

public class RenderCompanion extends RenderLiving<EntityCompanion>{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/companion.png");
	
	public RenderCompanion(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelPlayer(0.0625F, false), 0.3F);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityCompanion entity) {
		return entity.getType().getTexture();
	}

}
