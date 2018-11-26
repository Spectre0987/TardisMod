package net.tardis.mod.util.client;

import java.lang.reflect.Field;
import java.nio.FloatBuffer;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.renderers.controls.RenderDoor;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.client.worldshell.WorldBoti;
import net.tardis.mod.proxy.ClientProxy;

@SideOnly(Side.CLIENT)
public class RenderHelper {
	
	static Framebuffer fb;
	static WorldBoti wBoti;
	static FloatBuffer FOG_BUFFER = GLAllocation.createDirectFloatBuffer(16);
	
	public RenderHelper() {}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks, float rotation, @Nullable Vec3d offset, @Nullable Vec3d size, boolean renderFog) {
		if(ClientProxy.getRenderBOTI() && MinecraftForgeClient.getRenderPass() == 1) {
			
			if(offset == null)offset = new Vec3d(-1, 0, -7);
			GlStateManager.pushMatrix();
			GlStateManager.color(1, 1, 1);
			
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
				wBoti.setWorldTime(te.getWorldShell().getTime());
				RenderHelper.setRenderGlobalWorld(wBoti);
				Minecraft.getMinecraft().world = wBoti;
				Framebuffer old = Minecraft.getMinecraft().getFramebuffer();
				int width = Minecraft.getMinecraft().displayWidth, height = Minecraft.getMinecraft().displayHeight;
				if(fb == null) fb = new Framebuffer(width, height, true);
				GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
				GlStateManager.pushMatrix();
				GlStateManager.rotate(180, 0, 1, 0);
				GlStateManager.rotate(rotation, 0, 1, 0);
				Minecraft.getMinecraft().entityRenderer.disableLightmap();
				if(!wBoti.provider.isSkyColored()) {
					GlStateManager.pushMatrix();
					Vec3d color = wBoti.provider.getFogColor(0, 0);
					GlStateManager.enableFog();
					GlStateManager.setFog(GlStateManager.FogMode.LINEAR);
					if(color != null) {
						FOG_BUFFER.clear();
						FOG_BUFFER.put((float)color.x).put((float)color.y).put((float)color.z).put(1F);
						FOG_BUFFER.flip();
						GlStateManager.glFog(2918, FOG_BUFFER);
					}
					GlStateManager.setFogDensity(0.01F);
					GlStateManager.setFogStart(10F);
					GlStateManager.setFogEnd(20F);
					GlStateManager.disableFog();
					GlStateManager.popMatrix();
				}
				Minecraft.getMinecraft().renderGlobal.renderSky(partialTicks, MinecraftForgeClient.getRenderPass());
				renderShell.doRender(te, offset.x, offset.y, offset.z, 0, partialTicks, wBoti);
				Minecraft.getMinecraft().entityRenderer.enableLightmap();
				GlStateManager.popMatrix();
				
				RenderHelper.setRenderGlobalWorld(oldW);
				
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
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks, float rotation, @Nullable Vec3d offset, @Nullable Vec3d size) {
		RenderHelper.renderPortal(renderShell, te, partialTicks, rotation, offset, size, false);
	}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks) {
		RenderHelper.renderPortal(renderShell, te, partialTicks, 0F, null, null, false);
	}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks, float rot) {
		RenderHelper.renderPortal(renderShell, te, partialTicks, rot, null, null, false);
	}
	
	public static void renderPortal(RenderWorldShell renderShell, IContainsWorldShell te, float partialTicks, float rot, Vec3d offset) {
		RenderHelper.renderPortal(renderShell, te, partialTicks, rot, offset, null, false);
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

	public static void setRenderGlobalWorld(WorldClient world) {
		try {
			Field f = Minecraft.getMinecraft().renderGlobal.getClass().getDeclaredField(Tardis.getIsDev() ? "world" : "field_72769_h");
			if(f != null) {
				f.setAccessible(true);
				f.set(Minecraft.getMinecraft().renderGlobal, world);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void renderTransformAxis() {
		GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);
		bb.pos(-1, 0, 0).color(1F, 0, 0, 1F).endVertex();
		bb.pos(1, 0, 0).color(1F, 0, 0, 1F).endVertex();
		
		bb.pos(0, -1, 0).color(0F, 0, 1, 1F).endVertex();
		bb.pos(0, 1, 0).color(0F, 0, 1, 1F).endVertex();
		
		bb.pos(0, 0, -1).color(0F, 1, 0, 1F).endVertex();
		bb.pos(0, 0, 1).color(0F, 1, 0, 1F).endVertex();
		Tessellator.getInstance().draw();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
	}


	private static float lastBrightnessX = OpenGlHelper.lastBrightnessX;
	private static float lastBrightnessY = OpenGlHelper.lastBrightnessY;

	public static void setLightmapTextureCoords(float x, float y) {
		lastBrightnessX = OpenGlHelper.lastBrightnessX;
		lastBrightnessY = OpenGlHelper.lastBrightnessY;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, x, y);
	}

	public static void restoreLightmapTextureCoords() {
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
	}

	public static void setupRenderLightning() {
		GlStateManager.pushMatrix();
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_CONSTANT_ALPHA);
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
		setLightmapTextureCoords(240, 240);
	}

	public static void finishRenderLightning() {
		restoreLightmapTextureCoords();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	public static void drawLine(Vec3d start, Vec3d end, float lineWidth, float innerLineWidth, Vec3d color, float alpha) {
		if (start == null || end == null)
			return;

		Tessellator tes = Tessellator.getInstance();
		BufferBuilder wr = tes.getBuffer();

		if (lineWidth > 0) {
			GlStateManager.color((float) color.x, (float) color.y, (float) color.z, alpha);
			GlStateManager.glLineWidth(lineWidth);
			wr.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
			wr.pos(start.x, start.y, start.z).endVertex();
			wr.pos(end.x, end.y, end.z).endVertex();
			tes.draw();
		}

		if (innerLineWidth > 0) {
			GlStateManager.color(1, 1, 1, MathHelper.clamp(alpha - 0.2F, 0, 1));
			GlStateManager.glLineWidth(innerLineWidth);
			wr.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
			wr.pos(start.x, start.y, start.z).endVertex();
			wr.pos(end.x, end.y, end.z).endVertex();
			tes.draw();
		}
	}

	public static void drawGlowingLine(Vec3d start, Vec3d end, float thickness, Vec3d color) {
		drawGlowingLine(start, end, thickness, color, 1F);
	}

	public static void drawGlowingLine(Vec3d start, Vec3d end, float thickness, Vec3d color, float alpha) {
		if (start == null || end == null)
			return;

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bb = tessellator.getBuffer();
		int smoothFactor = Minecraft.getMinecraft().gameSettings.ambientOcclusion;
		int layers = 10 + smoothFactor * 20;

		GlStateManager.pushMatrix();
		start = start.scale(-1D);
		end = end.scale(-1D);
		GlStateManager.translate(-start.x, -start.y, -start.z);
		start = end.subtract(start);
		end = end.subtract(end);

		{
			double x = end.x - start.x;
			double y = end.y - start.y;
			double z = end.z - start.z;
			double diff = MathHelper.sqrt(x * x + z * z);
			float yaw = (float) (Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
			float pitch = (float) -(Math.atan2(y, diff) * 180.0D / 3.141592653589793D);
			GlStateManager.rotate(-yaw, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);
		}

		for (int layer = 0; layer <= layers; ++layer) {
			if (layer < layers) {
				GlStateManager.color((float) color.x, (float) color.y, (float) color.z, 1.0F / layers / 2);
				GlStateManager.depthMask(false);
			} else {
				GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
				GlStateManager.depthMask(true);
			}
			double size = thickness + (layer < layers ? layer * (1.25D / layers) : 0.0D);
			double d = (layer < layers ? 1.0D - layer * (1.0D / layers) : 0.0D) * 0.1D;
			double width = 0.0625D * size;
			double height = 0.0625D * size;
			double length = start.distanceTo(end) + d;

			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
			bb.pos(-width, height, length).endVertex();
			bb.pos(width, height, length).endVertex();
			bb.pos(width, height, -d).endVertex();
			bb.pos(-width, height, -d).endVertex();
			bb.pos(width, -height, -d).endVertex();
			bb.pos(width, -height, length).endVertex();
			bb.pos(-width, -height, length).endVertex();
			bb.pos(-width, -height, -d).endVertex();
			bb.pos(-width, -height, -d).endVertex();
			bb.pos(-width, -height, length).endVertex();
			bb.pos(-width, height, length).endVertex();
			bb.pos(-width, height, -d).endVertex();
			bb.pos(width, height, length).endVertex();
			bb.pos(width, -height, length).endVertex();
			bb.pos(width, -height, -d).endVertex();
			bb.pos(width, height, -d).endVertex();
			bb.pos(width, -height, length).endVertex();
			bb.pos(width, height, length).endVertex();
			bb.pos(-width, height, length).endVertex();
			bb.pos(-width, -height, length).endVertex();
			bb.pos(width, -height, -d).endVertex();
			bb.pos(width, height, -d).endVertex();
			bb.pos(-width, height, -d).endVertex();
			bb.pos(-width, -height, -d).endVertex();
			tessellator.draw();
		}

		GlStateManager.popMatrix();
	}



}
