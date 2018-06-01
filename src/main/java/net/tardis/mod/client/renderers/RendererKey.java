package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelKey;
import net.tardis.mod.util.helpers.Helper;

public class RendererKey extends TileEntityItemStackRenderer {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/items/key.png");
	public ModelKey model = new ModelKey();
	
	Minecraft mc;
	
	public RendererKey() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void renderByItem(ItemStack itemStackIn) {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180, 1, 0, 0);
		Vec3d off = Helper.convertToPixels(8, -15, 0);
		GlStateManager.translate(off.x, off.y, off.z);
		GlStateManager.scale(0.5, 0.5, 0.5);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
}
