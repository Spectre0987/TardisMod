package net.tardis.mod.capability;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

public interface ITardisCap extends INBTSerializable<NBTTagCompound> {
	
	void setTardis(BlockPos pos);
	
	BlockPos getTardis();
	
	boolean isInFlight();
	
	void setInFlight(boolean inFlight);
	
	void update();
	
	void setExterior(IBlockState exterior);
	
	IBlockState getExterior();
	
	void sync();
	
}
