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
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.capability.ITardisCap;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.dimensions.WorldProviderTardis;
import net.tardis.mod.util.common.helpers.Helper;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * Created by Suffril
 * on 20/01/2019.
 */
public class MessagePlayFlySound implements IMessage {
	
	private String sound;
	private String playerUUID;
	
	public MessagePlayFlySound() {
	}
	
	public MessagePlayFlySound(SoundEvent sound, String playerUUID) {
		this.playerUUID = playerUUID;
		this.sound = sound.getRegistryName().toString();
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		playerUUID = ByteBufUtils.readUTF8String(buf);
		this.sound = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, playerUUID);
		ByteBufUtils.writeUTF8String(buf, sound);
	}
	
	public static class Handler implements IMessageHandler<MessagePlayFlySound, IMessage> {
		@Override
		public IMessage onMessage(MessagePlayFlySound message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				EntityPlayer player = Minecraft.getMinecraft().world.getPlayerEntityByUUID(UUID.fromString(message.playerUUID));
				if (player != null) {
					Helper.playSound(player, new ResourceLocation(message.sound), SoundCategory.PLAYERS, true, 1.0F, new Supplier<Boolean>() {
						@Override
						public Boolean get() {
							return player.world.provider instanceof WorldProviderTardis;
						}
					});
				}
			});
			return null;
		}
	}
}