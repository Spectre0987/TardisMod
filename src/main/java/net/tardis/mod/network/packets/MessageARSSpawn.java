package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageARSSpawn implements IMessage{

	public BlockPos pos = BlockPos.ORIGIN;
	public ItemStack stack = ItemStack.EMPTY;
	
	public MessageARSSpawn() {}
	
	public MessageARSSpawn(BlockPos pos, ItemStack stack){
		this.pos = pos;
		this.stack = stack;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = BlockPos.fromLong(buf.readLong());
		this.stack = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public static class Handler implements IMessageHandler<MessageARSSpawn, IMessage>{

		@Override
		public IMessage onMessage(MessageARSSpawn message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					WorldServer ws = ctx.getServerHandler().player.getServerWorld();
					TileEntity te = ws.getTileEntity(message.pos);
					EntityPlayerMP player = ctx.getServerHandler().player;
					if(te instanceof TileEntityTardis) {
						TileEntityTardis tardis = (TileEntityTardis)te;
						if(tardis.getArtron() > 64) {
							tardis.setArtron(tardis.getArtron() - 64);
							InventoryHelper.spawnItemStack(ws, player.posX, player.posY, player.posZ, message.stack);
						}
					}
				}
			});
			return null;
		}
		
		
	}

}
