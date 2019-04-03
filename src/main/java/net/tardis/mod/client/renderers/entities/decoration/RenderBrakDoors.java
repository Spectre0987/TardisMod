package net.tardis.mod.client.renderers.entities.decoration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.decoration.ModelRoundelDoors02;
import net.tardis.mod.common.entities.brak.EntityDoorsBrakSecondary;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderBrakDoors extends Render<EntityDoorsBrakSecondary>{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/roundel_doors02.png");
	public static ModelRoundelDoors02 model = new ModelRoundelDoors02();
	public static ItemStack OPEN = new ItemStack(TItems.doors_brak, 1, 1);
	public static ItemStack CLOSED = new ItemStack(TItems.doors_brak, 1, 0);
	
	public RenderBrakDoors(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDoorsBrakSecondary entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityDoorsBrakSecondary entity, double x, double y, double z, float entityYaw,float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1, z);
		GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
		if(entity.hurtTime > 0)
			GlStateManager.translate(0, 0, -Math.cos(entity.hurtTime) * 0.01);
		GlStateManager.translate(0, 0, Helper.precentToPixels(9));
		GlStateManager.scale(2, 2, 2);
		Minecraft.getMinecraft().getRenderItem().renderItem(entity.isOpen() ? OPEN : CLOSED, TransformType.NONE);
		GlStateManager.popMatrix();
	}

}
