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
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.items.clothing.ItemBowTie;
import net.tardis.mod.common.items.clothing.ItemFez;
import net.tardis.mod.common.items.clothing.ItemSpaceSuit;
import net.tardis.mod.common.items.components.ArtronCapacitor;
import net.tardis.mod.common.items.components.DematerializationCircut;
import net.tardis.mod.common.items.components.FluidLink;
import net.tardis.mod.common.items.components.ItemComponent;
import net.tardis.mod.common.items.components.ItemTVG;

@Mod.EventBusSubscriber
public class TItems {

	public static List<Item> items = new ArrayList<>();

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

    public static Item interior_door = null;

    public static Item marker = null;
	
    public static Item sonic13th = null;
	public static Item sonicBlaster = createItem(new ItemSonicBlaster(), "sonic_blaster");
	
	public static Item bessie = createItem(new ItemESpawn(EntityBessie::new), "bessie");
	
	public static Item tardis_repairkit = createItem(new ItemRepairKit(), "tardis_repairkit");
	
	// Componenets
	public static Item fluid_link = null;
	public static Item artron_capacitor = null;
	public static Item demat_circut = null;
	public static Item antenna = createItem(new ItemComponent(), "antenna");
	public static Item chameleon_circuit = createItem(new ItemComponent(), "chameleon_circuit");
	public static Item temporal_grace_circuits = createItem(new ItemComponent(), "temporal_grace_circuits");
	public static Item stabilizers = createItem(new ItemComponent(), "stabilizers");


    @SubscribeEvent
	public static void regItems(RegistryEvent.Register<Item> event) {
		for (Item item : items) {
			event.getRegistry().register(item);
		}
	}
	
	public static void init() {
		key = createItem(new ItemKey(), "key");
		circuts = createItem(new ItemBase(), "circuts");
		gunstick = createItem(new ItemBase(), "gunstick");
		power_cell = createItem(new ItemBase(), "power_cell");
		fob_watch = createItem(new ItemFobWatch(), "fob_watch");
		ray_gun = createItem(new ItemRayGun(), "ray_gun");
		void_specs = createItem(new ItemHat(EnumClothes.HAT_VOID_SPECS), "void_specs");
		fez = createItem(new ItemFez(), "fez");
		bowtie = createItem(new ItemBowTie(), "bowtie");
		sonic_cane = createItem(new ItemSonic(), "sonic_cane");
		vortex_manip = createItem(new ItemVortexManipulator(), "vortex_manip");
		sonic_screwdriver = createItem(new ItemSonic(), "sonic_screwdriver");
		space_helm = createItem(new ItemSpaceSuit(0, EntityEquipmentSlot.HEAD), "space_helm");
		space_chest = createItem(new ItemSpaceSuit(1, EntityEquipmentSlot.CHEST), "space_chest");
		space_legs = createItem(new ItemSpaceSuit(2, EntityEquipmentSlot.LEGS), "space_legs");
		manual = createItem(new ItemManual(), "tardis_manual");
		sonic_pen = createItem(new ItemSonic(), "sonic_pen");
		key_01 = createItem(new ItemKey(), "key_01");
		first_cane = createItem(new ItemCane(), "first_cane");
		fourth_hat = createItem(new ItemHat(EnumClothes.HAT_FOURTH_DOC), "fourth_hat");
		thirteen_coat = createItem(new ItemClothing(EnumClothes.CHEST_13TH_COAT), "thirteen_coat");
		symbiotic_nuclei = createItem(new ItemSymbioticNuclei(), "symbiotic_nuclei");
		stattenheim_remote = createItem(new ItemRemote(), "stattenheim_remote");
		time_vector_generator = createItem(new ItemTVG(), "time_vector_generator");
		ruby = createItem(new ItemBase(), "ruby");
		sonic_shades = createItem(new ItemSonicShades(EnumClothes.HAT_SONIC_SHADES), "sonic_shades");
		biodampener = createItem(new ItemBase(), "biodampener");
		mercuryBottle = createItem(new ItemBase(), "mercury_bottle");
		crushedCinnabar = createItem(new ItemBase(), "cinnabar");
		hellbent_corridor = createItem(new ItemHellbentCorridor(), "hellbent_corridor");
		hellbent_door = createItem(new ItemHellbentDoor(), "hellbent_door");
		
		interior_door = createItem(new ItemInteriorDoor(), "interiordoor");
        marker = createItem(new ItemMarker(), "marker");
        sonic13th = createItem(new ItemSonic(), "sonic_screwdriver_13");
		
		// TARDIS Components
		fluid_link = createItem(new FluidLink(), "fluid_link");
		artron_capacitor = createItem(new ArtronCapacitor(), "artron_capacitor");
		demat_circut = createItem(new DematerializationCircut(), "demat_circut");
	}
	
	public static Item createItem(Item item, String name) {
		ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
		item.setTranslationKey("tardis." + name);
		item.setRegistryName(rl);
		items.add(item);
		return item;
	}
}
