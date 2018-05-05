package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTeleport implements IMessage{

	public BlockPos pos;
	public int id;
	
	public MessageTeleport() {}
	
	public MessageTeleport(BlockPos pos,int id) {
		this.pos=pos;
		this.id=id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos=BlockPos.fromLong(buf.readLong());
		this.id=buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeInt(this.id);
	}

}
