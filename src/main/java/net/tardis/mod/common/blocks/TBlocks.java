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
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;

@Mod.EventBusSubscriber
public class TBlocks {
	
	public static List<Block> blocks = new ArrayList<Block>();
	
	public static Block tardis;
	public static Block tardis_top;
	public static Block console;
	public static Block panel;
	public static Block temporal_lab;
	public static Block food_machine;
	public static Block megalos;
	public static Block meglos_slab;
	public static Block holoprojector;
	
	public static Block toyota_hexagon_1;
	public static Block toyota_hexagon_2;
	public static Block toyota_hexagon_3;
	public static Block toyota_hexagon_4;
	public static Block toyota_hexalight_1;
	public static Block toyota_hexalight_2;
	public static Block toyota_hexalight_3;
	public static Block toyota_hexalight_4;
	public static Block toyota_hexalight_off1;
	public static Block toyota_hexalight_off2;
	public static Block toyota_hexalight_off3;
	public static Block toyota_light_emergency;
	public static Block toyota_light_off;
	public static Block toyota_light_on;
	public static Block toyota_platform;
	public static Block toyota_platform_light;
	public static Block toyota_roof;
	public static Block toyota_roof_light;
	public static Block toyota_wall;
	public static Block toyota_wallroundel_1;
	public static Block toyota_wallroundel_2;
	public static Block toyota_wallroundel_3;
	public static Block toyota_wallroundel_4;
	public static Block toyota_wallroundel_5;
	public static Block toyota_wallroundel_6;
	
	public static Block sonicRedstone;
	
	public static Block electric_panel;
	
	//Exteriors
	public static Block tardis_top_01;
	public static Block tardis_top_02;
	public static Block json_tester;
	
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

		tardis_top = new BlockTardisTop(TileEntityDoor::new);
		register(tardis_top, "tardis_top");
		
		console = new BlockConsole();
		register(console, "console");
		
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
		
		holoprojector = new BlockHoloprojector();
		register(holoprojector, "holoprojector");
		
		//Toyota
		toyota_hexagon_1 = new BlockToyota(false);
		register(toyota_hexagon_1, "toyota_hexagon_1");
		toyota_hexagon_2 = new BlockToyota(false);
		register(toyota_hexagon_2, "toyota_hexagon_2");
		toyota_hexagon_3 = new BlockToyota(false);
		register(toyota_hexagon_3, "toyota_hexagon_3");
		toyota_hexagon_4 = new BlockToyota(false);
		register(toyota_hexagon_4, "toyota_hexagon_4");
		
		toyota_hexalight_1 = new BlockToyota(true);
		register(toyota_hexalight_1, "toyota_hexalight_1");
		toyota_hexalight_2 = new BlockToyota(true);
		register(toyota_hexalight_2, "toyota_hexalight_2");
		toyota_hexalight_3 = new BlockToyota(true);
		register(toyota_hexalight_3, "toyota_hexalight_3");
		toyota_hexalight_4 = new BlockToyota(true);
		register(toyota_hexalight_4, "toyota_hexalight_4");
		
		toyota_hexalight_off1 = new BlockToyota(false);
		register(toyota_hexalight_off1, "toyota_hexalight_off1");
		toyota_hexalight_off2 = new BlockToyota(true);
		register(toyota_hexalight_off2, "toyota_hexalight_off2");
		toyota_hexalight_off3 = new BlockToyota(false);
		register(toyota_hexalight_off3, "toyota_hexalight_off3");
		
		toyota_light_emergency = new BlockToyota(true);
		register(toyota_light_emergency, "toyota_light_emergency");
		toyota_light_off = new BlockToyota(false);
		register(toyota_light_off, "toyota_light_off");
		toyota_light_on = new BlockToyota(true);
		register(toyota_light_on, "toyota_light_on");
		
		toyota_platform = new BlockToyota(false);
		register(toyota_platform, "toyota_platform");
		toyota_platform_light = new BlockToyota(true);
		register(toyota_platform_light, "toyota_platform_light");
		
		toyota_roof = new BlockToyota(false);
		register(toyota_roof, "toyota_roof");
		toyota_roof_light = new BlockToyota(true);
		register(toyota_roof_light, "toyota_roof_light");
		
		toyota_wall = new BlockToyota(false);
		register(toyota_wall, "toyota_wall");
		toyota_wallroundel_1 = new BlockToyota(true);
		register(toyota_wallroundel_1, "toyota_wallroundel_1");
		toyota_wallroundel_2 = new BlockToyota(false);
		register(toyota_wallroundel_2, "toyota_wallroundel_2");
		toyota_wallroundel_3 = new BlockToyota(true);
		register(toyota_wallroundel_3, "toyota_wallroundel_3");
		toyota_wallroundel_4 = new BlockToyota(false);
		register(toyota_wallroundel_4, "toyota_wallroundel_4");
		toyota_wallroundel_5 = new BlockToyota(false);
		register(toyota_wallroundel_5, "toyota_wallroundel_5");
		toyota_wallroundel_6 = new BlockToyota(false);
		register(toyota_wallroundel_6, "toyota_wallroundel_6");
		
		
		
		//Exteriors

		tardis_top_01 = new BlockTardisTop(TileEntityDoor01::new);
		register(tardis_top_01, "tardis_top_01");

		tardis_top_02 = new BlockTardisTop(TileEntityDoor03::new);
		register(tardis_top_02, "tardis_top_02");
		
		if(Tardis.getIsDev()) {
			json_tester = new BlockJsonTester();
			register(json_tester, "json_tester");
		}
	}
	
}
