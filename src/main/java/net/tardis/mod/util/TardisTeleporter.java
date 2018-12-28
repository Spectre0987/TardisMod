package net.tardis.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;

public class TardisTeleporter {
	
	public static Entity move(Entity entity, int dimension, BlockPos pos) {
		return move(entity, dimension, pos.getX(), pos.getY(), pos.getZ());
	}
	
	public static Entity move(Entity entity, int dimension, Vec3d pos) {
		return move(entity, dimension, pos.x, pos.y, pos.z);
	}
	
	@Nullable
	public static Entity move(Entity entity, int dimension, double x, double y, double z) {
		if (entity.world.isRemote || !entity.isNonBoss()) {
			return null;
		}
		if (entity.dimension == dimension) {
			if (entity instanceof EntityPlayerMP) {
				((EntityPlayerMP) entity).connection.setPlayerLocation(x, y, z, entity.rotationYaw, entity.rotationPitch);
			} else {
				entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
			}
			return entity;
		}
		return entity.changeDimension(dimension, new TTTeleport(x, y, z));
	}
	
	
	private static final class TTTeleport implements ITeleporter {
		private final double x, y, z;
		
		private TTTeleport(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public void placeEntity(World world, Entity entity, float yaw) {
			entity.setLocationAndAngles(x, y, z, yaw, entity.rotationPitch);
		}
	}
}
