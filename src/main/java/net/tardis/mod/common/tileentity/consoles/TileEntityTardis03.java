package net.tardis.mod.common.tileentity.consoles;

import net.tardis.mod.common.entities.controls.ControlMonitor;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class TileEntityTardis03 extends TileEntityTardis {

	public TileEntityTardis03() {
		this.controlClases.add(ControlMonitor::new);
	}

}
