package net.tardis.mod.client.renderers;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.tardis.mod.client.worldshell.FakeClientPlayer;
import net.tardis.mod.common.items.ItemPlayerVictim;

public class TEISRVictim extends TileEntityItemStackRenderer {
	
	private static ModelPlayer STEVE = new ModelPlayer(0.0625F, false);
	private static ModelPlayer ALEX = new ModelPlayer(0.0312F, true);

	@Override
	public void renderByItem(ItemStack stack, float partialTicks) {
		GlStateManager.pushMatrix();
		UUID id = (!stack.hasTagCompound() || !stack.getTagCompound().hasUniqueId("player_id")) ?
				Minecraft.getMinecraft().player.getGameProfile().getId() : ItemPlayerVictim.getUUID(stack);
		FakeClientPlayer player = new FakeClientPlayer(Minecraft.getMinecraft().world, new GameProfile(id, "none"));
		ModelPlayer model = ALEX;
		model.isChild = false;
		GlStateManager.scale(0.3, 0.3, 0.3);
		Minecraft.getMinecraft().getTextureManager().bindTexture(player.getLocationSkin());
		model.render(player, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
}
