package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTR implements IMessage{

	public int id;
	public BlockPos pos;
	
	public MessageTR() {}
	
	public MessageTR(int id,BlockPos pos) {
		this.id=id;
		this.pos=pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		id=buf.readInt();
		pos=BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeLong(pos.toLong());
	}

}
