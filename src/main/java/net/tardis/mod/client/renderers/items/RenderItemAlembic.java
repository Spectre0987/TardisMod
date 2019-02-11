package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.tardis.mod.client.models.items.ModelAlembic;
import net.tardis.mod.client.renderers.tiles.RenderAlembic;

public class RenderItemAlembic extends TileEntityItemStackRenderer {

	Minecraft mc;
	ModelAlembic model = new ModelAlembic();

	public RenderItemAlembic() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(RenderAlembic.TEXTURE);
		GlStateManager.translate(1, -0.455F, 0);
		GlStateManager.rotate(0, 0.0F, 0.0F, 0.0F);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
