package net.tardis.mod.common.dimensions.telos;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDesert;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.entities.EntityCybermanTomb;

public class BiomeTelos extends BiomeDesert {

	public BiomeTelos() {
		super(new BiomeProperties("telos"));
		this.setRegistryName(new ResourceLocation(Tardis.MODID, "telos"));
		this.topBlock = Blocks.HAY_BLOCK.getDefaultState();
		this.fillerBlock = Blocks.HAY_BLOCK.getDefaultState();
		this.spawnableMonsterList = new ArrayList<>();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCybermanTomb.class, 25, 0, 2));
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {}

}
