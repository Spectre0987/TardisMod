package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor03;
import net.tardis.mod.client.models.exteriors.ModelRightDoor03;
import net.tardis.mod.client.models.exteriors.ModelTardis03;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor03;

public class RenderItemTardis03 extends TileEntityItemStackRenderer {

	Minecraft mc;
	ModelTardis03 model = new ModelTardis03();
	ModelRightDoor03 rd = new ModelRightDoor03();
	ModelLeftDoor03 ld = new ModelLeftDoor03();

	public RenderItemTardis03() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(RenderTileDoor03.TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
		ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
