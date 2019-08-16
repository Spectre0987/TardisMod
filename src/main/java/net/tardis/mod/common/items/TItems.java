package net.tardis.mod.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.EnumClothes;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.entities.brak.EntityDoorsBrakSecondary;
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.items.clothing.ItemBowTie;
import net.tardis.mod.common.items.clothing.ItemFez;
import net.tardis.mod.common.items.components.ItemArtronCapacitor;
import net.tardis.mod.common.items.components.ItemComponent;
import net.tardis.mod.common.items.components.ItemDematCircut;
import net.tardis.mod.common.items.components.ItemFluidLink;
import net.tardis.mod.common.items.components.ItemTVG;
import net.tardis.mod.common.sounds.TSounds;

@Mod.EventBusSubscriber
public class TItems {

	public static List<Item> items = new ArrayList<>();

	public static Item key = null;
	public static Item key_01 = null;
	public static Item circuts = null;
	public static Item gunstick = null;
	public static Item power_cell = null;

	public static Item ray_gun = null;

	public static Item void_specs = null;
	public static Item fez = null;
	public static Item bowtie = null;

	public static Item sonic_cane = null;
	public static Item sonic_screwdriver = null;
	public static Item manual = null;

	public static Item first_cane = null;
	public static Item fourth_hat = null;
	public static Item thirteen_coat = null;
	public static Item symbiotic_nuclei = null;

	public static Item stattenheim_remote = null;
	public static Item sonic_shades = null;
	public static Item time_vector_generator = null;
	public static Item biodampener = null;

	public static Item mercuryBottle = null;
	public static Item crushedCinnabar = null;

	public static Item hellbent_corridor = null;
	public static Item hellbent_door = null;

	public static Item interior_door = null;

	public static Item sonic13th = null;

	public static Item bessie = createItem(new ItemESpawn(EntityBessie::new), "bessie");

	public static Item tardis_repairkit = createItem(new ItemRepairKit(), "tardis_repairkit");
	
	public static Item doors_brak = createItem(new ItemBrakDoors(EntityDoorsBrakSecondary::new), "brak_doors_closed");
	public static Item repair_capsule = createItem(new ItemRepairCapsule(), "repair_capsule");

	// Componenets
	public static Item fluid_link = null;
	public static Item artron_capacitor = null;
	public static Item demat_circut = null;
	public static Item antenna = createItem(new ItemComponent(), "antenna");
	public static Item chameleon_circuit = createItem(new ItemComponent(), "chameleon_circuit");
	public static Item temporal_grace_circuits = createItem(new ItemComponent(), "temporal_grace_circuits");
	public static Item stabilizers = createItem(new ItemComponent(), "stabilizers");
	public static Item thermo = createItem(new ItemComponent(), "thermo");
	
	//Sonic screwdrivers
	public static Item sonic_second = createItem(new ItemSonic(TSounds.sonic_generic), "sonic_second");
	public static Item sonic_third = createItem(new ItemSonic(TSounds.sonic_silver), "sonic_third");
	public static Item sonic_fourth = createItem(new ItemSonic(TSounds.sonic_silver), "sonic_fourth");
	public static Item sonic_fifth = createItem(new ItemSonic(TSounds.sonic_silver), "sonic_fifth");
	public static Item sonic_seven = createItem(new ItemSonic(TSounds.sonic_silver), "sonic_seven");
	public static Item sonic_war = createItem(new ItemSonic(TSounds.sonic_silver), "sonic_war");
	public static Item sonic_10 = createItem(new ItemSonic(TSounds.sonic_generic, true), "sonic_10");

	public static Item sonic_romana = createItem(new ItemSonic(TSounds.sonic_generic), "sonic_romana");
	public static Item sonic_lance = createItem(new ItemSonic(TSounds.sonic_generic), "sonic_lance");
	public static Item sonic_lipstick = createItem(new ItemSonic(TSounds.sonic_generic), "sonic_lipstick");
	public static Item sonic_trowel = createItem(new ItemSonic(TSounds.sonic_generic),"sonic_trowel");
	public static Item sonic_pen = createItem(new ItemSonic(TSounds.sonic_generic), "sonic_pen");
	
	//TODO: Add the shrinky- killy thing
	public static Item player_victim = createItem(new ItemPlayerVictim(), "player_victim");

	@SubscribeEvent
	public static void regItems(RegistryEvent.Register<Item> event) {
		items.forEach(item -> event.getRegistry().register(item));
	}

	public static void init() {
		key = createItem(new ItemKey(), "key");
		circuts = createItem(new ItemBase(), "circuts");
		gunstick = createItem(new ItemBase(), "gunstick");
		power_cell = createItem(new ItemBase(), "power_cell");
		ray_gun = createItem(new ItemRayGun(), "ray_gun");
		void_specs = createItem(new ItemHat(EnumClothes.HAT_VOID_SPECS), "void_specs");
		fez = createItem(new ItemFez(), "fez");
		bowtie = createItem(new ItemBowTie(), "bowtie");
		sonic_cane = createItem(new ItemSonic(TSounds.sonic_generic), "sonic_cane");
		sonic_screwdriver = createItem(new ItemSonic(TSounds.sonic_generic), "sonic_screwdriver");
		manual = createItem(new ItemManual(), "tardis_manual");
		key_01 = createItem(new ItemKey(), "key_01");
		first_cane = createItem(new ItemCane(), "first_cane");
		fourth_hat = createItem(new ItemHat(EnumClothes.HAT_FOURTH_DOC), "fourth_hat");
		thirteen_coat = createItem(new ItemClothing(EnumClothes.CHEST_13TH_COAT), "thirteen_coat");
		symbiotic_nuclei = createItem(new ItemSymbioticNuclei(), "symbiotic_nuclei");
		stattenheim_remote = createItem(new ItemRemote(), "stattenheim_remote");
		time_vector_generator = createItem(new ItemTVG(), "time_vector_generator");
		sonic_shades = createItem(new ItemSonicShades(EnumClothes.HAT_SONIC_SHADES), "sonic_shades");
		biodampener = createItem(new ItemBase(), "biodampener");
		mercuryBottle = createItem(new ItemBase(), "mercury_bottle");
		crushedCinnabar = createItem(new ItemBase(), "cinnabar");
		hellbent_corridor = createItem(new ItemHellbentCorridor(), "hellbent_corridor");
		hellbent_door = createItem(new ItemHellbentDoor(), "hellbent_door");

		interior_door = createItem(new ItemInteriorDoor(), "interiordoor");
		sonic13th = createItem(new ItemSonic(TSounds.sonic_generic), "sonic_screwdriver_13");
	

		// TARDIS Components
		fluid_link = createItem(new ItemFluidLink(), "fluid_link");
		artron_capacitor = createItem(new ItemArtronCapacitor(), "artron_capacitor");
		demat_circut = createItem(new ItemDematCircut(), "demat_circuit");
	}

	public static Item createItem(Item item, String name) {
		ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
		item.setTranslationKey("tardis." + name);
		item.setRegistryName(rl);
		items.add(item);
		item.setCreativeTab(TardisTabs.ITEMS);
		return item;
	}
}