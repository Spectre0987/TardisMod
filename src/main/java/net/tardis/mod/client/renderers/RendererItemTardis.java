package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.tardis.mod.client.models.ModelTardis;

public class RendererItemTardis extends TileEntityItemStackRenderer {

	Minecraft mc;
	ModelTardis model=new ModelTardis();
	
	public RendererItemTardis() {
		mc=Minecraft.getMinecraft();
	}

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(RenderTardis.TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderByItem(ItemStack stack, float partialTicks) {
		this.renderByItem(stack);
		super.renderByItem(stack, partialTicks);
	}
}
