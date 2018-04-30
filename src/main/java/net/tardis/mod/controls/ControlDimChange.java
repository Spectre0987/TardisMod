package net.tardis.mod.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlDimChange extends EntityControl{

	public ControlDimChange(TileEntityTardis tardis) {
		super(tardis);
		this.setRotation(90, 45F);
	}
	
	public ControlDimChange(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}

	@Override
	public Vec3d getOffset() {
		return Helper.normalizeVec3d(-10,14,16);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			Integer[] ids=DimensionManager.getIDs(true);
			TileEntityTardis tardis=(TileEntityTardis)world.getTileEntity(this.tardisPos);
			if(!player.isSneaking())++tardis.dimIndex;
			else --tardis.dimIndex;
			if(tardis.dimIndex>=ids.length||tardis.dimIndex<0)tardis.dimIndex=0;
			tardis.setTargetDimension(ids[tardis.dimIndex]!=TDimensions.id?ids[tardis.dimIndex]:0);
		}
		else this.ticks=20;
	}

}
