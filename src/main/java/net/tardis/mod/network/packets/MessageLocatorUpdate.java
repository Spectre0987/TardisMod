package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.cap.ITardisTracker;

public class MessageLocatorUpdate implements IMessage {

	public NBTTagCompound data;
	
	public MessageLocatorUpdate() {}
	
	public MessageLocatorUpdate(NBTTagCompound tag){
		this.data = tag;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		data = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, data);
	}
	
	public static class Handler implements IMessageHandler<MessageLocatorUpdate, IMessage> {

		@Override
		public IMessage onMessage(MessageLocatorUpdate message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				for(ItemStack stack : new ItemStack[] {Minecraft.getMinecraft().player.getHeldItemMainhand(), Minecraft.getMinecraft().player.getHeldItemOffhand()}) {
					ITardisTracker tracker = stack.getCapability(ITardisTracker.TRACKER, EnumFacing.DOWN);
					if(tracker != null) {
						tracker.deserialize(message.data);
					}
				}
			});
			return null;
		}
		
	}

}
