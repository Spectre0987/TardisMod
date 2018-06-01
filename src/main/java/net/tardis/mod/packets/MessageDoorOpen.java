package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
	
}
