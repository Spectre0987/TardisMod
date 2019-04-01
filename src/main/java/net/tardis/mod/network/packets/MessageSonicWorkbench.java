package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.tileentity.TileEntitySonicWorkbench;

public class MessageSonicWorkbench implements IMessage{

	public BlockPos pos = BlockPos.ORIGIN;
	public ItemStack stack;
	
	public MessageSonicWorkbench() {}
	
	public MessageSonicWorkbench(BlockPos pos, ItemStack stack) {
		this.pos = pos;
		this.stack = stack;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = BlockPos.fromLong(buf.readLong());
		this.stack = new ItemStack(Item.getItemById(buf.readInt()));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeInt(Item.getIdFromItem(this.stack.getItem()));
	}
	
	public static class Handler implements IMessageHandler<MessageSonicWorkbench, IMessage>{

		@Override
		public IMessage onMessage(MessageSonicWorkbench message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					TileEntitySonicWorkbench bench = (TileEntitySonicWorkbench) ctx.getServerHandler().player.world.getTileEntity(message.pos);
					if(bench != null) {
						bench.setInventorySlotContents(0, message.stack);
					}
				}});
			return null;
		}
		
	}

}
