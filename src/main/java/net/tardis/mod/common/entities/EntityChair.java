package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.decoration.TileEntityChair;

public class EntityChair extends Entity {

	public BlockPos chair = BlockPos.ORIGIN;

	public EntityChair(World worldIn) {
		super(worldIn);
		this.setSize(1F, 1F);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}

	@Override
	public double getMountedYOffset() {
		return 0.5;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {
			if (chair == null || BlockPos.ORIGIN.equals(chair))
				kill();
			if (this.getPassengers() == null || this.getPassengers().size() < 1)
				kill();
		}
	}

	public void setChairPos(BlockPos pos) {
		this.chair = pos;
	}

	public void kill() {
		if (chair != null && !BlockPos.ORIGIN.equals(chair)) {
			TileEntityChair chair = (TileEntityChair) world.getTileEntity(this.chair);
			if (chair != null) {
				chair.isSit = false;
			}
		}
		this.setDead();
	}
}
