package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.client.guis.GUICompanion.EnumAction;
import net.tardis.mod.common.entities.EntityCompanion;

public class MessageCompanion implements IMessage {

	public int id;
	public EnumAction action;
	
	public MessageCompanion() {}
	
	public MessageCompanion(int id, EnumAction act) {
		this.id = id;
		this.action = act;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.action = Enum.valueOf(EnumAction.class, ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		ByteBufUtils.writeUTF8String(buf, action.name());
	}

	public static class Handler implements IMessageHandler<MessageCompanion, IMessage>{
		@Override
		public IMessage onMessage(MessageCompanion message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					EntityCompanion comp = (EntityCompanion)ctx.getServerHandler().player.getServerWorld().getEntityByID(message.id);
					if(comp != null) {
						switch(message.action) {
						case FOLLOW : {
							comp.setSit(!comp.getSit());
						};
						}
					}
				}});
			return null;
		}
		
	}
}
