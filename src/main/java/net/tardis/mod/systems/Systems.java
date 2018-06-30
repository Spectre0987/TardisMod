package net.tardis.mod.systems;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

public class Systems{
	
	private static ArrayList<Systems> systems = new ArrayList<Systems>();
	
	private Item item;
	private boolean isDamaged = false;
	
	public Systems(Item item) {
		this.item = item;
	}
	
	public static Systems getSystemByID(int id) {
		if(id > systems.size()) {
			return systems.get(id);
		}
		return null;
	}
	
	public static int getIDForSystem(Systems system) {
		int id = 0;
		for(Systems s : systems) {
			if(s.equals(system)) {
				return id;
			}
			++id;
		}
		return -1;
	}

	/**
	 * Gets the Item that repairs this system
	 * @return
	 */
	public Item getItem() {
		return this.item;
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		NBTTagCompound nt = new NBTTagCompound();
		nt.setInteger("id", this.getIDForSystem(this));
		nt.setBoolean("damaged", isDamaged);
		tag.setTag("system", nt);
	}
	
	public static Systems readFromNBT(NBTTagCompound tag) {
		NBTTagCompound nt = (NBTTagCompound) tag.getTag("system");
		Systems system = Systems.getSystemByID(tag.getInteger("id"));
		system.isDamaged = nt.getBoolean("damaged");
		return system;
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

	public static void register(Systems sys) {
		systems.add(sys);
	}
}