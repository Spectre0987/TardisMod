package net.tardis.mod.common.misc;

import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public interface TardisControlFactory<T extends EntityControl> {
	
	T createControl(TileEntityTardis tardis);

}
