package net.tardis.mod.common.dimensions.moon;

import java.util.Random;

import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomePlains;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.telos.BiomeTelos;

public class BiomeMoon extends BiomePlains {

	public BiomeMoon() {
		super(false, new BiomeProperties("Moon").setBaseBiome("plains").setHeightVariation(0.00F).setBaseHeight(2F).setRainDisabled().setTemperature(0.4F));
		this.topBlock = TBlocks.moon_dirt.getDefaultState();
		this.fillerBlock = TBlocks.moon_dirt.getDefaultState();
		this.decorator = new BiomeTelos.TelosDecorator();
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.decorator.generateFalls = false;
	}

	@Override
	public EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
		return EnumFlowerType.RED_TULIP;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {}

	@Override
	public void addDefaultFlowers() {}

	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return TREE_FEATURE;
	}

}
