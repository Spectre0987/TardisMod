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
