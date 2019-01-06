package net.tardis.mod.util.common.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.tardis.mod.Tardis;

import java.util.ArrayList;
import java.util.List;

public class EntityHelper {
	
	public static int id = 0;
	public static List<Biome> biomes = new ArrayList<Biome>();
	
	public static void registerStatic(Class entityClass, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 120, 10, false);
	}

	public static void registerMobEgg(Class entityClass, String name, int chance, int primary, int secondary) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 80, 3, false, primary, secondary);
		EntityRegistry.addSpawn(entityClass, chance, 1, 1, EnumCreatureType.MONSTER, biomes.toArray(new Biome[1]));
	}

	public static void registerMob(Class entityClass, String name, int chance) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 80, 3, false);
		EntityRegistry.addSpawn(entityClass, chance, 1, 1, EnumCreatureType.MONSTER, biomes.toArray(new Biome[1]));
	}

	public static void registerNoSpawn(Class entityClass, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 64, 1, true);
	}

	public static void registerNoSpawnEgg(Class entityClass, String name, int primary, int secondary) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 64, 1, true, primary, secondary);
	}
	
	public static void makeGoodBiomes() {
		for (ResourceLocation rl : Biome.REGISTRY.getKeys()) {
			Biome b = Biome.REGISTRY.getObject(rl);
			if (b != Biomes.HELL && b != Biomes.SKY && !b.getRegistryName().toString().contains("ocean")) biomes.add(b);
		}
	}
	
	public static void registerProjectiles(Class entityClass, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 64, 5, true);
	}
	
	public static void registerEntity(Class<? extends Entity> entityClass, String entityName) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, entityName), entityClass, entityName, ++id, Tardis.instance, 64, 5, true, 1, 1);
	}
}
