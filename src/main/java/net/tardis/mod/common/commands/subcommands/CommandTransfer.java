package net.tardis.mod.common.commands.subcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.commands.TardisCommand;
import net.tardis.mod.util.common.helpers.TardisHelper;

import javax.annotation.Nullable;
import java.util.List;

public class CommandTransfer extends CommandTemplate {

    public CommandTransfer(TardisCommand parent, String permission) {
        super(parent, permission);
    }

    @Override
    public String getName() {
        return "transfer";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return super.getUsage(sender)+" <username>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayerMP && TardisHelper.hasTardis(sender.getCommandSenderEntity().getUniqueID());
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayerMP))
            throw new CommandException("You are not a player. You must run these commands in game.");

        if (args.length < 1)
            throw new CommandException(getUsage(sender));

        String newOwnerName = args[0];
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);

        EntityPlayer newOwner = server.getPlayerList().getPlayerByUsername(newOwnerName);
        if (newOwner != null && TardisHelper.hasTardis(player.getGameProfile().getId()) && !TardisHelper.hasTardis(newOwner.getGameProfile().getId())) {
            BlockPos pos = TardisHelper.getTardis(player.getGameProfile().getId());
            if (pos != null) {
                TardisHelper.tardisOwners.remove(player.getGameProfile().getId().toString());
                TardisHelper.tardisOwners.put(newOwner.getGameProfile().getId().toString(), pos.toImmutable());
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
    }
}
