package net.tardis.mod.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.WorldShell;

public class TileEntityInteriorDoor extends TileEntity implements ITickable, IContainsWorldShell {
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(-1, 0, 0, 2, 4, 1).offset(getPos());
	}

	@Override
	public WorldShell getWorldShell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWorldShell(WorldShell worldShell) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		
	}

}
