package net.tardis.mod.common.dimensions.moon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.TDimensions;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import java.util.Random;

@Mod.EventBusSubscriber(Side.CLIENT)
public class RenderGallifreySky extends IRenderHandler {
	
	private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("textures/environment/moon_phases.png");
	
	private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("textures/environment/sun.png");
	private static final ResourceLocation SUN_BOX = new ResourceLocation(Tardis.MODID, "textures/environment/gallifrey_sky.png");
	
	public static Sphere sky = new Sphere();
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		GlStateManager.pushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.disableFog();
	
	// ========================== Sky box start =========================
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();
		Minecraft.getMinecraft().getTextureManager().bindTexture(SUN_BOX);
		sky.setTextureFlag(true);
		GlStateManager.rotate(90, 1, 0, 0);
		sky.draw(64, 25, 25);
		GlStateManager.rotate(-90, 1, 0, 0);
		
		GlStateManager.disableBlend();
		
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.color(1, 1, 1, 0.6f);
		GlStateManager.rotate(world.getTotalWorldTime() / 64.0f, 0, 1, 0);
		GlStateManager.rotate(90, 1, 0, 0);
		sky.draw(64, 25, 25);
		GlStateManager.rotate(-world.getTotalWorldTime() / 64.0f, 0, 1, 0);
		GlStateManager.rotate(-90, 0, 1, 0);
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.popMatrix();
		// ========================== Sky box stop =========================
		
		//===================Sun start================
		GlStateManager.translate(-50, 60, 120);
		GlStateManager.rotate(-50, 1, 0, 0);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		Minecraft.getMinecraft().getTextureManager().bindTexture(SUN_TEXTURES);
		GlStateManager.scale(0.5f, 0.5f, 0.5f);
		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 128, 128, 128, 128);
		GlStateManager.translate(0, 80, 100);
		GlStateManager.rotate(25, 0, 1, 0);
		GlStateManager.scale(2, 2, 2);
		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 128, 128, 128, 128);
		GlStateManager.disableBlend();
		GlStateManager.enableCull();
		GlStateManager.enableDepth();
		GlStateManager.enableFog();
		GlStateManager.popMatrix();
		//===================Sun stop================
	}
	
	@SubscribeEvent
	public static void onColorFog(EntityViewRenderEvent.RenderFogEvent.FogColors event){
		if(Minecraft.getMinecraft().player.dimension == TDimensions.GALLIFREY_ID) {
			
			boolean isDay = Minecraft.getMinecraft().world.getWorldTime() <= 13000;
			
			float red, green, blue;
			if (isDay) {
				red = 0.92F;
				green = 0.47F;
				blue = 0.05F;
			} else {
				red = 0f;
				green = 0f;
				blue = 0f;
			}
			
			event.setRed(red);
			event.setGreen(green);
			event.setBlue(blue);
		}
	}
	
}
