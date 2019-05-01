package net.tardis.mod.common.commands.subcommands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tardis.mod.common.commands.TardisCommand;
import net.tardis.mod.common.tileentity.TileEntityTardisCoral;

public class CommandGrow extends CommandTemplate {

    public CommandGrow(TardisCommand parent, String permission) {
        super(parent, permission);
    }

    @Override
    public String getName() {
        return "grow";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayerMP))
            throw new CommandException("You are not a player. You must run these commands in game.");
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);

        World world = player.getEntityWorld();
        RayTraceResult ray = world.rayTraceBlocks(player.getPositionVector().add(0, player.getEyeHeight(), 0), player.getPositionVector().add(player.getLookVec().scale(16D)));
        if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK) {
            TileEntity te = world.getTileEntity(ray.getBlockPos());
            if (te != null && te instanceof TileEntityTardisCoral) {
                ((TileEntityTardisCoral) te).grow();
            }
        }
    }
}
