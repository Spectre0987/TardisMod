package net.tardis.mod.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.RenderWorldShell;

public class RenderHelper {
	
	Minecraft mc;
	
	public RenderHelper() {
		mc = Minecraft.getMinecraft();
	}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks) {
		GlStateManager.pushMatrix();
		Minecraft mc = Minecraft.getMinecraft();
		GL11.glEnable(GL11.GL_STENCIL_TEST);
		
		// Always write to stencil buffer
		GL11.glStencilFunc(GL11.GL_NEVER, 1, 0xFF);
		GL11.glStencilOp(GL11.GL_REPLACE, GL11.GL_KEEP, GL11.GL_KEEP);
		GL11.glStencilMask(0xFF);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);

		drawOutline();

		// Only pass stencil test if equal to 1
		GL11.glStencilMask(0x00);
		GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);

		// Draw scene from portal view
		try {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180,0,1,0);
		mc.entityRenderer.disableLightmap();
		renderShell.doRender(te, -1, 0, -7, 0, partialTicks);
		mc.entityRenderer.enableLightmap();
		GlStateManager.popMatrix();
		}
		catch(Exception e) {}

		GL11.glDisable(GL11.GL_STENCIL_TEST);
		
		// Draw portal stencils so portals wont be drawn over
		GL11.glColorMask(false, false, false, false);
		drawOutline();
		
		//Set things back
		GL11.glColorMask(true, true, true, true);
	    GlStateManager.popMatrix();
	}
	
	public static void drawOutline() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(RenderDoor.TEXTURE);
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, 2, 0).tex(0, 1).endVertex();
		buf.pos(1, 2, 0).tex(1, 1).endVertex();
		buf.pos(1, 0, 0).tex(1, 0).endVertex();
		tes.draw();
	}

}
