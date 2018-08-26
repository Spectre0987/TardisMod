package net.tardis.mod.common.screwdriver;

import java.util.ArrayList;
import java.util.List;

import net.tardis.mod.Tardis;

public class ScrewdriverHandler {

	public static List<IScrew> MODES = new ArrayList<IScrew>();
	
	public static void register(IScrew screw) {
		MODES.add(screw);
		Tardis.LOG.info("Registered Sonic interaction: {}", screw.getName());
	}

	public static void init() {
		register(new InteractionGeneral());
		register(new InteractionHallwayGen());
		register(new InteractionGRoom());
		register(new InteractonElectricPanel());
		register(new InteractionEntity());
		register(new ModeSignal());
	}
}
