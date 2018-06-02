package net.tardis.mod.common.ars;

import java.util.ArrayList;
import java.util.List;

import net.tardis.mod.common.stuctures.TardisStructure;

public class ARS {
	
	private static List<TardisStructure> rooms = new ArrayList<TardisStructure>();
	
	public static void register(TardisStructure structure) {
		rooms.add(structure);
	}
	
	public static TardisStructure getStructure(int id) {
		if (id >= rooms.size() || id < 0) return null;
		return rooms.get(id);
	}
	
	public static int getID(TardisStructure structure) {
		int id = 0;
		for (TardisStructure comp : rooms) {
			if (comp.equals(structure))
				return id;
			++id;
		}
		return -1;
	}
	
}
