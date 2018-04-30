package net.tardis.mod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.items.TItems;

public class TBlocks{
	
	public static List<Block> blocks=new ArrayList<Block>();
	
	public static Block tardis;
	public static Block tardis_top;
	public static Block console;
	public static Block door;
	public static Block panel;
	public static Block light;
	public static Block homing_beacon;
	public static Block chronodine_generator;
	public static Block temporal_lab;
	public static Block grate;
	public static Block sonic_block;
	public static Block umbrella_stand;
	public static Block alembic;
	public static Block time_rotor;
	public static Block room_gen;
	
	public static Block force_field;
	public static Block time_rotor_interior;
	
	public static void register() {
		
		tardis=new TardisBlock();
		register(tardis,"tardis");
		
		console=new Console();
		register(console,"console");
		
		door=new TBlock().setCreativeTab(null);
		register(door,"door");
		
		panel=new TBlock().setLightOpacity(0);
		register(panel,"panel");
		
		tardis_top=new TardisBlockTop();
		register(tardis_top,"tardis_top");
		
		light=new TBlock().setLightLevel(1F);
		register(light,"light");
		
		homing_beacon=new HomingBeacon();
		register(homing_beacon,"homing_beacon");
		
		chronodine_generator=new ChronodineGenerator();
		register(chronodine_generator,"chronodine_generator");
		
		temporal_lab=new TemporalLab();
		register(temporal_lab,"temporal_lab");
		
		grate=new Grate();
		register(grate,"grate");
		
		sonic_block=new SonicBlock();
		register(sonic_block,"sonic_block");
		
		umbrella_stand=new UmbrellaStand();
		register(umbrella_stand,"umbrella_stand");
		
		alembic=new Alembic();
		register(alembic,"alembic");
		
		time_rotor=new TimeRotor();
		register(time_rotor,"time_rotor");
		
		room_gen=new TBlock();
		register(room_gen,"room_gen");
		
		//Block That Exist Solely for Models
		time_rotor_interior=new BlockModel();
		register(time_rotor_interior,"time_rotor_interior");
		
		force_field=new BlockModel();
		register(force_field,"force_field");
		
	}
	
	public static void register(Block block,String name) {
		ResourceLocation rl=new ResourceLocation(Tardis.MODID,name);
		block.setUnlocalizedName(name);
		block.setRegistryName(rl);
		blocks.add(block);
		TItems.items.add(new ItemBlock(block).setRegistryName(rl));
	}

}
