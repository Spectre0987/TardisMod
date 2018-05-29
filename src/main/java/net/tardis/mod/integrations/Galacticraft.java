package net.tardis.mod.integrations;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.common.items.clothing.SpaceSuit;

public class Galacticraft {
	
	public static void preInit() {
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

	public static class EventHandler{
		
		@SubscribeEvent
		public void stopSuffication(GCCoreOxygenSuffocationEvent.Pre event) {
			if(event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof SpaceSuit)
				event.setCanceled(true);
		}
	}
}
