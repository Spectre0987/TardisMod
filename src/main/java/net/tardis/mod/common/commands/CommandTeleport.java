package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.common.strings.TStrings;

public class CommandTeleport extends CommandBase {

    @Override
    public String getName() {
        return "tardistp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.tardis.teleportin.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
    	EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer(sender);

    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer && PermissionAPI.hasPermission((EntityPlayer) sender, TStrings.Permissions.TP_IN_TARDIS);
    }


}
