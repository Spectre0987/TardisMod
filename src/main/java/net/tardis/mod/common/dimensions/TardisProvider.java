package net.tardis.mod.common.dimensions;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;

public class TardisProvider extends WorldProvider {
	
	public TardisProvider() {}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new TardisChunkGenerator(this.world, this.world.getSeed());
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return super.calculateCelestialAngle(worldTime, partialTicks);
	}
	
	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return super.calcSunriseSunsetColors(celestialAngle, partialTicks);
	}
	
	@Override
	public int getDimension() {
		return super.getDimension();
	}
	
	@Override
	public Biome getBiomeForCoords(BlockPos pos) {
		return Biomes.DESERT;
	}
	
	@Override
	public void calculateInitialWeather() {}
	
	@Override
	public void updateWeather() {}
	
	@Override
	public DimensionType getDimensionType() {
		return TDimensions.tardisType;
	}
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
}
