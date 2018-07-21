package net.tardis.mod.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.blocks.BlockTileBase;
import net.tardis.mod.common.entities.EntityTardis;

public class RenderTardis extends Render<EntityTardis> {
	
	Minecraft mc;
	WorldShell shell;
	public RenderTardis() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
		shell = new WorldShell(BlockPos.ORIGIN);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityTardis entity) {
		return null;
	}
	
	@Override
	public void doRender(EntityTardis entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		//GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(entity.renderRotation, 0, 1, 0);
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		IBlockState state = Block.getStateById(entity.getState());
		Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(state, BlockPos.ORIGIN, shell, bb);
		Tessellator.getInstance().draw();
		if(state.getBlock() instanceof BlockTileBase) {
			TileEntityRendererDispatcher.instance.render(((BlockTileBase)state.getBlock()).createTileEntity(mc.world, state), -0.5, 0, -0.5, partialTicks);
		}
		GlStateManager.popMatrix();
	}
	
}
