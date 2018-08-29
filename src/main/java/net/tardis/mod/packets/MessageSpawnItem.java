package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSpawnItem implements IMessage{

	ItemStack stack;
	
	public MessageSpawnItem() {}
	
	public MessageSpawnItem(ItemStack s){
		stack = s;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		stack = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public static class Handler implements IMessageHandler<MessageSpawnItem, IMessage>{

		@Override
		public IMessage onMessage(MessageSpawnItem message, MessageContext ctx) {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                EntityPlayerMP player = ctx.getServerHandler().player;
                if (player != null) player.inventory.addItemStackToInventory(message.stack);
            });
			return null;
		}}

}
