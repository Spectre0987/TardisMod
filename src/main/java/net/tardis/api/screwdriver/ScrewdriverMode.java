package net.tardis.api.screwdriver;

import java.util.ArrayList;
import java.util.List;

public class ScrewdriverMode {
	
	public static List<IScrew> modes = new ArrayList<IScrew>();
	
	public static void register(IScrew screw) {
		modes.add(screw);
	}
	
}
