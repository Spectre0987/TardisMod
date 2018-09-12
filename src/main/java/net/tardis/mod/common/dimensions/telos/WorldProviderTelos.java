package net.tardis.mod.common.dimensions.telos;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.common.dimensions.TDimensions;

public class WorldProviderTelos extends WorldProvider {

	public static final Vec3d fogColor = new Vec3d(0.611, 0.69, 0.788);
	private BiomeProviderSingle biomeP = new BiomeProviderSingle(TDimensions.telosBiome);
	
	public WorldProviderTelos() {}
	
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

	@Override
	public BiomeProvider getBiomeProvider() {
		return biomeP;
	}

	@Override
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		return fogColor;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean doesXZShowFog(int x, int z) {
		return true;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorTelos(this.world, this.getSeed());
	}
}
