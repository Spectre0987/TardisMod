package net.tardis.mod.client.renderers.sky;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.IRenderHandler;
import net.tardis.mod.client.renderers.controls.RenderDoor;

public class RendererSpaceSky extends IRenderHandler {

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(50, 1, 50);
		GlStateManager.translate(-0.5, -25, -0.5);
		mc.getTextureManager().bindTexture(RenderDoor.BLACK_HOLE_TEXTURE);
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, 0, 1).tex(0, 1).endVertex();
		buf.pos(1, 0, 1).tex(1, 1).endVertex();
		buf.pos(1, 0, 0).tex(1, 0).endVertex();
		tes.draw();
		GlStateManager.popMatrix();
	}

}
