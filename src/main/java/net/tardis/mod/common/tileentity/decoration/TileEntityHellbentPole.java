package net.tardis.mod.common.tileentity.decoration;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityHellbentPole extends TileEntity{

	public TileEntityHellbentPole() {}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(0, 0, 0, 1, 4, 1).offset(getPos());
	}

}
