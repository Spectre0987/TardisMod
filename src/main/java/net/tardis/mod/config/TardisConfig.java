
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
	public static final Boti BOTI = new Boti();


	public static class Dimensions {

		@Config.LangKey("config.dims")
		public int[] bDims = new int[]{};

		@Config.LangKey("config.dim.usewhitelist")
		@Config.Comment("If this is enabled, only a specific list of dimensions can be travelled to via the Tardis")
		public boolean USE_WHITELIST = false;

		@Config.LangKey("config.setdim")
		@Config.Comment("If this is enabled, You can set dimensions manually below")
		public boolean setDimension = false;

		@Config.LangKey("config.tDim")
		@Config.Comment("Tardis Dimension ID")
		public int tardisDimension = 10;

		@Config.LangKey("config.bhDim")
		@Config.Comment("Space Dimension ID")
		public int spaceDimension = 22;

		@Config.LangKey("config.telosDim")
		@Config.Comment("Telos Dimension ID")
		public int telosDimension = 23;
		
		@Config.LangKey("config.moonDim")
		@Config.Comment("Moon Dimension ID")
		public int moonDimension = 24;
		
		@Config.LangKey("config.gallifreyDim")
		@Config.Comment("Gallifrey Dimension ID")
		public int gallifreyDimension = 24;

		@Config.LangKey("config.tardis.oregen")
		@Config.Comment("Should the TARDIS Mod generate ores?")
		public boolean generateOres = true;
	}

	public static class UseEntities {

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
		@Config.Comment("If enabled, this will give the player a Tardis coral on their first join")
		public boolean givePlayerKey = false;

		@Config.LangKey("config.misc.maxrand")
		@Config.Comment("Set to Zero to make it anywhere in the world.")
		public int maxRandom = 5000;

		@Config.LangKey("config.misc.on.kill")
		public boolean killControlsOnKillCommand = false;

		@Config.LangKey("config.misc.shake")
		public boolean camShake = true;

		@Config.LangKey("config.misc.recharge")
		public float artronRechargeRate = 4F;

		@Config.LangKey("config.misc.warn")
		@Config.Comment("If this is enabled and optifine is detected, then a warning at startup is displayed")
		public boolean warn = true;

	}

	public static class Boti {

		@Config.LangKey("config.boti.enabled")
		@Config.Comment("This is used to toggle the bigger on the inside effect")
		public boolean enabled = true;

		@Config.LangKey("config.boti.skip")
		@Config.Comment("This is used to block any blocks from being rendered in the Bigger on the inside effect (For compatibility) ex. ic2:te or if you want to block all of ic2 ic2:*")
		public String[] modids = {};
		
		@Config.LangKey("config.boti.ticks")
		@Config.Comment("How many ticks before we poll the world again")
		public int botiTickRate = 100;
		
		@Config.LangKey("config.boti.fast")
		@Config.Comment("If this is enabled boti will check sides before rendering. Set this to false if you crash")
		public boolean checkSides = true;
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
