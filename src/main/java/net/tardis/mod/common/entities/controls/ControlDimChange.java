package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.util.helpers.Helper;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ControlDimChange extends EntityControl {
	
	public ControlDimChange(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlDimChange(World world) {
		super(world);
		this.setSize(0.125F, 0.0625F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-1.5, -2, -12);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			Integer[] ids = DimensionManager.getIDs(true);
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			if (!player.isSneaking())
				++tardis.dimIndex;
			else
				--tardis.dimIndex;
			if (tardis.dimIndex >= ids.length || tardis.dimIndex < 0) tardis.dimIndex = 0;
			tardis.setTargetDimension(ids[tardis.dimIndex] != TDimensions.id ? ids[tardis.dimIndex] : 0);
		} else
			this.ticks = 20;
	}
	
}
