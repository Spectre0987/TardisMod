package net.tardis.mod.common.dimensions.gallifrey;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.dimensions.moon.RenderGallifreySky;
import sun.reflect.generics.tree.Tree;

import javax.annotation.Nullable;

public class WorldProviderGallifrey extends WorldProviderSurface {


	@Override
	protected void init() {
		super.init();
		this.biomeProvider = new GallifreyBiomeProvider(world);
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorGallifrey(world, world.getSeed());
	}
	
	@Nullable
	@Override
	@SideOnly(Side.CLIENT)
	public IRenderHandler getSkyRenderer() {
		return RenderGallifreySky.getInstance();
	}

	@Override
	public DimensionType getDimensionType() {
		return TDimensions.GALLIFREY_TYPE;
	}
	
}
