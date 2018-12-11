package net.tardis.mod.common.tileentity.decoration;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class TileEntityToyotaSpin extends TileEntity{

	public static final AxisAlignedBB renderBox = new AxisAlignedBB(0, 0, 0, 3, 4, 3).offset(-1, 0, -1);
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return this.renderBox.offset(getPos());
	}

	public boolean isInFlight() {
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(TardisHelper.getTardisForPosition(this.getPos()));
		return tardis != null && tardis.isInFlight() ? true : false;
	}

}
