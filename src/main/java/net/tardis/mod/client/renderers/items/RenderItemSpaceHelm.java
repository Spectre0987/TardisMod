package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.clothing.ModelSpaceHelm;

public class RenderItemSpaceHelm extends TileEntityItemStackRenderer {

	public static ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/clothing/space_suit.png");
	public ModelSpaceHelm model = new ModelSpaceHelm();
	
	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180, 0,0,1);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
