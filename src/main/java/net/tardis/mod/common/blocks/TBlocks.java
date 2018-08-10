package net.tardis.mod.common.blocks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.BlockToyota.BlockToyotaFacing;
import net.tardis.mod.common.blocks.BlockToyota.BlockToyotaSlab;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;
import net.tardis.mod.util.GenerateJson;

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
	public static Block toyota_upper_divider;
	public static Block toyota_platform;
	public static Block toyota_platform_top;
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
	
	public static Block toyota_platform_full;
	public static Block toyota_platform_slab;
	
	public static Block sonicRedstone;
	
	public static Block electric_panel;
	
	public static Block ruby_ore;
	public static Block cinnabar_ore;
	public static Block tardis_coral;
	
	public static Block alembic;
	
	public static Block hellbent_floor;
	public static Block hellbent_glass01;
	public static Block hellbent_glass02;
	public static Block hellbent_glass03;
	public static Block hellbent_glass04;
	public static Block hellbent_glass05;
	public static Block hellbent_glass06;
	public static Block hellbent_glass07;
	public static Block hellbent_glass08;
	public static Block hellbent_roundel01;
	public static Block hellbent_roundel02;
	public static Block hellbent_roundel03;
	public static Block hellbent_silverwall;
	public static Block hellbent_vents;
	public static Block hellbent_wall;
	
	//Exteriors
	public static Block tardis_top_01;
	public static Block tardis_top_02;
	
	//Consoles
	public static Block console_01;
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
			TItems.items.add(new ItemBlock(block).setRegistryName(rl));
		}
	}
	
	public static void register() {
		
		tardis = new BlockTardis();
		register(tardis, "tardis");

		tardis_top = new BlockTardisTop(TileEntityDoor::new);
		register(tardis_top, "tardis_top");
		
		console = new BlockConsole(TileEntityTardis::new);
		register(console, "console");
		
		panel = new BlockPanel();
		register(panel, "panel");
		
		temporal_lab = new BlockTemporalLab();
		register(temporal_lab, "temporal_lab");
		
		food_machine = new BlockFoodMachine();
		register(food_machine, "food_machine");
		
		electric_panel = new BlockEPanel();
		register(electric_panel, "electric_panel");
		
		megalos = new BlockMegalos();
		register(megalos, "megalos");
		
		holoprojector = new BlockHoloprojector();
		register(holoprojector, "holoprojector");
		
		//Toyota
		toyota_hexagon_1 = new BlockToyotaFacing(false);
		register(toyota_hexagon_1, "toyota_hexagon_1");
		toyota_hexagon_2 = new BlockToyotaFacing(false);
		register(toyota_hexagon_2, "toyota_hexagon_2");
		toyota_hexagon_3 = new BlockToyotaFacing(false);
		register(toyota_hexagon_3, "toyota_hexagon_3");
		toyota_hexagon_4 = new BlockToyotaFacing(false);
		register(toyota_hexagon_4, "toyota_hexagon_4");
		
		toyota_hexalight_1 = new BlockToyotaFacing(true);
		register(toyota_hexalight_1, "toyota_hexalight_1");
		toyota_hexalight_2 = new BlockToyotaFacing(true);
		register(toyota_hexalight_2, "toyota_hexalight_2");
		toyota_hexalight_3 = new BlockToyotaFacing(true);
		register(toyota_hexalight_3, "toyota_hexalight_3");
		toyota_hexalight_4 = new BlockToyotaFacing(true);
		register(toyota_hexalight_4, "toyota_hexalight_4");
		
		toyota_hexalight_off1 = new BlockToyotaFacing(false);
		register(toyota_hexalight_off1, "toyota_hexalight_off1");
		toyota_hexalight_off2 = new BlockToyotaFacing(true);
		register(toyota_hexalight_off2, "toyota_hexalight_off2");
		toyota_hexalight_off3 = new BlockToyotaFacing(false);
		register(toyota_hexalight_off3, "toyota_hexalight_off3");
		
		toyota_light_emergency = new BlockToyota(true);
		register(toyota_light_emergency, "toyota_light_emergency");
		toyota_light_off = new BlockToyota(false);
		register(toyota_light_off, "toyota_light_off");
		toyota_light_on = new BlockToyota(true);
		register(toyota_light_on, "toyota_light_on");
		toyota_upper_divider = new BlockToyota(false);
		register(toyota_upper_divider, "toyota_upper_divider");
		
		toyota_platform = new BlockToyota(false);
		register(toyota_platform, "toyota_platform");
		toyota_platform_light = new BlockToyota(true);
		register(toyota_platform_light, "toyota_platform_light");
		toyota_platform_top = new BlockToyota(false);
		register(toyota_platform_top, "toyota_platform_top");
		
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
		
		toyota_platform_full = new BlockToyota(false);
		register(toyota_platform_full, "toyota_platform_full");
		
		toyota_platform_slab = new BlockToyotaSlab(false);
		register(toyota_platform_slab, "toyota_platform_slab");
		
		ruby_ore = new BlockItemDrop(TItems.ruby).setHardness(2F);
		ruby_ore.setHarvestLevel("pickaxe", 2);
		register(ruby_ore, "ruby_ore");
		
		tardis_coral = new BlockTardisCoral();
		register(tardis_coral, "tardis_coral");
		
		alembic = new BlockAlembic();
		register(alembic, "alembic");
		
		cinnabar_ore = new BlockItemDrop(TItems.crushedCinnabar, 3);
		register(cinnabar_ore, "cinnabar_ore");
		
		
		hellbent_floor = new BlockBase();
		register(hellbent_floor, "hellbent_floor");
		
		/*hellbent_glass01 = new BlockBaseGlass();
		register(hellbent_glass01, "hellbent_glass01");
		
		hellbent_glass02 = new BlockBaseGlass();
		register(hellbent_glass02, "hellbent_glass02");
		
		hellbent_glass03 = new BlockBaseGlass();
		register(hellbent_glass03, "hellbent_glass03");
		
		hellbent_glass04 = new BlockBaseGlass();
		register(hellbent_glass04, "hellbent_glass04");
		
		hellbent_glass05 = new BlockBaseGlass();
		register(hellbent_glass05, "hellbent_glass05");
		
		hellbent_glass06 = new BlockBaseGlass();
		register(hellbent_glass06, "hellbent_glass06");
		
		hellbent_glass07 = new BlockBaseGlass();
		register(hellbent_glass07, "hellbent_glass07");
		
		hellbent_glass08 = new BlockBaseGlass();
		register(hellbent_glass08, "hellbent_glass08");*/
		
		hellbent_roundel01 = new BlockBase();
		register(hellbent_roundel01, "hellbent_roundel01");
		
		hellbent_roundel02 = new BlockBase();
		register(hellbent_roundel02, "hellbent_roundel02");
		
		hellbent_roundel03 = new BlockBase();
		register(hellbent_roundel03, "hellbent_roundel03");
		
		hellbent_silverwall = new BlockBase();
		register(hellbent_silverwall, "hellbent_silverwall");
		
		hellbent_vents = new BlockBase();
		register(hellbent_vents, "hellbent_vents");
		
		hellbent_wall = new BlockBase();
		register(hellbent_wall, "hellbent_wall");
	
		
		//Consoles
		console_01 = new BlockConsole(TileEntityTardis01::new);
		register(console_01, "console_01");
		
		
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
	
	public static void registerGen(Block item, String name) {
		GenerateJson.genCubeAll(name);
		register(item, name);
	}
	
}
