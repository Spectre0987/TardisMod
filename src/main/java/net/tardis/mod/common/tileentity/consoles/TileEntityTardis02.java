package net.tardis.mod.common.tileentity.consoles;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;

public class TileEntityTardis02 extends TileEntityTardis{

	public AxisAlignedBB BB = new AxisAlignedBB(-1, 0, -1, 2, 2, 2);
	
	public TileEntityTardis02() {}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return BB.offset(getPos());
	}
}
