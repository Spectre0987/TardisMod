package net.tardis.mod.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.TDimensions;

@Config(modid=Tardis.MODID, name="TARDIS Mod")
public class TardisConfig {

	@Config.LangKey("config.category.dims")
	public static final BlockedDimensions BlockedDimensions=new BlockedDimensions();
	
	public static class BlockedDimensions{
		
		@Config.LangKey("config.dims")
		@Config.Comment("comment.dims")
		public int[] bDims=new int[] {TDimensions.id, 1};
	}
	
	@Mod.EventBusSubscriber(modid = Tardis.MODID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Tardis.MODID)) {
				ConfigManager.sync(Tardis.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
