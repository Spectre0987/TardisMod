package net.tardis.mod.common.dimensions.telos;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.tardis.mod.common.dimensions.TDimensions;

public class ChunkGeneratorTelos implements IChunkGenerator {

	World world; 
	Random rand = new Random();
	NoiseGeneratorPerlin noise = new NoiseGeneratorPerlin(this.rand, 2);
	
	public ChunkGeneratorTelos() {}
	
	public ChunkGeneratorTelos(World world, Long seed){
		this.world = world;
		this.rand = new Random(seed);
	}
	
	@Override
	public Chunk generateChunk(int cx, int cz) {
		Chunk c = new Chunk(world, new ChunkPrimer(), cx, cz);
		for(int x = 0; x < 16; ++x) {
			for(int z = 0; z < 16; ++z) {
				
			}
		}
		c.generateSkylightMap();
		return c;
	}

	@Override
	public void populate(int x, int z) {}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return null;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,boolean findUnexplored) {
		return BlockPos.ORIGIN;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

}
