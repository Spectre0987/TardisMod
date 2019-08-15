package net.tardis.mod.common.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.config.TardisConfig;

import java.util.Random;

public class WorldGenTardis implements IWorldGenerator {

	WorldGenMinable CINNABAR_GEN = new WorldGenMinable(TBlocks.cinnabar_ore.getDefaultState(), 6);

	public WorldGenTardis() {

	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!TardisConfig.Dimensions.generateOres)
			return;
		for (int chances = 0; chances < 5; ++chances) {
			if (random.nextInt(100) < 90) {
				CINNABAR_GEN.generate(world, random, new BlockPos(chunkX * 16, random.nextInt(80), chunkZ * 16).add(8, 0, 8));
			}
		}
	}

}
