package net.tardis.mod.common.protocols;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ProtocolConsole implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis t) {
		if(!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(t.getPos());
			NBTTagCompound tardisTag = tardis.writeToNBT(new NBTTagCompound());
			world.setBlockState(t.getPos(), t.getClass() == TileEntityTardis.class ? TBlocks.console_01.getDefaultState() : TBlocks.console.getDefaultState());
			TileEntity te = world.getTileEntity(t.getPos());
			te.readFromNBT(tardisTag);
			
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.change.console";
	}

}
