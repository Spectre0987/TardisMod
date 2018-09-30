package net.tardis.mod.client.renderers.decorations.hellbent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.decoration.ModelHellbentDoor;
import net.tardis.mod.common.entities.hellbent.EntityHellbentDoor;
import net.tardis.mod.util.common.helpers.Helper;

public class RenderHellbentDoor extends Render<EntityHellbentDoor> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/hellbent/door.png");
	public ModelHellbentDoor door = new ModelHellbentDoor();
	Minecraft mc;
	
	public RenderHellbentDoor(RenderManager manager) {
		super(manager);
		mc = Minecraft.getMinecraft();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHellbentDoor entity) {
		return null;
	}

	@Override
	public void doRender(EntityHellbentDoor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(Helper.getAngleFromFacing(entity.getHorizontalFacing()), 0, 1, 0);
		GlStateManager.translate(-0.5, 0, 0.5);
		mc.getTextureManager().bindTexture(TEXTURE);
		door.render(null, 0, 0, 0, 0, 0, 0.0625F);

		if(entity.isOpen()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(1, 0, -2 + Helper.precentToPixels(2));
			GlStateManager.rotate(-85, 0, 1, 0);
			door.renderLeftDoor(0.0625F);
			GlStateManager.popMatrix();
		}
		else door.renderLeftDoor(0.0625F);

		if(entity.isOpen()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 0, -1 + Helper.precentToPixels(2));
			GlStateManager.rotate(85, 0, 1, 0);
			door.renderRightDoor(0.0625F);
			GlStateManager.popMatrix();
		}
		else door.renderRightDoor(0.0625F);
		GlStateManager.popMatrix();
	}

}
