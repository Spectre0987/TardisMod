package net.tardis.mod.systems;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public class Systems{
	
	private static HashMap<String, Systems> systems = new HashMap<String, Systems>();
	
	private Item item;
	private boolean isDamaged = false;
	private boolean preventFight = false;
	private String name = "";
	
	public Systems(Item item) {
		this.item = item;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public boolean getDamaged() {
		return this.isDamaged;
	}
	
	public boolean getPreventFlight() {
		return this.preventFight;
	}
	
	public void onDamaged() {
		this.isDamaged = true;
		this.preventFight = true;
	}
	
	public static Systems readFromNBT(NBTTagCompound tag) {
		NBTTagCompound nTag = (NBTTagCompound)tag.getTag(NBT.TAG);
		Systems sys = systems.get(nTag.getString(NBT.ID));
		sys.isDamaged = nTag.getBoolean(NBT.DAMAGED);
		sys.preventFight = nTag.getBoolean(nTag.getString(NBT.PREVENT_FIGHT));
		sys.item = Item.getByNameOrId(nTag.getString(NBT.ITEM));
		return sys;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		NBTTagCompound nTag = new NBTTagCompound();
		nTag.setBoolean(NBT.DAMAGED, this.isDamaged);
		nTag.setBoolean(NBT.PREVENT_FIGHT, this.preventFight);
		nTag.setString(NBT.ITEM, this.item.getRegistryName().toString());
		nTag.setString(NBT.ID, this.name);
		tag.setTag(NBT.TAG, nTag);
		return tag;
	}
	public static class NBT{
		public static final String ID = "id";
		public static final String TAG = "SystemName";
		public static final String DAMAGED = "is_damaged";
		public static final String PREVENT_FIGHT = "preventFlight";
		public static final String ITEM = "item";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Systems)) {
			return false;
		}
		if(((Systems)obj).getItem() == this.getItem()) {
			return true;
		}
		return false;
	}

	public static void register(String name, Systems sys) {
		sys.name = name;
		systems.put(name, sys);
	}
}