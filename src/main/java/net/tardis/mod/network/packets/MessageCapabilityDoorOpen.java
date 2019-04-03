package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.capability.ITardisCap;
import net.tardis.mod.common.sounds.TSounds;

public class MessageCapabilityDoorOpen implements IMessage {
	
	public MessageCapabilityDoorOpen() {
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
	
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
	
	}
	
	public static class Handler implements IMessageHandler<MessageCapabilityDoorOpen, IMessage> {
		
		@Override
		public IMessage onMessage(MessageCapabilityDoorOpen message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
				EntityPlayer pilot = ctx.getServerHandler().player;
				ITardisCap data = CapabilityTardis.get(pilot);
				if(data.isInFlight()) {
					data.setDoorsOpen(!data.isOpen());
					pilot.world.playSound(null, pilot.posX, pilot.posY, pilot.posZ, data.isOpen() ? TSounds.door_open : TSounds.door_closed, SoundCategory.PLAYERS, 1.0F, 1.0F);
					data.sync();
				}
			});
			return null;
		}
	}
}
