package net.tardis.mod.client.worldshell;

import java.util.Objects;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RenderWorldShell {
	
	public RenderWorldShell() {}
	
	public void doRender(IContainsWorldShell worldShell, double x, double y, double z, float entityYaw, float partialTicks, @Nullable WorldBoti worldBoti) {
		//Moving this will crash
		if (worldBoti == null) {
			worldBoti = new WorldBoti(worldShell.getDimension(), Minecraft.getMinecraft().world, worldShell.getWorldShell());
		}
		
		worldBoti.setShell(worldShell.getWorldShell()); //Set the shell
		BlockPos offset = worldShell.getWorldShell().getOffset(); //Set the offset
		
		GlStateManager.pushMatrix();
		bindTexture(getBlockTextures());
		GlStateManager.translate(x - offset.getX(), y - offset.getY(), z - offset.getZ());
		
		//Render Blocks
		if (worldShell.getWorldShell().bufferstate == null || worldShell.getWorldShell().updateRequired) {
			for (BlockPos blockPos : worldShell.getWorldShell().blockMap.keySet()) {
				if (blockPos != null) {
					GlStateManager.color(1,1,1,1);
					renderBlock(worldBoti, blockPos, worldShell.getWorldShell().getBlockState(blockPos));
				}
			}
		}
		
		//Render Tiles
		if(worldShell.getWorldShell().getTESRs() != null && !worldShell.getWorldShell().getTESRs().isEmpty()) {
			for (TileEntity tileEntity : worldShell.getWorldShell().getTESRs()) {
				if (tileEntity != null) {
					tileEntity.setWorld(worldBoti);
					GlStateManager.color(1,1,1,1);
					renderTile(tileEntity, partialTicks);
				}
			}
		}
		
		
		if (worldShell.getWorldShell().getEntities() != null && !worldShell.getWorldShell().getEntities().isEmpty()) {
			for (NBTTagCompound stor : worldShell.getWorldShell().getEntities()) {
				Entity e = EntityList.createEntityFromNBT(stor, worldBoti);
				if (e != null) {
					GlStateManager.pushMatrix();
					GlStateManager.rotate(stor.getFloat("rot"), 0, 1, 0);
					Objects.requireNonNull(Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(e)).doRender(e, e.posX, e.posY, e.posZ, e.rotationYaw, 0);
					GlStateManager.popMatrix();
				}
			}
		}
		
		
		if (worldShell.getWorldShell().getPlayers() != null && !worldShell.getWorldShell().getPlayers().isEmpty()) {
			for (PlayerStorage stor : worldShell.getWorldShell().getPlayers()) {
				GlStateManager.pushMatrix();
				Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getConnection().getPlayerInfo(stor.profile.getId()).getLocationSkin());
				FakeClientPlayer player = new FakeClientPlayer(worldBoti, stor.profile);
				if (stor.tag != null) {
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
				GlStateManager.rotate(-player.rotationYaw, 0, 1, 0);
				Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(player).doRender(player, 0, 0, 0, player.rotationYawHead, 0);
				GlStateManager.popMatrix();
			}
		}
		
		GlStateManager.popMatrix();
		GlStateManager.resetColor();
	}
	
	//Render tile
	private void renderTile(TileEntity t, float partialTicks) {
		if(t == null) return;
		GlStateManager.pushMatrix();
		bindTexture(getBlockTextures());
		TileEntitySpecialRenderer<TileEntity> renderer = TileEntityRendererDispatcher.instance.getRenderer(t);
		if(renderer != null) {
			TileEntityRendererDispatcher.instance.render(t, t.getPos().getX(), t.getPos().getY(), t.getPos().getZ(), partialTicks);
		}
		GlStateManager.popMatrix();
	}
	
	private void bindTexture(ResourceLocation locationBlocksTexture) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(locationBlocksTexture);
	}
	
	
	protected ResourceLocation getBlockTextures() {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
	
	public void renderBlock(World world, BlockPos pos, IBlockState blockState) {
		GlStateManager.pushMatrix();
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexBuffer = tessellator.getBuffer();
		vertexBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
		if (blockState.getRenderType() != EnumBlockRenderType.INVISIBLE && blockState.getRenderType() != EnumBlockRenderType.LIQUID && !blockState.getBlock().hasTileEntity(blockState)) {
			GlStateManager.pushMatrix();
			IBakedModel bakedModel = blockRenderer.getBlockModelShapes().getModelForState(blockState);
			blockRenderer.getBlockModelRenderer().renderModel(world, bakedModel, blockState, pos, vertexBuffer, true);
			GlStateManager.popMatrix();
		}
		else if(blockState.getRenderType() == EnumBlockRenderType.LIQUID && !blockState.getBlock().hasTileEntity(blockState)) {
			GlStateManager.pushMatrix();
			Minecraft.getMinecraft().getBlockRendererDispatcher().fluidRenderer.renderFluid(world, blockState, pos, vertexBuffer);
			GlStateManager.popMatrix();
		}
		tessellator.draw();
		GlStateManager.popMatrix();
	}
}
