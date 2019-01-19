package net.tardis.mod.api.disguise;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.tardis.mod.Tardis;
import net.tardis.mod.util.common.helpers.EntityHelper;

import java.util.HashMap;

public class DisguiseRegistry {

	public static final ResourceLocation CACTUS = new ResourceLocation(Tardis.MODID, "shells/cactus.json");
	public static final ResourceLocation TREE = new ResourceLocation(Tardis.MODID, "shells/tree.json");
	public static final ResourceLocation MUSHROOM = new ResourceLocation(Tardis.MODID, "shells/mushroom.json");
	public static HashMap<String, ResourceLocation> DISGUISES = new HashMap<>();

	public static void registerDisguise(String biomeName, ResourceLocation disguiseLoc) {
		DISGUISES.put(biomeName, disguiseLoc);
	}

	/**
	 * Register a disguise for all biomes containing this string
	 **/
	public static void registerDisguiseAllBiomes(String name, ResourceLocation disguiseLoc) {
		for (Biome b : EntityHelper.biomes) {
			if (b.getRegistryName().toString().contains(name)) {
				DISGUISES.put(b.getRegistryName().toString(), disguiseLoc);
				Tardis.LOG.info("DisguiseRegistry has registered " + b.getRegistryName().toString());
			}
		}
	}

	public static ResourceLocation getDisguise(String name) {
		for (String biome : DISGUISES.keySet()) {
			if (biome.equals(name)) {
				return DISGUISES.get(name);
			}
		}
		return null;
	}

	public static void init() {
		registerDisguiseAllBiomes("desert", CACTUS);
		registerDisguiseAllBiomes("plains", TREE);
		registerDisguiseAllBiomes("forest", TREE);
		registerDisguiseAllBiomes("mushroom", MUSHROOM);
	}
}
