package net.tardis.mod.common.dimensions;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.tardis.mod.util.helpers.TardisHelper;

public class TardisChunkGenerator implements IChunkGenerator {
	
	private World world;
	private Random rand;
	
	public TardisChunkGenerator(World world, long seed) {
		this.world = world;
		this.rand = new Random(seed);
	}
	
	@Override
	public Chunk generateChunk(int x, int z) {
		Chunk c = new Chunk(world, x, z);
		if (TardisHelper.isConsoleChunk(c)) {
			/*
			 * for (int bx = 0; bx < 16; ++bx) { for (int bz = 0; bz < 16; ++bz) { IBlockState panel = TBlocks.panel.getDefaultState(); IBlockState light = TBlocks.light.getDefaultState(); c.setBlockState(new BlockPos(bx, 0, bz), bx % 2 == 0 ? panel : light); c.setBlockState(new BlockPos(bx, bz, 0), bx % 2 == 0 ? panel : light); c.setBlockState(new BlockPos(0, bx, bz), bz % 2 == 0 ? panel : light); c.setBlockState(new BlockPos(bx, bz, 15), bx % 2 == 0 ? panel : light); c.setBlockState(new BlockPos(15, bx, bz), bz % 2 == 0 ? panel : light); } } IBlockState panel = TBlocks.panel.getDefaultState(); IBlockState glass = TBlocks.grate.getDefaultState(); IBlockState light = TBlocks.light.getDefaultState(); // Walkway for (int bz = 1; bz < 15; bz++) { for (int bx = 0; bx < 5; bx++) { c.setBlockState(new BlockPos(6 + bx, 5, bz), bx == 0 || bx == 4 ? TBlocks.panel.getDefaultState() : TBlocks.grate.getDefaultState()); } } for (int bx = 0; bx <= 8; bx++) { for (int bz = 0; bz < 8; bz++) { c.setBlockState(new BlockPos(4 + bx, 5, 4 + bz), bx == 8 || bz == 7 || bx == 0 || bz == 0 ? panel : glass); } } // Roof for (int bx = 1; bx <= 14; bx++) { c.setBlockState(new BlockPos(bx, 16, 1), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(bx, 16, 14), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(1, 16, bx), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(14, 16, bx), bx % 2 == 0 ? light : panel); } for (int bx = 2; bx <= 13; bx++) { c.setBlockState(new BlockPos(bx, 17, 2), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(bx, 17, 13), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(2, 17, bx), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(13, 17, bx), bx % 2 == 0 ? light : panel); } for (int bx = 3; bx <= 12; bx++) { c.setBlockState(new BlockPos(bx, 18, 3), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(bx, 18, 12), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(3, 18, bx), bx % 2 == 0 ? light : panel); c.setBlockState(new BlockPos(12, 18, bx), bx % 2 == 0 ? light : panel); } for (int bx = 4; bx <= 11; bx++) { for (int bz = 4; bz <= 11; bz++) { c.setBlockState(new BlockPos(bx, 19, bz), bx % 2 == 0 || bz % 2 == 0 ? light : panel); } }
			 */
			
		}
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
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}
	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {}
	
	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
	
}
