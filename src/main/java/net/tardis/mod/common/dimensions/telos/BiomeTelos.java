package net.tardis.mod.common.dimensions.telos;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraftforge.common.BiomeDictionary;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityCybermanTomb;

public class BiomeTelos extends BiomeDesert {

	public BiomeTelos() {
		super(new BiomeProperties("telos"));
		this.setRegistryName(new ResourceLocation(Tardis.MODID, "telos"));
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
}
