package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelPen;

public class RenderItemSonicPen extends TileEntityItemStackRenderer {

	Minecraft mc;
	public ModelPen model = new ModelPen();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/items/sonic_pen.png");
	
	public RenderItemSonicPen() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180F,0,0,1);
		GlStateManager.scale(0.5,0.5,0.5);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
