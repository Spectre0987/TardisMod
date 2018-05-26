package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import scala.util.Random;

public class ControlRandom extends EntityControl {
	
	public ControlRandom(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlRandom(World world) {
		super(world);
		this.setSize(0.1875F, 0.125F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(0, -1, 9);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			Random rand = new Random();
			BlockPos loc = tardis.getLocation();
			tardis.setDesination(new BlockPos(loc.getX() + (rand.nextInt(1000) - 500), rand.nextInt(100), loc.getZ() + (rand.nextInt(1000) - 500)), tardis.getTargetDim());
		} else
			this.ticks = 80;
	}
	
}
