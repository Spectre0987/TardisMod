package net.tardis.mod.client.worldshell;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class BlockStorage {
	
	//Stores individual blocks in a worldShell
	
	public IBlockState blockstate;
	public NBTTagCompound tileentity = null;
	public int light;
	
	public BlockStorage() {
	}
	
	public BlockStorage(IBlockState b, TileEntity t, int l) {
		blockstate = b;
		if (t != null) tileentity = t.serializeNBT();
		light = l;
	}
	
	public BlockStorage(ByteBuf buf) {
		this.fromBuf(buf);
	}
	
	public void toBuf(ByteBuf buf) {
		buf.writeInt(Block.getStateId(blockstate));
		buf.writeInt(light);
		ByteBufUtils.writeTag(buf, tileentity);
	}
	
	public void fromBuf(ByteBuf buf) {
		blockstate = Block.getStateById(buf.readInt());
		light = buf.readInt();
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		if (tag != null) {
			tileentity = tag;
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if(this == other)
			return true;
		if(!(other instanceof BlockStorage))
			return false;
		BlockStorage stor = (BlockStorage)other;
		if(blockstate.equals(stor.blockstate) && light == stor.light && ((this.tileentity == null && stor.tileentity == null) || this.tileentity.equals(stor.tileentity)))
			return true;
		return super.equals(other);
	}
}