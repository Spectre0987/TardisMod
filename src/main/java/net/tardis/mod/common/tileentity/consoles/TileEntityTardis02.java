package net.tardis.mod.common.tileentity.consoles;

import net.minecraft.util.math.AxisAlignedBB;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class TileEntityTardis02 extends TileEntityTardis{

	public TileEntityTardis02() {}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(-1, 0, -1, 2, 2, 2).offset(getPos());
	}

	@Override
	public boolean createControls() {
		boolean b = super.createControls();
		if(b) {
			EntityControl c = this.getControl(ControlDoor.class);
			if(c != null) {
				c.setPositionAndRotation(c.posX, c.posY, c.posZ, -90, 0);
			}
		}
		return b;
	}
}
