package net.tardis.mod.proxy;

import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.screwdriver.ScrewdriverHandler;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.packets.NetworkHandler;

public class ServerProxy {

	public void preInit() {
		NetworkHandler.init();
		TDimensions.init();
		ScrewdriverHandler.init();
		TardisSystems.init();
	}

	public void init() {

	}

	public void postInit() {}
	
}
