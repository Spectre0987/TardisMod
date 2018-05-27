package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class MessageSyncTardisChunkData implements IMessage {
	
	private Chunk chunk;
	
	public MessageSyncTardisChunkData() {}
	
	public MessageSyncTardisChunkData(Chunk chunk) {
		this.chunk = chunk;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int dim = buf.readInt();
		if (dim == Minecraft.getMinecraft().world.provider.getDimension()) {
			chunk = Minecraft.getMinecraft().world.getChunkFromChunkCoords(buf.readInt(), buf.readInt());
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(chunk.getWorld().provider.getDimension());
		buf.writeInt(chunk.x);
		buf.writeInt(chunk.z);
	}
	
	public static class Handler implements IMessageHandler<MessageSyncTardisChunkData, IMessage> {
		
		@Override
		public IMessage onMessage(MessageSyncTardisChunkData message, MessageContext ctx) {
			if (message.chunk != null) {
				
			}
			return null;
		}
	}
}
