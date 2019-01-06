<<<<<<< HEAD
package net.tardis.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;

public class TardisTeleporter {

	public static Entity move(Entity entity, int dimension, BlockPos pos, EnumFacing facing) {
		return move(entity, dimension, pos.getX(), pos.getY(), pos.getZ(), facing);
	}

	public static Entity move(Entity entity, int dimension, Vec3d pos, EnumFacing facing) {
		return move(entity, dimension, pos.x, pos.y, pos.z, facing);
	}
	
	@Nullable
	public static Entity move(Entity entity, int dimension, double x, double y, double z, EnumFacing facing) {
		if (entity.world.isRemote || !entity.isNonBoss()) {
			return null;
		}

		if (entity instanceof EntityPlayerMP) {
			EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entity;
			if (entity.dimension != dimension) {
				FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new TTTeleport(x, y, z, facing));
			}
			entityPlayerMP.connection.setPlayerLocation(x, y, z, entity.rotationYaw, facing.getHorizontalIndex());
			return entityPlayerMP;
		}

		if (entity.dimension == dimension) {
			entity.setPosition(x, y, z);
			return entity;
		}

		return entity.changeDimension(dimension, new TTTeleport(x, y, z, facing));
	}
	
	
	private static final class TTTeleport implements ITeleporter {
		private final double x, y, z;
		private final EnumFacing facing;

		TTTeleport(double x, double y, double z, EnumFacing facing) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.facing = facing;
		}
		
		@Override
		public void placeEntity(World world, Entity entity, float yaw) {
			entity.setLocationAndAngles(x, y, z, yaw, entity.rotationPitch);
		}
	}
}
=======
package net.tardis.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TardisTeleporter extends Teleporter {
	
	public TardisTeleporter(WorldServer world) {
		super(world);
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {}

	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		return true;
	}

	@Override
	public boolean makePortal(Entity entityIn) {
		return true;
	}

	@Override
	public void removeStalePortalLocations(long worldTime) {}

	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		super.placeEntity(world, entity, yaw);
	}
}
>>>>>>> parent of 0a3d984f... Fixed entity teleporting, Added that dudes new sounds, fixed BOTI null crash
