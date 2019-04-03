package net.tardis.mod.capability;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.INBTSerializable;

public interface ITardisCap extends INBTSerializable<NBTTagCompound> {
	
	void setFlightTardis(BlockPos pos);
	
	BlockPos getFlightTardis();
	
	void setTardis(BlockPos pos);
	
	BlockPos getTardis();
	
	boolean isInFlight();
	
	void setInFlight(boolean inFlight);
	
	void update();
	
	void setExterior(IBlockState exterior);
	
	IBlockState getExterior();
	
	void sync();
	
	void setHasFuel(boolean b);
	boolean hasFuel();
	
	int timeOnGround();
	void setTimeOnGround(int time);
	
	void setDoorsOpen(boolean open);
	boolean isOpen();

	void setPrevPos(Vec3d positionVector);
	Vec3d getPrevPos();
	
	void setPrevRot(Vec2d vec);
	Vec2d getPrevRot();
	
	CapabilityTardis.TardisFlightState getFlightState();
	
	void setFlightState(CapabilityTardis.TardisFlightState state);
	
	float getAlpha();
	
	
	class Vec2d {
		public double x;
		public double y;
		
		public Vec2d(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
}
