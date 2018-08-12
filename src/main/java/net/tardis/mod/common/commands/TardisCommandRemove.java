package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.tardis.mod.util.helpers.TardisHelper;

public class TardisCommandRemove extends CommandBase {

	public TardisCommandRemove() {
		
	}
	
	@Override
	public String getName() {
		return "tardisremove";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "command.tardis.remove";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[0]);
		if(player != null && TardisHelper.hasTardis(player.getGameProfile().getId())) {
			TardisHelper.tardisOwners.remove(player.getGameProfile().getId().toString());
		}
	}

}
