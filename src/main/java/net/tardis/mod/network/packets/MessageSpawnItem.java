package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
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
                if (player != null) {
                	EntityItem item = new EntityItem(player.world, player.posX, player.posY, player.posZ, message.stack);
                	player.world.spawnEntity(item);
                }
            });
			return null;
		}}

}
