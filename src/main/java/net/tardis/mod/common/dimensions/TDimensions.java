package net.tardis.mod.common.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.config.TardisConfig;

public class TDimensions {
	
	public static int id;
	public static DimensionType tardisType;
	
	public static void register() {
		if(TardisConfig.BlockedDimensions.setDimension)
			id = TardisConfig.BlockedDimensions.tardisDimension;
		else id = DimensionManager.getNextFreeDimId();
		tardisType = DimensionType.register("tardis", "_tardis", id, TardisProvider.class, false);
		DimensionManager.registerDimension(id, tardisType);
	}
	
}
