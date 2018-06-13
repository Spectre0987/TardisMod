package net.tardis.mod.util.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class PlayerHelper {

    public static void sendMessage(EntityPlayer player, String message, boolean hotBar) {
        player.sendStatusMessage(new TextComponentString(message), hotBar);
    }

}
