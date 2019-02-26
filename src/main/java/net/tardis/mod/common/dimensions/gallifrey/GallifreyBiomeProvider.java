package net.tardis.mod.common.dimensions.gallifrey;


import com.google.common.collect.Lists;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.*;
import net.tardis.mod.common.dimensions.TDimensions;

import java.util.List;


public class GallifreyBiomeProvider extends BiomeProvider {

    public static final List<Biome> BIOMES_TO_SPAWN_IN = Lists.newArrayList(TDimensions.gallifreyRedlands,TDimensions.gallifreyMountains);


    public GallifreyBiomeProvider(World world){

        super(world.getWorldInfo());

    }

    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
        GenLayer generationLayer = buildBiomeProcedure(seed);
        GenLayerVoronoiZoom layer = new GenLayerVoronoiZoom(10, generationLayer);
        layer.initWorldGenSeed(seed);

        GenLayer[] layers = new GenLayer[] { generationLayer, layer, generationLayer };
        return super.getModdedBiomeGenerators(worldType, seed, layers);
    }

    private static GenLayer buildBiomeProcedure(long seed) {

        GenLayer layer = new GenLayerGallifreyBiomes(seed);
        layer = new GenLayerZoom(1000, layer);
        layer = new GenLayerZoom(1000, layer);
        layer = new GenLayerZoom(1000, layer);
        layer = new GenLayerZoom(1000, layer);
        layer = new GenLayerZoom(1000, layer);
        layer = new GenLayerFuzzyZoom(1000, layer);


        return layer;
    }


    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return BIOMES_TO_SPAWN_IN;
    }

}
