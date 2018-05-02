package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlLaunch extends EntityControl{
	
	public ControlLaunch(TileEntityTardis tardis) {
		super(tardis);
		this.setRotation(-90, 45);
	}

	public ControlLaunch(World world) {
		super(world);
		this.setSize(0.4F,0.4F);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-7,16,0);
	}

	@Override
	public Vec3d getRotation() {
		return new Vec3d(20,0,0);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntity te=world.getTileEntity(this.getConsolePos());
			if(te!=null) {
				TileEntityTardis tardis=(TileEntityTardis)te;
				if(!tardis.isInFlight())tardis.startFlight();
			}
		}
		else ticks=30;
	}

}
