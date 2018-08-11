package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.util.helpers.TardisHelper;

public class CommandTeleport extends CommandBase {

    @Override
    public String getName() {
        return "tpintardis";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.tardis.teleportin";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
    EntityPlayerMP player = CommandBase.getCommandSenderAsPlayer(sender);
        if (TardisHelper.hasTardis(player.getUniqueID())){
            BlockPos pos = TardisHelper.getTardis(player.getUniqueID());
            player.connection.setPlayerLocation(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, Helper.get360FromFacing(EnumFacing.EAST), 0);
            server.getPlayerList().transferPlayerToDimension(player, TDimensions.TARDIS_ID,new TardisTeleporter());
        }
        else {
            sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
        }
    }
}
