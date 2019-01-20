package net.tardis.mod.network.packets;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.protocols.TardisProtocol;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageHandlerProtocol implements IMessageHandler<MessageProtocol, IMessage> {

	@Override
	public IMessage onMessage(MessageProtocol mes, MessageContext ctx) {
		ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
			WorldServer ws = ctx.getServerHandler().player.getServerWorld();
			TileEntity te = ws.getTileEntity(mes.consolePos);
			if (te instanceof TileEntityTardis) {
				TardisProtocol.getProtocolFromId(mes.id).onActivated(ws, (TileEntityTardis) te);
			}
		});
		return null;
	}

}
