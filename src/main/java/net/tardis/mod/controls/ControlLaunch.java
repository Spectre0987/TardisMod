package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ControlLaunch extends EntityControl {
	
	public ControlLaunch(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlLaunch(World world) {
		super(world);
		this.setSize(0.0625F, 0.25F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-7, 0, 7.5);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(this.getConsolePos());
			if (te != null) {
				TileEntityTardis tardis = (TileEntityTardis) te;
				if (!tardis.isInFlight()) tardis.startFlight();
			}
		} else
			ticks = 30;
	}
	
	public void setSize(float x, float y, float z) {
		float sx = x / 2;
		float sy = y / 2;
		float sz = z / 2;
		this.setEntityBoundingBox(new AxisAlignedBB(this.posX - sx, this.posY - sy, this.posZ - sz, this.posX + sx, this.posY + sy, this.posZ + sz));
	}
}
