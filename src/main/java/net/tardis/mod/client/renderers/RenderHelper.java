package net.tardis.mod.client.renderers;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.renderers.controls.RenderDoor;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.client.worldshell.WorldBoti;
import net.tardis.mod.proxy.ClientProxy;

@SideOnly(Side.CLIENT)
public class RenderHelper {
	
	static Framebuffer fb;
	static WorldBoti wBoti;
	
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
				if(wBoti == null || wBoti.dimension != te.getDimnesion()) wBoti = new WorldBoti(te.getDimnesion(), Minecraft.getMinecraft().world, te.getWorldShell());
				WorldClient oldW = Minecraft.getMinecraft().world;
				Minecraft.getMinecraft().world = wBoti;
				Framebuffer old = Minecraft.getMinecraft().getFramebuffer();
				int width = Minecraft.getMinecraft().displayWidth, height = Minecraft.getMinecraft().displayHeight;
				if(fb == null) fb = new Framebuffer(width, height, true);
				Minecraft.getMinecraft().world.setWorldTime(0L);
				GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
				GlStateManager.pushMatrix();
				GlStateManager.rotate(180,0,1,0);
				GlStateManager.rotate(rotation, 0, 1, 0);
				Minecraft.getMinecraft().entityRenderer.disableLightmap();
				Minecraft.getMinecraft().renderGlobal.renderSky(partialTicks, MinecraftForgeClient.getRenderPass());
				Minecraft.getMinecraft().renderGlobal.renderClouds(partialTicks, MinecraftForgeClient.getRenderPass(), te.getWorldShell().getOffset().getX(), te.getWorldShell().getOffset().getY(), te.getWorldShell().getOffset().getZ());
				renderShell.doRender(te, offset.x, offset.y, offset.z, 0, partialTicks, wBoti);
				Minecraft.getMinecraft().entityRenderer.enableLightmap();
				GlStateManager.popMatrix();
				
				 old.bindFramebuffer(true);
					
				fb.deleteFramebuffer();
				Minecraft.getMinecraft().world = oldW;
				
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}

			GL11.glDisable(GL11.GL_STENCIL_TEST);
			
			GL11.glColorMask(false, false, false, false);
			GlStateManager.depthMask(false);
			drawOutline(size);
			
			//Set things back
			GL11.glColorMask(true, true, true, true);
			GlStateManager.depthMask(true);
		   
		   
			GlStateManager.popMatrix();
		}
		else if(!ClientProxy.getRenderBOTI()){
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(RenderDoor.BLACK);
		buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, size.y, 0).tex(0, 1).endVertex();
		buf.pos(size.x, size.y, 0).tex(1, 1).endVertex();
		buf.pos(size.x, 0, 0).tex(1, 0).endVertex();
		tes.draw();
		GlStateManager.popMatrix();
	}

}
