package net.tardis.mod.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class SpaceTimeCoord {
	
	public static final SpaceTimeCoord ORIGIN = new SpaceTimeCoord(BlockPos.ORIGIN, 0, "None");
	private BlockPos pos;
	private int dimension;
	public String name = "None";
	
	public SpaceTimeCoord(BlockPos p, int dim, String name) {
		this.dimension = dim;
		pos = p.toImmutable();
		this.name = name;
	}
	
	public BlockPos getPos() {
		return pos;
	}
	
	public int getDimension() {
		return dimension;
	}
	
	public static SpaceTimeCoord readFromNBT(NBTTagCompound tag) {
		return new SpaceTimeCoord(BlockPos.fromLong(tag.getLong("stcPos")), tag.getInteger("stcDim"), tag.getString("name"));
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setLong("stcPos", getPos().toLong());
		tag.setInteger("stcDim", getDimension());
		tag.setString("name", name);
		return tag;
	}
	
	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof SpaceTimeCoord)) return false;
		SpaceTimeCoord co = (SpaceTimeCoord) ob;
		return this.getPos().equals(co.getPos()) && this.getDimension() == co.getDimension();
	}
	
}
