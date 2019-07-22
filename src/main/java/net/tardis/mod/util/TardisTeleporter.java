package net.tardis.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

public class TardisTeleporter implements ITeleporter {
	
	private BlockPos targetPos= null;
	
	public TardisTeleporter(BlockPos targetPos) {
		this.targetPos = targetPos;
	}
	public TardisTeleporter() {}
	
	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		if(targetPos != null)
			entity.moveToBlockPosAndAngles(targetPos, yaw, entity.rotationPitch);
	}
}
