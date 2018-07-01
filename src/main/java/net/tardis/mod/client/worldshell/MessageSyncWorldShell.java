package net.tardis.mod.client.worldshell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.tardis.mod.config.TardisConfig;

public class MessageSyncWorldShell implements IMessage {
	//Sync the world shell from client to server
	
	public WorldShell worldShell;
	
	public MessageSyncWorldShell(WorldShell ws) {
		worldShell = ws;
	}
	
	public MessageSyncWorldShell() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int s = buf.readInt();
		worldShell = new WorldShell(BlockPos.fromLong(buf.readLong()));
		for (int i = 0; i < s; ++i) {
			BlockPos bp = BlockPos.fromLong(buf.readLong());
			BlockStorage bs = new BlockStorage();
			bs.fromBuf(buf);
			worldShell.blockMap.put(bp, bs);
		}
		worldShell.setTESRs();
		worldShell.updateRequired = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(worldShell.blockMap.size());
		buf.writeLong(worldShell.getOffset().toLong());
		for(Entry<BlockPos,BlockStorage> e:worldShell.blockMap.entrySet()) {
			buf.writeLong(e.getKey().toLong());
			e.getValue().toBuf(buf);
		}
	}

}
