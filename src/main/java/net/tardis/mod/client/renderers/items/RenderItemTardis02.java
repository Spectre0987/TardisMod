package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor02;
import net.tardis.mod.client.models.exteriors.ModelRightDoor02;
import net.tardis.mod.client.models.exteriors.ModelTardis02;
import net.tardis.mod.client.renderers.exteriors.RendererTileDoor01;

public class RenderItemTardis02 extends TileEntityItemStackRenderer {

	Minecraft mc;
	ModelTardis02 model = new ModelTardis02();
	ModelRightDoor02 rd = new ModelRightDoor02();
	ModelLeftDoor02 ld = new ModelLeftDoor02();

	public RenderItemTardis02() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(RendererTileDoor01.TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
		ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
