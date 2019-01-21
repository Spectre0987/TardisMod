package net.tardis.mod.common.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class TSounds {

	private static ArrayList<SoundEvent> sounds = new ArrayList<SoundEvent>();

	public static SoundEvent takeoff = register("takeoff");
	public static SoundEvent loop = register("loop");
	public static SoundEvent sonic = register("sonic");
	public static SoundEvent dalek_ray = register("dalek_ray");
	public static SoundEvent dalek = register("dalek");
	public static SoundEvent force_field = register("force_field");
	public static SoundEvent door_locked = register("locked");
	public static SoundEvent door_open = register("door_open");
	public static SoundEvent door_closed = register("door_closed");
	public static SoundEvent interior_hum_80 = register("interior_hum_80");
	public static SoundEvent cloister_bell = register("cloister_bell");
	public static SoundEvent drum_beat = register("drum_beat");
	public static SoundEvent engine_stutter = register("stutter");
	public static SoundEvent phone = register("phone");
	public static SoundEvent knocking = register("knocking");
	public static SoundEvent vmSound = register("vm_teleport");
	public static SoundEvent flyLoop = register("fly_loop");
	public static SoundEvent bessieHorn = register("bessie_horn");
	public static SoundEvent control_01 = register("control_01");
	public static SoundEvent control_02 = register("control_02");
	public static SoundEvent control_03 = register("control_03");

	//1963
	public static SoundEvent INTERIOR_HUM_1963 = register("1963_interior_hum");
	public static SoundEvent INTERIOR_DOOR_1963 = register("1963_int_door");
	public static SoundEvent FOOD_MACHINE = register("1963_food_machine");

	public static SoundEvent tardis_land = register("tardis_land");


	public static SoundEvent register(String name) {
		ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
		SoundEvent event = new SoundEvent(rl);
		event.setRegistryName(rl);
		sounds.add(event);
		return event;
	}

	@SubscribeEvent
	public static void regSounds(RegistryEvent.Register<SoundEvent> e) {
		for (SoundEvent sound : sounds) {
			e.getRegistry().register(sound);
		}
	}

}