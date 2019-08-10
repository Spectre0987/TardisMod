package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageReinitStencil implements IMessage{

	public MessageReinitStencil() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {}

	@Override
	public void toBytes(ByteBuf buf) {}
	
	public static class Handler implements IMessageHandler<MessageReinitStencil, IMessage>{

		@Override
		public IMessage onMessage(MessageReinitStencil message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					if(!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
						Minecraft.getMinecraft().getFramebuffer().enableStencil();
					}
				}
			});
			return null;
		}}

}
