package net.tardis.mod.client.models;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.google.gson.stream.JsonReader;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.client.models.exteriors.IExteriorModel;

public class ModelBlocks implements IExteriorModel{
	
	public ResourceLocation loc;
	private HashMap<BlockPos, IBlockState> blocks = new HashMap<>();
	public BlockPos size = BlockPos.ORIGIN;
	public BlockPos offset = BlockPos.ORIGIN;
	public Vec3d rotation = new Vec3d(0, 0, 0);
	
	public ModelBlocks(ResourceLocation loc) {
		this.loc = loc;
		((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(new IResourceManagerReloadListener() {
			@Override
			public void onResourceManagerReload(IResourceManager resourceManager) {
				read();
			}});
	}

	public void render() {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.translate(offset.getX(), offset.getY(), offset.getZ());
		GlStateManager.rotate((float)rotation.x, 1, 0, 0);
		GlStateManager.rotate((float)rotation.y, 0, 1, 0);
		GlStateManager.rotate((float)rotation.z, 0, 0, 1);
		for(BlockPos pos : this.blocks.keySet()) {
			GlStateManager.pushMatrix();
			BufferBuilder bb = Tessellator.getInstance().getBuffer();
			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
			Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(blocks.get(pos), pos, Minecraft.getMinecraft().world, bb);
			Tessellator.getInstance().draw();
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
		
	}

	public void read() {
		try {
			InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream();
			JsonReader jr = new JsonReader(new InputStreamReader(is));
			
			jr.beginObject();
			while(jr.hasNext()) {
				String nameS = jr.nextName();
				if(nameS.equals("structure")) {
					jr.beginObject();
					while(jr.hasNext()) {
						jr.nextName();
						jr.beginObject();
						int x = 0, y = 0, z = 0, meta = 0;
						Block block = null;
						while(jr.hasNext()) {
							String name = jr.nextName();
							if(name.equals("X")) {
								x = jr.nextInt();
							}
							if(name.equals("Y")) {
								y = jr.nextInt();
							}
							if(name.equals("Z")) {
								z = jr.nextInt();
							}
							if(name.equals("Block")) {
								block = Block.getBlockFromName(jr.nextString());
							}
							if(name.equals("Meta")) {
								meta = jr.nextInt();
							}
							
						}
						blocks.put(new BlockPos(x, y, z), block.getStateFromMeta(meta));
						jr.endObject();
					}
					jr.endObject();
				}
				else if(nameS.equals("data")) {
					jr.beginObject();
					int x = 0, y = 0, z = 0;
					int sx = 0, sy = 0, sz = 0;
					double rotX = 0, rotY = 0, rotZ = 0;
					while(jr.hasNext()) {
						String name = jr.nextName();
						if(name.equals("position")) {
							jr.beginArray();
							x = jr.nextInt();
							y = jr.nextInt();
							z = jr.nextInt();
							jr.endArray();
						}
						if(name.equals("size")) {
							jr.beginArray();
							sx = jr.nextInt();
							sy = jr.nextInt();
							sz = jr.nextInt();
							jr.endArray();
						}
						if(name.equals("rotation")) {
							jr.beginArray();
							rotX = jr.nextDouble();
							rotY = jr.nextDouble();
							rotZ = jr.nextDouble();
							jr.endArray();
						}
					}
					this.rotation = new Vec3d(rotX, rotY, rotZ);
					this.size = new BlockPos(sx, sy, sz);
					this.offset = new BlockPos(x, y, z);
					jr.endObject();
				}
			}
			jr.endObject();
			jr.close();
		}
		catch(IOException e) {}
	}
	
	public ResourceLocation getKey() {
		return this.loc;
	}
	
	@Override
	public void renderClosed(float scale) {
		this.render();
	}

	@Override
	public void renderOpen(float scale) {
		this.render();
	}
}
