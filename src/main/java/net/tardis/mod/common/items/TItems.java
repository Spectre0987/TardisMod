package net.tardis.mod.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.armor.BowTie;
import net.tardis.mod.armor.Fez;
import net.tardis.mod.armor.VoidSpecs;

public class TItems {
	
	public static List<Item> items = new ArrayList<Item>();
	
	public static Item key;
	public static Item circuts;
	public static Item sonic_screwdriver;
	
	public static Item gunstick;
	public static Item isotope_64;
	public static Item ray_gun;
	public static Item sonic_blaster;
	
	public static Item void_specs;
	public static Item fob_watch;
	public static Item fez;
	public static Item bowtie;
	public static Item sonic_cane;
	public static Item umbrella_closed;
	public static Item umbrella_open;
	public static Item vortex_manip;
	
	public static void register() {
		
		key = new ItemKey();
		register(key, "key");
		
		circuts = new ItemBase();
		register(circuts, "circuts");
		
		sonic_screwdriver = new ItemSScrewdriver();
		register(sonic_screwdriver, "sonic_screwdriver");
		
		gunstick = new ItemBase();
		register(gunstick, "gunstick");
		
		isotope_64 = new ItemBase();
		register(isotope_64, "isotope_64");
		
		ray_gun = new ItemBaseRayGun();
		register(ray_gun, "ray_gun");
		
		void_specs = new VoidSpecs();
		register(void_specs, "void_specs");
		
		fob_watch = new ItemBase();
		register(fob_watch, "fob_watch");
		
		fez = new Fez();
		register(fez, "fez");
		
		bowtie = new BowTie();
		register(bowtie, "bowtie");
		
		sonic_blaster = new ItemSonicBlaster();
		register(sonic_blaster, "sonic_blaster");
		
		sonic_cane = new ItemSScrewdriver();
		register(sonic_cane, "cane");
		
		umbrella_closed = new ItemSUmbrella();
		register(umbrella_closed, "umbrella_closed");
		
		umbrella_open = new ItemSUmbrella().setCreativeTab(null);
		register(umbrella_open, "umbrella_open");
		
		vortex_manip = new ItemVortexManipulator();
		register(vortex_manip, "vortex_manip");
	}
	
	public static void register(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(new ResourceLocation(Tardis.MODID, name));
		items.add(item);
	}
}
