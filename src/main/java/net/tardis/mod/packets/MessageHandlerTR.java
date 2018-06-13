package net.tardis.mod.packets;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.recipes.TemporalRecipe;
import net.tardis.mod.common.tileentity.TileEntityTemporalLab;

public class MessageHandlerTR implements IMessageHandler<MessageTR, IMessage> {
	
	@Override
	public IMessage onMessage(MessageTR mes, MessageContext ctx) {
		ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
			@Override
			public void run() {
				TileEntity te = ctx.getServerHandler().player.getServerWorld().getTileEntity(mes.pos);
				if (te instanceof TileEntityTemporalLab) {
					((TileEntityTemporalLab) te).currentRecipe = TemporalRecipe.recipes.get(mes.id);
				}
			}
		});
		return null;
	}
	
}
