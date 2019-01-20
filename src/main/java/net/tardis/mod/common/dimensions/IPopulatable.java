package net.tardis.mod.common.dimensions;

import net.minecraft.world.World;

import java.util.Random;

public interface IPopulatable {

	void gen(World world, Random rand, int x, int z);

}
