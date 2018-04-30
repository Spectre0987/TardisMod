package net.tardis.api.controls;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class SpaceTimeCoord {
	
	public static final SpaceTimeCoord ORIGIN=new SpaceTimeCoord(BlockPos.ORIGIN,0);
	private BlockPos pos;
	private int dimension;
	
	public SpaceTimeCoord(BlockPos p,int dim) {
		this.dimension=dim;
		pos=p.toImmutable();
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public int getDimension() {
		return dimension;
	}
	
	public static SpaceTimeCoord readFromNBT(NBTTagCompound tag) {
		return new SpaceTimeCoord(BlockPos.fromLong(tag.getLong("stcPos")),tag.getInteger("stcDim"));
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setLong("stcPos", getPos().toLong());
		tag.setInteger("stcDim", getDimension());
		return tag;
	}

	@Override
	public boolean equals(Object ob) {
		if(!(ob instanceof SpaceTimeCoord))
			return false;
		SpaceTimeCoord co=(SpaceTimeCoord)ob;
		return this.getPos().equals(co.getPos())&&this.getDimension()==co.getDimension();
	}
	
	

}
