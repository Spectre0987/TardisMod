package net.tardis.mod;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
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
import net.tardis.mod.blocks.TBlocks;
import net.tardis.mod.capabilty.ITimeLord;
import net.tardis.mod.capabilty.TimeLord;
import net.tardis.mod.capabilty.TimeLordCapibiltyStorage;
import net.tardis.mod.controls.ControlDimChange;
import net.tardis.mod.controls.ControlDoor;
import net.tardis.mod.controls.ControlFlight;
import net.tardis.mod.controls.ControlFuel;
import net.tardis.mod.controls.ControlLaunch;
import net.tardis.mod.controls.ControlRandom;
import net.tardis.mod.controls.ControlSTCButton;
import net.tardis.mod.controls.ControlSTCLoad;
import net.tardis.mod.controls.ControlScanner;
import net.tardis.mod.controls.ControlScreen;
import net.tardis.mod.controls.ControlX;
import net.tardis.mod.controls.ControlY;
import net.tardis.mod.controls.ControlZ;
import net.tardis.mod.creativetabs.TardisTab;
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.entities.EntityAngel;
import net.tardis.mod.entities.EntityCam;
import net.tardis.mod.entities.EntityDalek;
import net.tardis.mod.entities.EntityDalekRay;
import net.tardis.mod.entities.EntityForceField;
import net.tardis.mod.entities.EntityTardis;
import net.tardis.mod.handlers.TEventHandler;
import net.tardis.mod.helpers.EntityHelper;
import net.tardis.mod.items.TItems;
import net.tardis.mod.packets.MessageAngel;
import net.tardis.mod.packets.MessageCam;
import net.tardis.mod.packets.MessageHandlerCam;
import net.tardis.mod.packets.MessageHandlerProtocol;
import net.tardis.mod.packets.MessageHandlerTR;
import net.tardis.mod.packets.MessageHelperAngel;
import net.tardis.mod.packets.MessageProtocol;
import net.tardis.mod.packets.MessageTR;
import net.tardis.mod.protocols.TardisProtocolForceField;
import net.tardis.mod.proxy.ServerProxy;
import net.tardis.mod.recipes.TemporalRecipe;
import net.tardis.mod.screwdriver.Hall;
import net.tardis.mod.screwdriver.RecallMode;
import net.tardis.mod.screwdriver.RoundelMode;
import net.tardis.mod.screwdriver.TransmatMode;
import net.tardis.mod.sounds.TSounds;
import net.tardis.mod.tileentity.TileEntityAlembic;
import net.tardis.mod.tileentity.TileEntityDoor;
import net.tardis.mod.tileentity.TileEntitySonicGun;
import net.tardis.mod.tileentity.TileEntityTardis;
import net.tardis.mod.tileentity.TileEntityTemporalLab;
import net.tardis.mod.tileentity.TileEntityTimeRotor;
import net.tardis.mod.tileentity.TileEntityUmbrellaStand;
import net.tardis.mod.world.TardisLoadingCallback;

@Mod(modid = Tardis.MODID, name = Tardis.NAME, version = Tardis.VERSION)
public class Tardis
{
    public static final String MODID = "tardis";
    public static final String NAME = "Tardis Mod";
    public static final String VERSION = "2.0Alpha";
    
    private static Logger logger;
    
    public static CreativeTabs tab;
    
    public static SimpleNetworkWrapper packet_instance;
    
    @Instance
    public static Tardis instance=new Tardis();
    
    @SidedProxy(clientSide="net.tardis.mod.proxy.ClientProxy",serverSide="net.tardis.mod.proxy.ServerProxy")
    public static ServerProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        tab=new TardisTab();
        TSounds.register();
        TBlocks.register();
        TItems.register();
        TDimensions.register();
        EntityHelper.makeGoodBiomes();
        MinecraftForge.EVENT_BUS.register(new TEventHandler());
        packet_instance=NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        EntityHelper.registerStatic(ControlLaunch.class,"launch_lever");
        EntityHelper.registerStatic(ControlX.class,"x_valve");
        EntityHelper.registerStatic(ControlY.class,"y_valve");
        EntityHelper.registerStatic(ControlZ.class,"z_valve");
        EntityHelper.registerStatic(ControlDimChange.class, "dim_change");
        EntityHelper.registerStatic(ControlScreen.class, "screen");
        EntityHelper.registerStatic(ControlRandom.class, "rand_control");
        EntityHelper.registerStatic(ControlDoor.class, "tarids_door");
        EntityHelper.registerStatic(ControlSTCLoad.class,"stc_load");
        EntityHelper.registerStatic(ControlSTCButton.class,"stc_button");
        EntityHelper.registerStatic(ControlScanner.class, "scanner");
        EntityHelper.registerStatic(ControlFlight.class, "control_flight");
        EntityHelper.registerStatic(ControlFuel.class, "fuel");
        EntityHelper.registerStatic(EntityCam.class, "cam");
        EntityHelper.registerStatic(EntityForceField.class, "force_field");
        EntityHelper.registerNoSpawn(EntityTardis.class, "tardis");
        EntityHelper.registerProjectiles(EntityDalekRay.class, "ray_dalek");
        GameRegistry.registerTileEntity(TileEntityTardis.class, "TileEntityTardis");
        GameRegistry.registerTileEntity(TileEntityDoor.class, "TileEntityDoor");
        GameRegistry.registerTileEntity(TileEntityTemporalLab.class, "TileEntityTemporalLab");
        GameRegistry.registerTileEntity(TileEntitySonicGun.class, "TileEntitySonicGun");
        GameRegistry.registerTileEntity(TileEntityUmbrellaStand.class, "TileEntityUmbrellaStand");
        GameRegistry.registerTileEntity(TileEntityAlembic.class, "TileEntityAlembic");
        GameRegistry.registerTileEntity(TileEntityTimeRotor.class, "TileEntityTimeRotor");
        
        packet_instance.registerMessage(MessageHelperAngel.class,MessageAngel.class, 0, Side.SERVER);
        packet_instance.registerMessage(MessageHandlerCam.class, MessageCam.class, 1, Side.CLIENT);
        packet_instance.registerMessage(MessageHandlerTR.class, MessageTR.class, 2, Side.SERVER);
        packet_instance.registerMessage(MessageHandlerProtocol.class, MessageProtocol.class, 3, Side.SERVER);
        
        ScrewdriverMode.register(new RecallMode());
        ScrewdriverMode.register(new TransmatMode());
        ScrewdriverMode.register(new RoundelMode());
        
        ForgeChunkManager.setForcedChunkLoadingCallback(instance, new TardisLoadingCallback());
        
        TemporalRecipe.register(new TemporalRecipe(new ItemStack(TItems.circuts),400));
        TemporalRecipe.register(new TemporalRecipe(new ItemStack(TItems.isotope_64),1200));
        
        TardisProtocol.register(new TardisProtocolForceField());
        
        //CapabilityManager.INSTANCE.register(ITimeLord.class,new TimeLordCapibiltyStorage(),TimeLord.class);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.renderEntities();
        //Ore Dictionary
        OreDictionary.registerOre("oreUranium", TItems.isotope_64);
    }
}
