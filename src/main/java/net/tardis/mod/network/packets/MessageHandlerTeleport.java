package net.tardis.mod.network.packets;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.sounds.TSounds;

public class MessageHandlerTeleport implements IMessageHandler<MessageTeleport, IMessage> {

	@Override
	public IMessage onMessage(MessageTeleport mes, MessageContext ctx) {
		ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
			WorldServer world = ctx.getServerHandler().player.getServerWorld();
			BlockPos pos = world.getTopSolidOrLiquidBlock(mes.pos);
			ctx.getServerHandler().player.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
			ctx.getServerHandler().player.world.playSound(null, ctx.getServerHandler().player.getPosition(), TSounds.vmSound, SoundCategory.AMBIENT, 1F, 1F);
		});
		return null;
	}

}
