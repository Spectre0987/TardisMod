package net.tardis.mod.common.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public interface IEntityFactory<T extends Entity> {
	
	T createEntity(World world);
}
