package net.tardis.mod.common.dimensions.gallifrey;


import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.tardis.mod.common.dimensions.TDimensions;



public class GallifreyBiomeProvider extends BiomeProvider {


    public GallifreyBiomeProvider(World world){

        super(world.getWorldInfo());
        getBiomesToSpawnIn().clear();

        getBiomesToSpawnIn().add(TDimensions.gallifreyFarmlands);
        getBiomesToSpawnIn().add(TDimensions.gallifreyRedlands);
        makeLayers(world.getSeed());

    }


    private void makeLayers(long seed) {
        GenLayer biomes = new GenLayerGallifreyBiomes(1L);

        biomes = new GenLayerZoom(1000, biomes);
        biomes = new GenLayerZoom(1001, biomes);

        biomes.initWorldGenSeed(seed);

        genBiomes = biomes;

    }
}
