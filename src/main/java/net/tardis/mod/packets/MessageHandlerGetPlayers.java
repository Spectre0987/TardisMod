package net.tardis.mod.packets;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerGetPlayers implements IMessageHandler<MessageGetPlayers,IMessage>{

	public MessageHandlerGetPlayers() {}
	
	@Override
	public IMessage onMessage(MessageGetPlayers message, MessageContext ctx) {
		
		return null;
	}

}
