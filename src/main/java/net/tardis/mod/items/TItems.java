package net.tardis.mod.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.armor.BowTie;
import net.tardis.mod.armor.Fez;
import net.tardis.mod.armor.VoidSpecs;

public class TItems {
	
	public static List<Item> items=new ArrayList<Item>();
	
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
	
	public static void register() {
		
		key=new Key();
		register(key,"key");
		
		circuts=new TItem();
		register(circuts,"circuts");
		
		sonic_screwdriver=new SScrewdriver();
		register(sonic_screwdriver,"sonic_screwdriver");
		
		gunstick=new TItem();
		register(gunstick,"gunstick");
		
		isotope_64=new TItem();
		register(isotope_64,"isotope_64");
		
		ray_gun=new RayGun();
		register(ray_gun,"ray_gun");
		
		void_specs=new VoidSpecs();
		register(void_specs,"void_specs");
		
		fob_watch=new TItem();
		register(fob_watch,"fob_watch");
		
		fez=new Fez();
		register(fez,"fez");
		
		bowtie=new BowTie();
		register(bowtie,"bowtie");
		
		/*sonic_blaster=new SonicBlaster();
		register(sonic_blaster,"sonic_blaster");*/
		
		sonic_cane=new SScrewdriver();
		register(sonic_cane,"cane");
		
		umbrella_closed=new SUmbrella();
		register(umbrella_closed,"umbrella_closed");
		
		umbrella_open=new SUmbrella().setCreativeTab(null);
		register(umbrella_open,"umbrella_open");
	}
	
	public static void register(Item item,String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(new ResourceLocation(Tardis.MODID,name));
		items.add(item);
	}
}
