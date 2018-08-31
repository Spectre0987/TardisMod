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
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class RenderWorldShell {

	WorldBoti worldBoti;
	
	public RenderWorldShell() {
		
	}
	
	public void doRender(IContainsWorldShell entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		//Moving this will crash
		if(worldBoti == null)worldBoti = new WorldBoti(0, Minecraft.getMinecraft().world, new WorldShell(BlockPos.ORIGIN));
		if (entity instanceof IContainsWorldShell) {
			BufferBuilder bb = Tessellator.getInstance().getBuffer();

            IContainsWorldShell container = entity;
			
			GlStateManager.pushMatrix();
			worldBoti.setShell(container.getWorldShell());
<<<<<<< HEAD
			GlStateManager.depthFunc(GL11.GL_ALWAYS);
=======
			//GlStateManager.depthFunc(GL11.GL_ALWAYS);
>>>>>>> parent of ac84b76... Worked on "The thing"
			
			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

			BlockPos offset = container.getWorldShell().getOffset();
			GlStateManager.translate(x - offset.getX(), y - offset.getY(), z - offset.getZ());
			
			try {
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
			}
			catch(Exception e) {}

			Tessellator.getInstance().draw();
			GlStateManager.depthFunc(GL11.GL_LEQUAL);
	        GlStateManager.enableNormalize();
	        GlStateManager.enableLighting();
			for (TileEntity t : container.getWorldShell().getTESRs()) {
				if (t != null) {
					try {
						t.setWorld(worldBoti);
						TileEntityRendererDispatcher.instance.render(t, t.getPos().getX(), t.getPos().getY(),t.getPos().getZ(), partialTicks);
					}
					catch(Exception e) {}
				}
			}
			try {
				if(container.getWorldShell().getEntities() != null) {
					for(NBTTagCompound stor : container.getWorldShell().getEntities()) {
						Entity e = EntityList.createEntityFromNBT(stor, worldBoti);
						if(e != null)Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(e).doRender(e, e.posX, e.posY, e.posZ, e.rotationYaw, 0);
					}
				}
			}
			catch(Exception e) {}
			
			if(container.getWorldShell().getPlayers() != null) {
				for(PlayerStorage stor : container.getWorldShell().getPlayers()) {
					try {
						GlStateManager.pushMatrix();
						Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getConnection().getPlayerInfo(stor.profile.getId()).getLocationSkin());
						FakeClientPlayer player = new FakeClientPlayer(worldBoti, stor.profile);
						if(stor.tag != null) {
							player.readFromNBT(stor.tag);
							player.setSneaking(stor.tag.getBoolean("sneak"));
							player.ticksExisted = stor.tag.getInteger("ageInTicks");
							player.limbSwing = stor.tag.getFloat("limbSwing");
							player.limbSwingAmount = stor.tag.getFloat("limbSwingAmount");
							player.rotationYaw = stor.tag.getFloat("rotationYaw");
							player.rotationYawHead = stor.tag.getFloat("rotationYawHead");
							player.swingingHand = EnumHand.MAIN_HAND;
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
		}
	}

	private void bindTexture(ResourceLocation locationBlocksTexture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(locationBlocksTexture);
	}

	
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

}
