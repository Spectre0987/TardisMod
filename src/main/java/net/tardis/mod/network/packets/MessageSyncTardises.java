package net.tardis.mod.network.packets;

import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class MessageSyncTardises implements IMessage {

	public MessageSyncTardises() {}
	
	public Map<String, BlockPos> map = new HashMap<String, BlockPos>();
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int size = buf.readInt();
		for(int i = 0; i < size; ++i) {
			map.put(ByteBufUtils.readUTF8String(buf), BlockPos.fromLong(buf.readLong()));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(TardisHelper.tardisOwners.size());
		for(String key : TardisHelper.tardisOwners.keySet()) {
			ByteBufUtils.writeUTF8String(buf, key);
			buf.writeLong(TardisHelper.tardisOwners.get(key).toLong());
		}
	}
	
	public static class Handler implements IMessageHandler<MessageSyncTardises, IMessage>{

		@Override
		public IMessage onMessage(MessageSyncTardises message, MessageContext ctx) {
			//Back the FUCK off from my runnables.
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					TardisHelper.tardisOwners.clear();
					TardisHelper.tardisOwners.putAll(message.map);
				}
			});
			return null;
		}
		
	}

}
