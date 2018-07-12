package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderTEISRItem extends TileEntityItemStackRenderer {

	Minecraft mc;
	public ModelBase base;
	public ResourceLocation texture;
	
	public RenderTEISRItem(){
		mc = Minecraft.getMinecraft();
	}
	
	public RenderTEISRItem(ModelBase model, ResourceLocation rl) {
		this();
		this.base = model;
		this.texture = rl;
	}
	
	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		if(texture != null) {
			mc.getTextureManager().bindTexture(this.texture);
		}
		base.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
