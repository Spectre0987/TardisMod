package net.tardis.mod.common.dimensions.space;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;
import net.tardis.mod.Tardis;

public class RenderSpace extends IRenderHandler {

	float lastRot = 0;
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {}

}
