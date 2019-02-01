package net.tardis.mod.common.dimensions;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.gallifrey.BiomeGallifrey;
import net.tardis.mod.common.dimensions.gallifrey.WorldProviderGallifrey;
import net.tardis.mod.common.dimensions.moon.BiomeMoon;
import net.tardis.mod.common.dimensions.moon.MoonProvider;
import net.tardis.mod.common.dimensions.space.SpaceProvider;
import net.tardis.mod.common.dimensions.telos.BiomeTelos;
import net.tardis.mod.common.dimensions.telos.WorldProviderTelos;
import net.tardis.mod.config.TardisConfig;

import java.util.ArrayList;
import java.util.List;

public class TDimensions {

	public static int TARDIS_ID, SPACE_ID, TELOS_ID, MOON_ID, GALLIFREY_ID;
	public static DimensionType tardisType, spaceType, telosType, MOON_TYPE, GALLIFREY_TYPE;
	
	public static Biome telosBiome = new BiomeTelos(true);
	public static Biome telosBiomeOrange = new BiomeTelos(false);
	public static Biome moonBiome = new BiomeMoon();
	public static Biome gallifreyBiome = new BiomeGallifrey();
	
	
	public static void register() {

		boolean setDim = TardisConfig.Dimensions.setDimension;

		if (setDim)
			TARDIS_ID = TardisConfig.Dimensions.tardisDimension;
		else TARDIS_ID = DimensionManager.getNextFreeDimId();
		tardisType = DimensionType.register("tardis", "_tardis", TARDIS_ID, WorldProviderTardis.class, false);
		DimensionManager.registerDimension(TARDIS_ID, tardisType);

		if (setDim)
			SPACE_ID = TardisConfig.Dimensions.spaceDimension;
		else SPACE_ID = DimensionManager.getNextFreeDimId();
		spaceType = DimensionType.register("space", "_space", SPACE_ID, SpaceProvider.class, false);
		DimensionManager.registerDimension(SPACE_ID, spaceType);

		if (setDim)
			TELOS_ID = TardisConfig.Dimensions.telosDimension;
		else TELOS_ID = DimensionManager.getNextFreeDimId();
		telosType = DimensionType.register("telos", "_telos", TELOS_ID, WorldProviderTelos.class, false);
		DimensionManager.registerDimension(TELOS_ID, telosType);

		if (setDim)
			MOON_ID = TardisConfig.Dimensions.moonDimension;
		else MOON_ID = DimensionManager.getNextFreeDimId();
		MOON_TYPE = DimensionType.register("moon", "_moon", MOON_ID, MoonProvider.class, false);
		DimensionManager.registerDimension(MOON_ID, MOON_TYPE);
		
		if(setDim){
			GALLIFREY_ID = TardisConfig.Dimensions.gallifreyDim;
		} else {
			GALLIFREY_ID = DimensionManager.getNextFreeDimId();
		}
		
		GALLIFREY_TYPE = DimensionType.register("gallifrey", "_gallifrey", GALLIFREY_ID, WorldProviderGallifrey.class, false);
		DimensionManager.registerDimension(GALLIFREY_ID, GALLIFREY_TYPE);
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
			registerBiome(TDimensions.telosBiome, "telos", BiomeDictionary.Type.SNOWY);
			registerBiome(TDimensions.telosBiomeOrange, "telos_orange", BiomeDictionary.Type.SNOWY);
			registerBiome(TDimensions.moonBiome, "moon", BiomeDictionary.Type.COLD);
			registerBiome(TDimensions.gallifreyBiome, "gallifrey", BiomeDictionary.Type.DENSE);
		}
	}
}
