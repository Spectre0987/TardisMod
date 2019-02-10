package net.tardis.mod.client.worldshell;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;

public class RenderWorldShell {

	public RenderWorldShell() {
	}

	public void doRender(IContainsWorldShell containsWorldShell, double x, double y, double z, float entityYaw, float partialTicks, @Nullable WorldBoti worldBoti) {
		//Moving this will crash
		final WorldShell worldShell = containsWorldShell.getWorldShell();
		if (worldBoti == null) {
			worldBoti = new WorldBoti(containsWorldShell.getDimension(), Minecraft.getMinecraft().world, worldShell);
		}

		worldBoti.setShell(worldShell); //Set the shell
		BlockPos offset = worldShell.getOffset(); //Set the offset

		GlStateManager.pushMatrix();
		{
			bindTexture(getBlockTextures());
			GlStateManager.translate(x - offset.getX(), y - offset.getY(), z - offset.getZ());

			//TODO: if entity rendering ever becomes multi-threaded get rid of the profiling
			final Profiler profiler = Minecraft.getMinecraft().profiler;
			profiler.startSection("rebuildWorldShellBlocks");
			//Render Blocks
			{
				if (worldShell.isBlockUpdateRequired()) {
					worldShell.blocksBuffer.reset();
					rebuildBlocks(worldShell.blockMap.keySet(), worldShell);
//			        worldShell.updateRequired = false;
				}

				profiler.endStartSection("renderWorldShellBlocks");
				renderBlocks(worldShell);
			}

			profiler.endStartSection("renderWorldShellTiles");
			//Render Tiles
			{
				if (worldShell.getTESRs() != null && !worldShell.getTESRs().isEmpty()) {
					for (TileEntity tileEntity : worldShell.getTESRs()) {
						if (tileEntity != null) {
							tileEntity.setWorld(worldBoti);
							GlStateManager.color(1, 1, 1, 1);
							renderTile(tileEntity, partialTicks);
						}
					}
				}
			}

			profiler.endStartSection("renderWorldShellEntities");
			//Entities
			{
				if (worldShell.getEntities() != null && !worldShell.getEntities().isEmpty()) {
					for (NBTTagCompound stor : worldShell.getEntities()) {
						Entity e = EntityList.createEntityFromNBT(stor, worldBoti);
						if (e != null) {
							GlStateManager.pushMatrix();
							GlStateManager.rotate(e.rotationYaw, 0, 1, 0);
							Objects.requireNonNull(Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(e)).doRender(e, e.posX, e.posY, e.posZ, e.rotationYaw, 0);
							GlStateManager.popMatrix();
						}
					}
				}
			}

			profiler.endStartSection("renderWorldShellPlayers");
			//Players
			{
				if (worldShell.getPlayers() != null && !worldShell.getPlayers().isEmpty()) {
					for (PlayerStorage stor : worldShell.getPlayers()) {
						GlStateManager.pushMatrix();
						Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getConnection().getPlayerInfo(stor.profile.getId()).getLocationSkin());
						FakeClientPlayer player = new FakeClientPlayer(worldBoti, stor.profile);
						if (stor.tag != null) {
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
					}
				}
			}
			profiler.endSection();
		}
		GlStateManager.popMatrix();
		GlStateManager.resetColor();
	}

	private void renderBlocks(final WorldShell worldShell) {

		//TODO: add compatibility with RenderChunk rebuildChunk Hooks (BetterFoliage and NoCubes rendering compatibility)
		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1, 1);

		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		final BufferBuilder blocksBuffer = worldShell.blocksBuffer;

		if (blocksBuffer.getVertexCount() > 0) {
			VertexFormat vertexformat = blocksBuffer.getVertexFormat();
			int i = vertexformat.getSize();
			ByteBuffer bytebuffer = blocksBuffer.getByteBuffer();
			List<VertexFormatElement> list = vertexformat.getElements();

			for (int j = 0; j < list.size(); ++j) {
				VertexFormatElement vertexformatelement = list.get(j);
				VertexFormatElement.EnumUsage vertexformatelement$enumusage = vertexformatelement.getUsage();
				int k = vertexformatelement.getType().getGlConstant();
				int l = vertexformatelement.getIndex();
				bytebuffer.position(vertexformat.getOffset(j));

				// moved to VertexFormatElement.preDraw
				vertexformatelement.getUsage().preDraw(vertexformat, j, i, bytebuffer);
			}

			GlStateManager.glDrawArrays(blocksBuffer.getDrawMode(), 0, blocksBuffer.getVertexCount());
			int i1 = 0;

			for (int j1 = list.size(); i1 < j1; ++i1) {
				VertexFormatElement vertexformatelement1 = list.get(i1);
				VertexFormatElement.EnumUsage vertexformatelement$enumusage1 = vertexformatelement1.getUsage();
				int k1 = vertexformatelement1.getIndex();

				// moved to VertexFormatElement.postDraw
				vertexformatelement1.getUsage().postDraw(vertexformat, i1, i, bytebuffer);
			}
		}

		GlStateManager.popMatrix();
		GlStateManager.color(1, 1, 1, 1);
	}

	//Render tile
	private void renderTile(TileEntity t, float partialTicks) {
		if (t == null) return;
		GlStateManager.pushMatrix();
		bindTexture(getBlockTextures());
		TileEntitySpecialRenderer<TileEntity> renderer = TileEntityRendererDispatcher.instance.getRenderer(t);
		if (renderer != null) {
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

	private void rebuildBlocks(final Iterable<BlockPos> blocks, final WorldShell worldShell) {
		BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		BufferBuilder bufferBuilder = worldShell.blocksBuffer;

		bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

		for (final BlockPos pos : blocks) {
			final IBlockState state = worldShell.getBlockState(pos);
			if (state.getRenderType() != EnumBlockRenderType.INVISIBLE) {
				for (BlockRenderLayer blockRenderLayer : BlockRenderLayer.values()) {
					if (!state.getBlock().canRenderInLayer(state, blockRenderLayer)) {
						continue;
					}
					ForgeHooksClient.setRenderLayer(blockRenderLayer);
					blockRendererDispatcher.renderBlock(state, pos, worldShell, bufferBuilder);
				}
				ForgeHooksClient.setRenderLayer(null);
			}
		}

		bufferBuilder.finishDrawing();

	}

}
