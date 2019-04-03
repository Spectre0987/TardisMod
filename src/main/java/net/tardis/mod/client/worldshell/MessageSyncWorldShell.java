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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncWorldShell implements IMessage {
	//Sync the world shell from client to server

	public WorldShell worldShell;
	public BlockPos tilePos = BlockPos.ORIGIN;
	public int id = -1;
	public EnumType type;

	public MessageSyncWorldShell(WorldShell ws, int id, EnumType type) {
		this.id = id;
		worldShell = ws;
		this.type = type;
	}

	public MessageSyncWorldShell(WorldShell ws, BlockPos pos, EnumType type) {
		this.tilePos = pos.toImmutable();
		this.worldShell = ws;
		this.id = -1;
		this.type = type;
	}

	public MessageSyncWorldShell() {}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.tilePos = BlockPos.fromLong(buf.readLong());
		this.worldShell = new WorldShell(BlockPos.fromLong(buf.readLong()));
		this.worldShell.setTime(buf.readLong());
		type = EnumType.values()[buf.readInt()];
		if(type == EnumType.BLOCKS) {
			int size = buf.readInt();
			for(int i = 0; i < size; ++i) {
				this.worldShell.blockMap.put(BlockPos.fromLong(buf.readLong()), new BlockStorage(buf));
			}
		}
		if(type == EnumType.ENTITITES) {
			int size = buf.readInt();
			List<NBTTagCompound> entities = new ArrayList<>();
			for(int i = 0; i < size; ++i) {
				entities.add(ByteBufUtils.readTag(buf));
			}
			this.worldShell.setEntities(entities);
		}
		if(type == EnumType.PLAYERS) {
			int size = buf.readInt();
			List<PlayerStorage> players = new ArrayList<>();
			for(int i = 0; i < size; ++i) {
				players.add(PlayerStorage.fromBytes(buf));
			}
			this.worldShell.setPlayers(players);
		}
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeLong(tilePos.toLong());
		buf.writeLong(worldShell.getOffset().toLong());
		buf.writeLong(this.worldShell.getTime());
		buf.writeInt(type.ordinal());
		if(type == EnumType.BLOCKS) {
			buf.writeInt(this.worldShell.blockMap.size());
			for(Entry<BlockPos, BlockStorage> stor : this.worldShell.blockMap.entrySet()) {
				buf.writeLong(stor.getKey().toLong());
				stor.getValue().toBuf(buf);
				
			}
		}
		if(type == EnumType.ENTITITES) {
			buf.writeInt(this.worldShell.getEntities().size());
			for(NBTTagCompound e : this.worldShell.getEntities()) {
				ByteBufUtils.writeTag(buf, e);
			}
		}
		if(type == EnumType.PLAYERS) {
			buf.writeInt(this.worldShell.getPlayers().size());
			for(PlayerStorage ps : this.worldShell.getPlayers()) {
				ps.toBytes(buf);
			}
		}
	}

	public static class Handler implements IMessageHandler<MessageSyncWorldShell, IMessage> {

		public Handler() {}

		@Override
		public IMessage onMessage(MessageSyncWorldShell mes, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					World world = Minecraft.getMinecraft().world;
					if (mes == null || world == null) return;
					if (mes.id == -1) {
						TileEntity te = world.getTileEntity(mes.tilePos);
						if (te != null && te instanceof IContainsWorldShell) {
							IContainsWorldShell cont = (IContainsWorldShell)te;
							if(!cont.getWorldShell().getOffset().equals(mes.worldShell.getOffset()))
								cont.setWorldShell(mes.worldShell);
							else{
								combineWorldShell(cont, mes.worldShell, mes.type);
							}
						}
					} else {
						Entity entity = world.getEntityByID(mes.id);
						if (entity != null && entity instanceof IContainsWorldShell) {
							IContainsWorldShell cont = (IContainsWorldShell) entity;
							if(!cont.getWorldShell().getOffset().equals(mes.worldShell.getOffset())){
								cont.setWorldShell(mes.worldShell);
							}
							else {
								combineWorldShell(cont, mes.worldShell, mes.type);
							}
						}
					}
				}
				
			});
			return null;
		}

		@SideOnly(Side.CLIENT)
		public static void combineWorldShell(IContainsWorldShell cont, WorldShell shell, EnumType type) {
			cont.getWorldShell().setTime(shell.getTime());
			if(type == EnumType.BLOCKS) {
				cont.getWorldShell().blockMap.putAll(shell.blockMap);
				cont.getWorldShell().setTESRs(cont.getRenderWorld() != null ? cont.getRenderWorld() : Minecraft.getMinecraft().world);
			}
			else if(type == EnumType.ENTITITES) {
				cont.getWorldShell().getEntitiesForRender().clear();
				cont.getWorldShell().setEntities(shell.getEntities());
				cont.getWorldShell().setEntities(cont.getRenderWorld() != null ? cont.getRenderWorld() : Minecraft.getMinecraft().world);
			}
		}

	}
	
	public static enum EnumType{
		BLOCKS,
		ENTITITES,
		PLAYERS
	}

}
