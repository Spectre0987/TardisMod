package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.tardis.mod.client.worldshell.FakeClientPlayer;

public class TEISRVictim extends TileEntityItemStackRenderer {
	
	private static ModelPlayer STEVE = new ModelPlayer(0.0625F, false);
	private static ModelPlayer ALEX = new ModelPlayer(0.0312F, true);

	@Override
	public void renderByItem(ItemStack itemStackIn, float partialTicks) {
		GlStateManager.pushMatrix();
		FakeClientPlayer player = new FakeClientPlayer(Minecraft.getMinecraft().world, Minecraft.getMinecraft().player.getGameProfile());
		ModelPlayer model = ALEX;
		model.isChild = false;
		GlStateManager.scale(0.3, 0.3, 0.3);
		Minecraft.getMinecraft().getTextureManager().bindTexture(player.getLocationSkin());
		model.render(player, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
}
