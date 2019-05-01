package net.tardis.mod.common.dimensions.space;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.api.dimensions.IDimensionProperties;
import net.tardis.mod.client.renderers.sky.RenderVoid;
import net.tardis.mod.common.dimensions.TDimensions;

public class SpaceProvider extends WorldProvider implements IDimensionProperties{
	
	public SpaceProvider() {
		
	}

	@Override
	public DimensionType getDimensionType() {
		return TDimensions.DIMTYPE_SPACE;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorSpace(this.world);
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.5F;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public float getCloudHeight() {
		return 1000;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getCloudRenderer() {
		return new RenderVoid();
	}

	@Override
	public boolean shouldMapSpin(String entity, double x, double z, double rotation) {
		return true;
	}

	@Override
	public void calculateInitialWeather() {}

	@Override
	public void updateWeather() {}

	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer() {
		return new RenderSpace();
	}

	@Override
	public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
		return WorldSleepResult.DENY;
	}
	
	@Override
	public boolean hasGravity() {
		return false;
	}

	@Override
	public boolean hasAir() {
		return false;
	}

	@Override
	public int getRadiationLevels() {
		return 10;
	}

	@Override
	public double transformGrav(double grav) {
		return grav * 0.01;
	}

}
