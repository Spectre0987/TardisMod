package net.tardis.api.controls;

import net.minecraft.util.math.Vec3d;

public interface IControl {
	
	String getName();
	
	Vec3d getOffset();
	
	Vec3d getRotation();
}
