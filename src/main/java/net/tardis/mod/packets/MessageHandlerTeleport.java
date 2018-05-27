package net.tardis.mod.packets;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerTeleport implements IMessageHandler<MessageTeleport, IMessage> {
	
	@Override
	public IMessage onMessage(MessageTeleport mes, MessageContext ctx) {
		ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
			@Override
			public void run() {
				WorldServer world = ctx.getServerHandler().player.getServerWorld();
				BlockPos pos = world.getTopSolidOrLiquidBlock(mes.pos);
				ctx.getServerHandler().player.setLocationAndAngles(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 0, 0);
			}
		});
		return null;
	}
	
}
