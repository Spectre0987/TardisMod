package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.SpaceTimeCoord;

public class MessageWaypointSave implements IMessage {

	BlockPos pos = BlockPos.ORIGIN;
	SpaceTimeCoord coord = SpaceTimeCoord.ORIGIN;

	public MessageWaypointSave() {
	}

	public MessageWaypointSave(BlockPos pos, SpaceTimeCoord coord) {
		this.pos = pos.toImmutable();
		this.coord = coord;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.coord = SpaceTimeCoord.readFromNBT(ByteBufUtils.readTag(buf));
		this.pos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, coord.writeToNBT(new NBTTagCompound()));
		buf.writeLong(pos.toLong());
	}

	public static class Handler implements IMessageHandler<MessageWaypointSave, IMessage> {

		@Override
		public IMessage onMessage(MessageWaypointSave message, MessageContext ctx) {
			//Frill...
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					WorldServer world = ctx.getServerHandler().player.getServerWorld().getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
					TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(message.pos);
					if (tardis != null) {
						for (int i = 0; i < tardis.saveCoords.size(); ++i) {
							if (SpaceTimeCoord.ORIGIN.equals(tardis.saveCoords.get(i))) {
								tardis.saveCoords.set(i, message.coord);
								break;
							}
						}
					} else System.out.println("No TARDIS, " + message.pos);
				}
			});
			return null;
		}
	}
}
