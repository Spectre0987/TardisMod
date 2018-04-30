package net.tardis.mod.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.blocks.TBlocks;
import net.tardis.mod.helpers.Helper;

public class RenderDoor extends Render {

	public RenderDoor() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y+Helper.normalizeVec3d(0,8,0).y, z);
		GlStateManager.rotate(180, 0, 1, 0);
		double scale=1;
		GlStateManager.scale(scale,scale,scale);
		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(TBlocks.door), TransformType.FIXED);
		GlStateManager.translate(0, 1, 0);
		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(TBlocks.door), TransformType.FIXED);
		GlStateManager.popMatrix();
	}

}
