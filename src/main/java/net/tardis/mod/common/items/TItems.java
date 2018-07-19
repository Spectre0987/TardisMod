package net.tardis.mod.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.clothing.ItemBowTie;
import net.tardis.mod.common.items.clothing.ItemFez;
import net.tardis.mod.common.items.clothing.ItemSpaceSuit;
import net.tardis.mod.common.items.clothing.ItemVoidSpecs;
import net.tardis.mod.common.items.components.ArtronCapacitor;
import net.tardis.mod.common.items.components.DematerializationCircut;
import net.tardis.mod.common.items.components.FluidLink;

@Mod.EventBusSubscriber
public class TItems {
	
	public static List<Item> items = new ArrayList<>();
	
	public static Item key;
	public static Item key_01;
	public static Item circuts;
	public static Item gunstick;
	public static Item power_cell;
	public static Item fob_watch;
	
	public static Item ray_gun;
	
	public static Item void_specs;
	public static Item fez;
	public static Item bowtie;
	
	public static Item sonic_cane;
	public static Item umbrella_closed;
	public static Item umbrella_open;
	public static Item vortex_manip;
	public static Item sonic_screwdriver;
	public static Item manual;
	public static Item sonic_pen;
	
	public static Item space_helm;
	public static Item space_chest;
	public static Item space_legs;
	
	public static Item first_cane;
	public static Item fourth_hat;
	public static Item thirteen_coat;
	
	// Componenets
	
	public static Item fluid_link;
	public static Item artron_capacitor;
	public static Item demat_circut;
	
	public static void register() {
		key = new ItemKey();
		register(key, "key");
		
		circuts = new ItemBase();
		register(circuts, "circuts");
		
		gunstick = new ItemBase();
		register(gunstick, "gunstick");
		
		power_cell = new ItemBase();
		register(power_cell, "power_cell");
		
		fob_watch = new ItemBase();
		register(fob_watch, "fob_watch");
		
		ray_gun = new ItemRayGun();
		register(ray_gun, "ray_gun");
		
		void_specs = new ItemVoidSpecs();
		register(void_specs, "void_specs");
		
		fez = new ItemFez();
		register(fez, "fez");
		
		bowtie = new ItemBowTie();
		register(bowtie, "bowtie");
		
		sonic_cane = new ItemSonic();
		register(sonic_cane, "sonic_cane");
		
		vortex_manip = new ItemVortexManipulator();
		register(vortex_manip, "vortex_manip");
		
		sonic_screwdriver = new ItemSonic();
		register(sonic_screwdriver, "sonic_screwdriver");
		
		space_helm = new ItemSpaceSuit(0, EntityEquipmentSlot.HEAD);
		register(space_helm, "space_helm");
		
		space_chest = new ItemSpaceSuit(1, EntityEquipmentSlot.CHEST);
		register(space_chest, "space_chest");
		
		space_legs = new ItemSpaceSuit(2, EntityEquipmentSlot.LEGS);
		register(space_legs, "space_legs");
		
		manual = new ItemManual();
		register(manual, "tardis_manual");
		
		sonic_pen = new ItemSonic();
		register(sonic_pen, "sonic_pen");
		
		key_01 = new ItemKey();
		register(key_01, "key_01");
		
		first_cane = new ItemCane();
		register(first_cane, "first_cane");
		
		fourth_hat = new ItemFourthHat();
		register(fourth_hat, "fourth_hat");
		
		thirteen_coat = new ItemThirteenCoat();
		register(thirteen_coat, "thirteen_coat");
		
		// TARDIS Components
		fluid_link = new FluidLink();
		register(fluid_link, "fluid_link");
		
		artron_capacitor = new ArtronCapacitor();
		register(artron_capacitor, "artron_capacitor");
		
		demat_circut = new DematerializationCircut();
		register(demat_circut, "demat_circut");
	}
	
	public static void register(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(new ResourceLocation(Tardis.MODID, name));
		items.add(item);
	}
}
