package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.dimensions.WorldProviderTardis;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.util.common.helpers.Helper;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Created by Suffril
 * on 20/01/2019.
 */
public class MessageSetupFlight implements IMessage {
	
	private String playerUUID;
	
	public MessageSetupFlight() {
	}
	
	public MessageSetupFlight(String playerUUID) {
		this.playerUUID = playerUUID;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		playerUUID = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, playerUUID);
	}
	
	public static class Handler implements IMessageHandler<MessageSetupFlight, IMessage> {
		@Override
		public IMessage onMessage(MessageSetupFlight message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				EntityPlayer player = Minecraft.getMinecraft().world.getPlayerEntityByUUID(UUID.fromString(message.playerUUID));
				if (player != null) {
					
					//I will clean up this mess sometime
					Helper.playSound(player, TSounds.flyLoop.getRegistryName(), SoundCategory.PLAYERS, true, 1.0F, () -> player.world.provider instanceof WorldProviderTardis);
					Helper.playSound(player, TSounds.cloister_bell.getRegistryName(), SoundCategory.PLAYERS, true, 1.0F, () -> player.world.provider instanceof WorldProviderTardis);
					Helper.playSound(player, TSounds.tardis_no_fuel.getRegistryName(), SoundCategory.PLAYERS, true, 1.0F, () -> player.world.provider instanceof WorldProviderTardis);
					Helper.playSound(player, TSounds.takeoff.getRegistryName(), SoundCategory.PLAYERS, true, 1.0F, () -> player.world.provider instanceof WorldProviderTardis);
					Helper.playSound(player, TSounds.tardis_land.getRegistryName(), SoundCategory.PLAYERS, true, 1.0F, () -> player.world.provider instanceof WorldProviderTardis);
					
					if (Minecraft.getMinecraft().player == player) {
						Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
					}
				}
			});
			return null;
		}
	}
}