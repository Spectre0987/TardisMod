package net.tardis.mod.common.dimensions.telos;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeDesert;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityCybermanTomb;

public class BiomeTelos extends BiomeDesert {

	public BiomeTelos() {
		super(new BiomeProperties("Telos").setTemperature(0F).setBaseBiome("desert"));
		this.setRegistryName(new ResourceLocation(Tardis.MODID, "telos"));
		this.decorator = new TelosDecorator();
		this.topBlock = TBlocks.telos_sand.getDefaultState();
		this.fillerBlock = TBlocks.telos_sand.getDefaultState();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCybermanTomb.class, 25, 0, 2));
		this.decorator.generateFalls = false;
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {}

	@Override
	public boolean canRain() {
		return false;
	}
	public static class TelosDecorator extends BiomeDecorator {
		
		public TelosDecorator() {}

		@Override
		public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {}

		@Override
		protected void genDecorations(Biome biomeIn, World worldIn, Random random) {}
	}
}
