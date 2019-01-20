package net.tardis.mod.common.tileentity.consoles;

import net.minecraft.util.math.AxisAlignedBB;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class TileEntityTardis02 extends TileEntityTardis {

	public AxisAlignedBB BB = new AxisAlignedBB(-1, 0, -1, 2, 2, 2);

	public TileEntityTardis02() {
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return BB.offset(getPos());
	}
}
