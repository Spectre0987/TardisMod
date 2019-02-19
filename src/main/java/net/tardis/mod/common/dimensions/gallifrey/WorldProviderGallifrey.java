package net.tardis.mod.common.dimensions.gallifrey;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.renderers.sky.RenderVoid;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.dimensions.moon.RenderGallifreySky;

import javax.annotation.Nullable;

public class WorldProviderGallifrey extends WorldProviderSurface {
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorGallifrey(world, world.getSeed());
	}
	
	@Override
	public BiomeProvider getBiomeProvider() {
		return new BiomeProviderSingle(TDimensions.gallifreyBiome);
	}
	
	public static RenderGallifreySky SKY = new RenderGallifreySky();
	
	@Nullable
	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getSkyRenderer() {
		return SKY;
	}
	
	@Nullable
	@Override
	public IRenderHandler getCloudRenderer() {
		return new RenderVoid();
	}
	
	@Override
	public DimensionType getDimensionType() {
		return TDimensions.GALLIFREY_TYPE;
	}
	
}
