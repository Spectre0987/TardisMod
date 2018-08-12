package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.TardisHelper;

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
        if (TardisHelper.hasTardis(player.getUniqueID())){
            BlockPos pos = TardisHelper.getTardis(player.getUniqueID());
            player.dismountRidingEntity();
            ((TileEntityTardis)server.getWorld(TDimensions.TARDIS_ID).getTileEntity(pos)).enterTARDIS(player);
        }
        else {
            sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        if (sender instanceof EntityPlayer)
            return PermissionAPI.hasPermission((EntityPlayer) sender, TStrings.Permissions.TP_IN_TARDIS);
        return false;
    }


}
