package net.tardis.mod.client.worldshell;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
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
import net.tardis.mod.common.tileentity.interiors.TileEntityInteriorDoor;

public class RenderWorldShell {

	public RenderWorldShell(){}
	
	public void doRender(IContainsWorldShell entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		if (entity instanceof IContainsWorldShell) {
			WorldClient client = Minecraft.getMinecraft().world;
			
			Minecraft.getMinecraft().world = new WorldClientBOTI(client, entity.getWorldShell());
			
			BufferBuilder bb = Tessellator.getInstance().getBuffer();

			IContainsWorldShell container = (IContainsWorldShell) entity;
			
			GlStateManager.pushMatrix();
			if(entity instanceof TileEntityDoor || entity instanceof TileEntityInteriorDoor) GlStateManager.depthFunc(GL11.GL_ALWAYS);
			
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
						t.setWorld(Minecraft.getMinecraft().world);
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
						FakeClientPlayer player = new FakeClientPlayer(Minecraft.getMinecraft().world, stor.profile);
						if(stor.tag != null) {
							player.readFromNBT(stor.tag);
							player.setSneaking(stor.tag.getBoolean("sneak"));
							player.ticksExisted = stor.tag.getInteger("ageInTicks");
							player.limbSwing = stor.tag.getFloat("limbSwing");
							player.limbSwingAmount = stor.tag.getFloat("limbSwingAmount");
							player.swingProgress = stor.tag.getFloat("swingProgress");
							player.motionX = stor.tag.getDouble("motionX");
							player.motionY = stor.tag.getDouble("motionY");
							player.motionZ = stor.tag.getDouble("motionZ");
							player.rotationYaw = stor.tag.getFloat("rotationYaw");
							player.rotationYawHead = stor.tag.getFloat("rotationYawHead");
							player.isRiding = stor.tag.getBoolean("riding");
						}
						GlStateManager.translate(stor.posX, stor.posY, stor.posZ);
						GlStateManager.rotate(-player.rotationYawHead, 0, 1, 0);
						Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(player).doRender(player, 0, 0, 0, 0, 0);
						GlStateManager.popMatrix();
					} catch(Exception e) {}
				}
			}
			GlStateManager.popMatrix();
			Minecraft.getMinecraft().world = client;
		}
	}

	private void bindTexture(ResourceLocation locationBlocksTexture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(locationBlocksTexture);
	}

	
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
