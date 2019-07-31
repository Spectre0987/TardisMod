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
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;

public class RenderWorldShell {
	
	private static VertexBuffer BOTI = null;
	static {
		((SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new IResourceManagerReloadListener() {

			@Override
			public void onResourceManagerReload(IResourceManager resourceManager) {
				BOTI = null;
			}
			
		});
	}
	
	public static void renderWorldShell(IContainsWorldShell cont, WorldClient world, double x, double y, double z) {
		//BOTI = null;

		GlStateManager.pushMatrix();
		BlockPos offset = cont.getWorldShell().getOffset();
		GlStateManager.translate(x - offset.getX(), y - offset.getY(), z - offset.getZ());
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.color(1F, 1F, 1F, 1F);
		RenderHelper.enableStandardItemLighting();
		if(BOTI == null) {
			BOTI = new VertexBuffer(DefaultVertexFormats.BLOCK);
			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
			for(Entry<BlockPos, BlockStorage> entry : cont.getWorldShell().blockMap.entrySet()) {
				IBlockState state = entry.getValue().blockstate;
				state = state.getActualState(world, entry.getKey());
				IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
				if(state.getRenderType() != EnumBlockRenderType.INVISIBLE && model != null) {
					GlStateManager.pushMatrix();
					int light = entry.getValue().light;
					if(light == 0)
						light = 4;
					
					Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer()
					.renderModelSmooth(world, model, state, entry.getKey(), bb, false, 0);
					//.renderModel(world, model, state, entry.getKey(), bb, true);

					GlStateManager.popMatrix();
				}
			}
			bb.finishDrawing();
			bb.reset();
			BOTI.bufferData(bb.getByteBuffer());
		}
		else {
			BOTI.bindBuffer();
			GlStateManager.glVertexPointer(3, GL11.GL_FLOAT, DefaultVertexFormats.BLOCK.getSize(), 0);
			GlStateManager.glEnableClientState(GL11.GL_VERTEX_ARRAY);
			GlStateManager.glEnableClientState(GL11.GL_NORMAL_ARRAY);
			//GlStateManager.glEnableClientState(GL11.GL_COLOR_ARRAY);
			GlStateManager.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
            BOTI.drawArrays(GL11.GL_QUADS);
            BOTI.unbindBuffer();
            GlStateManager.glDisableClientState(GL11.GL_VERTEX_ARRAY);
            GlStateManager.glDisableClientState(GL11.GL_COLOR_ARRAY);
            GlStateManager.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
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
					float light = (float)cont.getWorldShell().blockMap.get(entity.getPos()).light / 15F;
					GlStateManager.color(light, light, light);
					render.render(entity, entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ() - 1, 0, 0, 1);
					Minecraft.getMinecraft().entityRenderer.enableLightmap();
					GlStateManager.popMatrix();
				}
			}
		}
		//Entities
		GlStateManager.color(1F, 1F, 1F, 1F);
		for(Entity e : cont.getWorldShell().getEntitiesForRender()) {
			RenderHelper.enableStandardItemLighting();
			GlStateManager.pushMatrix();
			BlockStorage stor = cont.getWorldShell().blockMap.get(e.getPosition().down());
			float light = stor != null ? ((float)stor.light / 15F) : 1F;
			GlStateManager.color(light, light, light);
			Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(e).doRender(e, e.posX, e.posY, e.posZ, e.rotationYaw, 0);
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}
}