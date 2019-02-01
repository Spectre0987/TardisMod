package net.tardis.mod.common.dimensions.gallifrey;

import net.minecraft.block.BlockDirt;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.tardis.mod.common.blocks.TBlocks;


import java.util.Random;

public class BiomeGallifrey extends Biome {
	
	public BiomeGallifrey() {
		super(new BiomeProperties("Gallifrey").setBaseHeight(0.325f).setHeightVariation(0.18f).setBaseHeight(3.5F).setTemperature(0.2f).setRainfall(0.45f));
		this.decorator.treesPerChunk = 1;
		this.decorator.grassPerChunk = 10;
		this.decorator.flowersPerChunk = 2;
		this.decorator.reedsPerChunk = -999;
		this.decorator.cactiPerChunk = -999;
		this.spawnableCreatureList.clear();
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);
	}
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
	{
		this.topBlock = TBlocks.GALLIFREY_GRASS.getDefaultState();
		this.fillerBlock = TBlocks.GALLIFREY_DIRT.getDefaultState();
		
		if (noiseVal > 1.8D)
		{
			this.topBlock = TBlocks.GALLIFREY_DIRT.getDefaultState();
		}
		else if (noiseVal <= -1.85D)
		{
			this.topBlock = TBlocks.GALLIFREY_SAND.getDefaultState();
		}
		
		this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		//return 0xba785a;// 0xC49878; //0xCBC29E; //super.getGrassColorAtPos(pos);
		double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
		return d0 < -0.1D ? 0xba785a : 0xC49878;
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0xb5bf89;
	}
}