package net.tardis.mod.common.dimensions.gallifrey;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.tardis.mod.common.dimensions.TDimensions;

public class GenLayerGallifreyBiomes extends GenLayer {
    
    protected Biome[] GALLIFREY_BIOMES = (new Biome[]{
            TDimensions.BIOME_GALLIFREY_REDLANDS,
            TDimensions.BIOME_GALLIFREY_FARMLAND,
            TDimensions.BIOME_GALLIFREY_MOUNTAIN
    });

    public GenLayerGallifreyBiomes(long l) {
        super(l);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                initChunkSeed(dx + x, dz + z);
                dest[dx + dz * width] = Biome.getIdForBiome(GALLIFREY_BIOMES[nextInt(GALLIFREY_BIOMES.length)]);
//                if (nextInt(RARE_BIOME_CHANCE) == 0) {
//                    // make rare biome
//                    dest[dx + dz * width] = Biome.getIdForBiome(rareBiomes[nextInt(rareBiomes.length)]);
//                } else {
//                    // make common biome
//                    dest[dx + dz * width] = Biome.getIdForBiome(commonBiomes[nextInt(commonBiomes.length)]);
//                }
            }

        }
        return dest;
    }
}


