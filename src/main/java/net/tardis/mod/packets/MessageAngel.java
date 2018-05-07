package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageAngel implements IMessage {
	
	public boolean isSeen;
	public int id;
	
	public MessageAngel() {
		
	}
	
	public MessageAngel(Entity e, boolean b) {
		id = e.getEntityId();
		isSeen = b;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		isSeen = buf.readBoolean();
		id = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isSeen);
		buf.writeInt(id);
	}
	
}
