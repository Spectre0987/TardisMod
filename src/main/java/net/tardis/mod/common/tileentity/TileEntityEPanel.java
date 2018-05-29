package net.tardis.mod.common.tileentity;

import ic2.api.energy.prefab.BasicSource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityEPanel extends TileEntity implements ITickable{

	public BasicSource source = new BasicSource(this, 10000,1);
	
	public TileEntityEPanel() {}

	@Override
	public void update() {
		source.update();
		source.addEnergy(10);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		source.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		source.writeToNBT(compound);
		return super.writeToNBT(compound);
	}

	@Override
	public void invalidate() {
		source.invalidate();
		super.invalidate();
	}

	@Override
	public void onChunkUnload() {
		source.onChunkUnload();
		super.onChunkUnload();
	}

	@Override
	public void onLoad() {
		super.onLoad();
		source.onLoad();
	}
	
}
