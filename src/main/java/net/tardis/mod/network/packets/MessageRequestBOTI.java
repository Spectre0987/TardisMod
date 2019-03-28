package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell.EnumType;
import net.tardis.mod.network.NetworkHandler;

public class MessageRequestBOTI implements IMessage{

	BlockPos pos = BlockPos.ORIGIN;
	EnumType type = EnumType.BLOCKS;
	int entityID = -1;
	
	public MessageRequestBOTI() {}
	
	public MessageRequestBOTI(BlockPos pos) {
		this.pos = pos;
	}
	
	public MessageRequestBOTI(int id) {
		this.entityID = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int type = buf.readInt();
		if(type == 1) {
			pos = BlockPos.fromLong(buf.readLong());
		}
		else this.entityID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		int type = entityID == -1 ? 1 : 0;
		buf.writeInt(type);
		if(type == 1) {
			buf.writeLong(pos.toLong());
		}
		else buf.writeInt(this.entityID);
	}
	
	public static class Handler implements IMessageHandler<MessageRequestBOTI, IMessage>{

		@Override
		public IMessage onMessage(MessageRequestBOTI message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					System.out.println(message.entityID);
					if(message.entityID != -1) {
						Entity entity = ctx.getServerHandler().player.getServerWorld().getEntityByID(message.entityID);
						if(entity != null && entity instanceof IContainsWorldShell) {
							IContainsWorldShell cont = (IContainsWorldShell)entity;
							NetworkHandler.NETWORK.sendTo(new MessageSyncWorldShell(cont.getWorldShell(), message.entityID, message.type), ctx.getServerHandler().player);
						}
					}
					else {
						TileEntity te = ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
						if(te != null && te instanceof IContainsWorldShell) {
							IContainsWorldShell shell = (IContainsWorldShell)te;
							NetworkHandler.NETWORK.sendTo(new MessageSyncWorldShell(shell.getWorldShell(), message.pos, message.type), ctx.getServerHandler().player);
						}
					}
				}
			});
			return null;
		}}

}
