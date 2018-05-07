package net.tardis.mod.common.dimensions;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class TDimensions {
	
	public static int id;
	public static DimensionType tardisType;
	
	public static void register() {
		id = DimensionManager.getNextFreeDimId();
		tardisType = DimensionType.register("tardis", "_tardis", id, TardisProvider.class, false);
		DimensionManager.registerDimension(id, tardisType);
	}
	
}
