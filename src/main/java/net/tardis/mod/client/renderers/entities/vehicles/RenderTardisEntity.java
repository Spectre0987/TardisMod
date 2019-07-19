package net.tardis.mod.client.renderers.entities.vehicles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.common.entities.EntityTardis;

public class RenderTardisEntity extends Render<EntityTardis>{

	public RenderTardisEntity(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTardis entity) {
		return TextureMap.LOCATION_MISSING_TEXTURE;
	}

	@Override
	public void doRender(EntityTardis entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		EnumExterior exterior = entity.getExteriorEnum();
		Minecraft.getMinecraft().getTextureManager().bindTexture(exterior.tex);
		exterior.model.renderOpen(0.0625F);
		
		if(entity.getPassengers().size() > 0 && entity.getPassengers().get(0) instanceof EntityPlayerSP) {
			GlStateManager.translate(0, 0.5, -0.5);
			EntityPlayerSP player = (EntityPlayerSP)entity.getPassengers().get(0);
			Minecraft.getMinecraft().getTextureManager().bindTexture(player.getLocationSkin());
			ModelPlayer STEVE = new ModelPlayer(0.0625F, false);
			STEVE.isChild = false;
			STEVE.isRiding = true;
			STEVE.render(entity, 0, 0, entity.ticksExisted, 0, 0, 0.0625F);
		}
		GlStateManager.popMatrix();
	}

}
