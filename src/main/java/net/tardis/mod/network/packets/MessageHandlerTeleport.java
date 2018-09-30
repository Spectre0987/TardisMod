package net.tardis.mod.network.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerTeleport implements IMessageHandler<MessageTeleport, IMessage> {
	
	@Override
	public IMessage onMessage(MessageTeleport mes, MessageContext ctx) {
        ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
			WorldServer world = ctx.getServerHandler().player.getServerWorld();
            BlockPos pos = world.getTopSolidOrLiquidBlock(mes.pos);
            ((EntityPlayerMP)world.getEntityByID(mes.id)).connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
        });
		return null;
	}
	
}
