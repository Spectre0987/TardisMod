package net.tardis.mod.common.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.dimensions.space.SpaceProvider;
import net.tardis.mod.config.TardisConfig;

public class TDimensions {

	public static int TARDIS_ID;
	public static DimensionType tardisType;
	
	public static int SPACE_ID;
	public static DimensionType spaceType;
	
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
	}
	
}
