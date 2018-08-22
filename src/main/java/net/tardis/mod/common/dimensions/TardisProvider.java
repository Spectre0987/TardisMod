package net.tardis.mod.common.dimensions;

import net.minecraft.entity.player.EntityPlayer;
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
		return 0.5F;
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
	protected void generateLightBrightnessTable() {
		for (int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - i / 15.0F;
			this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 1.0F + 0.0F;
		}
	}

	@Override
	public float getSunBrightness(float par1) {
		return 0.3f;
	}

	@Override
	public float getSunBrightnessFactor(float par1) {
		return 0;
	}

	@Override
	public void updateWeather() {}
	
	@Override
	public DimensionType getDimensionType() {
		return TDimensions.tardisType;
	}
	
	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
		return WorldSleepResult.ALLOW;
	}
	
}
