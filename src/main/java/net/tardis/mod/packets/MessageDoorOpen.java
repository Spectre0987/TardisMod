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
	public boolean isRemat;
	public boolean isDemat;
	public float alpha;
	
	public MessageDoorOpen() {}
	
	public MessageDoorOpen(BlockPos pos, TileEntityDoor door) {
		this.pos = pos;
		this.isOpen = !door.isLocked;
		this.isRemat = door.isRemat;
		this.isDemat = door.isDemat;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = BlockPos.fromLong(buf.readLong());
		this.isOpen = buf.readBoolean();
		this.isDemat = buf.readBoolean();
		this.isRemat = buf.readBoolean();
		this.alpha = buf.readFloat();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.pos.toLong());
		buf.writeBoolean(this.isOpen);
		buf.writeBoolean(this.isDemat);
		buf.writeBoolean(this.isRemat);
		buf.writeFloat(this.alpha);
	}
	
	public static class Handler implements IMessageHandler<MessageDoorOpen, IMessage> {
		
		public Handler() {}
		
		@Override
		public IMessage onMessage(MessageDoorOpen mes, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
                Minecraft mc = Minecraft.getMinecraft();
                TileEntity te = mc.world.getTileEntity(mes.pos);
                if (te instanceof TileEntityDoor) {
                    TileEntityDoor door = ((TileEntityDoor) te);
                    door.isLocked = !mes.isOpen;
                    door.isDemat = mes.isDemat;
                    door.isRemat = mes.isRemat;
                }
            });
			return null;
		}
	}
	
}
