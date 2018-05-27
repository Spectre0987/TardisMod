package net.tardis.mod.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;

@Config(modid=Tardis.MODID, name="TARDIS Mod")
public class TardisConfig {

	@Config.RequiresMcRestart
	@Config.LangKey("config.category.dims")
	public static final Dimensions BlockedDimensions = new Dimensions();
	
	@Config.LangKey("config.entity")
	public static final UseEntities USE_ENTITIES = new UseEntities();
	
	public static class Dimensions{
		
		@Config.LangKey("config.dims")
		public int[] bDims=new int[] {1};
		
		@Config.LangKey("config.setdim")
		public boolean setDimension = false;
		
		@Config.LangKey("config.tDim")
		public int tardisDimension = 10;
	}
	
	public static class UseEntities{
		
		@Config.LangKey("config.entity.allowed")
		public boolean entities = true;
	}
	
	@Mod.EventBusSubscriber(modid = Tardis.MODID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Tardis.MODID)) {
				ConfigManager.sync(Tardis.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
