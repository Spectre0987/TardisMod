package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.client.models.console.contols.ModelZ;
import net.tardis.mod.client.renderers.RenderControl;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.info.TardisType;
import net.tardis.mod.util.helpers.Helper;

public class RenderZ extends RenderControl {
	
	public ModelZ model = new ModelZ();
	
	@Override
	public void renderControl(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks, TardisType tType) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		mc.getTextureManager().bindTexture(CONTROL_TEXTURE);
		GlStateManager.rotate(180, 1, 0, 0);
		Vec3d offset = Helper.convertToPixels(-1, 0, 0.7);
		GlStateManager.translate(offset.x, offset.y, offset.z);
		GlStateManager.rotate(60, 0, 1, 0);
		GlStateManager.rotate(-58, 1, 0, 0);
		Vec3d pos;
		if (entity.ticks == 0)
			pos = Helper.convertToPixels(0.5, -1.35, -0.5);
		else
			pos = Helper.convertToPixels(0.5, -1.35, -0.25);
		GlStateManager.translate(pos.x, pos.y, pos.z);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
}
