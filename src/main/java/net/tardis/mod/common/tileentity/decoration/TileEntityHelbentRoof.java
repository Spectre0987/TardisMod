package net.tardis.mod.common.tileentity.decoration;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityHelbentRoof extends TileEntity {

	public TileEntityHelbentRoof() {
	}


	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(-1, -1, -1, 2, 1, 2).offset(getPos());
	}

}
