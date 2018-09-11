package net.tardis.mod.common.dimensions.telos;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkGeneratorOverworld;

public class ChunkGeneratorTelos extends ChunkGeneratorOverworld {
	
	World world;
	
	public ChunkGeneratorTelos(World worldIn, long seed) {
		super(worldIn, seed, false, "");
		world = worldIn;
	}
	
	@Override
	public Chunk generateChunk(int x, int z) {
		Chunk c = super.generateChunk(x, z);
		for(int cx = 0; cx < 16; ++cx) {
			for(int cz = 0; cx < 16; ++cz) {
				for(int cy = 0; cy < world.getHeight(); ++cy) {
					BlockPos pos = new BlockPos(cx, cy, cz);
					if(c.getBlockState(pos).getBlock() == Blocks.STONE)
						c.setBlockState(pos, Blocks.GOLD_BLOCK.getDefaultState());
				}
			}
		}
		return c;
	}

	
}
