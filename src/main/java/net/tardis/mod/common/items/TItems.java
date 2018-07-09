package net.tardis.mod.common.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.renderers.RenderItemFoodMachine;
import net.tardis.mod.client.renderers.items.RendererItemDemat;
import net.tardis.mod.client.renderers.items.RendererItemTardis;
import net.tardis.mod.client.renderers.items.RendererKey;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.items.clothing.ItemBowTie;
import net.tardis.mod.common.items.clothing.ItemFez;
import net.tardis.mod.common.items.clothing.ItemVoidSpecs;
import net.tardis.mod.common.items.clothing.SpaceSuit;
import net.tardis.mod.common.items.components.ArtronCapacitor;
import net.tardis.mod.common.items.components.DematerializationCircut;
import net.tardis.mod.common.items.components.FluidLink;

@Mod.EventBusSubscriber
public class TItems {
	
	public static List<Item> items = new ArrayList<>();
	
	public static Item key;
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
		
		space_helm = new SpaceSuit(SpaceSuit.material, 0, EntityEquipmentSlot.HEAD);
		register(space_helm, "space_helm");
		
		space_chest = new SpaceSuit(SpaceSuit.material, 1, EntityEquipmentSlot.CHEST);
		register(space_chest, "space_chest");
		
		space_legs = new SpaceSuit(SpaceSuit.material, 2, EntityEquipmentSlot.LEGS);
		register(space_legs, "space_legs");
		
		manual = new ItemManual();
		register(manual, "tardis_manual");
		
		sonic_pen = new ItemSonic();
		register(sonic_pen, "sonic_pen");
		
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

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void setUprenders(ModelBakeEvent e) {
		System.out.println("renders");
		demat_circut.setTileEntityItemStackRenderer(new RendererItemDemat());
		key.setTileEntityItemStackRenderer(new RendererKey());
		Item.getItemFromBlock(TBlocks.tardis_top).setTileEntityItemStackRenderer(new RendererItemTardis());

		Item.getItemFromBlock(TBlocks.food_machine).setTileEntityItemStackRenderer(new RenderItemFoodMachine());
	}
}
