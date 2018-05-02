package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlFuel extends EntityControl{

	public ControlFuel(TileEntityTardis tardis) {
		super(tardis);
		this.setRotation(90, 130);
	}
	
	public ControlFuel(World world) {
		super(world);
		this.setSize(0.2F,0.2F);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-10,16,20);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis t=(TileEntityTardis)world.getTileEntity(getConsolePos());
			t.setFueling(t.isFueling()?false:true);
		}
		else ticks=20;
	}

}
