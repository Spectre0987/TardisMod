package net.tardis.mod.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.tileentity.TileEntityDoor;

public class MessageHandlerDoorOpen implements IMessageHandler<MessageDoorOpen, IMessage> {
	
	public MessageHandlerDoorOpen() {}
	
	@Override
	public IMessage onMessage(MessageDoorOpen mes, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(new Runnable() {
			@Override
			public void run() {
				Minecraft mc = Minecraft.getMinecraft();
				TileEntity te = mc.world.getTileEntity(mes.pos);
				if (te != null && te instanceof TileEntityDoor) {
					((TileEntityDoor) te).isLocked = mes.isOpen;
				}
			}
		});
		return null;
	}
	
}
