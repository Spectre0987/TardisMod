package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.tardis.mod.entities.EntityTardis;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;

public class ControlFlight extends EntityControl{

	public ControlFlight(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlFlight(World world) {
		super(world);
		this.setSize(0.0625F, 0.5F);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.normalizeVec3d(-22,0,-6);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis=(TileEntityTardis)world.getTileEntity(this.tardisPos);
			if(!tardis.isInFlight()) {
				WorldServer ws=((WorldServer)world).getMinecraftServer().getWorld(tardis.dimension);
				BlockPos wPos=tardis.getLocation();
				ws.setBlockToAir(tardis.getLocation());
				ws.setBlockToAir(tardis.getLocation().up());
				EntityTardis eT=new EntityTardis(ws,player);
				eT.setConsolePos(tardisPos);
				eT.setPosition(wPos.getX(), wPos.getY()+1, wPos.getZ());
				ws.spawnEntity(eT);
				player.setInvisible(true);
				player.setPosition(wPos.getX(), wPos.getY(), wPos.getZ());
				ForgeChunkManager.unforceChunk(tardis.tardisLocTicket, world.getChunkFromBlockCoords(tardisPos).getPos());
				ws.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP)player, tardis.dimension, new TardisTeleporter((WorldServer)world));
			}
			else player.sendMessage(new TextComponentTranslation("tardis.flying"));
		}
	}

}
