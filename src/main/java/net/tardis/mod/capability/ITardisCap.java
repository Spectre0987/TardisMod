package net.tardis.mod.capability;

import net.minecraft.util.math.BlockPos;

public interface ITardisCap {
	
	void setTardis(BlockPos pos);
	BlockPos getTardis();
	void update();

}
