package net.tardis.mod.client.worldshell;

import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;

public class RenderWorldShell {
	
	public static void renderWorldShell(IContainsWorldShell cont, WorldClient world, double x, double y, double z) {
		GlStateManager.pushMatrix();
		BlockPos offset = cont.getWorldShell().getOffset();
		GlStateManager.translate(x - offset.getX(), y - offset.getY(), z - offset.getZ());
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		for(Entry<BlockPos, BlockStorage> entry : cont.getWorldShell().blockMap.entrySet()) {
			IBlockState state = entry.getValue().blockstate;
			IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
			if(state.getRenderType() == EnumBlockRenderType.MODEL && model != null) {
				bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
				Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(cont.getWorldShell(), model, state, entry.getKey(), bb, true);
				Tessellator.getInstance().draw();
			}
			else if(state.getRenderType() == EnumBlockRenderType.LIQUID) {
				bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
				Minecraft.getMinecraft().getBlockRendererDispatcher().fluidRenderer.renderFluid(world, state, entry.getKey(), bb);
				Tessellator.getInstance().draw();
			}
		}
		//Tile Entites
		GlStateManager.color(1F, 1, 1, 1);
		for(TileEntity entity : cont.getWorldShell().getTESRs()) {
			if(entity != null) {
				entity.setWorld(world);
				TileEntitySpecialRenderer render = TileEntityRendererDispatcher.instance.getRenderer(entity);
				if(render != null)
					render.render(entity, entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ(), 0, 0, 1);
			}
		}
		
		//Entities
		GlStateManager.color(1F, 1, 1, 1);
		for(Entity entity : cont.getWorldShell().getEntitiesForRender()) {
			Render render = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(entity);
			if(render != null) {
				GlStateManager.rotate(entity.rotationYaw, 0, 1, 0);
				render.doRender(entity, entity.posX, entity.posY, entity.posZ, entity.rotationYaw, 0);
			}
		}
		GlStateManager.popMatrix();
	}
}