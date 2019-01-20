package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.SpaceTimeCoord;

public class MessageWaypointLoad implements IMessage {

	public BlockPos pos = BlockPos.ORIGIN;
	public boolean isDelete = false;

	public MessageWaypointLoad(BlockPos pos, boolean del) {
		this.pos = pos;
		this.isDelete = del;
	}

	public MessageWaypointLoad() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		this.isDelete = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeBoolean(isDelete);
	}

	public static class Handler implements IMessageHandler<MessageWaypointLoad, IMessage> {

		@Override
		public IMessage onMessage(MessageWaypointLoad message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					TileEntityTardis tardis = (TileEntityTardis) ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
					if (tardis != null) {
						if (message.isDelete)
							tardis.saveCoords.set(tardis.waypointIndex, SpaceTimeCoord.ORIGIN);
						else
							tardis.setSpaceTimeCoordnate(tardis.saveCoords.get(tardis.waypointIndex));
					}
				}
			});
			return null;
		}
	}

}
