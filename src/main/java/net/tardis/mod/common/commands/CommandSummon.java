package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.common.strings.TStrings;

import javax.annotation.Nullable;
import java.util.List;

public class CommandSummon extends CommandBase {

    @Override
    public String getName() {
        return "summontardis";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.tardis.summon.usage";
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP senderPlayer = CommandBase.getCommandSenderAsPlayer(sender);
        if (args.length < 1)
            throw new WrongUsageException(this.getUsage(sender));

    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer && PermissionAPI.hasPermission((EntityPlayer) sender, TStrings.Permissions.SUMMON_TARDIS);
    }
}
