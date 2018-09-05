package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageIInvSync implements IMessage {

	IInventory inv;
	int id;
	int size;
	
	public MessageIInvSync() {}

	public MessageIInvSync(int id, IInventory inv){
		this.id = id;
		this.inv = inv;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		size = buf.readInt();
		for(int i = 0; i < size; ++i) {
			inv.setInventorySlotContents(i, new ItemStack(ByteBufUtils.readTag(buf)));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(inv.getSizeInventory());
		for(int i = 0; i < inv.getSizeInventory(); ++i) {
			ByteBufUtils.writeTag(buf, inv.getStackInSlot(i).writeToNBT(new NBTTagCompound()));
		}
	}

	public static class Handler implements IMessageHandler<MessageIInvSync, IMessage>{

		@Override
		public IMessage onMessage(MessageIInvSync message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					WorldServer ws = ctx .getServerHandler().player.getServerWorld();
					Entity e = ws.getEntityByID(message.id);
					if(e != null && e instanceof IInventory) {
						for(int i = 0; i < message.size; ++i) {
							((IInventory)e).setInventorySlotContents(i, message.inv.getStackInSlot(i));
						}
					}
				}});
			return null;
		}
		
	}
}
