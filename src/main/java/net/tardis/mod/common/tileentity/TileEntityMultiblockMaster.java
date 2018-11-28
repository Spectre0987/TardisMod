package net.tardis.mod.common.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityMultiblockMaster extends TileEntity{
	
	private List<BlockPos> children = new ArrayList<>();
	
	public List<BlockPos> getChildren() {
		return children;
	}
	
	public void setChildren(List<BlockPos> pos) {
		this.children = pos;
		this.markDirty();
	}
	
	public void addChildren(BlockPos pos) {
		this.children.add(pos);
		this.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList list = compound.getTagList("poses", NBT.TAG_LONG);
		for(NBTBase base : list) {
			children.add(BlockPos.fromLong(((NBTTagLong)base).getLong()));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for(BlockPos pos : this.children) {
			list.appendTag(new NBTTagLong(pos.toLong()));
		}
		compound.setTag("poses", list);
		return super.writeToNBT(compound);
	}

	public List<TileEntityMultiblock> getBlocks(){
		List<TileEntityMultiblock> list = new ArrayList<TileEntityMultiblock>();
		for(BlockPos pos : children) {
			TileEntity te = world.getTileEntity(pos);
			if(te != null && te instanceof TileEntityMultiblock) {
				list.add((TileEntityMultiblock)te);
			}
		}
		return list;
	}
}
