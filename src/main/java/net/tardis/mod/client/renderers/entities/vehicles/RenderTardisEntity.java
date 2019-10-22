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
import net.tardis.mod.common.enums.EnumFlightState;

public class RenderTardisEntity extends Render<EntityTardis>{

	public static ModelPlayer STEVE = new ModelPlayer(0.0625F, false);
	public static ModelPlayer ALEX = new ModelPlayer(0.0315F, true);
	
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
		if(entity.getOpenState() == EnumFlightState.SITTING) {
			exterior.model.renderOpen(0.0625F);
			if(entity.getPassengers().size() > 0 && entity.getPassengers().get(0) instanceof EntityPlayerSP) {
				GlStateManager.translate(0, 0.5, -0.5);
				EntityPlayerSP player = (EntityPlayerSP)entity.getPassengers().get(0);
				ModelPlayer modelPlayer = STEVE;
				if(Minecraft.getMinecraft().player != player || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0) {
					Minecraft.getMinecraft().getTextureManager().bindTexture(player.getLocationSkin());
					modelPlayer.isChild = false;
					modelPlayer.isRiding = true;
					modelPlayer.render(entity, 0, 0, entity.ticksExisted, 0, 0, 0.0625F);
				}
			}
		}
		else exterior.model.renderClosed(0.0625F);
		GlStateManager.popMatrix();
	}
	
	public double interpolate(double pos, double prev) {
		float p = Minecraft.getMinecraft().getRenderPartialTicks();
		return prev + p * (pos - prev);
	}
}
