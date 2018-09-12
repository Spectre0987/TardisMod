package net.tardis.mod.common.dimensions;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.space.SpaceProvider;
import net.tardis.mod.common.dimensions.telos.BiomeTelos;
import net.tardis.mod.common.dimensions.telos.WorldProviderTelos;
import net.tardis.mod.config.TardisConfig;

public class TDimensions {

	public static int TARDIS_ID;
	public static DimensionType tardisType;
	
	public static int SPACE_ID;
	public static DimensionType spaceType;
	
	public static int TELOS_ID;
	public static DimensionType telosType;
	public static Biome telosBiome = new BiomeTelos(true);
	public static Biome telosBiomeOrange= new BiomeTelos(false);
	
	public static void register() {
		boolean setDim = TardisConfig.Dimensions.setDimension;
		
		if (setDim) {
			TARDIS_ID = TardisConfig.Dimensions.tardisDimension;
		}
		else {
			TARDIS_ID = DimensionManager.getNextFreeDimId();
		}
		tardisType = DimensionType.register("tardis", "_tardis", TARDIS_ID, TardisProvider.class, false);
		DimensionManager.registerDimension(TARDIS_ID, tardisType);
		
		if(setDim) {
			SPACE_ID = TardisConfig.Dimensions.spaceDimension;
		}
		else {
			SPACE_ID = DimensionManager.getNextFreeDimId();
		}
		spaceType = DimensionType.register("space", "_space", SPACE_ID, SpaceProvider.class, false);
		DimensionManager.registerDimension(SPACE_ID, spaceType);
		
		if(setDim) {
			TELOS_ID = TardisConfig.Dimensions.telosDimension;
		}
		else{
			TELOS_ID = DimensionManager.getNextFreeDimId();
		}
		
		telosType = DimensionType.register("telos", "_telos", TELOS_ID, WorldProviderTelos.class, false);
		DimensionManager.registerDimension(TELOS_ID, telosType);
		
		BiomeReg.init();
	}
	
	@EventBusSubscriber(modid = Tardis.MODID)
	public static class BiomeReg{
		
		private static List<Biome> BIOMES = new ArrayList<>();
		
		@SubscribeEvent
		public static void register(RegistryEvent.Register<Biome> event) {
			for(Biome b : BIOMES) {
				event.getRegistry().register(b);
			}
		}
		
		public static void registerBiome(Biome b, String name, BiomeDictionary.Type type) {
			b.setRegistryName(new ResourceLocation(Tardis.MODID, name));
			BIOMES.add(b);
		}
		
		public static void init() {
			registerBiome(TDimensions.telosBiome, "telos", BiomeDictionary.Type.SNOWY);
			registerBiome(TDimensions.telosBiomeOrange, "telos_orange", BiomeDictionary.Type.SNOWY);
		}
	}
}
