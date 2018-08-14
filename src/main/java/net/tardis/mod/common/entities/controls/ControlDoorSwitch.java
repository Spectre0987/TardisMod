package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.entities.hellbent.EntityHellbentDoor;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.util.helpers.Helper;

public class ControlDoorSwitch extends EntityControl{
	
	private AxisAlignedBB DOOR_BB = new AxisAlignedBB(-10, -10, -10, 10, 10, 10);

	public ControlDoorSwitch(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlDoorSwitch(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis instanceof TileEntityTardis01 || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(-13.5, -3, -4.5);
		}
		return Helper.convertToPixels(0,-2,11);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntity te = world.getTileEntity(getConsolePos());
			if(te != null && te instanceof TileEntityTardis) {
				TileEntityTardis tardis = (TileEntityTardis)te;
				if(!tardis.isInFlight()) {
					for(EntityHellbentDoor rd : world.getEntitiesWithinAABB(EntityHellbentDoor.class, DOOR_BB.offset(getPositionVector()))) {
						rd.setOpen(rd.isOpen() ? false : true);
					}
				}
			}
		}
	}

	@Override
	public String getControlName() {
		return "doorswitch";
	}

}
