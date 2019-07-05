package net.tardis.mod.common.tileentity;

import net.minecraft.util.math.BlockPos;

public interface IMultiblock {
	
	BlockPos getMaster();
	void setMaster(BlockPos pos);

}
