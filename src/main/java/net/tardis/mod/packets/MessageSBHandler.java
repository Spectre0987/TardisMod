package net.tardis.mod.packets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.blocks.TBlocks;
import net.tardis.mod.tileentity.TileEntitySonicGun;

public class MessageSBHandler implements IMessageHandler<MessageSB,IMessage>{

	public MessageSBHandler() {}
	
	@Override
	public IMessage onMessage(MessageSB mes, MessageContext ctx) {
		ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
			@Override
			public void run() {
				WorldServer world=ctx.getServerHandler().player.getServerWorld();
				for(int x=-1;x<2;x++) {
					for(int y=-1;y<2;y++) {
						for(int z=-1;z<2;z++) {
							BlockPos pos=mes.pos.add(x, y, z);
							IBlockState oldState=world.getBlockState(pos);
							if(oldState.getBlockHardness(world, pos)>-1) {
								world.setBlockState(pos, TBlocks.sonic_block.getDefaultState());
								((TileEntitySonicGun)world.getTileEntity(pos)).state=oldState;
							}
						}
					}
				}
			}});
		return null;
	}

}
