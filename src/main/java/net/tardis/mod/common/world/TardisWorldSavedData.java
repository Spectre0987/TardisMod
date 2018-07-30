package net.tardis.mod.common.world;

import java.util.Map;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;
import net.tardis.mod.util.helpers.TardisHelper;

public class TardisWorldSavedData extends WorldSavedData {
	
	public TardisWorldSavedData(String name) {
		super(name);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagList list = nbt.getTagList("tardises", Constants.NBT.TAG_COMPOUND);
		System.out.println("List is: " + list);
		for (NBTBase base : list) {
			NBTTagCompound tag = (NBTTagCompound) base;
			TardisHelper.tardisOwners.put(tag.getString("name"), BlockPos.fromLong(tag.getLong("pos")).toImmutable());
			//System.out.println(TardisHelper.tardisOwners.toString());
		}
		//System.out.println(nbt.toString());
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		Map<String, BlockPos> map = TardisHelper.tardisOwners;
		for (int i = 0; i < map.size(); ++i) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("name", map.keySet().toArray(new String[] {})[i]);
			tag.setLong("pos", map.values().toArray(new BlockPos[] {})[i].toLong());
			list.appendTag(tag);
		}
		nbt.setTag("tardises", list);
		return nbt;
	}
	
}
