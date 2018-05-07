package net.tardis.mod.util.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.tardis.mod.Tardis;

public class EntityHelper {
	
	public static int id = 0;
	public static List<Biome> biomes = new ArrayList<Biome>();
	
	public static void registerStatic(Class entityClass, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 32, 10, false);
	}
	
	public static void registerMob(Class entityClass, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 120, 5, true);
		EntityRegistry.addSpawn(entityClass, 50, 0, 1, EnumCreatureType.MONSTER, biomes.toArray(new Biome[1]));
	}
	
	public static void registerNoSpawn(Class entityClass, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID), entityClass, name, ++id, Tardis.instance, 64, 1, true);
	}
	
	public static void makeGoodBiomes() {
		for (ResourceLocation rl : Biome.REGISTRY.getKeys()) {
			Biome b = Biome.REGISTRY.getObject(rl);
			if (b != Biomes.HELL && b != Biomes.SKY) biomes.add(b);
		}
	}
	
	public static void registerProjectiles(Class entityClass, String name) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, name), entityClass, name, ++id, Tardis.instance, 64, 5, true);
	}
	
	public static void registerEntity(Class<? extends Entity> entityClass, String entityName) {
		EntityRegistry.registerModEntity(new ResourceLocation(Tardis.MODID, entityName), entityClass, entityName, ++id, Tardis.instance, 64, 5, true, 1, 1);
	}
}
