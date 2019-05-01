package net.tardis.mod.common.commands.subcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.common.commands.TardisCommand;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.util.common.helpers.FileHelper;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.TardisHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommandRemove extends CommandTemplate {

    public CommandRemove(TardisCommand parent, @Nullable String permission) {
        super(parent, permission);
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return super.getUsage(sender) + " <username>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return super.checkPermission(server, sender) || sender instanceof MinecraftServer;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) throw new CommandException(getUsage(sender));
        String toBeRemoved = args[0];
        Map<UUID, String> playersMap = FileHelper.getPlayersFromServerFile();

        if (playersMap.containsValue(toBeRemoved)) {
            UUID toBeRemovedID = Helper.getKeyByValue(playersMap, toBeRemoved);
            if (toBeRemovedID != null) {
                if (TardisHelper.hasTardis(toBeRemovedID)) {
                    server.getWorld(TDimensions.TARDIS_ID).setBlockToAir(TardisHelper.getTardis(toBeRemovedID));
                    TardisHelper.tardisOwners.remove(toBeRemovedID.toString());
                    sender.sendMessage(new TextComponentTranslation(TStrings.Commands.TARDIS_DELETED));
                } else {
                    sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
                }
            }
        } else {
            sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_PLAYER_FOUND));
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return getListOfStringsMatchingLastWord(args, FileHelper.getPlayersFromServerFile().values());
    }
}
