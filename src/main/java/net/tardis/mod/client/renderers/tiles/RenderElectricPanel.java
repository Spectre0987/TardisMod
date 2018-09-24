package net.tardis.mod.client.renderers.tiles;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.tardis.mod.common.tileentity.TileEntityEPanel;

public class RenderElectricPanel extends TileEntitySpecialRenderer<TileEntityEPanel> {

	@Override
	public void render(TileEntityEPanel te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
		IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(Block.getStateById(te.getID()));
		for(BakedQuad q : model.getQuads(Block.getStateById(te.getID()), null, 0)) {
			bb.addVertexData(q.getVertexData());
		}
		for(EnumFacing f : EnumFacing.values()) {
			for(BakedQuad q : model.getQuads(Block.getStateById(te.getID()), f, 0)) {
				bb.addVertexData(q.getVertexData());
			}
		}
		Tessellator.getInstance().draw();
		GlStateManager.popMatrix();
	}

}
