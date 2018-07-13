package net.tardis.mod.client.worldshell;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.tileentity.TileEntityDoor;

public class RenderWorldShell {

	public RenderWorldShell(){}
	
	public void doRender(IContainsWorldShell entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		if (entity instanceof IContainsWorldShell) {
			BufferBuilder bb = Tessellator.getInstance().getBuffer();

			IContainsWorldShell container = (IContainsWorldShell) entity;
			
			GlStateManager.pushMatrix();
			if(entity instanceof TileEntityDoor || entity instanceof ControlDoor) GlStateManager.depthFunc(GL11.GL_ALWAYS);
			
			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

			BlockPos offset = container.getWorldShell().getOffset();
			GlStateManager.translate(x - offset.getX(), y - offset.getY(), z - offset.getZ());
			
			if (container.getWorldShell().bufferstate == null || container.getWorldShell().updateRequired) {
				for (BlockPos bp : container.getWorldShell().blockMap.keySet()) {
					if (bp == null)
						continue;
					Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(container.getWorldShell().getBlockState(bp), bp, container.getWorldShell(), bb);
				}
				container.getWorldShell().bufferstate = bb.getVertexState();
			}
			else {
				bb.setVertexState(container.getWorldShell().bufferstate);
			}

			Tessellator.getInstance().draw();
			GlStateManager.depthFunc(GL11.GL_LEQUAL);
	        GlStateManager.enableNormalize();
	        GlStateManager.enableLighting();
			for (TileEntity t : container.getWorldShell().getTESRs()) {
				if (t != null) {
					TileEntityRendererDispatcher.instance.render(t, t.getPos().getX(), t.getPos().getY(),
							t.getPos().getZ(), partialTicks);
				}
			}
			GlStateManager.popMatrix();
		}
	}

	private void bindTexture(ResourceLocation locationBlocksTexture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(locationBlocksTexture);
	}

	
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
