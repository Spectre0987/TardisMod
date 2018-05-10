package net.tardis.mod.common.items;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.items.clothing.ItemBowTie;
import net.tardis.mod.common.items.clothing.ItemFez;
import net.tardis.mod.common.items.clothing.ItemVoidSpecs;

@Mod.EventBusSubscriber
public class TItems {
	public static Item key = register(new ItemKey(), "key");
	public static Item circuts = register(new ItemBase(), "circuits");
	public static Item sonic_screwdriver = register(new ItemSScrewdriver(), "sonic_screwdriver");
	
	public static Item gunstick = register(new ItemBase(), "gunstick");
	public static Item isotope_64 = register(new ItemBase(), "istotope_64");
	public static Item ray_gun = register(new ItemBaseRayGun(), "ray_gun");
	public static Item sonic_blaster = register(new ItemSonicBlaster(), "sonic_blaster");
	
	public static Item void_specs = register(new ItemVoidSpecs(), "void_specs");
	public static Item fob_watch = register(new ItemBase(), "fob_watch");
	public static Item fez = register(new ItemFez(), "fez");
	public static Item bowtie = register(new ItemBowTie(), "bowtie");
	public static Item sonic_cane = register(new ItemSScrewdriver(), "cane");
	public static Item umbrella_closed = register(new ItemSUmbrella(), "umbrella_closed");
	public static Item umbrella_open = register(new ItemSUmbrella().setCreativeTab(null), "umbrella_open");
	public static Item vortex_manip = register(new ItemVortexManipulator(), "vortex_manip");


	@SubscribeEvent
	public static void regItems(RegistryEvent.Register<Item> e) {
		for (Field field : TBlocks.class.getDeclaredFields()) {
			Block block = null;
			try {
				block = (Block) field.get(null);
			} catch (IllegalAccessException e1) {
				//No log spam
			}
			e.getRegistry().register(new ItemBlock(block).setRegistryName(Tardis.MODID, block.getUnlocalizedName().substring(5)));
		}

		for(Field field : TItems.class.getDeclaredFields()) {
			Item item = null;
			try {
				item = (Item) field.get(null);
			} catch (IllegalAccessException e1) {
				//No log spam
			}
			e.getRegistry().register(item);
		}
	}

	public static Item register(Item item, String name) {
		item.setUnlocalizedName(name);
		item.setRegistryName(new ResourceLocation(Tardis.MODID, name));
		return item;
	}
}
