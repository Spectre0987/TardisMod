package net.tardis.mod.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityMultiblock extends TileEntity implements IMultiblock{

	BlockPos masterPos = BlockPos.ORIGIN;

	public TileEntityMultiblock() {}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.masterPos = BlockPos.fromLong(compound.getLong("master"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("master", this.masterPos.toLong());
		return super.writeToNBT(compound);
	}

	@Override
	public BlockPos getMaster() {
		return this.masterPos;
	}

	@Override
	public void setMaster(BlockPos pos) {
		this.masterPos = pos;
		((TileEntityMultiblockMaster) this.getWorld().getTileEntity(pos)).addChildren(this.getPos());
		this.markDirty();
	}

	@Override
	public float getHardness() {
		return 5F;
	}

}
