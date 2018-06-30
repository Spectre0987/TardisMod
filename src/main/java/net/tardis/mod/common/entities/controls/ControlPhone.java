package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class ControlPhone extends EntityControl{

	public ControlPhone(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlPhone(World world) {
		super(world);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(0,16,0);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		
	}

}
