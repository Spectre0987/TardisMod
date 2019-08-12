package net.tardis.mod.common.dimensions.telos;

import java.awt.Color;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeDesert;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityCybermanInvasion;

public class BiomeTelos extends BiomeDesert {

	private int grassColor = new Color(175 / 255F, 143 / 255F, 82F / 255F).getRGB();

	public BiomeTelos(boolean snow) {
		super(new BiomeProperties("Telos").setTemperature(snow ? 0F : 0.3F).setBaseBiome("desert"));
		this.decorator = new TelosDecorator();
		this.topBlock = TBlocks.telos_sand.getDefaultState();
		this.fillerBlock = TBlocks.telos_sand.getDefaultState();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCybermanInvasion.class, 25, 0, 2));
		this.decorator.generateFalls = false;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
	}

	@Override
	public boolean canRain() {
		return false;
	}

	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return grassColor;
	}

	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return grassColor;
	}

	public static class TelosDecorator extends BiomeDecorator {

		public TelosDecorator() {
		}

		@Override
		public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
		}

		@Override
		protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
		}
	}

}
