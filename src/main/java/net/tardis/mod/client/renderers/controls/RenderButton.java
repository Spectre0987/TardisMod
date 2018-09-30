package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.client.models.console.contols.ModelButton;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderButton extends RenderControl {
	
	public ModelButton model = new ModelButton();
	
	@Override
	public void doRender(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.0625, z);
		mc.getTextureManager().bindTexture(CONTROL_TEXTURE);
		GlStateManager.rotate(180, 1, 0, 0);
		Vec3d offset = Helper.convertToPixels(0.5, 0, 0);
		GlStateManager.translate(offset.x, offset.y, offset.z);
		GlStateManager.rotate(120, 0, 1, 0);
		GlStateManager.rotate(-58, 1, 0, 0);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
	@Override
	public void renderControl(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks, TileEntityTardis tType) {
		
	}
	
}
