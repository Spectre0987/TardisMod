package net.tardis.mod.client.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.clothing.ModelSpaceLegs;

public class RenderItemSpaceLegs extends TileEntityItemStackRenderer {
	
	Minecraft mc;
	public ModelSpaceLegs model = new ModelSpaceLegs();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/clothing/space_suit.png");
	
	public RenderItemSpaceLegs() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
