package net.tardis.mod.common.tileentity.consoles;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class TileEntityTardis02 extends TileEntityTardis{

	public AxisAlignedBB BB = new AxisAlignedBB(-1, 0, -1, 2, 2, 2);
	
	public TileEntityTardis02() {
		this.coordList.add(new Vec3d(-Helper.precentToPixels(11F), -Helper.precentToPixels(1.5F), Helper.precentToPixels(2.75F)));
		this.coordList.add(new Vec3d(-Helper.precentToPixels(9F), -Helper.precentToPixels(1F), Helper.precentToPixels(3.25F)));
		this.coordList.add(new Vec3d(-Helper.precentToPixels(6F), -Helper.precentToPixels(1F), Helper.precentToPixels(1.25F)));
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return BB.offset(getPos());
	}
}
