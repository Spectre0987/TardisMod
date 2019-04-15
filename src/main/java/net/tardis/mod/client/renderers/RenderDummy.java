package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.entities.EntityDummy;

public class RenderDummy extends Render<EntityDummy>{

	private static ModelPlayer ALEX = new ModelPlayer(0.0625F, true);
	private static ModelPlayer STEVE = new ModelPlayer(0.0625F, false);
	
	public RenderDummy(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDummy entity) {
		return null;
	}

	@Override
	public void doRender(EntityDummy entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		Minecraft.getMinecraft().getTextureManager().bindTexture(((EntityPlayerSP)entity.player).getLocationSkin());
		ALEX.render(entity.player, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
