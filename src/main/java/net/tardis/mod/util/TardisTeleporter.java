package net.tardis.mod.util;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

public class TardisTeleporter implements ITeleporter {
	
	private final BlockPos targetPos;
	
	public TardisTeleporter(BlockPos targetPos) {
		this.targetPos = targetPos;
	}
	
	@Override
	public void placeEntity(World world, Entity entity, float yaw) {
		//entity.moveToBlockPosAndAngles(targetPos, yaw, entity.rotationPitch);
		entity.setPositionAndRotation(targetPos.getX() + 0.5, targetPos.getY() + 1, targetPos.getZ() + 0.5, yaw, 0);
	}

	@Override
	public boolean isVanilla() {
		return false;
	}
}
