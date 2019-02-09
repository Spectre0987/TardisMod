package net.tardis.mod.common.dimensions.gallifrey;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.tardis.mod.common.dimensions.IPopulatable;

import java.util.Random;

public class ChunkGeneratorGallifrey extends ChunkGeneratorOverworld {
	
	World world;
	Random rand = new Random();
	IPopulatable pop;
	
	public ChunkGeneratorGallifrey(World worldIn, long seed) {
		super(worldIn, seed, false, "");
		world = worldIn;
	}
	
	public ChunkGeneratorGallifrey(World worldIn, long seed, IPopulatable pop) {
		this(worldIn, seed);
		this.pop = pop;
	}
	
	@Override
	public void populate(int x, int z) {
		if (pop != null) {
			pop.gen(world, rand, x, z);
		}
	}
	
}