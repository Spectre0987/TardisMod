package net.tardis.mod.common.world;

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
			TardisHelper.tardisOwners.put(tag.getString("name"), BlockPos.fromLong(tag.getLong("pos")));
			System.out.println(TardisHelper.tardisOwners.toString());
		}
		System.out.println(nbt.toString());
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		int size = TardisHelper.tardisOwners.size();
		String[] names = TardisHelper.tardisOwners.keySet().toArray(new String[] {});
		BlockPos[] poses = TardisHelper.tardisOwners.values().toArray(new BlockPos[] {});
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < names.length; ++i) {
			if (names[i] != null && poses[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("name", names[i]);
				tag.setLong("pos", poses[i].toLong());
				list.appendTag(tag);
			}
		}
		nbt.setTag("tardises", list);
		System.out.println(nbt.toString());
		return nbt;
	}
	
}
