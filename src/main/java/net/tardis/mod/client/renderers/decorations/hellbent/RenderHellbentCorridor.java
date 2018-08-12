package net.tardis.mod.client.renderers.decorations.hellbent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.decoration.ModelHellbentCorridor;
import net.tardis.mod.common.entities.hellbent.EntityHellbentCorridor;
import net.tardis.mod.util.helpers.Helper;

public class RenderHellbentCorridor extends Render<EntityHellbentCorridor> {

	Minecraft mc;
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/hellbent/corridor.png");
	public ModelHellbentCorridor model = new ModelHellbentCorridor();
	
	public RenderHellbentCorridor(RenderManager manager) {
		super(manager);
		mc = Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHellbentCorridor entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityHellbentCorridor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(Helper.getAngleFromFacing(entity.getHorizontalFacing()), 0, 1, 0);
		GlStateManager.translate(-1.5, 0, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		
		if(entity.getOpen()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(Helper.precentToPixels(12), 0, Helper.precentToPixels(8));
			GlStateManager.rotate(85, 0, 1, 0);
			model.renderLeftDoor(0.0625F);
		    GlStateManager.popMatrix();
		}
		else model.renderLeftDoor(0.0625F);
	    if(entity.getOpen()) {
	    	GlStateManager.pushMatrix();
	    	GlStateManager.rotate(-90, 0, 1, 0);
	    	GlStateManager.translate(-2.5, 0, -2 - Helper.precentToPixels(2.5F));
	    	model.renderRightDoor(0.0625F);
	    	GlStateManager.popMatrix();
	    }
	    else model.renderRightDoor(0.0625F);
		GlStateManager.popMatrix();
	}

}
