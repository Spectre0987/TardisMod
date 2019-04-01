package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageConsoleChange implements IMessage{

	BlockPos pos;
	int stateID;
	
	public MessageConsoleChange() {}
	
	public MessageConsoleChange(BlockPos pos, int ID) {
		this.pos = pos;
		this.stateID = ID;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = BlockPos.fromLong(buf.readLong());
		this.stateID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.pos.toLong());
		buf.writeInt(stateID);
	}
	
	public static class Handler implements IMessageHandler<MessageConsoleChange, IMessage>{

		@Override
		public IMessage onMessage(MessageConsoleChange message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					WorldServer world = ctx.getServerHandler().player.getServerWorld();
					TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(message.pos);
					if(tardis != null) {
						NBTTagCompound tag = tardis.writeToNBT(new NBTTagCompound());
						world.setBlockState(message.pos, Block.getStateById(message.stateID));
						((TileEntityTardis)world.getTileEntity(message.pos)).readFromNBT(tag);
					}
				}
			});
			return null;
		}
		
	}

}
