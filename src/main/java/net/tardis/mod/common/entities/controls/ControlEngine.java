package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ControlEngine extends EntityControl{

	public ControlEngine(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}
	
	public ControlEngine(TileEntityTardis tardis) {
		super(tardis);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vec3d getOffset() {
		return new Vec3d(0,-1,0);
	}

	@Override
	public void preformAction(EntityPlayer player) {}

}
