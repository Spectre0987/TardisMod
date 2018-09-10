package net.tardis.mod.api.disguise;

import java.util.HashMap;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.tardis.mod.util.helpers.EntityHelper;

public class DisguiseRegistry {
	
	public static HashMap<String, ResourceLocation> DISGUISES = new HashMap<>();
	
	public static void registerDisguise(String biomeName, ResourceLocation disguiseLoc) {
		DISGUISES.put(biomeName, disguiseLoc);
	}
	
	/**Register a disguise for all biomes containing this string**/
	public static void registerDisguiseAllBiomes(String name, ResourceLocation disguiseLoc) {
		for(Biome b : EntityHelper.biomes) {
			if(b.getRegistryName().toString().contains(name))DISGUISES.put(b.getRegistryName().toString(), disguiseLoc);
		}
	}

}
