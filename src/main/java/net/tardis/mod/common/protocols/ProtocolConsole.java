package net.tardis.mod.common.protocols;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;

public class ProtocolConsole implements ITardisProtocol {

	@Override
	public void onActivated(World world, TileEntityTardis t) {
		if(!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(t.getPos());
			NBTTagCompound tardisTag = tardis.writeToNBT(new NBTTagCompound());
			world.setBlockState(t.getPos(), t.getClass() == TileEntityTardis.class ? TBlocks.console_01.getDefaultState() : (t.getClass() == TileEntityTardis01.class ? TBlocks.console_02.getDefaultState() : t.getClass() == TileEntityTardis02.class ? TBlocks.console_03.getDefaultState() : TBlocks.console.getDefaultState()));
			TileEntity te = world.getTileEntity(t.getPos());
			te.readFromNBT(tardisTag);
			te.markDirty();
		}
	}

	@Override
	public String getNameKey() {
		return "protocol.tardis.change.console";
	}

}
