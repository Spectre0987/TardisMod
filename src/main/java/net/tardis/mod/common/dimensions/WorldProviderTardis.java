package net.tardis.mod.common.dimensions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.renderers.sky.RenderVoid;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class WorldProviderTardis extends WorldProvider {

	public WorldProviderTardis() {}

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
	public void updateWeather() {
	}

	@Override
	public DimensionType getDimensionType() {
		return TDimensions.DIMTYPE_TARDIS;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
		return WorldSleepResult.ALLOW;
	}

	@Override
	public boolean canCoordinateBeSpawn(int x, int z) {
		return true;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public boolean doesWaterVaporize() {
		return false;
	}

	@Override
	public boolean isNether() {
		return false;
	}

	@Override
	public BlockPos getRandomizedSpawnPoint() {
		return BlockPos.ORIGIN;
	}

	@Override
	public int getRespawnDimension(EntityPlayerMP player) {
		if(TardisHelper.hasTardis(player.getUniqueID()))
			return this.getDimension();
		return super.getRespawnDimension(player);
	}

	@Override
	public boolean isDaytime() {
		return false;
	} 

	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}

	@Override
	public boolean isBlockHighHumidity(BlockPos pos) {
		return false;
	}

	@Override
	public double getHorizon() {
		return 0.0D;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getCloudRenderer() {
		return new RenderVoid();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer() {
		return new RenderVoid();
	}
}
