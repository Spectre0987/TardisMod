package net.tardis.mod.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;

@Config(modid = Tardis.MODID, name = "TARDIS Mod")
public class TardisConfig {
	
	@Config.RequiresMcRestart
	@Config.LangKey("config.category.dims")
	public static final Dimensions Dimensions = new Dimensions();
	
	@Config.LangKey("config.entity")
	public static final UseEntities USE_ENTITIES = new UseEntities();
	
	@Config.LangKey("config.misc")
	public static final Misc MISC = new Misc();
	
	@Config.LangKey("config.boti")
	public static final Boti BOTI= new Boti();


	public static class Dimensions {
		
		@Config.LangKey("config.dims")
		public int[] bDims = new int[] {};
		
		@Config.LangKey("config.dim.usewhitelist")
		public boolean USE_WHITELIST = false;
		
		@Config.LangKey("config.setdim")
		public boolean setDimension = false;
		
		@Config.LangKey("config.tDim")
		public int tardisDimension = 10;
		
		@Config.LangKey("config.bhDim")
		public int spaceDimension = 22;

		@Config.LangKey("config.telosDim")
		public int telosDimension = 23;
	}
	
	public static class UseEntities {
		
		@Config.RequiresMcRestart
		@Config.LangKey("config.entity.allowed")
		public boolean entities = true;
		
		@Config.LangKey("config.entity.cybermen.spawnchance")
		public int cybermanSpawnChance = 50;

		@Config.LangKey("config.entity.adipose.spawnchance")
		public int adiposeSpawnChance = 10;
	}
	
	public static class Misc {
		
		@Config.LangKey("config.misc.invasion")
		public boolean invasions = true;
		
		@Config.LangKey("config.misc.givekey")
		public boolean givePlayerKey = false;
		
		@Config.LangKey("config.misc.maxrand")
		@Config.Comment("Set to Zero to make it anywhere in the world.")
		public int maxRandom = 5000;

		@Config.LangKey("config.misc.on.kill")
		public boolean killControlsOnKillCommand = false;
		
		@Config.LangKey("config.misc.shake")
		public boolean camShake = true;
		
	}
	
	public static class Boti{
		
		@Config.LangKey("config.boti.enabled")
		public boolean enabled = true;
		
		@Config.LangKey("config.boti.skip")
		public String[] modids = {};
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
