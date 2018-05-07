package net.tardis.mod.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityTimeRotor extends TileEntity implements ITickable {
	
	public float offsetY = 0;
	boolean up = true;
	
	@Override
	public void update() {
		TileEntity te = world.getTileEntity(getPos().down());
		if (te != null && te instanceof TileEntityTardis) {
			if (((TileEntityTardis) te).isInFlight()) {
				if (up) {
					offsetY += 0.1F;
					if (offsetY > 3.0) up = false;
				} else {
					offsetY -= 0.1F;
					if (offsetY < 0.0) up = true;
				}
			} else {
				offsetY = 0;
				up = true;
			}
		}
	}
	
}
