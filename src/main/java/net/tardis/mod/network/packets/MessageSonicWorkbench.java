package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.items.ItemSonic;
import net.tardis.mod.common.tileentity.TileEntitySonicWorkbench;

public class MessageSonicWorkbench implements IMessage{

	public BlockPos pos = BlockPos.ORIGIN;
	public int index = 0;
	
	public MessageSonicWorkbench() {}
	
	public MessageSonicWorkbench(BlockPos pos, int index) {
		this.pos = pos;
		this.index = index;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = BlockPos.fromLong(buf.readLong());
		this.index = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeInt(index);
	}
	
	public static class Handler implements IMessageHandler<MessageSonicWorkbench, IMessage>{

		@Override
		public IMessage onMessage(MessageSonicWorkbench message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					TileEntitySonicWorkbench bench = (TileEntitySonicWorkbench) ctx.getServerHandler().player.world.getTileEntity(message.pos);
					if(bench != null && message.index < ItemSonic.SONICS.size()) {
						if(bench.getStackInSlot(0).getItem() instanceof ItemSonic) {
							ItemStack stack = ItemSonic.SONICS.get(message.index).copy();
							stack.deserializeNBT(bench.getStackInSlot(0).serializeNBT());
							bench.setInventorySlotContents(0, stack);
						}
					}
				}});
			return null;
		}
		
	}

}
