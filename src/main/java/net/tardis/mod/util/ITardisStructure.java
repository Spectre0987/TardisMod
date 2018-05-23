package net.tardis.mod.util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public interface ITardisStructure {
	
	BlockPos getOffset(EnumFacing facing);
	
	Rotation getRotation(EnumFacing facing);

}
