package net.tardis.mod;

import net.tardis.mod.common.blocks.BlockSonicWorkbench;
import net.tardis.mod.common.blocks.BlockSuitcase;
import net.tardis.mod.common.blocks.interfaces.IARSBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.api.disguise.DisguiseRegistry;
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.capability.ITardisCap;
import net.tardis.mod.capability.TardisCapStorage;
import net.tardis.mod.client.models.exteriors.TileEntityDoorTT;
import net.tardis.mod.common.ars.ConsoleRoom;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.commands.CommandDebug;
import net.tardis.mod.common.commands.TardisCommand;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.dimensions.TDimensions.BiomeReg;
import net.tardis.mod.common.entities.*;
import net.tardis.mod.common.entities.brak.EntityDoorsBrakSecondary;
import net.tardis.mod.common.entities.controls.*;
import net.tardis.mod.common.entities.hellbent.EntityHellbentCorridor;
import net.tardis.mod.common.entities.hellbent.EntityHellbentDoor;
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.items.ItemSonic;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.protocols.*;
import net.tardis.mod.common.recipes.RepairRecipes;
import net.tardis.mod.common.screwdriver.ScrewdriverHandler;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.*;
import net.tardis.mod.common.tileentity.*;
import net.tardis.mod.common.tileentity.TileEntityAlembic.AlembicRecipe;
import net.tardis.mod.common.tileentity.consoles.*;
import net.tardis.mod.common.tileentity.decoration.*;
import net.tardis.mod.common.tileentity.exteriors.*;
import net.tardis.mod.common.world.TardisLoadingCallback;
import net.tardis.mod.common.world.WorldGenTardis;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.handlers.GuiHandlerTardis;
import net.tardis.mod.integrations.Galacticraft;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.proxy.ServerProxy;
import net.tardis.mod.util.common.helpers.EntityHelper;
import net.tardis.mod.util.common.helpers.FileHelper;


@Mod(modid = Tardis.MODID, name = Tardis.NAME, version = Tardis.VERSION, dependencies = Tardis.DEP, updateJSON = Tardis.UPDATE_JSON_URL)
public class Tardis {

	public static final String MODID = "tardis";
	public static final String NAME = "Tardis Mod";
	public static final String DEP = "after:ic2, galacticraftcore; required-after:forge@[14.23.2.2638,)";
	public static final String VERSION = "0.1.0C";
	public static final String UPDATE_JSON_URL = "https://raw.githubusercontent.com/Spectre0987/TardisMod/master/update.json";
	public static final boolean updateChangesConfig = false;
	public static Logger LOG = LogManager.getLogger(NAME);
	public static boolean hasIC2 = false;

	@Instance(MODID)
	public static Tardis instance;

	@SidedProxy(clientSide = "net.tardis.mod.proxy.ClientProxy", serverSide = "net.tardis.mod.proxy.ServerProxy")
	public static ServerProxy proxy;

	public static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
		GameRegistry.registerTileEntity(clazz, new ResourceLocation(Tardis.MODID, name));
	}

	public static boolean getIsDev() {
		return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
		hasIC2 = Loader.isModLoaded(TStrings.ModIds.INDUSTRIAL_CRAFT);
		if (Loader.isModLoaded(TStrings.ModIds.GALACTICRAFT)) Galacticraft.preInit();
		TItems.init();
		TBlocks.register();
		BiomeReg.init();
		EntityHelper.makeGoodBiomes();
		EntityHelper.registerStatic(ControlLaunch.class, "launch_lever");
		EntityHelper.registerStatic(ControlX.class, "x_valve");
		EntityHelper.registerStatic(ControlY.class, "y_valve");
		EntityHelper.registerStatic(ControlZ.class, "z_valve");
		EntityHelper.registerStatic(ControlDimChange.class, "dim_change");
		EntityHelper.registerStatic(ControlRandom.class, "rand_control");
		EntityHelper.registerStatic(ControlDoor.class, "tardis_door");
		EntityHelper.registerStatic(ControlFuel.class, "fuel");
		EntityHelper.registerStatic(ControlLandType.class, "land_type");
		EntityHelper.registerStatic(ControlDirection.class, "direction_control");
		EntityHelper.registerStatic(ControlFastReturn.class, "tardis_fast_return");
		EntityHelper.registerStatic(ControlTelepathicCircuts.class, "telepathic_circuts");
		EntityHelper.registerStatic(ControlDoorSwitch.class, "tardis_door_control");
		EntityHelper.registerStatic(ControlPhone.class, "tardis_phone");
		EntityHelper.registerStatic(ControlMag.class, "tardis_magnitude");
		EntityHelper.registerStatic(ControlSonicSlot.class, "sonic_slot");
		EntityHelper.registerStatic(ControlStabilizers.class, "stabilizers");
		EntityHelper.registerStatic(ControlMonitor.class, "monitor");
		EntityHelper.registerStatic(ControlWaypoint.class, "waypoint_select");
		EntityHelper.registerStatic(EntityDummy.class, "dummy");
		EntityHelper.registerProjectiles(EntityLaserRay.class, "cyber_ray");
		EntityHelper.registerNoSpawn(EntityCorridor.class, "toyota_corridor");
		EntityHelper.registerNoSpawn(EntityDalekCasing.class, "dalek_casing");
		EntityHelper.registerNoSpawn(EntityHellbentCorridor.class, "hellbent_corridor");
		EntityHelper.registerNoSpawn(EntityHellbentDoor.class, "hellbent_door");
		EntityHelper.registerNoSpawn(EntityBessie.class, "bessie");
		EntityHelper.registerNoSpawn(EntityCompanion.class, "companion");
		EntityHelper.registerNoSpawn(EntityDalekSkaro.class, "dalek_scaro");
		EntityHelper.registerStatic(EntityChair.class, "chair");
		EntityHelper.registerStatic(EntityItemMaterializer.class, "item_materializer");
		EntityHelper.registerStatic(EntityDoorsBrakSecondary.class, "doors_brak_second");

		registerTileEntity(TileEntityTardis.class, "TileEntityTardis");
		registerTileEntity(TileEntityDoor.class, "TileEntityDoor");
		registerTileEntity(TileEntityUmbrellaStand.class, "TileEntityUmbrellaStand");
		registerTileEntity(TileEntityAlembic.class, "TileEntityAlembic");
		registerTileEntity(TileEntityFoodMachine.class, "TileEntityFoodMachine");
		registerTileEntity(TileEntityEPanel.class, "TileEntityEPanel");
		registerTileEntity(TileEntityTardisCoral.class, "TileEntityTardisCoral");
		registerTileEntity(TileEntityLight.class, "TileEntityLight");
		registerTileEntity(TileEntityHellbentLight.class, "TileEntityHellbentLight");
		registerTileEntity(TileEntityHellbentMonitor.class, "TileEntityHellbentMonitor");
		registerTileEntity(TileEntityHellbentPole.class, "TileEntityHellbentPole");
		registerTileEntity(TileEntityHelbentRoof.class, "TileEntityHelbentRoof");
		registerTileEntity(TileEntityComponentRepair.class, "TileEntityComponentRepair");
		registerTileEntity(TileEntitySonicGun.class, "TileEntitySonicGun");
		registerTileEntity(TileEntityChair.class, "chair");
		registerTileEntity(TileEntityAmSphere.class, "am_sphere");
		registerTileEntity(TileEntityToyotaSpin.class, "toyota_spinnything");

		registerTileEntity(TileEntityMultiblockMaster.class, "multi_master");
		registerTileEntity(TileEntityMultiblock.class, "multi");

		registerTileEntity(TileEntityJsonTester.class, "TileEntityJsonTester");

		//Exteriors
		registerTileEntity(TileEntityDoor01.class, "TileEntityDoor01");
		registerTileEntity(TileEntityDoor03.class, "TileEntityDoor03");
		registerTileEntity(TileEntityDoor04.class, "TileEntityDoor04");
		registerTileEntity(TileEntityDoor05.class, "TileEntityDoor05");
		registerTileEntity(TileEntityDoorCC.class, "TileEntityDoorCC");
		registerTileEntity(TileEntityDoorClock.class, "TileEntityDoorClock");
		registerTileEntity(TileEntityDoorTT.class, "TileEntityDoorTT");
		registerTileEntity(TileEntityDoorWood.class, "TileEntityDoorWood");
		registerTileEntity(TileEntityDoorWardrobe.class, "exterior_wardrobe");

		//Interiors
		registerTileEntity(TileEntityTardis01.class, "TileEntityTardis01");
		registerTileEntity(TileEntityTardis02.class, "TileEntityTardis02");
		registerTileEntity(TileEntityTardis03.class, "console_3");
		registerTileEntity(TileEntityTardis04.class, "console_4");
		registerTileEntity(TileEntityTardis05.class, "console_5");
		
		registerTileEntity(TileEntitySonicWorkbench.class, "sonic_workbench");
		registerTileEntity(TileEntityItemMaterializer.class, "item_materializer");
		registerTileEntity(TileEntityKerblam.class, "kerblam_box");
		registerTileEntity(TileEntityEgg.class, "ars_egg");

		NetworkHandler.init();

		ScrewdriverHandler.init();

		ForgeChunkManager.setForcedChunkLoadingCallback(instance, new TardisLoadingCallback());

		TardisProtocol.register(new ProtocolCCircuit());
		TardisProtocol.register(new ProtocolEnabledHADS());
		TardisProtocol.register(new ProtocolSystemReadout());
		TardisProtocol.register(new ProtocolConsole());
		if (Loader.isModLoaded(TStrings.ModIds.DIM_DOORS))
			TardisProtocol.register(new ProtocolFindDimDRfit());
		TardisProtocol.register(new ProtocolRepair());
		TardisProtocol.register(new ProtocolWaypoints());
		TardisProtocol.register(new ProtocolToggleHum());
		TardisProtocol.register(new ProtocolChangeInterior());
		TardisProtocol.register(new ProtocolStealth());
		//TardisProtocol.register(new ProtocolForceField());

		// Register All Mobs Here.
		EntityHelper.registerMobEgg(EntityCybermanInvasion.class, "invasion_cyberman", TardisConfig.USE_ENTITIES.cybermanSpawnChance, 5, 4);
		EntityHelper.registerMobEgg(EntityDalek.class, "dalek", 5, 5, 1);
		EntityHelper.registerMobEgg(EntityQuark.class, "quark", 5, 5, 2);
		EntityHelper.registerMobEgg(EntityRaider.class, "TMraider", 5, 5, 2);
		EntityHelper.registerMobEgg(EntityAdipose.class, "adipose", TardisConfig.USE_ENTITIES.adiposeSpawnChance, 5, 3);

		proxy.preInit();

		TardisSystems.register("flight", SystemFlight.class);
		TardisSystems.register("dimensional", SystemDimension.class);
		TardisSystems.register("fluid_links", SystemFluidLinks.class);
		TardisSystems.register("antenna", SystemAntenna.class);
		TardisSystems.register("chameleon", SystemCCircuit.class);
		TardisSystems.register("temporal_grace", SystemTemporalGrace.class);
		TardisSystems.register("stabilizers", SystemStabilizers.class);
		TardisSystems.register("thermo", SystemThermo.class);


		GameRegistry.registerWorldGenerator(new WorldGenTardis(), 1);

		DisguiseRegistry.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandlerTardis());

		RepairRecipes.registerRecipe(TItems.fluid_link, TItems.mercuryBottle);
		RepairRecipes.registerRecipe(TItems.demat_circut, Items.ENDER_PEARL);
		RepairRecipes.registerRecipe(TItems.antenna, TItems.circuts);
		RepairRecipes.registerRecipe(TItems.stabilizers, TItems.circuts);
		RepairRecipes.registerRecipe(TItems.time_vector_generator, Items.ENDER_PEARL);
		RepairRecipes.registerRecipe(TItems.chameleon_circuit, TItems.circuts);
		RepairRecipes.registerRecipe(TItems.temporal_grace_circuits, Items.SHIELD);
		RepairRecipes.registerRecipe(TItems.thermo, Items.IRON_INGOT);
		
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_0.png", "interior/interior_0", new BlockPos(10, 2, 9));
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_1.png", "interior/interior_1", new BlockPos(9, 2, 9));
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_greymatter.png", "interior/interior_greymatter", new BlockPos(17 ,2 ,17));
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_study.png", "interior/interior_study", new BlockPos(14,2,8));
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_lodge.png", "interior/interior_lodge", new BlockPos(17,2,17));
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_aquatic.png", "interior/interior_aquatic", new BlockPos(19,2,18));
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_industrial.png", "interior/interior_industrial", new BlockPos(11,2,17));
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_helian.png", "interior/interior_helian", new BlockPos(18,1,18));
		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_magmatic.png", "interior/interior_magmatic", new BlockPos(21, 3, 16));

		ConsoleRoom.registerConsoleRoom("textures/gui/previews/preview_builder.png", "interior/interior_builder", new BlockPos(9, 1, 9));

		CapabilityManager.INSTANCE.register(ITardisCap.class, new TardisCapStorage(), CapabilityTardis::new);
		
		TileEntitySonicWorkbench.RECIPES.put(TItems.key, new Item[]{TItems.key_01});
		Item[] sonics = new Item[ItemSonic.SONICS.size()];
		for(int sonicID = 0; sonicID < sonics.length; ++sonicID) {
			sonics[sonicID] = ItemSonic.SONICS.get(sonicID);
		}
		TileEntitySonicWorkbench.RECIPES.put(TItems.sonic_screwdriver, sonics);
		
		FileHelper.readOrWriteARS(event.getModConfigurationDirectory());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		// Ore Dictionary
		OreDictionary.registerOre("oreUranium", TItems.power_cell);
		OreDictionary.registerOre("gemRuby", TItems.ruby);
		OreDictionary.registerOre("oreRuby", TBlocks.ruby_ore);
		OreDictionary.registerOre("dustCinnabar", TItems.crushedCinnabar);
		OreDictionary.registerOre("oreCinnabar", TBlocks.cinnabar_ore);

		//Permissions
		PermissionAPI.registerNode(TStrings.Permissions.TP_IN_TARDIS, DefaultPermissionLevel.OP, "Allows players to teleport themself in their TARDIS");
		PermissionAPI.registerNode(TStrings.Permissions.TP_IN_TARDIS_OTHER, DefaultPermissionLevel.OP, "Allows players to teleport themself in the TARDIS of a specified player");
		PermissionAPI.registerNode(TStrings.Permissions.REMOVE_TARDIS, DefaultPermissionLevel.OP, "Allows players to delete a TARDIS");
		PermissionAPI.registerNode(TStrings.Permissions.RESTORE_TARDIS, DefaultPermissionLevel.OP, "Allows players to restore their TARDIS Systems");
		PermissionAPI.registerNode(TStrings.Permissions.GROW, DefaultPermissionLevel.OP, "Allows players to grow their TARDIS Coral faster");
		PermissionAPI.registerNode(TStrings.Permissions.TP_OUT_TARDIS, DefaultPermissionLevel.OP, "Allows players to teleport themself out of their TARDIS");

		//This should be in pre-init, but it seems some mods have a weird obsession with claiming already taken ids
		TDimensions.register();

		TBlocks.BLOCKS.forEach(block -> {
			if (block instanceof IARSBlock && !(block instanceof BlockSonicWorkbench) && !(block instanceof BlockSuitcase)){
				IARSBlock block1 = (IARSBlock) block;
				TileEntityEgg.register(new ItemStack(block1.getItemARS(),64));
			}
		});
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
		for (ItemStack cinnabar : OreDictionary.getOres("dustCinnabar")) {
			AlembicRecipe.registerRecipe(cinnabar.getItem(), TItems.mercuryBottle);
		}

	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new TardisCommand());
		if (Tardis.getIsDev()) {
			event.registerServerCommand(new CommandDebug());
		}
	}
}
