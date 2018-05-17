package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.tardis.mod.client.models.ModelTardis;
import net.tardis.mod.client.models.console.contols.ModelExteriorDoorL;
import net.tardis.mod.client.models.console.contols.ModelExteriorDoorR;

public class RendererItemTardis extends TileEntityItemStackRenderer {

	Minecraft mc;
	ModelTardis model=new ModelTardis();
	ModelExteriorDoorR door_r=new ModelExteriorDoorR();
	ModelExteriorDoorL door_l=new ModelExteriorDoorL();
	
	public RendererItemTardis() {
		mc=Minecraft.getMinecraft();
	}

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(RenderTardis.TEXTURE);
		double scale=0.35D;
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.translate(1.5, 2, 0);
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.rotate(23.5F,1,0,0);
		GlStateManager.rotate(45,0,1,0);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		door_r.render(null, 0, 0, 0, 0, 0, 0.0625F);
		door_l.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
}
