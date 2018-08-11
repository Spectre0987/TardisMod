package net.tardis.mod.common.tileentity;

import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityHellbentLight extends TileEntityLight{

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(-0.5, 0, -0.5, 1.5, 1, 1.5).offset(getPos());
	}

}
