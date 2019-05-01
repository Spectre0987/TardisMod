package net.tardis.mod.common.commands.subcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.server.permission.PermissionAPI;
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

public class CommandTeleport extends CommandTemplate {

    public CommandTeleport(TardisCommand parent, @Nullable String permission) {
        super(parent, permission);
    }

    @Override
    public String getName() {
        return "interior";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return super.getUsage(sender) + "| <username>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayerMP))
            throw new CommandException("You are not a player. You must run these commands in game.");
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);

        if (args.length >= 1 && PermissionAPI.hasPermission(player,TStrings.Permissions.TP_IN_TARDIS_OTHER)){
            handleTeleport(player,args[0]);
        }
        else if (PermissionAPI.hasPermission(player, TStrings.Permissions.TP_IN_TARDIS)){
            handleTeleport(player, null);
        }
        else
            throw new CommandException(getUsage(sender));
    }

    private void handleTeleport(EntityPlayerMP player, @Nullable String tardisOwner) {
        MinecraftServer server = player.getServer();
        UUID playerID;

        if (tardisOwner == null){
            playerID = player.getUniqueID();
        }
        else {
            Map<UUID, String> playersMap = FileHelper.getPlayersFromServerFile();
            playerID = Helper.getKeyByValue(playersMap, tardisOwner);
        }

        if (playerID != null) {
            if (TardisHelper.hasTardis(playerID)) {
                BlockPos pos = TardisHelper.getTardis(playerID);
                player.dismountRidingEntity();
                WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(TDimensions.TARDIS_ID);
                TileEntityTardis tileTardis = (TileEntityTardis) worldServer.getTileEntity(pos);
                if (tileTardis != null) {
                    tileTardis.enterTARDIS(player);
                } else {
                    player.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED + " but most likely a issue has arisen somewhere..."));
                }
            } else {
                player.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
            }
        }
        else{
            player.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_PLAYER_FOUND));
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return getListOfStringsMatchingLastWord(args, FileHelper.getPlayersFromServerFile().values());
    }
}
