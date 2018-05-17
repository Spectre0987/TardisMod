package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.TardisTeleporter;

public class ControlDoor extends EntityControl {
	
	public ControlDoor(TileEntityTardis tardis) {
		super(tardis);
		this.setSize(1F, 2F);
	}
	
	public ControlDoor(World world) {
		super(world);
		this.setSize(1F, 2F);
	}
	
	@Override
	public boolean canBePushed() {
		return true;
	}
	
	@Override
	public Vec3d getOffset() {
		return new Vec3d(0, -1, 6);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote && this.getConsolePos() != null) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			if (!tardis.isInFlight()) {
				if (!player.isSneaking()) {
					WorldServer ws = (WorldServer) world;
					BlockPos tp = tardis.getLocation().south();
					ws.getMinecraftServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) player, tardis.dimension, new TardisTeleporter(ws));
					player.setPositionAndUpdate(tp.getX() + 0.5, tp.getY(), tp.getZ() + 0.5);
					player.setSpawnPoint(tardis.getLocation().north(), true);
					player.setSpawnDimension(tardis.dimension);
				} else {
					WorldServer ws = ((WorldServer) world).getMinecraftServer().getWorld(tardis.dimension);
					((TileEntityDoor) ws.getTileEntity(tardis.getLocation().up())).toggleLockedNoKey(player);
				}
			}
			else {
				world.playSound(null, this.getPosition(), TSounds.door_locked, SoundCategory.BLOCKS, 1F, 1F);
			}
		}
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		if (!(entityIn instanceof EntityPlayer)) {
			if (!world.isRemote && getConsolePos() != null) {
				TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
				BlockPos pos = tardis.getLocation().north(3);
				entityIn.setPosition(pos.getX(), pos.getY(), pos.getZ());
				entityIn.changeDimension(tardis.dimension);
			}
		}
	}
}
