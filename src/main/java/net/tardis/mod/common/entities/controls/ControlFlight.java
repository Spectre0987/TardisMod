package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;

public class ControlFlight extends EntityControl {
	
	public ControlFlight(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlFlight(World world) {
		super(world);
		this.setSize(0.25F, 0.125F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-4, -3, 12);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			if (!tardis.isInFlight()) {
				WorldServer ws = ((WorldServer) world).getMinecraftServer().getWorld(tardis.dimension);
				BlockPos wPos = tardis.getLocation();
				ws.setBlockToAir(tardis.getLocation());
				ws.setBlockToAir(tardis.getLocation().up());
				EntityTardis eT = new EntityTardis(ws, player);
				eT.setConsolePos(getConsolePos());
				eT.setPosition(wPos.getX(), wPos.getY() + 1, wPos.getZ());
				ws.spawnEntity(eT);
				player.setInvisible(true);
				player.setPosition(wPos.getX(), wPos.getY(), wPos.getZ());
				ForgeChunkManager.unforceChunk(tardis.tardisLocTicket, world.getChunkFromBlockCoords(getConsolePos()).getPos());
				ws.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, tardis.dimension, new TardisTeleporter((WorldServer) world));
			} else
				player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_IN_FLIGHT), true);
		}
	}
	
}
