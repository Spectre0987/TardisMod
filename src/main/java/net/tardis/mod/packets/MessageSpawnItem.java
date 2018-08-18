package net.tardis.mod.packets;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSpawnItem implements IMessage{

	ItemStack stack;
	UUID id;
	
	public MessageSpawnItem() {}
	
	public MessageSpawnItem(UUID id, ItemStack s){
		stack = s;
		this.id = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		stack = ByteBufUtils.readItemStack(buf);
		id = UUID.fromString(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeItemStack(buf, stack);
		ByteBufUtils.writeUTF8String(buf, id.toString());
	}
	
	public static class Handler implements IMessageHandler<MessageSpawnItem, IMessage>{

		@Override
		public IMessage onMessage(MessageSpawnItem message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					EntityPlayer player = ctx.getServerHandler().player.getServer().getPlayerList().getPlayerByUUID(message.id);
					if(player != null) player.inventory.addItemStackToInventory(message.stack);
				}});
			return null;
		}}

}
