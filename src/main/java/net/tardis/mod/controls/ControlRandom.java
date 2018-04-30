package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;
import scala.util.Random;

public class ControlRandom extends EntityControl{

	public ControlRandom(TileEntityTardis tardis) {
		super(tardis);
		this.setSize(0.0625F,0.0625F);
		this.setRotation(90F, 45F);
	}
	
	public ControlRandom(World world) {
		super(world);
		this.setSize(0.0625F,0.0625F);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.normalizeVec3d(-7.5,16,8);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis=(TileEntityTardis)world.getTileEntity(this.tardisPos);
			Random rand=new Random();
			BlockPos loc=tardis.getLocation();
			tardis.setDesination(new BlockPos(loc.getX()+(rand.nextInt(1000)-500),rand.nextInt(100),loc.getZ()+(rand.nextInt(1000)-500)),tardis.getTargetDim());
		}
		else this.ticks=20;
	}

}
