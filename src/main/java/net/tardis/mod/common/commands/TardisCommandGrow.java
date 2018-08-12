package net.tardis.mod.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardisCoral;

public class TardisCommandGrow extends CommandBase{

	@Override
	public String getName() {
		return "tardisgrow";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.tardis.grow";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = this.getCommandSenderAsPlayer(sender);
		World world = sender.getEntityWorld();
		RayTraceResult ray = world.rayTraceBlocks(player.getPositionVector().addVector(0, player.getEyeHeight(), 0), player.getPositionVector().add(player.getLookVec().scale(16D)));
		if(ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
			TileEntity te = world.getTileEntity(ray.getBlockPos());
			if(te != null && te instanceof TileEntityTardisCoral) {
				((TileEntityTardisCoral)te).grow();
			}
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 1;
	}
	
}
