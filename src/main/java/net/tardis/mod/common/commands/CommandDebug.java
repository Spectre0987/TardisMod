package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageReinitStencil;

public class CommandDebug extends CommandBase {
	
	public CommandDebug() {}
	
	@Override
	public String getName() {
		return "topt";
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return "";
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		if(sender instanceof EntityPlayerMP) {
			NetworkHandler.NETWORK.sendTo(new MessageReinitStencil(), (EntityPlayerMP)sender);
		}
	}
}
