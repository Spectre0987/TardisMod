package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlZ extends EntityControl{

	public ControlZ(TileEntityTardis tardis) {
		super(tardis);
	}

	public ControlZ(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-7,-0.5,5.5);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis=((TileEntityTardis)world.getTileEntity(this.getConsolePos()));
			tardis.setDesination(tardis.getDestination().add(0,0,player.isSneaking()?-10:10),tardis.getTargetDim());
		}
		else if(this.ticks<=0){
			ticks=20;
			direction=player.isSneaking()?-1:1;
		}
	}

}
