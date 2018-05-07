package net.tardis.mod.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerCam implements IMessageHandler<MessageCam, IMessage> {
	
	@Override
	public IMessage onMessage(MessageCam mes, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(new Runnable() {
			@Override
			public void run() {
				Minecraft mc = Minecraft.getMinecraft();
				System.out.println("World ID: " + mes.worldID);
				System.out.println("Entity ID: " + mes.entityID);
				WorldClient wc = new WorldClient(ctx.getClientHandler(), new WorldSettings(mc.world.getWorldInfo()), mes.worldID, mc.world.getDifficulty(), mc.mcProfiler);
				System.out.println("World: " + wc.toString());
				mc.loadWorld(wc);
				mc.renderGlobal.setWorldAndLoadRenderers(wc);
				// mc.setRenderViewEntity(wc.getEntityByID(mes.entityID));
			}
		});
		return null;
	}
	
}
