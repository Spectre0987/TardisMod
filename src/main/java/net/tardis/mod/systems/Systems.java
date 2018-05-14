package net.tardis.mod.systems;

import java.util.HashMap;
import java.util.Map;

public class Systems {
	
	private static Map<String, System> systems=new HashMap<String, System>();
	
	public static void register(String name,System sys) {
		systems.put(name, sys);
	}

	public static System getSystem(String name) {
		return systems.get(name);
	}
}
