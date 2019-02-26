package net.tardis.mod.common.dimensions.gallifrey.biomes;


import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.TBlocks;

import java.util.Random;

public class BiomeMountains extends Biome {

	private static final IBlockState GRASS = Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS);


	// Tree Generation
	private static final ResourceLocation[] treeList = {
			new ResourceLocation(Tardis.MODID, "gallifrey/trees/gal_tree_large"),
			new ResourceLocation(Tardis.MODID, "gallifrey/trees/gal_tree_tall"),
			new ResourceLocation(Tardis.MODID, "gallifrey/trees/gal_tree_normal"),
			new ResourceLocation(Tardis.MODID, "gallifrey/trees/gal_tree_small"),
			new ResourceLocation(Tardis.MODID, "gallifrey/trees/gal_tree_small_skinny"),
			new ResourceLocation(Tardis.MODID, "gallifrey/trees/gal_shrub")
	};


	public BiomeMountains() {
		super(new BiomeProperties("Red Mountains").setBaseHeight(0.4F).setHeightVariation(0.6F).setTemperature(0F).setWaterColor(0xEB623D).setSnowEnabled());
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();

		this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 20, 2, 3));
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 20, 2, 3));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 20, 2, 3));

		this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 25, 1, 4));

		this.decorator = new BiomeDecoratorGallifrey();
		this.decorator.treesPerChunk = 30;
		this.decorator.extraTreeChance = 0.05F;
		this.decorator.flowersPerChunk = 4;
		this.decorator.grassPerChunk = 10;
		
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return getModdedBiomeGrassColor(0xE74C3C);
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return getModdedBiomeFoliageColor(0xEAEDED);
	}
	
	
	@Override
	public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double stoneNoiseVal) {
		IBlockState topBlock = TBlocks.gallifreyan_grass_snow.getDefaultState();
		IBlockState fillerBlock = TBlocks.gallifreyan_dirt.getDefaultState();
		IBlockState seaFloorBlock = TBlocks.gallifreyan_stone.getDefaultState();
		
		boolean hitFloorYet = false;
		int topBlocksToFill = 0;
		int dirtBlocksToFill = 0;
		int seaFloorBlocksToFill = 0;
		int dirtDepth = Math.max(0, (int) (stoneNoiseVal / 3.0D + 3 + rand.nextDouble() * 0.25D));
		int seaFloorDepth = 1 + rand.nextInt(2);
		
		int localX = x & 15;
		int localZ = z & 15;
		
		// start at the top and move downwards
		for (int y = 255; y >= 0; --y) {
			
			IBlockState state = primer.getBlockState(localZ, y, localX);
			
			// bedrock at the bottom
			if (y <= rand.nextInt(5)) {
				primer.setBlockState(localZ, y, localX, Blocks.BEDROCK.getDefaultState());
				continue;
			}
			
			if (state.getMaterial() == Material.AIR) {
				// topBlocks and dirtBlocks can occur after any pocket of air
				topBlocksToFill = (topBlock == null ? 0 : 1);
				dirtBlocksToFill = dirtDepth;
				continue;
			} else if (!hitFloorYet && state.getMaterial() == Material.WATER) {
				// seaFloorBlocks can occur after surface water
				seaFloorBlocksToFill = seaFloorDepth;
			}
			
			if (state.getBlock() == Blocks.STONE) {
				hitFloorYet = true;
				if (topBlocksToFill > 0) {
					if (y >= 62) {
						primer.setBlockState(localZ, y, localX, topBlock);
					} else if (y >= 56 - dirtDepth) {
						primer.setBlockState(localZ, y, localX, fillerBlock);
					} else {
						primer.setBlockState(localZ, y, localX, Blocks.GRAVEL.getDefaultState());
						dirtBlocksToFill = 0;
					}
					topBlocksToFill--;
				} else if (seaFloorBlocksToFill > 0) {
					primer.setBlockState(localZ, y, localX, seaFloorBlock);
					--seaFloorBlocksToFill;
				} else if (dirtBlocksToFill > 0) {
					primer.setBlockState(localZ, y, localX, fillerBlock);
					--dirtBlocksToFill;
				}
				
			}
		}
	}
	
	
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random random) {
		return null;
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		
		int maxTrees = rand.nextInt(3);
		for (int trees = 0; trees < maxTrees; ++trees) {
			BlockPos treePos = worldIn.getTopSolidOrLiquidBlock(pos.add(rand.nextInt(14) + 8, 0, rand.nextInt(14)+ 8));
			if (worldIn.getBlockState(treePos.down()).getBlock() == TBlocks.gallifreyan_grass_snow) {
				generateGallifreyTrees(worldIn, worldIn.getTopSolidOrLiquidBlock(treePos), treeList[rand.nextInt(treeList.length)]);
			}
		}

	}
	
	public static void generateGallifreyTrees(World world, BlockPos pos, ResourceLocation location) {
		if (!world.isRemote) {
			Template treeTemp = ((WorldServer) world).getStructureTemplateManager().get(world.getMinecraftServer(), location);
			BlockPos treePos = pos.add(-treeTemp.getSize().getX() / 2, 0, -treeTemp.getSize().getZ() / 2);
			if (world.getBlockState(treePos).isSideSolid(world, treePos, EnumFacing.UP))
				treeTemp.addBlocksToWorld(world, treePos, new PlacementSettings());
		}
	}
	
	
	public static class BiomeDecoratorGallifrey extends BiomeDecorator {


		public ChunkGeneratorSettings chunkProviderSettings;

		public WorldGenerator coalGen;
		public WorldGenerator ironGen;
		public WorldGenerator lapisGen;
		/** Field that holds gold WorldGenMinable */
		public WorldGenerator goldGen;
		public WorldGenerator redstoneGen;
		public WorldGenerator diamondGen;


		public BiomeDecoratorGallifrey() {}
		
		@Override
		public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {

			this.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), this.chunkProviderSettings.coalSize);
			this.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), this.chunkProviderSettings.ironSize);
			this.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), this.chunkProviderSettings.goldSize);
			this.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), this.chunkProviderSettings.redstoneSize);
			this.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), this.chunkProviderSettings.diamondSize);
			this.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), this.chunkProviderSettings.lapisSize);


		}
		
		@Override
		protected void genDecorations(Biome biomeIn, World worldIn, Random random) {}
		
	}




	
}
