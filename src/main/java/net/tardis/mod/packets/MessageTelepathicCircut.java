package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageTelepathicCircut implements IMessage {

	public BlockPos pos = BlockPos.ORIGIN;
	public String name = "";
	
	public MessageTelepathicCircut() {
		
	}
	
	public MessageTelepathicCircut(BlockPos tardisPos, String name) {
		pos = tardisPos.toImmutable();
		this.name = name;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		name = ByteBufUtils.readUTF8String(buf);
		pos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, name);
		buf.writeLong(pos.toLong());
	}

	public static class Handler implements IMessageHandler<MessageTelepathicCircut, IMessage>{
		
		@Override
		public IMessage onMessage(MessageTelepathicCircut message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					MinecraftServer server = ctx.getServerHandler().player.getServer();
					WorldServer ws = DimensionManager.getWorld(TDimensions.id);
					EntityPlayer player = server.getPlayerList().getPlayerByUsername(message.name);
					if(player != null) {
						TileEntity te = ws.getTileEntity(message.pos);
						if(te != null && te instanceof TileEntityTardis) {
							TileEntityTardis tardis = (TileEntityTardis)ws.getTileEntity(message.pos);
							tardis.setDesination(player.getPosition(), player.dimension);
							tardis.startFlight();
						}
					}
				}});
			return null;
		}
		
	}
}
