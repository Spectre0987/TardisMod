package net.tardis.mod.common.commands;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.TardisHelper;

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

        EntityPlayerMP player = playerExist(args[0], server);
        if (player != null){
            if (TardisHelper.hasTardis(player.getUniqueID())){
                BlockPos tardisbp = TardisHelper.getTardis(player.getUniqueID());
                TileEntity te = server.getWorld(TDimensions.TARDIS_ID).getTileEntity(tardisbp);
                if (te instanceof TileEntityTardis){
                    ((TileEntityTardis) te).setDesination(senderPlayer.getPosition().add(1,0,1),senderPlayer.dimension);
                    ((TileEntityTardis) te).startFlight();
                    sender.sendMessage(new TextComponentTranslation(TStrings.Commands.TARDIS_TRAVEL));
                }
            }
            else {
                sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
            }
        }
        else {
            sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_PLAYER_FOUND));
        }
    }

    private EntityPlayerMP playerExist(String name, MinecraftServer server){
        List<EntityPlayerMP> players = server.getPlayerList().getPlayers();
        for (EntityPlayerMP p : players){
            if (p.getName().equals(name)) return p;
        }
        return null;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        if (sender instanceof EntityPlayer)
            return PermissionAPI.hasPermission((EntityPlayer) sender, TStrings.Permissions.SUMMON_TARDIS);
        return false;
    }
}
