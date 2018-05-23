package net.tardis.mod;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import net.tardis.api.protocols.TardisProtocol;
import net.tardis.api.screwdriver.ScrewdriverMode;
import net.tardis.mod.client.creativetabs.TardisTab;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.EntityCam;
import net.tardis.mod.common.entities.EntityDalekRay;
import net.tardis.mod.common.entities.EntityForceField;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.entities.controls.ControlDimChange;
import net.tardis.mod.common.entities.controls.ControlDirection;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.entities.controls.ControlEngine;
import net.tardis.mod.common.entities.controls.ControlFlight;
import net.tardis.mod.common.entities.controls.ControlFuel;
import net.tardis.mod.common.entities.controls.ControlLandType;
import net.tardis.mod.common.entities.controls.ControlLaunch;
import net.tardis.mod.common.entities.controls.ControlRandom;
import net.tardis.mod.common.entities.controls.ControlSTCButton;
import net.tardis.mod.common.entities.controls.ControlSTCLoad;
import net.tardis.mod.common.entities.controls.ControlScanner;
import net.tardis.mod.common.entities.controls.ControlScreen;
import net.tardis.mod.common.entities.controls.ControlX;
import net.tardis.mod.common.entities.controls.ControlY;
import net.tardis.mod.common.entities.controls.ControlZ;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.protocols.TardisProtocolForceField;
import net.tardis.mod.common.recipes.TemporalRecipe;
import net.tardis.mod.common.screwdriver.GRoomMode;
import net.tardis.mod.common.screwdriver.HallwayMode;
import net.tardis.mod.common.screwdriver.RecallMode;
import net.tardis.mod.common.screwdriver.TransmatMode;
import net.tardis.mod.common.tileentity.TileEntityAlembic;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityFoodMachine;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityTemporalLab;
import net.tardis.mod.common.tileentity.TileEntityUmbrellaStand;
import net.tardis.mod.common.world.TardisLoadingCallback;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.handlers.TEventHandler;
import net.tardis.mod.packets.MessageAngel;
import net.tardis.mod.packets.MessageCam;
import net.tardis.mod.packets.MessageDoorOpen;
import net.tardis.mod.packets.MessageHandlerCam;
import net.tardis.mod.packets.MessageHandlerDoorOpen;
import net.tardis.mod.packets.MessageHandlerProtocol;
import net.tardis.mod.packets.MessageHandlerTR;
import net.tardis.mod.packets.MessageHandlerTeleport;
import net.tardis.mod.packets.MessageHelperAngel;
import net.tardis.mod.packets.MessageProtocol;
import net.tardis.mod.packets.MessageTR;
import net.tardis.mod.packets.MessageTeleport;
import net.tardis.mod.proxy.ServerProxy;
import net.tardis.mod.util.helpers.EntityHelper;

@Mod(modid = Tardis.MODID, name = Tardis.NAME, version = Tardis.VERSION)
public class Tardis {
	public static final String MODID = "tardis";
	public static final String NAME = "Tardis Mod";
	public static final String VERSION = "2.0 Alpha";
	
	private static Logger logger;
	
	public static CreativeTabs tab;
	
	public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
	
	@Instance
	public static Tardis instance = new Tardis();
	
	@SidedProxy(clientSide = "net.tardis.mod.proxy.ClientProxy", serverSide = "net.tardis.mod.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		tab = new TardisTab();
		TItems.register();
		TBlocks.register();
		TDimensions.register();
		EntityHelper.makeGoodBiomes();
		MinecraftForge.EVENT_BUS.register(new TEventHandler());
		EntityHelper.registerStatic(ControlLaunch.class, "launch_lever");
		EntityHelper.registerStatic(ControlX.class, "x_valve");
		EntityHelper.registerStatic(ControlY.class, "y_valve");
		EntityHelper.registerStatic(ControlZ.class, "z_valve");
		EntityHelper.registerStatic(ControlDimChange.class, "dim_change");
		EntityHelper.registerStatic(ControlScreen.class, "screen");
		EntityHelper.registerStatic(ControlRandom.class, "rand_control");
		EntityHelper.registerStatic(ControlDoor.class, "tardis_door");
		EntityHelper.registerStatic(ControlSTCLoad.class, "stc_load");
		EntityHelper.registerStatic(ControlSTCButton.class, "stc_button");
		EntityHelper.registerStatic(ControlScanner.class, "scanner");
		EntityHelper.registerStatic(ControlFlight.class, "control_flight");
		EntityHelper.registerStatic(ControlFuel.class, "fuel");
		EntityHelper.registerStatic(ControlEngine.class, "tardis_engine");
		EntityHelper.registerStatic(EntityCam.class, "cam");
		EntityHelper.registerStatic(EntityForceField.class, "force_field");
		EntityHelper.registerStatic(ControlLandType.class, "land_type");
		EntityHelper.registerStatic(ControlDirection.class, "direction_control");
		EntityHelper.registerNoSpawn(EntityTardis.class, "tardis");
		EntityHelper.registerProjectiles(EntityDalekRay.class, "ray_dalek");
		GameRegistry.registerTileEntity(TileEntityTardis.class, "TileEntityTardis");
		GameRegistry.registerTileEntity(TileEntityDoor.class, "TileEntityDoor");
		GameRegistry.registerTileEntity(TileEntityTemporalLab.class, "TileEntityTemporalLab");
		GameRegistry.registerTileEntity(TileEntityUmbrellaStand.class, "TileEntityUmbrellaStand");
		GameRegistry.registerTileEntity(TileEntityAlembic.class, "TileEntityAlembic");
		GameRegistry.registerTileEntity(TileEntityFoodMachine.class, "TileEntityFoodMachine");
		
		NETWORK.registerMessage(MessageHelperAngel.class, MessageAngel.class, 0, Side.SERVER);
		NETWORK.registerMessage(MessageHandlerCam.class, MessageCam.class, 1, Side.CLIENT);
		NETWORK.registerMessage(MessageHandlerTR.class, MessageTR.class, 2, Side.SERVER);
		NETWORK.registerMessage(MessageHandlerProtocol.class, MessageProtocol.class, 3, Side.SERVER);
		NETWORK.registerMessage(MessageHandlerTeleport.class, MessageTeleport.class, 4, Side.SERVER);
		NETWORK.registerMessage(MessageHandlerDoorOpen.class, MessageDoorOpen.class, 5, Side.CLIENT);
		
		ScrewdriverMode.register(new RecallMode());
		ScrewdriverMode.register(new TransmatMode());
		ScrewdriverMode.register(new HallwayMode());
		ScrewdriverMode.register(new GRoomMode());
		
		ForgeChunkManager.setForcedChunkLoadingCallback(instance, new TardisLoadingCallback());
		
		TemporalRecipe.register(new TemporalRecipe(new ItemStack(TItems.circuts), 400));
		TemporalRecipe.register(new TemporalRecipe(new ItemStack(TItems.isotope_64), 1200));
		
		TardisProtocol.register(new TardisProtocolForceField());
		
		if(TardisConfig.USE_ENTITIES.entities) {
			//Register All Mobs Here.
		}
		// CapabilityManager.INSTANCE.register(ITimeLord.class,new TimeLordCapibiltyStorage(),TimeLord.class);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.renderEntities();
		
		// Ore Dictionary
		OreDictionary.registerOre("oreUranium", TItems.isotope_64);
	}
}
