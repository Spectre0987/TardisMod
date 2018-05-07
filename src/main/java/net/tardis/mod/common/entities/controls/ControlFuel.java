package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ControlFuel extends EntityControl {
	
	public ControlFuel(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlFuel(World world) {
		super(world);
		this.setSize(0.2F, 0.2F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(0, 0, 0);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis t = (TileEntityTardis) world.getTileEntity(getConsolePos());
			t.setFueling(t.isFueling() ? false : true);
		} else
			ticks = 20;
	}
	
}
