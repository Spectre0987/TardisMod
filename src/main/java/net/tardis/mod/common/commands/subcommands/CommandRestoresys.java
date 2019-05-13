package net.tardis.mod.common.commands.subcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.common.commands.TardisCommand;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CommandRestoresys extends CommandTemplate {

    public CommandRestoresys(TardisCommand parent, @Nullable String permission) {
        super(parent, permission);
    }

    @Override
    public String getName() {
        return "restoresys";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return super.getUsage(sender)+ " <username> <system...s>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return super.checkPermission(server, sender) || sender instanceof MinecraftServer;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2)
            throw new CommandException(getUsage(sender));

        int j = 0;
        EntityPlayerMP player;
        if (Arrays.asList(server.getOnlinePlayerNames()).contains(args[j]))
            player = server.getPlayerList().getPlayerByUsername(args[j++]);
        else
            throw new CommandException(getUsage(sender));

        List<String> systemNames = new ArrayList<>();
        if (args[j].equals("*")){
            systemNames = TardisSystems.getSystemsnames();
        }
        else{
            for (int i = j; i < args.length; i++)
                systemNames.add(args[i]);
        }

        this.restoreSystem(player, player.getUniqueID(), systemNames.toArray(new String[]{}));
    }

    private void restoreSystem(EntityPlayerMP player, @Nullable UUID id, String... names) throws CommandException {
        List<TardisSystems.BaseSystem> systemBases = new ArrayList<>();
        UUID owner = (id == null ? player.getUniqueID() : id);

        for (String s : names) {
            TardisSystems.BaseSystem system = TardisSystems.createFromName(s);
            if (system != null)
                systemBases.add(system);
            else
                player.sendMessage(new TextComponentString(s + ": " + new TextComponentTranslation(TStrings.Commands.NO_SYSTEM).getFormattedText()));
        }

        if (TardisHelper.hasTardis(owner)) {
            TileEntityTardis tardis = (TileEntityTardis) player.getServerWorld().getMinecraftServer().getWorld(TDimensions.TARDIS_ID).getTileEntity(TardisHelper.getTardis(owner));
            if (tardis != null) {
                for (TardisSystems.BaseSystem system : systemBases) {
                    tardis.getSystem(system.getClass()).setHealth(1F);
                    player.sendStatusMessage(new TextComponentString(new TextComponentTranslation(system.getNameKey()).getFormattedText() + " : " + new TextComponentTranslation(TStrings.Commands.SYSTEM_RESTORED).getFormattedText()), false);
                }
            } else throw new CommandException(TStrings.Commands.NO_TARIDS_IN_WORLD);
        } else throw new CommandException(TStrings.Commands.NO_TARDIS_OWNED);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length <= 1)
            return Arrays.asList(server.getOnlinePlayerNames());

        List<String> systemNames = TardisSystems.getSystemsnames();
        systemNames.add("*");
        return getListOfStringsMatchingLastWord(args, systemNames);
    }
}
