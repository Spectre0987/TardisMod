package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderTEISRItem extends TileEntityItemStackRenderer {

	private Minecraft mc;
	private ModelBase base;
	private ResourceLocation texture;

	public RenderTEISRItem() {
		mc = Minecraft.getMinecraft();
	}

	public RenderTEISRItem(ModelBase model, ResourceLocation rl) {
		this();
		this.base = model;
		this.texture = rl;
	}

	public RenderTEISRItem(ModelBase modelBase) {
		this.base = modelBase;
		this.texture = null;
	}

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		if (texture != null) {
			mc.getTextureManager().bindTexture(this.texture);
		}
		base.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
