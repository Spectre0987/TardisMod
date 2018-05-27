package net.tardis.mod.packets;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.boti.FakeWorld;

/**
 * Created by Nictogen on 3/5/18.
 */
public class MessageChunkData implements IMessage {
	private int chunkX;
	private int chunkZ;
	private int availableSections;
	private byte[] buffer;
	private List<NBTTagCompound> tileEntityTags;
	private boolean fullChunk;
	private int dimensionID;
	
	public MessageChunkData() {}
	
	public MessageChunkData(Chunk chunkIn, int changedSectionFilter, int dimensionID) {
		this.chunkX = chunkIn.x;
		this.chunkZ = chunkIn.z;
		this.fullChunk = changedSectionFilter == 65535;
		boolean flag = chunkIn.getWorld().provider.hasSkyLight();
		this.buffer = new byte[this.calculateChunkSize(chunkIn, flag, changedSectionFilter)];
		this.availableSections = this.extractChunkData(new PacketBuffer(getWriteBuffer()), chunkIn, flag, changedSectionFilter);
		this.dimensionID = dimensionID;
		this.tileEntityTags = Lists.newArrayList();
		
		for (Map.Entry<BlockPos, TileEntity> entry : chunkIn.getTileEntityMap().entrySet()) {
			BlockPos blockpos = entry.getKey();
			TileEntity tileentity = entry.getValue();
			int i = blockpos.getY() >> 4;
			
			if (this.fullChunk || (changedSectionFilter & 1 << i) != 0) {
				NBTTagCompound nbttagcompound = tileentity.getUpdateTag();
				this.tileEntityTags.add(nbttagcompound);
			}
		}
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer packetBuffer = new PacketBuffer(buf);
		this.chunkX = packetBuffer.readInt();
		this.chunkZ = packetBuffer.readInt();
		this.fullChunk = packetBuffer.readBoolean();
		this.availableSections = packetBuffer.readVarInt();
		this.dimensionID = packetBuffer.readInt();
		int i = packetBuffer.readVarInt();
		
		if (i > 2097152) {
			throw new RuntimeException("Chunk Packet trying to allocate too much memory on read.");
		} else {
			this.buffer = new byte[i];
			packetBuffer.readBytes(this.buffer);
			int j = ByteBufUtils.readVarInt(buf, 5);
			this.tileEntityTags = Lists.newArrayList();
			
			for (int k = 0; k < j; ++k) {
				this.tileEntityTags.add(ByteBufUtils.readTag(buf));
			}
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer packetBuffer = new PacketBuffer(buf);
		packetBuffer.writeInt(this.chunkX);
		packetBuffer.writeInt(this.chunkZ);
		packetBuffer.writeBoolean(this.fullChunk);
		packetBuffer.writeVarInt(this.availableSections);
		packetBuffer.writeInt(this.dimensionID);
		packetBuffer.writeVarInt(this.buffer.length);
		packetBuffer.writeBytes(this.buffer);
		packetBuffer.writeVarInt(this.tileEntityTags.size());
		
		for (NBTTagCompound nbttagcompound : this.tileEntityTags) {
			packetBuffer.writeCompoundTag(nbttagcompound);
		}
	}
	
	private ByteBuf getWriteBuffer() {
		ByteBuf bytebuf = Unpooled.wrappedBuffer(this.buffer);
		bytebuf.writerIndex(0);
		return bytebuf;
	}
	
	public static class Handler implements IMessageHandler<MessageChunkData, IMessage> {
		
		@Override
		public IMessage onMessage(MessageChunkData message, MessageContext ctx) {
			
			Minecraft.getMinecraft().addScheduledTask(() -> {
				
				FakeWorld world = FakeWorld.getFakeWorld(message.dimensionID);
				
				if (message.fullChunk) {
					world.doPreChunk(message.chunkX, message.chunkZ, true);
				}
				
				world.invalidateBlockReceiveRegion(message.chunkX << 4, 0, message.chunkZ << 4, (message.chunkX << 4) + 15, 256, (message.chunkZ << 4) + 15);
				Chunk chunk = world.getChunkFromChunkCoords(message.chunkX, message.chunkZ);
				
				chunk.read(message.getReadBuffer(), message.availableSections, message.fullChunk);
				
				world.markBlockRangeForRenderUpdate(message.chunkX << 4, 0, message.chunkZ << 4, (message.chunkX << 4) + 15, 256, (message.chunkZ << 4) + 15);
				
				if (!message.fullChunk || world.provider.shouldClientCheckLighting()) {
					chunk.resetRelightChecks();
				}
				
				for (NBTTagCompound nbttagcompound : message.tileEntityTags) {
					BlockPos blockpos = new BlockPos(nbttagcompound.getInteger("x"), nbttagcompound.getInteger("y"), nbttagcompound.getInteger("z"));
					TileEntity tileentity = world.getTileEntity(blockpos);
					
					if (tileentity != null) {
						tileentity.handleUpdateTag(nbttagcompound);
					}
				}
				
			});
			return null;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public PacketBuffer getReadBuffer() {
		return new PacketBuffer(Unpooled.wrappedBuffer(this.buffer));
	}
	
	private int extractChunkData(PacketBuffer buf, Chunk chunkIn, boolean writeSkylight, int changedSectionFilter) {
		int i = 0;
		ExtendedBlockStorage[] aextendedblockstorage = chunkIn.getBlockStorageArray();
		int j = 0;
		
		for (int k = aextendedblockstorage.length; j < k; ++j) {
			ExtendedBlockStorage extendedblockstorage = aextendedblockstorage[j];
			
			if (extendedblockstorage != Chunk.NULL_BLOCK_STORAGE && (!this.fullChunk || !extendedblockstorage.isEmpty()) && (changedSectionFilter & 1 << j) != 0) {
				i |= 1 << j;
				extendedblockstorage.getData().write(buf);
				buf.writeBytes(extendedblockstorage.getBlockLight().getData());
				
				if (writeSkylight) {
					buf.writeBytes(extendedblockstorage.getSkyLight().getData());
				}
			}
		}
		
		if (this.fullChunk) {
			buf.writeBytes(chunkIn.getBiomeArray());
		}
		
		return i;
	}
	
	private int calculateChunkSize(Chunk chunkIn, boolean p_189556_2_, int p_189556_3_) {
		int i = 0;
		ExtendedBlockStorage[] aextendedblockstorage = chunkIn.getBlockStorageArray();
		int j = 0;
		
		for (int k = aextendedblockstorage.length; j < k; ++j) {
			ExtendedBlockStorage extendedblockstorage = aextendedblockstorage[j];
			
			if (extendedblockstorage != Chunk.NULL_BLOCK_STORAGE && (!this.fullChunk || !extendedblockstorage.isEmpty()) && (p_189556_3_ & 1 << j) != 0) {
				i = i + extendedblockstorage.getData().getSerializedSize();
				i = i + extendedblockstorage.getBlockLight().getData().length;
				
				if (p_189556_2_) {
					i += extendedblockstorage.getSkyLight().getData().length;
				}
			}
		}
		
		if (this.fullChunk) {
			i += chunkIn.getBiomeArray().length;
		}
		
		return i;
	}
}
