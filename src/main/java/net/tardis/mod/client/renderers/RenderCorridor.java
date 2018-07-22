package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelToyotaCorridor;
import net.tardis.mod.common.entities.EntityCorridor;

public class RenderCorridor extends Render<EntityCorridor> {

	Minecraft mc;
	ModelToyotaCorridor model = new ModelToyotaCorridor();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/corridor_toyota.png");
	
	public RenderCorridor() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCorridor entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityCorridor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		
	}

}
