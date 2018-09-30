package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageProtocol implements IMessage {
	
	public int id;
	public BlockPos consolePos;
	
	public MessageProtocol() {}
	
	public MessageProtocol(BlockPos pos, int id) {
		this.id = id;
		this.consolePos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		consolePos = BlockPos.fromLong(buf.readLong()).toImmutable();
		id = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(consolePos.toLong());
		buf.writeInt(id);
	}
	
}
