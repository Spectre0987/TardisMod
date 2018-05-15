package net.tardis.mod.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.clothing.ItemBowtie;
import net.tardis.mod.common.items.clothing.ItemFez;
import net.tardis.mod.common.items.clothing.ItemVoidSpecs;
import net.tardis.mod.common.items.components.ArtronCapacitor;
import net.tardis.mod.common.items.components.DematerializationCircut;
import net.tardis.mod.common.items.components.FluidLink;

public class TItems {
	
	public static List<Item> items=new ArrayList<>();
	
	public static Item key;
	public static Item circuts;
	public static Item gunstick;
	public static Item isotope_64;
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
	
	//Componenets
	
	public static Item fluid_link;
	public static Item artron_capacitor;
	public static Item demat_circut;
	
	public static void register() {
		key=new ItemKey();
		register(key,"key");
		
		circuts=new ItemBase();
		register(circuts,"circuts");
		
		gunstick=new ItemBase();
		register(gunstick,"gunstick");
		
		isotope_64=new ItemBase();
		register(isotope_64,"isotope_64");
		
		fob_watch=new ItemBase();
		register(fob_watch,"fob_watch");
		
		ray_gun=new ItemRayGun();
		register(ray_gun,"ray_gun");
		
		void_specs=new ItemVoidSpecs();
		register(void_specs,"void_specs");
		
		fez=new ItemFez();
		register(fez,"fez");
		
		bowtie=new ItemBowtie();
		register(bowtie,"bowtie");
		
		sonic_cane=new ItemSonic();
		register(sonic_cane,"sonic_cane");
		
		vortex_manip=new ItemVortexManipulator();
		register(vortex_manip,"vortex_manip");
		
		sonic_screwdriver=new ItemSonic();
		register(sonic_screwdriver,"sonic_screwdriver");
		
		//TARDIS Components
		fluid_link=new FluidLink();
		register(fluid_link,"fluid_link");
		
		artron_capacitor=new ArtronCapacitor();
		register(artron_capacitor,"artron_capacitor");
		
		demat_circut=new DematerializationCircut();
		register(demat_circut,"demat_circut");
	}

	public static void register(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(new ResourceLocation(Tardis.MODID, name));
		items.add(item);
	}
}
