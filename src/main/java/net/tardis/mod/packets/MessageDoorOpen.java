package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.tileentity.TileEntityDoor;

public class MessageDoorOpen implements IMessage {
	
	public BlockPos pos = BlockPos.ORIGIN;
	public boolean isOpen = false;
	
	public MessageDoorOpen() {}
	
	public MessageDoorOpen(BlockPos pos, boolean isopen) {
		this.pos = pos;
		this.isOpen = isopen;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = BlockPos.fromLong(buf.readLong());
		this.isOpen = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.pos.toLong());
		buf.writeBoolean(this.isOpen);
	}
	
	public static class Handler implements IMessageHandler<MessageDoorOpen, IMessage> {
		
		public Handler() {}
		
		@Override
		public IMessage onMessage(MessageDoorOpen mes, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
                Minecraft mc = Minecraft.getMinecraft();
                TileEntity te = mc.world.getTileEntity(mes.pos);
                if (te instanceof TileEntityDoor) {
                    ((TileEntityDoor) te).isLocked = mes.isOpen;
                }
            });
			return null;
		}
	}
	
}
