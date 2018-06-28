package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.common.blocks.TBlocks;

public class RenderTESIRItem extends TileEntityItemStackRenderer {

	Minecraft mc;
	public ModelBase base;
	public ResourceLocation texture;
	
	public RenderTESIRItem(){
		mc = Minecraft.getMinecraft();
	}
	
	public RenderTESIRItem(ModelBase model, ResourceLocation rl) {
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
		System.out.println("Rendering: " + Item.getItemFromBlock(TBlocks.console).getRegistryName());
		GlStateManager.popMatrix();
	}

}
