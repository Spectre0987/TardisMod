package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageCam implements IMessage {
	
	public int entityID;
	public int worldID;
	
	public MessageCam() {}
	
	public MessageCam(int id, int wId) {
		entityID = id;
		worldID = wId;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		entityID = buf.readInt();
		worldID = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(entityID);
		buf.writeInt(worldID);
	}
	
}
