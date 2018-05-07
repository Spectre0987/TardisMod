package net.tardis.mod.common.sounds;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.tardis.mod.Tardis;

public class TSounds {
	
	public static List<SoundEvent> sounds = new ArrayList<SoundEvent>();
	
	public static SoundEvent takeoff;
	public static SoundEvent loop;
	public static SoundEvent sonic;
	public static SoundEvent dalek_ray;
	public static SoundEvent dalek;
	public static SoundEvent force_field;
	public static SoundEvent door_locked;
	
	public static void register() {
		takeoff = register("takeoff");
		loop = register("loop");
		sonic = register("sonic");
		dalek_ray = register("dalek_ray");
		dalek = register("dalek");
		force_field = register("force_field");
		door_locked = register("locked");
	}
	
	public static SoundEvent register(String name) {
		ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
		SoundEvent event = new SoundEvent(rl);
		event.setRegistryName(rl);
		sounds.add(event);
		return event;
	}
	
}
