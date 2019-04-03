package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.tardis.mod.capability.CapabilityTardis;

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
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;
			if (CapabilityTardis.get(player).isInFlight()) {
				CapabilityTardis.get(player).setFlightState(CapabilityTardis.get(player).getFlightState().name().toLowerCase().contains("demat") ? CapabilityTardis.TardisFlightState.REMAT : CapabilityTardis.TardisFlightState.DEMAT);
			}
		}
	}
}
