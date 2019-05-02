package net.tardis.mod.common.commands.subcommands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.common.commands.TardisCommand;
import net.tardis.mod.common.strings.TStrings;

import javax.annotation.Nullable;

public abstract class CommandTemplate extends CommandBase {
    private TardisCommand parent;
    private String permission;

    CommandTemplate(TardisCommand parent, @Nullable String permission) {
        this.parent = parent;
        this.permission = permission;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayerMP && PermissionAPI.hasPermission((EntityPlayerMP)sender.getCommandSenderEntity(), permission);
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/" + parent.getName() + " " + getName();
    }
}
