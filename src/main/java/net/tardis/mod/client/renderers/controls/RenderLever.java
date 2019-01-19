package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.client.models.console.contols.ModelLever;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderLever extends RenderControl {

	ModelLever model = new ModelLever();

	public RenderLever() {
	}

	@Override
	public void renderControl(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks, TileEntityTardis tType) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		Minecraft.getMinecraft().getTextureManager().bindTexture(CONTROL_TEXTURE);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(60F, 0, 1, 0);
		Vec3d offset = Helper.convertToPixels(-0.5, -2.5, 0);
		TileEntity te = mc.world.getTileEntity(entity.getConsolePos());
		if (te != null && te instanceof TileEntityTardis) {
			TileEntityTardis tardis = (TileEntityTardis) te;
			if (!tardis.isInFlight())
				offset = Helper.convertToPixels(-0.5, -2.5, 0);
			else
				offset = Helper.convertToPixels(-0.5, -1.5, -2);
		}
		GlStateManager.translate(offset.x, offset.y, offset.z);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	public void doRender(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

}
