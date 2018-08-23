package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.systems.TardisSystems.ISystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageDamageSystem implements IMessage{

	private BlockPos pos = BlockPos.ORIGIN;
	private String system = "";
	
	public MessageDamageSystem() {}
	
	public MessageDamageSystem(BlockPos pos, String id) {
		this.pos = pos;
		this.system = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		system = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		ByteBufUtils.writeUTF8String(buf, system);
	}
	
	public static class Helper implements IMessageHandler<MessageDamageSystem, IMessage>{
		@Override
		public IMessage onMessage(MessageDamageSystem message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
				WorldServer ws = ctx.getServerHandler().player.getServer().getWorld(TDimensions.TARDIS_ID);
				TileEntityTardis tardis = (TileEntityTardis) ws.getTileEntity(message.pos);
				if (tardis != null) {
					ISystem s = null;
					for (ISystem sys : tardis.systems) {
						if (TardisSystems.getIdBySystem(sys).equals(message.system)) {
							sys.setHealth(0.00F);
						}
					}
				}
			});
			return null;
		}
		
	}

}
