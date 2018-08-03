package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.guis.EnumExterior;
import net.tardis.mod.common.entities.EntityTardis;

public class RenderTardis extends Render<EntityTardis> {
	
	Minecraft mc;
	
	public RenderTardis() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityTardis entity) {
		return null;
	}
	
	@Override
	public void doRender(EntityTardis entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(entity.renderRotation, 0, 1, 0);
		EnumExterior ext = Enum.valueOf(EnumExterior.class, entity.getExterior());
		mc.getTextureManager().bindTexture(ext.tex);
		GlStateManager.rotate(180, 1, 0, 0);
		ext.model.renderClosed(0.0625F);
		GlStateManager.popMatrix();
	}
	
}
