package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.tardis.mod.common.tileentity.TileEntityJsonTester;

public class RenderJsonHelper extends TileEntitySpecialRenderer<TileEntityJsonTester> {

	Minecraft mc;
	
	public RenderJsonHelper() {
		mc = Minecraft.getMinecraft();
	}
	@Override
	public void render(TileEntityJsonTester te, double x, double y, double z, float partialTicks, int destroyStage,float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5, y + 1, z + 0.5);
		mc.getRenderItem().renderItem(te.stack, TransformType.GROUND);
		GlStateManager.popMatrix();
	}

}
