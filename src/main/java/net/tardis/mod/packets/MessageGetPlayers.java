package net.tardis.mod.packets;

import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageGetPlayers implements IMessage {

	List<EntityPlayerMP> players;
	
	public MessageGetPlayers() {}
	
	public MessageGetPlayers(List<EntityPlayerMP> players) {
		this.players = players;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

}
