package net.tardis.mod.client.renderers.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.IRenderHandler;

public class RendererSpaceSky extends IRenderHandler {

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		GlStateManager.pushMatrix();
		GlStateManager.popMatrix();
	}

}
