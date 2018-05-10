package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public class TBlocks {

	public static Block tardis = register(new BlockTardis(), "tardis");
	public static Block tardis_top = register(new BlockTardisTop(), "tardis_top");
	public static Block console = register(new BlockConsole(), "console");
	public static Block door = register(new BlockBase().setCreativeTab(null), "door");
	public static Block panel = register(new BlockBase().setLightOpacity(0), "panel");
	public static Block light = register(new BlockBase().setLightLevel(1F), "light");
	public static Block homing_beacon = register(new BlockHomingBeacon(), "homing_beacon");
	public static Block chronodine_generator = register(new BlockChronodineGenerator(), "chronodine_generator");;
	public static Block temporal_lab = register(new BlockTemporalLab(), "temporal_lab");;
	public static Block grate = register(new BlockGrate(), "grate");;
	public static Block sonic_block = register(new BlockSonic(), "sonic_block");;
	public static Block umbrella_stand = register(new BlockUmbrellaStand(), "umbrella_stand");
	public static Block alembic = register(new BlockAlembic(), "alembic");;
	public static Block time_rotor = register(new BlockTimeRotor(), "time_rotor");;
	public static Block room_gen = register(new BlockBase(), "room_gen");

	public static Block force_field = register(new BlockModel(), "force_field");;
	public static Block time_rotor_interior = register(new BlockModel(),"time_rotor_interior");

	public static Block register(Block block, String name) {
		ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
		block.setUnlocalizedName(name);
		block.setRegistryName(rl);
		return block;
	}

	@SubscribeEvent
	public static void regBlocks(RegistryEvent.Register<Block> e) {
		for(Field field : TBlocks.class.getDeclaredFields()) {
			Block block = null;
			try {
				block = (Block) field.get(null);
			} catch (IllegalAccessException e1) {
				//No log spam
			}
			e.getRegistry().register(block);
		}
	}
	
}
