package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.util.helpers.Helper;

public class ControlDoorSwitch extends EntityControl{

	public ControlDoorSwitch(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlDoorSwitch(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis instanceof TileEntityTardis01) {
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
					ControlDoor door = (ControlDoor)tardis.getControl(ControlDoor.class);
					door.setOpen(door.isOpen() ? false : true);
					world.playSound(null, getPosition(), door.isOpen() ? TSounds.door_open : TSounds.door_closed, SoundCategory.BLOCKS, 0.5F,1F);
				}
			}
		}
	}

}
