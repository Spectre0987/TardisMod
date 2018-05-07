package net.tardis.mod.common.world;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IGeneratable {
	
	void gen(World world, BlockPos pos, EnumFacing facing);
}
