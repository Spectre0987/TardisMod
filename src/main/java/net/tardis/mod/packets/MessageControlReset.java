package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageControlReset implements IMessage {

	public BlockPos consolePos = BlockPos.ORIGIN;
	
	public MessageControlReset() {}
	
	public MessageControlReset(BlockPos pos) {
		this.consolePos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.consolePos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.consolePos.toLong());
	}
	
	public static class Handler implements IMessageHandler<MessageControlReset, IMessage>{

		@Override
		public IMessage onMessage(MessageControlReset message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					WorldServer ws = DimensionManager.getWorld(TDimensions.id);
					TileEntity te = ws.getTileEntity(message.consolePos);
					if(te != null && te instanceof TileEntityTardis) {
						TileEntityTardis tardis = (TileEntityTardis)te;
						if(tardis.controls != null) {
							for(EntityControl c : tardis.controls) {
								c.setDead();
							}
							tardis.controls = null;
						}
						tardis.createControls();
					}
				}});
			return null;
		}
		
	}

}
