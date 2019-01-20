package net.tardis.mod.common.screwdriver;

import net.tardis.mod.Tardis;

import java.util.ArrayList;
import java.util.List;

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
		register(new InteractionEntity());
		register(new ModeSignal());
	}
}
