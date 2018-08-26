package net.tardis.mod.common.dimensions.telos;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.tardis.mod.common.dimensions.TDimensions;

public class WorldProviderTelos extends WorldProvider{

	public WorldProviderTelos() {}

	@Override
	public BiomeProvider getBiomeProvider() {
		return new BiomeProviderSingle(TDimensions.telosBiome);
	}

	@Override
	public DimensionType getDimensionType() {
		return TDimensions.telosType;
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public boolean isSkyColored() {
		return false;
	}

}
