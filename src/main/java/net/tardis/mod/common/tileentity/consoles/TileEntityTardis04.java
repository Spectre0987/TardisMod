package net.tardis.mod.common.tileentity.consoles;

import net.tardis.mod.common.entities.controls.ControlMonitor;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class TileEntityTardis04 extends TileEntityTardis{
	
	public TileEntityTardis04() {
		this.controlClases.add(ControlMonitor::new);
	}

}
