package net.tardis.mod.integrations;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.common.entities.EntityCyberman;
import net.tardis.mod.common.entities.EntityDalek;
import net.tardis.mod.common.items.clothing.ItemSpaceSuit;

public class Galacticraft {
	
	public static void preInit() {
		MinecraftForge.EVENT_BUS.register(new Galacticraft.EventHandler());
	}
	
	public static class EventHandler {
		
		@SubscribeEvent
		public void stopSuffication(GCCoreOxygenSuffocationEvent.Pre event) {
			Iterable<ItemStack> armor = event.getEntityLiving().getArmorInventoryList();
			int count = 0;
			for(ItemStack stack : armor) {
				if(stack.getItem() instanceof ItemSpaceSuit) {
					++count;
				}
			}
			if(count >= 3) {
				event.setCanceled(true);
			}
			if(event.getEntityLiving() instanceof EntityDalek || event.getEntityLiving() instanceof EntityCyberman) {
				event.setCanceled(true);
			}
		}
	}
}
