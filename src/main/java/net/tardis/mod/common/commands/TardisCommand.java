package net.tardis.mod.common.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.command.CommandTreeBase;
import net.tardis.mod.common.commands.subcommands.*;
import net.tardis.mod.common.strings.TStrings;

import java.text.MessageFormat;

public class TardisCommand extends CommandTreeBase {

    public TardisCommand(){
        addSubcommand(new CommandGrow(this, TStrings.Permissions.GROW));
        addSubcommand(new CommandTransfer(this, null));
        addSubcommand(new CommandTeleport(this, null));
        addSubcommand(new CommandRemove(this, TStrings.Permissions.REMOVE_TARDIS));
        addSubcommand(new CommandExterior(this, TStrings.Permissions.TP_OUT_TARDIS));
        addSubcommand(new CommandRestoresys(this, TStrings.Permissions.RESTORE_TARDIS));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getName() {
        return "tardis";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        StringBuilder usageString = new StringBuilder();
        usageString.append("/tardis ");
        for (ICommand subcommand : getSubCommands()) {
            usageString.append(MessageFormat.format("{0} | ", subcommand.getName()));
        }
        return usageString.toString();
    }
}
