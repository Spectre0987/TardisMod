package net.tardis.mod.client.models.exteriors;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;

public class ModelTardisWoodDoor extends ModelBase implements IExteriorModel {

	IBlockState topState = Blocks.SPRUCE_DOOR.getDefaultState().withProperty(BlockDoor.HALF, EnumDoorHalf.UPPER);
	IBlockState bottomState = Blocks.SPRUCE_DOOR.getDefaultState().withProperty(BlockDoor.HALF, EnumDoorHalf.LOWER);

	@Override
	public void renderClosed(float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, 0, 0);
		GlStateManager.rotate(0, 0, 0, 0);
		IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(topState);
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
		for (EnumFacing f : EnumFacing.values()) {
			for (BakedQuad quad : model.getQuads(topState, f, 0)) {
				bb.addVertexData(quad.getVertexData());
			}
		}
		for (BakedQuad quad : model.getQuads(topState, null, 0)) {
			bb.addVertexData(quad.getVertexData());
		}
		Tessellator.getInstance().draw();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
		GlStateManager.translate(0, -1, 0);
		IBakedModel bottomModel = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(bottomState);
		for (EnumFacing f : EnumFacing.VALUES) {
			for (BakedQuad quad : bottomModel.getQuads(bottomState, f, 0)) {
				bb.addVertexData(quad.getVertexData());
			}
		}
		for (BakedQuad quad : bottomModel.getQuads(bottomState, null, 0)) {
			bb.addVertexData(quad.getVertexData());
		}
		Tessellator.getInstance().draw();
		GlStateManager.popMatrix();
	}

	@Override
	public void renderOpen(float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(1, 0, 0.81);
		GlStateManager.rotate(-90, 0, 1, 0);
		this.renderClosed(scale);
		GlStateManager.popMatrix();
	}

}
