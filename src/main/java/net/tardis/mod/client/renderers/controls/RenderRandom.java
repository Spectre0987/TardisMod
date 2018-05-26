package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.client.models.console.contols.ModelRandom;
import net.tardis.mod.client.renderers.RenderControl;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.info.TardisType;
import net.tardis.mod.util.helpers.Helper;

public class RenderRandom extends RenderControl {
	
	public RenderRandom() {}

	ModelRandom model = new ModelRandom();
	
	@Override
	public void renderControl(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks,TardisType tType) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.0625F, z);
		mc.getTextureManager().bindTexture(CONTROL_TEXTURE);
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.rotate(-58,1,0,0);
		Vec3d offset = Helper.convertToPixels(-1.5, -1.5, 0);
		GlStateManager.rotate(entity.ticks,0,0,1);
		GlStateManager.translate(offset.x, offset.y, offset.z);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
