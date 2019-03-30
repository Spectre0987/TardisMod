package net.tardis.mod.client.worldshell;

import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
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
		GlStateManager.color(1F, 1, 1, 1f);
		RenderHelper.enableStandardItemLighting();
		for(Entry<BlockPos, BlockStorage> entry : cont.getWorldShell().blockMap.entrySet()) {
			IBlockState state = entry.getValue().blockstate;
			IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
			if(state.getRenderType() == EnumBlockRenderType.MODEL && model != null) {
				GlStateManager.pushMatrix();
				GlStateManager.translate(entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
				Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModelBrightness(model, state, (float)entry.getValue().light / 15, true);
				GlStateManager.popMatrix();
			}
			else if(state.getRenderType() == EnumBlockRenderType.LIQUID) {
				bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
				Minecraft.getMinecraft().getBlockRendererDispatcher().fluidRenderer.renderFluid(world, state, entry.getKey(), bb);
				Tessellator.getInstance().draw();
			}
		}
		//Tile Entites
		RenderHelper.enableStandardItemLighting();
		for(TileEntity entity : cont.getWorldShell().getTESRs()) {
			if(entity != null) {
				entity.setWorld(world);
				TileEntitySpecialRenderer render = TileEntityRendererDispatcher.instance.getRenderer(entity);
				if(render != null) {
					GlStateManager.pushMatrix();
					Minecraft.getMinecraft().entityRenderer.disableLightmap();
					float light = (float)cont.getWorldShell().blockMap.get(entity.getPos()).light / 15;
					GlStateManager.color(light, light, light);
					render.render(entity, entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ(), 0, 0, 1);
					Minecraft.getMinecraft().entityRenderer.enableLightmap();
					GlStateManager.popMatrix();
				}
			}
		}
		//Entities
		GlStateManager.color(1F, 1F, 1F, 1F);
		for(Entity e : cont.getWorldShell().getEntitiesForRender()) {
			GlStateManager.pushMatrix();
			BlockStorage stor = cont.getWorldShell().blockMap.get(e.getPosition().down());
			float light = stor != null ? ((float)stor.light / 15) : 1F;
			GlStateManager.color(light, light, light);
			Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(e).doRender(e, e.posX, e.posY, e.posZ, e.rotationYaw, 0);
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}
}