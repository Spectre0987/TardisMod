package net.tardis.mod.common.commands.subcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tardis.mod.common.commands.TardisCommand;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.FileHelper;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.TardisHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommandExterior extends CommandTemplate {

    public CommandExterior(TardisCommand parent, @Nullable String permission) {
        super(parent, permission);
    }

    @Override
    public String getName() {
        return "exterior";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return super.getUsage(sender) + " <username>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayerMP))
            throw new CommandException("You are not a player. You must run these commands in game.");
        if (args.length < 1)
            throw new CommandException(getUsage(sender));

        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        String tardisOwner = args[0];
        Map<UUID, String> playersMap = FileHelper.getPlayersFromServerFile();
        UUID playerID = Helper.getKeyByValue(playersMap, tardisOwner);

        if (playerID != null) {
            if (TardisHelper.hasTardis(playerID)) {
                BlockPos pos = TardisHelper.getTardis(playerID);
                player.dismountRidingEntity();
                TileEntityTardis tileTardis = (TileEntityTardis) FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(TDimensions.TARDIS_ID).getTileEntity(pos);
                if (tileTardis != null) {
                    tileTardis.transferPlayer(player, true);
                } else {
                    sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED + " but most likely a issue has arisen somewhere..."));
                }
            } else {
                sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
            }
        }
        else{
            sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_PLAYER_FOUND));
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return getListOfStringsMatchingLastWord(args, FileHelper.getPlayersFromServerFile().values());
    }
}
