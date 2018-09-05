package net.tardis.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;
import net.tardis.mod.client.creativetabs.TardisTab;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.commands.CommandDebug;
import net.tardis.mod.common.commands.CommandTardis;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.EntityAdipose;
import net.tardis.mod.common.entities.EntityAirshell;
import net.tardis.mod.common.entities.EntityCompanion;
import net.tardis.mod.common.entities.EntityCorridor;
import net.tardis.mod.common.entities.EntityCybermanInvasion;
import net.tardis.mod.common.entities.EntityCybermanTomb;
import net.tardis.mod.common.entities.EntityDalek;
import net.tardis.mod.common.entities.EntityDalekCasing;
import net.tardis.mod.common.entities.EntityDalekRay;
import net.tardis.mod.common.entities.EntityForceField;
import net.tardis.mod.common.entities.EntityRayCyberman;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.entities.controls.ControlDimChange;
import net.tardis.mod.common.entities.controls.ControlDirection;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.entities.controls.ControlDoorSwitch;
import net.tardis.mod.common.entities.controls.ControlFastReturn;
import net.tardis.mod.common.entities.controls.ControlFlight;
import net.tardis.mod.common.entities.controls.ControlFuel;
import net.tardis.mod.common.entities.controls.ControlLandType;
import net.tardis.mod.common.entities.controls.ControlLaunch;
import net.tardis.mod.common.entities.controls.ControlMag;
import net.tardis.mod.common.entities.controls.ControlPhone;
import net.tardis.mod.common.entities.controls.ControlRandom;
import net.tardis.mod.common.entities.controls.ControlSTCButton;
import net.tardis.mod.common.entities.controls.ControlSTCLoad;
import net.tardis.mod.common.entities.controls.ControlScanner;
import net.tardis.mod.common.entities.controls.ControlSonicSlot;
import net.tardis.mod.common.entities.controls.ControlTelepathicCircuts;
import net.tardis.mod.common.entities.controls.ControlX;
import net.tardis.mod.common.entities.controls.ControlY;
import net.tardis.mod.common.entities.controls.ControlZ;
import net.tardis.mod.common.entities.hellbent.EntityHellbentCorridor;
import net.tardis.mod.common.entities.hellbent.EntityHellbentDoor;
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.protocols.ProtocolARS;
import net.tardis.mod.common.protocols.ProtocolCCircuit;
import net.tardis.mod.common.protocols.ProtocolConsole;
import net.tardis.mod.common.protocols.ProtocolEnabledHADS;
import net.tardis.mod.common.protocols.ProtocolFindDimDRfit;
import net.tardis.mod.common.protocols.ProtocolLock;
import net.tardis.mod.common.protocols.ProtocolRegenRoom;
import net.tardis.mod.common.protocols.ProtocolSystemReadout;
import net.tardis.mod.common.protocols.TardisProtocol;
import net.tardis.mod.common.screwdriver.ScrewdriverHandler;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.SystemAntenna;
import net.tardis.mod.common.systems.SystemCCircuit;
import net.tardis.mod.common.systems.SystemDimension;
import net.tardis.mod.common.systems.SystemFlight;
import net.tardis.mod.common.systems.SystemFluidLinks;
import net.tardis.mod.common.systems.TardisSystems;
import net.tardis.mod.common.tileentity.TileEntityAlembic;
import net.tardis.mod.common.tileentity.TileEntityAlembic.AlembicRecipe;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityEPanel;
import net.tardis.mod.common.tileentity.TileEntityFoodMachine;
import net.tardis.mod.common.tileentity.TileEntityHellbentLight;
import net.tardis.mod.common.tileentity.TileEntityHoloprojector;
import net.tardis.mod.common.tileentity.TileEntityInteriorDoor;
import net.tardis.mod.common.tileentity.TileEntityJsonTester;
import net.tardis.mod.common.tileentity.TileEntityLight;
import net.tardis.mod.common.tileentity.TileEntitySonicGun;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityTardisCoral;
import net.tardis.mod.common.tileentity.TileEntityTemporalLab;
import net.tardis.mod.common.tileentity.TileEntityUmbrellaStand;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.common.tileentity.decoration.TileEntityHelbentRoof;
import net.tardis.mod.common.tileentity.decoration.TileEntityHellbentMonitor;
import net.tardis.mod.common.tileentity.decoration.TileEntityHellbentPole;
import net.tardis.mod.common.tileentity.decoration.TileEntityRoundelChest;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoorCC;
import net.tardis.mod.common.world.TardisLoadingCallback;
import net.tardis.mod.common.world.WorldGenTardis;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.integrations.Galacticraft;
import net.tardis.mod.integrations.WeepingAngel;
import net.tardis.mod.packets.MessageCompanion;
import net.tardis.mod.packets.MessageDamageSystem;
import net.tardis.mod.packets.MessageDemat;
import net.tardis.mod.packets.MessageDoorOpen;
import net.tardis.mod.packets.MessageExteriorChange;
import net.tardis.mod.packets.MessageHandlerProtocol;
import net.tardis.mod.packets.MessageHandlerTeleport;
import net.tardis.mod.packets.MessageIInvSync;
import net.tardis.mod.packets.MessageProtocol;
import net.tardis.mod.packets.MessageSpawnItem;
import net.tardis.mod.packets.MessageTelepathicCircut;
import net.tardis.mod.packets.MessageTeleport;
import net.tardis.mod.packets.MessageUpdateBessie;
import net.tardis.mod.proxy.ServerProxy;
import net.tardis.mod.util.helpers.EntityHelper;

@Mod(modid = Tardis.MODID, name = Tardis.NAME, version = Tardis.VERSION, dependencies = Tardis.DEP, updateJSON = Tardis.UPDATE_JSON_URL)
public class Tardis {

	public static final String MODID = "tardis";
	public static final String NAME = "Tardis Mod";
	public static final String DEP = "after:ic2, galacticraftcore, " + TStrings.ModIds.WEEPING_ANGELS + "; required-after:forge@[14.23.2.2638,)";
	public static final String VERSION = "0.0.7A";
	public static final String UPDATE_JSON_URL = "https://raw.githubusercontent.com/Spectre0987/TardisMod/master/update.json";
	public static Logger LOG = LogManager.getLogger(NAME);

	public static CreativeTabs tab;
	
	public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
	
	public static boolean hasIC2 = false;
	
	public static DamageSource SUFFICATION = new DamageSource("damage.noair");

	@Instance(MODID)
	public static Tardis instance;
	
	@SidedProxy(clientSide = "net.tardis.mod.proxy.ClientProxy", serverSide = "net.tardis.mod.proxy.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		LOG.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
	}


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
		hasIC2 = Loader.isModLoaded(TStrings.ModIds.INDUSTRIAL_CRAFT);
		if (Loader.isModLoaded(TStrings.ModIds.GALACTICRAFT)) Galacticraft.preInit();
		if(Loader.isModLoaded(TStrings.ModIds.WEEPING_ANGELS)) WeepingAngel.preInit();
		
		tab = new TardisTab();
		TItems.init();
		TBlocks.register();
		TDimensions.register();
		EntityHelper.makeGoodBiomes();
		EntityHelper.registerStatic(ControlLaunch.class, "launch_lever");
		EntityHelper.registerStatic(ControlX.class, "x_valve");
		EntityHelper.registerStatic(ControlY.class, "y_valve");
		EntityHelper.registerStatic(ControlZ.class, "z_valve");
		EntityHelper.registerStatic(ControlDimChange.class, "dim_change");
		EntityHelper.registerStatic(ControlRandom.class, "rand_control");
		EntityHelper.registerStatic(ControlDoor.class, "tardis_door");
		EntityHelper.registerStatic(ControlSTCLoad.class, "stc_load");
		EntityHelper.registerStatic(ControlSTCButton.class, "stc_button");
		EntityHelper.registerStatic(ControlScanner.class, "scanner");
		EntityHelper.registerStatic(ControlFlight.class, "control_flight");
		EntityHelper.registerStatic(ControlFuel.class, "fuel");
		EntityHelper.registerStatic(EntityForceField.class, "force_field");
		EntityHelper.registerStatic(ControlLandType.class, "land_type");
		EntityHelper.registerStatic(ControlDirection.class, "direction_control");
		EntityHelper.registerStatic(ControlFastReturn.class, "tardis_fast_return");
		EntityHelper.registerStatic(ControlTelepathicCircuts.class, "telepathic_circuts");
		EntityHelper.registerStatic(ControlDoorSwitch.class, "tardis_door_control");
		EntityHelper.registerStatic(ControlPhone.class, "tardis_phone");
		EntityHelper.registerStatic(ControlMag.class, "tardis_magnitude");
		EntityHelper.registerStatic(ControlSonicSlot.class, "sonic_slot");
		EntityHelper.registerNoSpawn(EntityTardis.class, "tardis");
		EntityHelper.registerProjectiles(EntityDalekRay.class, "ray_dalek");
		EntityHelper.registerProjectiles(EntityRayCyberman.class, "cyber_ray");
		EntityHelper.registerNoSpawn(EntityCorridor.class, "toyota_corridor");
		EntityHelper.registerNoSpawn(EntityAirshell.class, "airshell");
		EntityHelper.registerNoSpawn(EntityDalekCasing.class, "dalek_casing");
		EntityHelper.registerNoSpawn(EntityHellbentCorridor.class, "hellbent_corridor");
		EntityHelper.registerNoSpawn(EntityHellbentDoor.class, "hellbent_door");
		EntityHelper.registerNoSpawn(EntityBessie.class, "bessie");
		EntityHelper.registerNoSpawn(EntityCompanion.class, "companion");
		
		registerTileEntity(TileEntityTardis.class, "TileEntityTardis");
		registerTileEntity(TileEntityDoor.class, "TileEntityDoor");
		registerTileEntity(TileEntityTemporalLab.class, "TileEntityTemporalLab");
		registerTileEntity(TileEntityUmbrellaStand.class, "TileEntityUmbrellaStand");
		registerTileEntity(TileEntityAlembic.class, "TileEntityAlembic");
		registerTileEntity(TileEntityFoodMachine.class, "TileEntityFoodMachine");
		registerTileEntity(TileEntityEPanel.class, "TileEntityEPanel");
		registerTileEntity(TileEntityHoloprojector.class, "TileEntityHoloprojector");
		registerTileEntity(TileEntityTardisCoral.class, "TileEntityTardisCoral");
		registerTileEntity(TileEntityLight.class, "TileEntityLight");
		registerTileEntity(TileEntityHellbentLight.class, "TileEntityHellbentLight");
		registerTileEntity(TileEntityHellbentMonitor.class, "TileEntityHellbentMonitor");
		registerTileEntity(TileEntityHellbentPole.class, "TileEntityHellbentPole");
		registerTileEntity(TileEntityHelbentRoof.class, "TileEntityHelbentRoof");
		registerTileEntity(TileEntityRoundelChest.class, "TileEntityRoundelChest");
		
		registerTileEntity(TileEntityInteriorDoor.class, "TileEntityInteriorDoor");
		
		registerTileEntity(TileEntityJsonTester.class, "TileEntityJsonTester");
		
		//Exteriors
		registerTileEntity(TileEntityDoor01.class, "TileEntityDoor01");
		registerTileEntity(TileEntityDoor03.class, "TileEntityDoor03");
		registerTileEntity(TileEntityDoorCC.class, "TileEntityDoorCC");
		
		//Interiors
		registerTileEntity(TileEntityTardis01.class, "TileEntityTardis01");
		registerTileEntity(TileEntityTardis02.class, "TileEntityTardis02");
		registerTileEntity(TileEntitySonicGun.class, "TileEntitySonicGun");
		
		NETWORK.registerMessage(MessageHandlerProtocol.class, MessageProtocol.class, 1, Side.SERVER);
		NETWORK.registerMessage(MessageHandlerTeleport.class, MessageTeleport.class, 2, Side.SERVER);
		NETWORK.registerMessage(MessageDoorOpen.Handler.class, MessageDoorOpen.class, 3, Side.CLIENT);
		NETWORK.registerMessage(MessageTelepathicCircut.Handler.class, MessageTelepathicCircut.class, 4, Side.SERVER);
		NETWORK.registerMessage(MessageSyncWorldShell.Handler.class, MessageSyncWorldShell.class, 5, Side.CLIENT);
		NETWORK.registerMessage(MessageExteriorChange.Handler.class, MessageExteriorChange.class, 6, Side.SERVER);
		NETWORK.registerMessage(MessageDemat.Handler.class, MessageDemat.class, 7, Side.CLIENT);
		NETWORK.registerMessage(MessageSpawnItem.Handler.class, MessageSpawnItem.class, 8, Side.SERVER);
		NETWORK.registerMessage(MessageDamageSystem.Helper.class, MessageDamageSystem.class, 9, Side.SERVER);
		NETWORK.registerMessage(MessageUpdateBessie.Handler.class, MessageUpdateBessie.class, 10, Side.SERVER);
		NETWORK.registerMessage(MessageIInvSync.Handler.class, MessageIInvSync.class, 11, Side.SERVER);
		NETWORK.registerMessage(MessageCompanion.Handler.class, MessageCompanion.class, 12, Side.SERVER);

		ScrewdriverHandler.init();
		
		ForgeChunkManager.setForcedChunkLoadingCallback(instance, new TardisLoadingCallback());
		
		TardisProtocol.register(new ProtocolCCircuit());
		TardisProtocol.register(new ProtocolEnabledHADS());
		TardisProtocol.register(new ProtocolSystemReadout());
		TardisProtocol.register(new ProtocolConsole());
		TardisProtocol.register(new ProtocolARS());
		TardisProtocol.register(new ProtocolRegenRoom());
		TardisProtocol.register(new ProtocolLock());
		if(Loader.isModLoaded(TStrings.ModIds.DIM_DOORS)) TardisProtocol.register(new ProtocolFindDimDRfit());
		
		if (TardisConfig.USE_ENTITIES.entities) {
			// Register All Mobs Here.
			EntityHelper.registerMob(EntityCybermanInvasion.class, "invasion_cyberman", TardisConfig.USE_ENTITIES.cybermanSpawnChance);
			EntityHelper.registerNoSpawn(EntityDalek.class, "dalek");
			EntityHelper.registerNoSpawn(EntityCybermanTomb.class, "cyberman_tomb");
			EntityHelper.registerMob(EntityAdipose.class,"adipose", TardisConfig.USE_ENTITIES.adiposeSpawnChance);
		}
		proxy.preInit();
		
		TardisSystems.register("flight", SystemFlight.class);
		TardisSystems.register("dimensional", SystemDimension.class);
		TardisSystems.register("fluid_links", SystemFluidLinks.class);
		TardisSystems.register("antenna", SystemAntenna.class);
		TardisSystems.register("chameleon", SystemCCircuit.class);
		
		
		GameRegistry.registerWorldGenerator(new WorldGenTardis(), 1);
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
		PermissionAPI.registerNode(TStrings.Permissions.SUMMON_TARDIS, DefaultPermissionLevel.OP, "Allows players to summon a TARDIS owned by someone");
		PermissionAPI.registerNode(TStrings.Permissions.REMOVE_TARDIS, DefaultPermissionLevel.OP, "Allows players to delete a TARDIS");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
		for(ItemStack cinnabar : OreDictionary.getOres("dustCinnabar")) {
			AlembicRecipe.registerRecipe(cinnabar.getItem(), TItems.mercuryBottle);
		}
	}
	
	public static void registerTileEntity(Class<? extends TileEntity> clazz, String name) {
		GameRegistry.registerTileEntity(clazz, new ResourceLocation(Tardis.MODID, name));
	}
	
	public static boolean getIsDev() {
		return (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event){
		event.registerServerCommand(new CommandTardis());
		if(Tardis.getIsDev()) {
			event.registerServerCommand(new CommandDebug());
		}
	}
}
