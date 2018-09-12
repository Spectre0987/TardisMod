package net.tardis.mod.common.dimensions.telos;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

public class ChunkGeneratorTelos extends ChunkGeneratorOverworld {
	
	World world;
	
	public ChunkGeneratorTelos(World worldIn, long seed) {
		super(worldIn, seed, false, "");
		world = worldIn;
	}

	@Override
	public void replaceBiomeBlocks(int cx, int cz, ChunkPrimer primer, Biome[] biomesIn) {
		super.replaceBiomeBlocks(cx, cz, primer, biomesIn);
		for(int x = 0; x < 16; ++x) {
			for(int z = 0; z < 16; ++z) {
				for(int y = 0; y < world.getHeight(); ++y) {
					if(primer.getBlockState(x, y, z).getBlock() == Blocks.STONE) {
						primer.setBlockState(x, y, z, Blocks.GOLD_BLOCK.getDefaultState());
					}
				}
			}
		}
	}

	@Override
	public void populate(int x, int z) {}

	
}
