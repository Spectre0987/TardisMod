package net.tardis.mod.util.common.helpers;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	
	public static boolean isPlayerMoving(EntityPlayer player) {
		float motion = MathHelper.sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ);
		return ((double) motion >= 0.01D);
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
	
	
	public static void resetCapabilities(EntityPlayer player) {
		PlayerCapabilities capabilities = player.capabilities;
		if (player.isCreative()) {
			capabilities.allowFlying = true;
			capabilities.isCreativeMode = true;
			capabilities.disableDamage = true;
		} else if (player.isSpectator()) {
			capabilities.allowFlying = true;
			capabilities.isCreativeMode = false;
			capabilities.disableDamage = true;
			capabilities.isFlying = true;
		} else {
			capabilities.allowFlying = false;
			capabilities.isCreativeMode = false;
			capabilities.disableDamage = false;
			capabilities.isFlying = false;
		}
		
		capabilities.allowEdit = !player.isSpectator();
	}
	
}
