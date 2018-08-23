package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.util.helpers.TardisHelper;

public class CommandTardisTransfer extends CommandBase {

	public CommandTardisTransfer() {
		
	}
	
	@Override
	public String getName() {
		return "tardistransfer";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "tardis.command.transfer";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length < 1)throw new CommandException("tardis.commmand.exception");
		EntityPlayerMP player = this.getCommandSenderAsPlayer(sender);
		EntityPlayer newOwner = server.getPlayerList().getPlayerByUsername(args[0]);
		if(newOwner != null && TardisHelper.hasTardis(player.getGameProfile().getId()) && !TardisHelper.hasTardis(newOwner.getGameProfile().getId())) {
			BlockPos pos = TardisHelper.getTardis(player.getGameProfile().getId());
			if(pos != null) {
				TardisHelper.tardisOwners.remove(player.getGameProfile().getId().toString());
				TardisHelper.tardisOwners.put(newOwner.getGameProfile().getId().toString(), pos.toImmutable());
			}
		}
	}

}
