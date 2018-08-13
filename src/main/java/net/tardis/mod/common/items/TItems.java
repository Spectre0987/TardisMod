package net.tardis.mod.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.EnumClothes;
import net.tardis.mod.common.items.clothing.*;
import net.tardis.mod.common.items.components.ArtronCapacitor;
import net.tardis.mod.common.items.components.DematerializationCircut;
import net.tardis.mod.common.items.components.FluidLink;

@Mod.EventBusSubscriber
public class TItems {

	public static List<Item> items = new ArrayList<Item>();

	public static Item key = null;
	public static Item key_01 = null;
	public static Item circuts = null;
	public static Item gunstick = null;
	public static Item power_cell = null;
	public static Item fob_watch = null;
	
	public static Item ray_gun = null;
	
	public static Item void_specs = null;
	public static Item fez = null;
	public static Item bowtie = null;
	
	public static Item sonic_cane = null;
	public static Item umbrella_closed = null;
	public static Item umbrella_open = null;
	public static Item vortex_manip = null;
	public static Item sonic_screwdriver = null;
	public static Item manual = null;
	public static Item sonic_pen = null;
	
	public static Item space_helm = null;
	public static Item space_chest = null;
	public static Item space_legs = null;

	public static Item first_cane = null;
	public static Item fourth_hat = null;
	public static Item thirteen_coat = null;
	public static Item symbiotic_nuclei = null;
	
	public static Item stattenheim_remote = null;
	public static Item sonic_shades = null;
	public static Item time_vector_generator = null;
	public static Item biodampener = null;
	
	public static Item ruby = null;
	public static Item mercuryBottle = null;
	public static Item crushedCinnabar = null;
	
	public static Item hellbent_corridor = null;
	public static Item hellbent_door = null;
	
	// Componenets
	public static Item fluid_link = null;
	public static Item artron_capacitor = null;
	public static Item demat_circut = null;

	
	@SubscribeEvent
	public static void regItems(RegistryEvent.Register<Item> event) {
		for (Item item : items) {
			event.getRegistry().register(item);
		}
	}
	
	public static void init() {
		key = new ItemKey("key");
		circuts = new ItemBase("circuts");
		gunstick = new ItemBase("gunstick");
		power_cell = new ItemBase("power_cell");
		fob_watch = new ItemFobWatch("fob_watch");
		ray_gun = new ItemRayGun("ray_gun");

		void_specs = new ItemHat(EnumClothes.HAT_VOID_SPECS, "void_specs");
		fez = new ItemFez("fez");
		bowtie = new ItemBowTie("bowtie");
		sonic_cane = new ItemSonic("sonic_cane");
		vortex_manip = new ItemVortexManipulator("vortex_manip");
		sonic_screwdriver = new ItemSonic("sonic_screwdriver");
		space_helm = new ItemSpaceSuit(0, EntityEquipmentSlot.HEAD, "space_helm");
		space_chest = new ItemSpaceSuit(1, EntityEquipmentSlot.CHEST, "space_chest");
		space_legs = new ItemSpaceSuit(2, EntityEquipmentSlot.LEGS, "space_legs");
		manual = new ItemManual("tardis_manual");
		sonic_pen = new ItemSonic("sonic_pen");
		key_01 = new ItemKey("key_01");
		first_cane = new ItemCane("first_cane");
		fourth_hat = new ItemHat(EnumClothes.HAT_FOURTH_DOC, "fourth_hat");
		thirteen_coat = new ItemClothing(EnumClothes.CHEST_13TH_COAT, "thirteen_coat");
		symbiotic_nuclei = new ItemSymbioticNuclei("symbiotic_nuclei");
		stattenheim_remote = new ItemRemote("stattenheim_remote");
		time_vector_generator = new ItemTVG("time_vector_generator");
		ruby = new ItemBase("ruby");
		sonic_shades = new ItemHat(EnumClothes.HAT_SONIC_SHADES, "sonic_shades");
		biodampener = new ItemBase("biodampener");
		mercuryBottle = new ItemBase("mercury_bottle");
		crushedCinnabar = new ItemBase("cinnabar");
		hellbent_corridor = new ItemHellbentCorridor("hellbent_corridor");
		hellbent_door = new ItemHellbentDoor("hellbent_door");
		
		// TARDIS Components
		fluid_link = new FluidLink("fluid_link");
		artron_capacitor = new ArtronCapacitor("artron_capacitor");
		demat_circut = new DematerializationCircut("demat_circut");
	}
}
