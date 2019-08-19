package net.tardis.mod.client.renderers.tiles;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityTractorBeam;

public class RenderTractorBeam extends TileEntitySpecialRenderer<TileEntityTractorBeam> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/tractor_beam.png");
	
	@Override
	public void render(TileEntityTractorBeam te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		
		GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		Minecraft.getMinecraft().entityRenderer.disableLightmap();
		this.bindTexture(TEXTURE);
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
		double maxY = te.getRenderBoundingBox().minY - te.getPos().getY();
		Color color = te.shouldRise() ? new Color(0x00d2ff): Color.RED;
		
		bb.pos(0, 0, 0).tex(0, 0).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F).endVertex();
		bb.pos(1, 0, 1).tex(1, 0).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F).endVertex();
		bb.pos(1, maxY, 1).tex(1, maxY).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F).endVertex();
		bb.pos(0, maxY, 0).tex(0, maxY).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F).endVertex();
		
		bb.pos(0, 0, 1).tex(0, 0).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F).endVertex();
		bb.pos(1, 0, 0).tex(1, 0).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F).endVertex();
		bb.pos(1, maxY, 0).tex(1, maxY).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F).endVertex();
		bb.pos(0, maxY, 1).tex(0, maxY).color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 1F).endVertex();
		
		Tessellator.getInstance().draw();
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.enableCull();
		GlStateManager.enableLighting();
		Minecraft.getMinecraft().entityRenderer.enableLightmap();
		GlStateManager.popMatrix();
	}

	@Override
	public boolean isGlobalRenderer(TileEntityTractorBeam te) {
		return true;
	}

	@Override
	protected void bindTexture(ResourceLocation location) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
	}
}
