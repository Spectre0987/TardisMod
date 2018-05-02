package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlY extends EntityControl{

	public ControlY(TileEntityTardis tardis) {
		super(tardis);
		this.setRotation(90, 50);
	}
	
	public ControlY(World world) {
		super(world);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(8,12,-6);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis=((TileEntityTardis)world.getTileEntity(this.tardisPos));
			tardis.setDesination(tardis.getDestination().add(0,player.isSneaking()?-10:10,0), tardis.getTargetDim());
			Helper.say("New Tardis Destination is: "+Helper.formatBlockPos(tardis.getDestination()));
		}
		else if(this.ticks<=0){
			ticks=20;
			direction=player.isSneaking()?-1:1;
		}
	}

}
