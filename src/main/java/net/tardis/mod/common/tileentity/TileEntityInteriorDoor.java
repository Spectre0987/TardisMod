package net.tardis.mod.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityInteriorDoor extends TileEntity {
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(-1, 0, 0, 2, 4, 1).offset(getPos());
	}

}
