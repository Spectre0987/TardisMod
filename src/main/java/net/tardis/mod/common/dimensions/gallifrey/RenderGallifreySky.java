package net.tardis.mod.common.dimensions.gallifrey;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.TDimensions;
import org.lwjgl.util.glu.Sphere;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class RenderGallifreySky extends IRenderHandler {
	
	private static final ResourceLocation SUN = new ResourceLocation(Tardis.MODID, "textures/environment/sun.png");
	private static final ResourceLocation DAY = new ResourceLocation(Tardis.MODID, "textures/environment/gallifrey_sky_day.png");
	private static final ResourceLocation NIGHT = new ResourceLocation(Tardis.MODID, "textures/environment/gallifrey_sky_night.png");
	private static final ResourceLocation DOG = new ResourceLocation(Tardis.MODID, "textures/environment/dog.png");
	
	public static Sphere SKY = new Sphere();
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		
		boolean isDay = Minecraft.getMinecraft().world.getWorldTime() <= 13000;
		
		GlStateManager.pushMatrix();
		GlStateManager.disableDepth();
		GlStateManager.disableFog();
		
		// ========================== Sky box start =========================
		GlStateManager.pushMatrix();
		GlStateManager.scale(3,3,3);
		GlStateManager.disableCull();
		Minecraft.getMinecraft().getTextureManager().bindTexture(isDay ? DAY : NIGHT);
		SKY.setTextureFlag(true);
		GlStateManager.rotate(-90, 1, 0, 0);
		GlStateManager.disableBlend();
		GlStateManager.rotate(world.getTotalWorldTime() / 40F, 0, 1, 0);
		SKY.draw(64, 32, 32);
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.popMatrix();
		// ========================== Sky box stop =========================
		
		//===================Sun start================
		if (isDay) {
			GlStateManager.translate(-50, 60, 120);
			GlStateManager.rotate(-50, 1, 0, 0);
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			Minecraft.getMinecraft().getTextureManager().bindTexture(SUN);
			GlStateManager.scale(0.5f, 0.5f, 0.5f);
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 70, 70, 70, 70);
			GlStateManager.translate(0, 80, 100);
			GlStateManager.rotate(25, 0, 1, 0);
			GlStateManager.scale(2, 2, 2);
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 70, 70, 70, 70);
		}
		GlStateManager.disableBlend();
		GlStateManager.enableCull();
		GlStateManager.enableDepth();
		GlStateManager.enableFog();
		GlStateManager.popMatrix();
		//===================Sun stop================
	}
	
	
}
