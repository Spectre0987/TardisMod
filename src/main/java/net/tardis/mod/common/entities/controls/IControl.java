package net.tardis.mod.common.entities.controls;

import net.minecraft.util.math.Vec3d;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public interface IControl {
	
	String getControlName();
	
	Vec3d getOffset(TileEntityTardis tardis);
}
