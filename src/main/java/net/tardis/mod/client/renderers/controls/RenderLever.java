package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.tardis.mod.client.models.console.contols.ModelLever;
import net.tardis.mod.client.renderers.RenderControl;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.info.TardisType;

public class RenderLever extends RenderControl{

	ModelLever model = new ModelLever();
	
	public RenderLever() {}
	
	@Override
	public void renderControl(EntityControl entity, float entityYaw, float partialTicks, TardisType tType) {
		
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		Minecraft.getMinecraft().getTextureManager().bindTexture(CONTROL_TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
