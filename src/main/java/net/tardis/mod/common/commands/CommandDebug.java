package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandDebug extends CommandBase {

	public CommandDebug() {
	}

	@Override
	public String getName() {
		return "tdebug";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

	}

}
