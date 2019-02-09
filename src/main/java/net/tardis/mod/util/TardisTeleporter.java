package net.tardis.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ITeleporter;

public class TardisTeleporter implements ITeleporter {
	
	private final BlockPos targetPos;
	
	public TardisTeleporter(BlockPos targetPos) {
		this.targetPos = targetPos;
	}
	
	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		entity.moveToBlockPosAndAngles(targetPos, yaw, entity.rotationPitch);
	}
}
