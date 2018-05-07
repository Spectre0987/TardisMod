package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.blocks.TBlocks;

public class RenderFField extends Render {
	
	Minecraft mc;
	
	public RenderFField() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 4, z);
		GlStateManager.scale(8, 8, 8);
		mc.getRenderItem().renderItem(new ItemStack(TBlocks.force_field), TransformType.GROUND);
		GlStateManager.popMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	
}
