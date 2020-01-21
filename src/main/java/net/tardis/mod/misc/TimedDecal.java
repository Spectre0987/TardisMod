package net.tardis.mod.misc;

import net.minecraft.util.math.Vec3d;

public class TimedDecal {

	private Vec3d pos = Vec3d.ZERO;
	private int ticksLeft = 0;
	
	public TimedDecal(Vec3d hit, int time) {
		this.pos = hit;
		this.ticksLeft = time;
	}
	
	public TimedDecal(double x, double y, double z, int time) {
		this(new Vec3d(x, y, z), time);
	}
	
	public Vec3d getPos() {
		return this.pos;
	}
	
	public int getTimeLeft() {
		return this.ticksLeft;
	}
	
	public int shrinkTime() {
		this.ticksLeft -= 1;
		return this.ticksLeft;
	}
}
