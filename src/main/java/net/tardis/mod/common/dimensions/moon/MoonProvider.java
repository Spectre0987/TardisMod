package net.tardis.mod.common.dimensions.moon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.api.dimensions.IDimensionProperties;
import net.tardis.mod.client.renderers.sky.SkyRendererMoon;
import net.tardis.mod.common.dimensions.IPopulatable;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.dimensions.telos.ChunkGeneratorTelos;

import java.util.Random;

public class MoonProvider extends WorldProvider implements IDimensionProperties {
	
	private static BiomeProviderSingle biomeP = new BiomeProviderSingle(TDimensions.BIOME_MOON);

	public MoonProvider() {
	}

	@Override
	public DimensionType getDimensionType() {
		return TDimensions.MOON_TYPE;
	}

	@Override
	public BiomeProvider getBiomeProvider() {
		return biomeP;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorTelos(this.world, this.getSeed(), new IPopulatable() {

			GenCrater gen = new GenCrater();

			@Override
			public void gen(World world, Random rand, int x, int z) {
				if (rand.nextInt(100) <= 5) {
					int size = 5 + rand.nextInt(10);
					BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x * 16, 0, z * 16)).add(16, size / 2, 16);
					gen.genCrater(world, pos, size);
				}
			}
		});
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		return new Vec3d(0, 0, 0);
	}

	@Override
	public boolean isSkyColored() {
		return false;
	}

	@Override
	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
		return new Vec3d(0, 0, 0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer() {
		return new SkyRendererMoon();
	}

	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return new float[]{0, 0, 0, 0};
	}

	@Override
	public void onPlayerAdded(EntityPlayerMP player) {
		super.onPlayerAdded(player);
		player.setNoGravity(true);
	}

	@Override
	public void onPlayerRemoved(EntityPlayerMP player) {
		super.onPlayerRemoved(player);
		player.setNoGravity(false);
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
		return 0;
	}

	@Override
	public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
		return WorldSleepResult.ALLOW;
	}

}
