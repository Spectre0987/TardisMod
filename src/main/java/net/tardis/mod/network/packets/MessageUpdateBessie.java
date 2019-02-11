package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.entities.vehicles.EntityBessie;

public class MessageUpdateBessie implements IMessage {

	public int bessieID;

	public MessageUpdateBessie() {
	}

	public MessageUpdateBessie(int bessieID) {
		this.bessieID = bessieID;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		bessieID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(bessieID);
	}


	public static class Handler implements IMessageHandler<MessageUpdateBessie, IMessage> {
		@Override
		public IMessage onMessage(MessageUpdateBessie message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
				World ws = ctx.getServerHandler().player.world;
				EntityBessie bessie = (EntityBessie) ws.getEntityByID(message.bessieID);
				bessie.playHorn();

			});
			return null;
		}

	}

}
