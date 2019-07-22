package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.entities.EntityTardis;

public class MessageTardisFlight implements IMessage{

	public boolean up = false;
	public int id = 0;
	
	public MessageTardisFlight() {}
	
	public MessageTardisFlight(int id, boolean up){
		this.id = id;
		this.up = up;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.up = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeBoolean(this.up);
	}
	
	public static class Handler implements IMessageHandler<MessageTardisFlight, IMessage>{

		@Override
		public IMessage onMessage(MessageTardisFlight message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					Entity entity = ctx.getServerHandler().player.getServerWorld().getEntityByID(message.id);
					if(entity instanceof EntityTardis) {
						entity.motionY += message.up ? 0.23 : -0.23;
					}
				}
				
			});
			return null;
		}
		
		
	}

}
