package net.tardis.mod.client.renderers.entities.decoration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.entities.brak.EntityDoorsBrakSecondary;
import net.tardis.mod.common.items.TItems;

public class RenderBrakDoors extends Render<EntityDoorsBrakSecondary>{

	public RenderBrakDoors(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDoorsBrakSecondary entity) {
		return null;
	}

	@Override
	public void doRender(EntityDoorsBrakSecondary entity, double x, double y, double z, float entityYaw,float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1, z);
		GlStateManager.scale(2, 2, 2);
		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(TItems.doors_brak), TransformType.NONE);
		GlStateManager.popMatrix();
	}

}
