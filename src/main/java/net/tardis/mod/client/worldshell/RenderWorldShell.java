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
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.tileentity.TileEntityDoor;

public class RenderWorldShell {

	public RenderWorldShell(){}
	
	public void doRender(IContainsWorldShell entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		if (entity instanceof IContainsWorldShell) {
			BufferBuilder bb = Tessellator.getInstance().getBuffer();

			IContainsWorldShell container = (IContainsWorldShell) entity;
			
			GlStateManager.pushMatrix();
			if(entity instanceof TileEntityDoor) GlStateManager.depthFunc(GL11.GL_ALWAYS);
			
			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

			BlockPos offset = container.getWorldShell().getOffset();
			GlStateManager.translate(x - offset.getX(), y - offset.getY(), z - offset.getZ());
			
			if (container.getWorldShell().bufferstate == null || container.getWorldShell().updateRequired) {
				for (BlockPos bp : container.getWorldShell().blockMap.keySet()) {
					if (bp == null)
						continue;
					try {
						Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(container.getWorldShell().getBlockState(bp), bp, container.getWorldShell(), bb);
					}
					catch(Exception e) {}
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
					try {
						TileEntityRendererDispatcher.instance.render(t, t.getPos().getX(), t.getPos().getY(),t.getPos().getZ(), partialTicks);
					}
					catch(Exception e) {}
				}
			}
			if(container.getWorldShell().getEntities() != null) {
				for(NBTTagCompound stor : container.getWorldShell().getEntities()) {
					try {
						Entity e = EntityList.createEntityFromNBT(stor, Minecraft.getMinecraft().world);
						if(e != null)Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(e).doRender(e, e.posX, e.posY, e.posZ, e.rotationYaw, 0);
					}
					catch(Exception e) {}
				}
			}
			
			if(container.getWorldShell().getPlayers() != null) {
				for(PlayerStorage stor : container.getWorldShell().getPlayers()) {
					try {
						GlStateManager.pushMatrix();
						Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getConnection().getPlayerInfo(stor.profile.getId()).getLocationSkin());
						GlStateManager.translate(stor.posX, stor.posY, stor.posZ);
						//new ModelPlayer(0.0625F, false).render(null, 0, 0, 0, 0, 0, 0.0625F);
						GlStateManager.popMatrix();
					} catch(Exception e) {}
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
