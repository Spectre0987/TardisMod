package net.tardis.mod.client.worldshell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncWorldShell implements IMessage {
	//Sync the world shell from client to server

	public WorldShell worldShell;
	public BlockPos tilePos = BlockPos.ORIGIN;
	public int id;
	
	public MessageSyncWorldShell(WorldShell ws, int id) {
		this.id = id;
		worldShell = ws;
	}
	
	public MessageSyncWorldShell(WorldShell ws, BlockPos pos) {
		this.tilePos = pos.toImmutable();
		this.worldShell = ws;
		this.id = -1;
	}
	
	public MessageSyncWorldShell() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.tilePos = BlockPos.fromLong(buf.readLong());
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
		
		List<NBTTagCompound> tagList = new ArrayList<>();
		int max = buf.readInt();
		for(int i = 0; i < max; ++i) {
			tagList.add(ByteBufUtils.readTag(buf));
		}
		worldShell.setEntities(tagList);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeLong(tilePos.toLong());
		buf.writeInt(worldShell.blockMap.size());
		buf.writeLong(worldShell.getOffset().toLong());
		for(Entry<BlockPos,BlockStorage> e:worldShell.blockMap.entrySet()) {
			buf.writeLong(e.getKey().toLong());
			e.getValue().toBuf(buf);
		}
		
		if(worldShell.getEntities() != null && worldShell.getEntities().size() > 0) {
			buf.writeInt(worldShell.getEntities().size());
			for(NBTTagCompound tag : worldShell.getEntities()) {
				ByteBufUtils.writeTag(buf, tag);
			}
		}
		else buf.writeInt(0);
	}
	
	public static class Handler implements IMessageHandler<MessageSyncWorldShell, IMessage> {

		public Handler() {}
		
		@Override
		public IMessage onMessage(MessageSyncWorldShell mes, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					 World world = Minecraft.getMinecraft().world;
		                if(mes.id == -1) {
		                    TileEntity te = world.getTileEntity(mes.tilePos);
		                    if(te != null && te instanceof IContainsWorldShell) {
		                        ((IContainsWorldShell)te).setWorldShell(mes.worldShell);
		                    }
		                }
		                else {
		                    Entity entity = world.getEntityByID(mes.id);
		                    if(entity != null && entity instanceof IContainsWorldShell){
		                        ((IContainsWorldShell)entity).setWorldShell(mes.worldShell);
		                    }
		                }
				}});
			return null;
		}

	}

}
