package net.tardis.mod.util.common.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.sound.SoundInteriorHum;
import net.tardis.mod.common.tileentity.TileEntityTardis;

import java.util.function.Supplier;

public class PlayerHelper {
	
	public static void sendMessage(EntityPlayer player, String message, boolean hotBar) {
		if (!player.world.isRemote) {
			player.sendStatusMessage(new TextComponentTranslation(message), hotBar);
		}
	}
	
	public static void sendMessage(EntityPlayer player, TextComponentTranslation message, boolean hotBar) {
		if (!player.world.isRemote) {
			player.sendStatusMessage(message, hotBar);
		}
	}
	
	public static boolean isPlayerMoving(EntityPlayer player){
		float motion = MathHelper.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
		return  ((double)motion >= 0.01D);
	}
	
	/**
	 * This method is client side only.
	 *
	 * @param player - The player
	 * @return whether the player has alex arms
	 */
	@SideOnly(Side.CLIENT)
	public static boolean hasSmallArms(AbstractClientPlayer player) {
		return player.getSkinType().equals("slim");
	}
	
}
