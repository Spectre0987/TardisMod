package net.tardis.mod.common.blocks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;

@Mod.EventBusSubscriber
public class TBlocks {
	
	public static List<Block> blocks = new ArrayList<Block>();
	
	public static Block tardis;
	public static Block tardis_top;
	public static Block console;
	public static Block door;
	public static Block panel;
	public static Block temporal_lab;
	public static Block food_machine;
	public static Block megalos;
	public static Block meglos_slab;
	
	public static Block sonicRedstone;
	
	public static Block electric_panel;
	
	//Exteriors
	public static Block tardis_top_01;
	public static Block tardis_top_02;
	//public static Block json_tester;
	
	public static void register(Block block, String name) {
		ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
		block.setUnlocalizedName(name);
		block.setRegistryName(rl);
		blocks.add(block);
		try {
			Field[] fields = block.getClass().getDeclaredFields();
			for (Field f : fields) {
				Object o = f.get(block);
				if (o instanceof ItemBlock) {
					TItems.items.add(((ItemBlock) o).setRegistryName(rl));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void register() {
		
		tardis = new BlockTardis();
		register(tardis, "tardis");
		
		tardis_top = new BlockTardisTop();
		register(tardis_top, "tardis_top");
		
		console = new BlockConsole();
		register(console, "console");
		
		door = new BlockBase();
		register(door, "door");
		
		panel = new BlockPanel();
		register(panel, "panel");
		
		temporal_lab = new BlockTemporalLab();
		register(temporal_lab, "temporal_lab");
		
		food_machine = new BlockFoodMachine();
		register(food_machine, "food_machine");
		
		if (Tardis.hasIC2)
			electric_panel = new BlockEPanel();
		else
			electric_panel = new BlockBase();
		register(electric_panel, "electric_panel");
		
		megalos = new BlockMegalos();
		register(megalos, "megalos");
		
		//Exteriors
		
		tardis_top_01 = new BlockTardisTop(TileEntityDoor01.class);
		register(tardis_top_01, "tardis_top_01");
		
		tardis_top_02 = new BlockTardisTop(TileEntityDoor03.class);
		register(tardis_top_02, "tardis_top_02");
		
		/*json_tester = new BlockJsonTester();
		register(json_tester, "json_tester");*/
	}
	
}
