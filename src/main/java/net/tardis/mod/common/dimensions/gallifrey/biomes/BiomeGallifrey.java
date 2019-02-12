package net.tardis.mod.common.dimensions.gallifrey.biomes;


import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.*;
import net.tardis.mod.common.blocks.TBlocks;



import java.util.Random;

public class BiomeGallifrey extends Biome{

	protected static final WorldGenAbstractTree TREE = new WorldGenBirchTree(false, false);

	public BiomeGallifrey() {
		super(new BiomeProperties("gallifrey").setBaseHeight(0.0F).setHeightVariation(0.1F).setTemperature(6.0F).setRainDisabled().setWaterColor(16745874));
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();



		this.decorator.treesPerChunk = 30;
		this.decorator.extraTreeChance = 0.05F;
		this.decorator.flowersPerChunk = 4;
		this.decorator.grassPerChunk = 10;

	}

	@Override
	public int getGrassColorAtPos(BlockPos pos)
	{
		return getModdedBiomeGrassColor(0xE74C3C);
	}

	@Override
	public int getFoliageColorAtPos(BlockPos pos)
	{
		return getModdedBiomeFoliageColor(0xEAEDED);
	}


	@Override
	public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double stoneNoiseVal)
	{
		IBlockState topBlock = TBlocks.gallifreyan_grass.getDefaultState();
		IBlockState fillerBlock = TBlocks.gallifreyan_dirt.getDefaultState();
		IBlockState seaFloorBlock = TBlocks.gallifreyan_stone.getDefaultState();
		
		boolean hitFloorYet = false;
		int topBlocksToFill = 0;
		int dirtBlocksToFill = 0;
		int seaFloorBlocksToFill = 0;
		int dirtDepth = Math.max(0, (int)(stoneNoiseVal / 3.0D + 3 + rand.nextDouble() * 0.25D));
		int seaFloorDepth = 1 + rand.nextInt(2);
		
		int localX = x & 15;
		int localZ = z & 15;

		// start at the top and move downwards
		for (int y = 255; y >= 0; --y)
		{
			
			IBlockState state = primer.getBlockState(localZ, y, localX);
			
			// bedrock at the bottom
			if (y <= rand.nextInt(5))
			{
				primer.setBlockState(localZ, y, localX, Blocks.BEDROCK.getDefaultState());
				continue;
			}
			
			if (state.getMaterial() == Material.AIR)
			{
				// topBlocks and dirtBlocks can occur after any pocket of air
				topBlocksToFill = (topBlock == null ? 0 : 1);
				dirtBlocksToFill = dirtDepth;
				continue;
			}
			else if (!hitFloorYet && state.getMaterial() == Material.WATER)
			{
				// seaFloorBlocks can occur after surface water
				seaFloorBlocksToFill = seaFloorDepth;
			}
			
			if (state.getBlock() == Blocks.STONE)
			{
				hitFloorYet = true;
				if (topBlocksToFill > 0)
				{
					if (y >= 62)
					{
						primer.setBlockState(localZ, y, localX, topBlock);
					}
					else if (y >= 56 - dirtDepth)
					{
						primer.setBlockState(localZ, y, localX, fillerBlock);
					}
					else
					{
						primer.setBlockState(localZ, y, localX, Blocks.GRAVEL.getDefaultState());
						dirtBlocksToFill = 0;
					}
					topBlocksToFill--;
				}
				else if (seaFloorBlocksToFill > 0)
				{
					primer.setBlockState(localZ, y, localX, seaFloorBlock);
					--seaFloorBlocksToFill;
				}
				else if (dirtBlocksToFill > 0)
				{
					primer.setBlockState(localZ, y, localX, fillerBlock);
					--dirtBlocksToFill;
				}


			}
		}
	}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random random){

		return TREE;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos)
	{
		super.decorate(worldIn, rand, pos);
	}


}
