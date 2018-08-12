package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.util.helpers.TardisHelper;

import javax.annotation.Nullable;
import java.util.List;

public class TardisCommandRemove extends CommandBase {

	public TardisCommandRemove() {
		
	}
	
	@Override
	public String getName() {
		return "tardisremove";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "command.tardis.remove.usage";
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1)
			throw new WrongUsageException(this.getUsage(sender));

		EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[0]);
		if (player != null) {
			if (TardisHelper.hasTardis(player.getGameProfile().getId())) {
				TardisHelper.tardisOwners.remove(player.getGameProfile().getId().toString());
				sender.sendMessage(new TextComponentTranslation(TStrings.Commands.TARDIS_DELETED));
			}
			else {
				sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_TARDIS_OWNED));
			}
		}
		else {
			sender.sendMessage(new TextComponentTranslation(TStrings.Commands.NO_PLAYER_FOUND));
		}
	}



}
