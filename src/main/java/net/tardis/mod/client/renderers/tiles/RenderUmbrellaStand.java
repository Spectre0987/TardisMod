package net.tardis.mod.client.renderers.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.tardis.mod.common.tileentity.TileEntityUmbrellaStand;

public class RenderUmbrellaStand extends TileEntitySpecialRenderer {
	
	public RenderUmbrellaStand() {
		
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.3, y + 1, z + 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		Minecraft.getMinecraft().getRenderItem().renderItem(((TileEntityUmbrellaStand) te).getUmbrella(), TransformType.FIRST_PERSON_RIGHT_HAND);
		GlStateManager.popMatrix();
	}
	
}
