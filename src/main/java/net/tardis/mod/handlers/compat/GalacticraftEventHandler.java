package net.tardis.mod.handlers.compat;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class GalacticraftEventHandler {
	
	@SubscribeEvent
	public void stopSuffication(GCCoreOxygenSuffocationEvent.Pre event) {}

}
