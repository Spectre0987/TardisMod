package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.ars.ConsoleRoom;
import net.tardis.mod.common.entities.IShouldDie;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageChangeInterior implements IMessage{

	int index = -1;
	BlockPos pos = BlockPos.ORIGIN;
	
	public MessageChangeInterior() {}
	
	public MessageChangeInterior(int id, BlockPos pos) {
		this.index = id;
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.index = buf.readInt();
		this.pos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(index);
		buf.writeLong(this.pos.toLong());
		
	}
	
	public static class Handler implements IMessageHandler<MessageChangeInterior, IMessage>{

		@Override
		public IMessage onMessage(MessageChangeInterior message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable(){
				@Override
				public void run() {
					if(!ConsoleRoom.CONSOLE_ROOMS.isEmpty() && ConsoleRoom.CONSOLE_ROOMS.size() > message.index) {
						TileEntityTardis tardis = (TileEntityTardis) ctx.getServerHandler().player.world.getTileEntity(message.pos);
						IBlockState state = ctx.getServerHandler().player.world.getBlockState(message.pos);
						NBTTagCompound tag = tardis.writeToNBT(new NBTTagCompound());
						for(int x = -25; x < 25;++x) {
							for(int y = -25; y < 25;++y) {
								for(int z = -25; z < 25;++z) {
									BlockPos pos = new BlockPos(x, y, z).add(message.pos);
									if(!(ctx.getServerHandler().player.world.getTileEntity(pos) instanceof TileEntityTardis));
										ctx.getServerHandler().player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
								}
							}
						}
						for(Entity entity : ctx.getServerHandler().player.world.getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.grow(20).offset(message.pos))) {
							if(entity instanceof IShouldDie) {
								entity.setDead();
							}
						}
						ConsoleRoom.CONSOLE_ROOMS.get(message.index).generate(ctx.getServerHandler().player.getServerWorld(), message.pos);
						ctx.getServerHandler().player.getServerWorld().setBlockState(message.pos, state);
						TileEntity te = ctx.getServerHandler().player.getServerWorld().getTileEntity(message.pos);
						if(te != null && te instanceof TileEntityTardis)
							((TileEntityTardis)te).readFromNBT(tag);
					}
				}
			});
			return null;
		}
		
	}

}
