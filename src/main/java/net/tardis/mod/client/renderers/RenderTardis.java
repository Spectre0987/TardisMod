package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityTardis;

public class RenderTardis extends Render {
	
	Minecraft mc;
	public static final ItemStack stack = new ItemStack(TBlocks.tardis_top);
	
	public RenderTardis() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1, z);
		GlStateManager.rotate(((EntityTardis) entity).renderRotation, 0, 1, 0);
		mc.getRenderItem().renderItem(stack, TransformType.GROUND);
		GlStateManager.popMatrix();
	}
	
}
