package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.handlers.FlightModeHandler;

public class CommandDebug extends CommandBase {

	public CommandDebug() {}
	
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
			((EntityPlayer) sender).world.loadedTileEntityList.forEach(tileEntity -> {
				if (tileEntity instanceof TileEntityTardis && ((EntityPlayer) sender).getDistanceSq(tileEntity.getPos()) < 10) {
					FlightModeHandler.start((EntityPlayer) sender, (TileEntityTardis) tileEntity);
					return;
				}
				
				if (tileEntity instanceof TileEntityDoor && ((EntityPlayer) sender).getDistanceSq(tileEntity.getPos()) < 10) {
					TileEntityDoor door = (TileEntityDoor) tileEntity;
					TileEntity tile = DimensionManager.getWorld(TDimensions.TARDIS_ID).getTileEntity(door.getConsolePos());
					FlightModeHandler.stop((EntityPlayer) sender, (TileEntityTardis) tile);
					return;
				}
				
			});
		}
	}

}
