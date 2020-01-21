package net.tardis.mod.network.packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.tileentity.TileEntityEgg;

public class MessageConfigSync implements IMessage{

	List<ItemStack> arsItems = new ArrayList<ItemStack>();
	
	public MessageConfigSync(List<ItemStack> list){
		this.arsItems = list;
		System.out.println(list);
	}
	public MessageConfigSync() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int index = buf.readInt();
		for(int i = 0; i < index; ++i) {
			arsItems.add(new ItemStack(ByteBufUtils.readTag(buf)));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(arsItems.size());
		for(ItemStack stack : arsItems) {
			ByteBufUtils.writeTag(buf, stack.serializeNBT());
		}
	}
	
	public static class Handler implements IMessageHandler<MessageConfigSync, IMessage>{

		@Override
		public IMessage onMessage(MessageConfigSync message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					TileEntityEgg.ITEMS.clear();
					TileEntityEgg.ITEMS.addAll(message.arsItems);
				}
			});
			return null;
		}}

}
