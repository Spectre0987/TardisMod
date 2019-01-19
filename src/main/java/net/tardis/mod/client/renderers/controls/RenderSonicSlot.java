package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.entities.controls.ControlSonicSlot;

public class RenderSonicSlot extends Render<ControlSonicSlot> {

	public RenderSonicSlot(RenderManager renderM) {
		super(renderM);
	}

	@Override
	protected ResourceLocation getEntityTexture(ControlSonicSlot entity) {
		return null;
	}

	@Override
	public void doRender(ControlSonicSlot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		GlStateManager.rotate(-45, 1, 0, 0);
		Minecraft.getMinecraft().getRenderItem().renderItem(entity.getItemStack(), TransformType.THIRD_PERSON_RIGHT_HAND);
		GlStateManager.popMatrix();
	}

}
