package net.tardis.mod.common.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
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
	public static Biome telosBiome = new BiomeTelos();
	
	public static void register() {
		if (TardisConfig.Dimensions.setDimension) {
			TARDIS_ID = TardisConfig.Dimensions.tardisDimension;
		}
		else {
			TARDIS_ID = DimensionManager.getNextFreeDimId();
		}
		tardisType = DimensionType.register("tardis", "_tardis", TARDIS_ID, TardisProvider.class, false);
		DimensionManager.registerDimension(TARDIS_ID, tardisType);
		
		if(TardisConfig.Dimensions.setDimension) {
			SPACE_ID = TardisConfig.Dimensions.spaceDimension;
		}
		else {
			SPACE_ID = DimensionManager.getNextFreeDimId();
		}
		spaceType = DimensionType.register("space", "_space", SPACE_ID, SpaceProvider.class, false);
		DimensionManager.registerDimension(SPACE_ID, spaceType);
		
		TELOS_ID = DimensionManager.getNextFreeDimId();
		telosType = DimensionType.register("telos", "telos", TELOS_ID, WorldProviderTelos.class, false);
		DimensionManager.registerDimension(TELOS_ID, telosType);
	}
	
	@EventBusSubscriber(modid = Tardis.MODID)
	public static class BiomeReg{
		
		@SubscribeEvent
		public static void register(RegistryEvent.Register<Biome> event) {
			event.getRegistry().register(TDimensions.telosBiome);
		}
	}
}
