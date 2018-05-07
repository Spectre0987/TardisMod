package net.tardis.mod.common.sounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;

@Mod.EventBusSubscriber
public class TSounds {

	public static SoundEvent takeoff = register("takeoff");
	public static SoundEvent loop = register("loop");
	public static SoundEvent sonic = register("sonic");
	public static SoundEvent dalek_ray  = register("dalek_ray");
	public static SoundEvent dalek = register("dalek");
	public static SoundEvent force_field = register("force_field");
	public static SoundEvent door_locked = register("locked");

	public static SoundEvent register(String name) {
		ResourceLocation rl = new ResourceLocation(Tardis.MODID, name);
		SoundEvent event = new SoundEvent(rl);
		event.setRegistryName(rl);
		return event;
	}

	@SubscribeEvent
	public static void regSounds(RegistryEvent.Register<SoundEvent> e) {
		for(Field field : TSounds.class.getDeclaredFields()) {
			SoundEvent sound = null;
			try {
				sound = (SoundEvent) field.get(null);
			} catch (IllegalAccessException e1) {
				//No log spam
			}
			System.out.println(sound.getSoundName());
			e.getRegistry().register(sound);
		}
	}

}
