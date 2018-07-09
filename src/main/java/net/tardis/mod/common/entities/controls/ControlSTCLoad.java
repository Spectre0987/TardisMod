package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class ControlSTCLoad extends EntityControl {
	
	public static final DataParameter<Boolean> IS_LOADING = EntityDataManager.createKey(ControlSTCLoad.class, DataSerializers.BOOLEAN);
	
	public ControlSTCLoad(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlSTCLoad(World tardis) {
		super(tardis);
		this.setSize(0.0625F, 0.0625F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-12, -2, -3.4);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			tardis.setLoading(tardis.getLoading() ? false : true);
			this.dataManager.set(IS_LOADING, tardis.getLoading());
		} else
			this.ticks = 20;
	}
	
	public boolean getLoading() {
		return dataManager.get(IS_LOADING);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_LOADING, false);
	}
	
}
