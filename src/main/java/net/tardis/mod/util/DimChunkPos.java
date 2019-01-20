package net.tardis.mod.util;

import net.minecraft.util.math.ChunkPos;

public class DimChunkPos {

	ChunkPos pos;
	int dim;

	public DimChunkPos(ChunkPos pos, int dim) {
		this.pos = pos;
		this.dim = dim;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DimChunkPos))
			return false;
		if (obj == this)
			return true;
		DimChunkPos oPos = (DimChunkPos) obj;
		return oPos.pos.equals(this.pos) && this.dim == oPos.dim;
	}

}
