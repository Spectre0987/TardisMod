package net.tardis.mod.packets;

import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.entities.EntityAngel;

public class MessageHelperAngel implements IMessageHandler<MessageAngel, IMessage> {
	
	public MessageHelperAngel() {}
	
	@Override
	public IMessage onMessage(MessageAngel mes, MessageContext ctx) {
		((WorldServer) ctx.getServerHandler().player.world).addScheduledTask(new Runnable() {
			@Override
			public void run() {
				WorldServer ws = (WorldServer) ctx.getServerHandler().player.world;
				Entity e = ws.getEntityByID(mes.id);
				if (e != null) {
					((EntityAngel) e).shouldMove = mes.isSeen;
				}
			}
		});
		return null;
	}
	
}
