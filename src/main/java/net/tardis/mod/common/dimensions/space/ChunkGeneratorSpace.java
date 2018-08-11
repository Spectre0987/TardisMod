package net.tardis.mod.common.dimensions.space;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkGeneratorSpace implements IChunkGenerator {

	private World world;
	
	public ChunkGeneratorSpace() {}
	
	public ChunkGeneratorSpace(World world) {
		this.world = world;
	}
	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer cp = new ChunkPrimer();
		
		Chunk c = new Chunk(world, cp, x, z);
		byte[] bytes = c.getBiomeArray();
		for(int i = 0; i < bytes.length; ++i) {
			bytes[i] = (byte)Biome.getIdForBiome(Biomes.VOID);
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
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

}
