package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageSB implements IMessage {
	
	public BlockPos pos;
	
	public MessageSB() {}
	
	public MessageSB(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = BlockPos.fromLong(buf.readLong());
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
	}
	
}
