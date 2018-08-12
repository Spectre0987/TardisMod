package net.tardis.mod.util.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.tardis.mod.Tardis;
import net.tardis.mod.util.DimChunkPos;

public class RiftHelper {
	
	public static Random rand = new Random();
	public static List<DimChunkPos> RIFT_POS = new ArrayList<>();

	public static void writeRiftStatus(Chunk chunk, World world, NBTTagCompound tag) {
		if(tag == null) return;
		if(RIFT_POS.contains(new DimChunkPos(chunk.getPos(), world.provider.getDimension()))) {
			tag.setBoolean(Tardis.MODID + ":isRift", true);
			return;
		}
		if(!tag.hasKey(Tardis.MODID + ":isRift")){
			if(rand.nextInt(10) == 0) {
				tag.setBoolean(Tardis.MODID + ":isRift", true);
				return;
			}
		}
		tag.setBoolean(Tardis.MODID + ":isRift", false);
	}

	public static void readRiftStatus(Chunk chunk, World world, NBTTagCompound data) {
		if(data.hasKey(Tardis.MODID + ":isRift")) {
			if(data.getBoolean(Tardis.MODID + ":isRift")) {
				DimChunkPos cPos = new DimChunkPos(chunk.getPos(), world.provider.getDimension());
				if(!RIFT_POS.contains(cPos))RIFT_POS.add(cPos);
			}
		}
	}
	
	public static boolean isRift(ChunkPos pos, World world) {
		DimChunkPos nPos = new DimChunkPos(pos, world.provider.getDimension());
		for(DimChunkPos c : RIFT_POS) {
			if(c.equals(nPos)) {
				return true;
			}
		}
		return false;
	}

}
