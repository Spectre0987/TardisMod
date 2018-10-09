package net.tardis.mod.common.dimensions;

import java.util.Random;

import net.minecraft.world.World;

public interface IPopulatable {
	
	void gen(World world, Random rand, int x, int z);

}
