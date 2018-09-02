package net.tardis.mod.client.renderers;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.proxy.ClientProxy;

@SideOnly(Side.CLIENT)
public class RenderHelper {
	
	static Framebuffer fb;
	
	public RenderHelper() {}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks, float rotation, @Nullable Vec3d offset, @Nullable Vec3d size) {
		if(ClientProxy.getRenderBOTI() && MinecraftForgeClient.getRenderPass() == 1) {
			if(offset == null)offset = new Vec3d(-1, 0, -7);
			GlStateManager.pushMatrix();
			GL11.glEnable(GL11.GL_STENCIL_TEST);
			// Always write to stencil buffer
			GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
			GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
			GL11.glStencilMask(0xFF);
			GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
	
			GlStateManager.depthMask(false);
			drawOutline(size);
			GlStateManager.depthMask(true);
	
			// Only pass stencil test if equal to 1(So only if rendered before)
			GL11.glStencilMask(0x00);
			GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);
	
			// Draw scene from portal view
			
			try {
				Framebuffer old = Minecraft.getMinecraft().getFramebuffer();
				int width = Minecraft.getMinecraft().displayWidth, height = Minecraft.getMinecraft().displayHeight;
				if(fb == null) fb = new Framebuffer(width, height, true);
				GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
				GlStateManager.pushMatrix();
				GlStateManager.rotate(180,0,1,0);
				GlStateManager.rotate(rotation, 0, 1, 0);
				Minecraft.getMinecraft().entityRenderer.disableLightmap();
				renderShell.doRender(te, offset.x, offset.y, offset.z, 0, partialTicks);
				Minecraft.getMinecraft().entityRenderer.enableLightmap();
				GlStateManager.popMatrix();
				
				old.bindFramebuffer(true);
				
				fb.deleteFramebuffer();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}

			GL11.glDisable(GL11.GL_STENCIL_TEST);
			
			GL11.glColorMask(false, false, false, false);
			RenderHelper.drawOutline(size);
			
			//Set things back
			GL11.glColorMask(true, true, true, true);
		    GlStateManager.popMatrix();
		}
		else if(MinecraftForgeClient.getRenderPass() == 1){
			RenderHelper.drawOutline(size);
		}
	}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks) {
		RenderHelper.renderPortal(renderShell, te, partialTicks, 0F, null, null);
	}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks, float rot) {
		RenderHelper.renderPortal(renderShell, te, partialTicks, rot, null, null);
	}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks, float rot, Vec3d offset) {
		RenderHelper.renderPortal(renderShell, te, partialTicks, rot, offset, null);
	}
	
	public static void drawOutline(@Nullable Vec3d size) {
		if(size == null)size = new Vec3d(1, 2, 0);
		GlStateManager.pushMatrix();
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		GlStateManager.color(0F, 0F, 0F, 1F);
		buf.pos(0, 0, 0).endVertex();
		buf.pos(0, size.y, 0).endVertex();
		buf.pos(size.x, size.y, 0).endVertex();
		buf.pos(size.x, 0, 0).endVertex();
		tes.draw();
		GlStateManager.popMatrix();
	}

}
