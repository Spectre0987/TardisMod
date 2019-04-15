package net.tardis.mod.common.dimensions;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.gallifrey.WorldProviderGallifrey;
import net.tardis.mod.common.dimensions.gallifrey.biomes.BiomeMountains;
import net.tardis.mod.common.dimensions.gallifrey.biomes.BiomeRedlands;
import net.tardis.mod.common.dimensions.gallifrey.biomes.BiomeWastelands;
import net.tardis.mod.common.dimensions.moon.BiomeMoon;
import net.tardis.mod.common.dimensions.moon.MoonProvider;
import net.tardis.mod.common.dimensions.space.SpaceProvider;
import net.tardis.mod.common.dimensions.telos.BiomeTelos;
import net.tardis.mod.common.dimensions.telos.WorldProviderTelos;
import net.tardis.mod.config.TardisConfig;

import java.util.ArrayList;
import java.util.List;

public class TDimensions {
	
	//Tardis
	public static int TARDIS_ID;
	public static DimensionType DIMTYPE_TARDIS;
	
	//Moon
	public static int MOON_ID;
	public static DimensionType MOON_TYPE;
	public static Biome BIOME_MOON = new BiomeMoon();
	
	//Space
	public static int SPACE_ID;
	public static DimensionType DIMTYPE_SPACE;
	
	//Telos
	public static int TELOS_ID;
	public static DimensionType DIMTYPE_TELOS;
	public static Biome BIOME_TELOS = new BiomeTelos(true);
	public static Biome BIOME_TELOS_ORANGE = new BiomeTelos(false);

	//Gallifrey
	public static int GALLIFREY_ID;
	public static DimensionType GALLIFREY_TYPE;
	public static Biome BIOME_GALLIFREY_REDLANDS = new BiomeRedlands();
	public static Biome BIOME_GALLIFREY_FARMLAND = new BiomeWastelands();
	public static Biome BIOME_GALLIFREY_MOUNTAIN = new BiomeMountains();
	
	
	

	public static void register() {

		boolean setDim = TardisConfig.Dimensions.setDimension;

		if (setDim)
			TARDIS_ID = TardisConfig.Dimensions.tardisDimension;
		else TARDIS_ID = DimensionManager.getNextFreeDimId();
		DIMTYPE_TARDIS = DimensionType.register("tardis", "_tardis", TARDIS_ID, WorldProviderTardis.class, false);
		DimensionManager.registerDimension(TARDIS_ID, DIMTYPE_TARDIS);

		if (setDim)
			SPACE_ID = TardisConfig.Dimensions.spaceDimension;
		else SPACE_ID = DimensionManager.getNextFreeDimId();
		DIMTYPE_SPACE = DimensionType.register("space", "_space", SPACE_ID, SpaceProvider.class, false);
		DimensionManager.registerDimension(SPACE_ID, DIMTYPE_SPACE);

		if (setDim)
			TELOS_ID = TardisConfig.Dimensions.telosDimension;
		else TELOS_ID = DimensionManager.getNextFreeDimId();
		DIMTYPE_TELOS = DimensionType.register("telos", "_telos", TELOS_ID, WorldProviderTelos.class, false);
		DimensionManager.registerDimension(TELOS_ID, DIMTYPE_TELOS);

		if (setDim)
			MOON_ID = TardisConfig.Dimensions.moonDimension;
		else MOON_ID = DimensionManager.getNextFreeDimId();
		MOON_TYPE = DimensionType.register("moon", "_moon", MOON_ID, MoonProvider.class, false);
		DimensionManager.registerDimension(MOON_ID, MOON_TYPE);
		
		if (Tardis.getIsDev()) { //Comment/Remove this on build/release
			if (setDim)
				GALLIFREY_ID = TardisConfig.Dimensions.gallifreyDimension;
			else GALLIFREY_ID = DimensionManager.getNextFreeDimId();
			GALLIFREY_TYPE = DimensionType.register("gallifrey", "_gallifrey", GALLIFREY_ID, WorldProviderGallifrey.class, false);
			DimensionManager.registerDimension(GALLIFREY_ID, GALLIFREY_TYPE);
		}
	}

	@EventBusSubscriber(modid = Tardis.MODID)
	public static class BiomeReg {

		private static List<Biome> BIOMES = new ArrayList<>();

		@SubscribeEvent
		public static void register(RegistryEvent.Register<Biome> event) {
			BIOMES.forEach(biome -> event.getRegistry().register(biome));
		}

		public static void registerBiome(Biome b, String name, BiomeDictionary.Type type) {
			b.setRegistryName(new ResourceLocation(Tardis.MODID, name));
			BIOMES.add(b);
		}

		public static void init() {
			registerBiome(TDimensions.BIOME_TELOS, "telos", BiomeDictionary.Type.SNOWY);
			registerBiome(TDimensions.BIOME_TELOS_ORANGE, "telos_orange", BiomeDictionary.Type.SNOWY);
			registerBiome(TDimensions.BIOME_MOON, "moon", BiomeDictionary.Type.COLD);
			
			registerBiome(TDimensions.BIOME_GALLIFREY_REDLANDS, "redlands", BiomeDictionary.Type.PLAINS);
			registerBiome(TDimensions.BIOME_GALLIFREY_FARMLAND, "farmlands", BiomeDictionary.Type.PLAINS);
			registerBiome(TDimensions.BIOME_GALLIFREY_MOUNTAIN, "mountains", BiomeDictionary.Type.SNOWY);
		}
	}
}
