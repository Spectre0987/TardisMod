package net.tardis.mod.common.entities.controls;

import net.minecraft.block.Block;
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
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.util.TardisTeleporter;
import net.tardis.mod.util.helpers.Helper;

public class ControlFlight extends EntityControl {
	
	public ControlFlight(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlFlight(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(10, -2, 6);
		}
		return Helper.convertToPixels(-4, -3, 12);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			if (!tardis.isInFlight()) {
				WorldServer ws = world.getMinecraftServer().getWorld(tardis.dimension);
				BlockPos wPos = tardis.getLocation();
				ws.setBlockToAir(tardis.getLocation());
				ws.setBlockToAir(tardis.getLocation().up());
				EntityTardis eT = new EntityTardis(ws, player);
				eT.setConsolePos(getConsolePos());
				eT.setPosition(wPos.getX(), wPos.getY() + 1, wPos.getZ());
				eT.setState(Block.getStateId(tardis.getTopBlock()));
				ws.spawnEntity(eT);
				player.setInvisible(true);
				ForgeChunkManager.unforceChunk(tardis.tardisLocTicket, world.getChunk(getConsolePos()).getPos());
				ws.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, tardis.dimension, new TardisTeleporter((WorldServer) world));
				player.setLocationAndAngles(wPos.getX(), wPos.getY(), wPos.getZ(), 0, 0);
				ws.addScheduledTask(() -> player.startRiding(eT, true));
			} else
				player.sendStatusMessage(new TextComponentTranslation(TStrings.TARDIS_IN_FLIGHT), true);
		}
	}
	
	@Override
	public String getControlName() {
		return "Real World - Flight Interface";
	}
	
}
