package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlX extends EntityControl{
	
	public ControlX(TileEntityTardis tardis) {
		super(tardis);
		this.setRotation(180, 45);
	}
	
	public ControlX(World world) {
		super(world);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(22,12,8);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis=((TileEntityTardis)world.getTileEntity(this.getConsolePos()));
			tardis.setDesination(tardis.getDestination().add(player.isSneaking()?-10:10,-1,0), tardis.getTargetDim());
		}
		else if(this.ticks<=0){
			this.ticks=20;
			this.direction=player.isSneaking()?-1:1;
		}
	}

}
