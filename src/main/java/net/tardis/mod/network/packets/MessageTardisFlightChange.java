package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.enums.EnumFlightState;

public class MessageTardisFlightChange implements IMessage{

	public int id = 0;
	public EnumFlightState state = EnumFlightState.CLOSED;
	
	public MessageTardisFlightChange() {}
	
	public MessageTardisFlightChange(int id, EnumFlightState state) {
		this.id = id;
		this.state = state;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.state = EnumFlightState.values()[buf.readInt()];
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeInt(state.ordinal());
	}

	public static class Handler implements IMessageHandler<MessageTardisFlightChange, IMessage>{

		@Override
		public IMessage onMessage(MessageTardisFlightChange message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					Entity entity = ctx.getServerHandler().player.getServerWorld().getEntityByID(message.id);
					if(entity instanceof EntityTardis) {
						((EntityTardis)entity).setOpenState(message.state);
					}
				}
				
			});
			return null;
		}}
}
