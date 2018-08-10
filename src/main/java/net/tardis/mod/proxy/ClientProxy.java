package net.tardis.mod.proxy;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.client.EnumClothes;
import net.tardis.mod.client.models.ModelFirstCane;
import net.tardis.mod.client.models.ModelKey01;
import net.tardis.mod.client.models.clothing.ModelVortexM;
import net.tardis.mod.client.overlays.OverlayHandler;
import net.tardis.mod.client.renderers.RenderCorridor;
import net.tardis.mod.client.renderers.RenderInvis;
import net.tardis.mod.client.renderers.RenderItemFoodMachine;
import net.tardis.mod.client.renderers.RenderItemSonicPen;
import net.tardis.mod.client.renderers.RenderScreen;
import net.tardis.mod.client.renderers.RenderTardis;
import net.tardis.mod.client.renderers.consoles.RenderConsole01;
import net.tardis.mod.client.renderers.controls.RenderConsole;
import net.tardis.mod.client.renderers.controls.RenderDoor;
import net.tardis.mod.client.renderers.entities.RenderCyberRay;
import net.tardis.mod.client.renderers.entities.RenderCybermanInvasion;
import net.tardis.mod.client.renderers.entities.RenderCybermanTomb;
import net.tardis.mod.client.renderers.entities.RenderDalek;
import net.tardis.mod.client.renderers.entities.RenderDalekCaseing;
import net.tardis.mod.client.renderers.entities.RenderRay;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor03;
import net.tardis.mod.client.renderers.exteriors.RendererTileDoor01;
import net.tardis.mod.client.renderers.items.RenderItemAlembic;
import net.tardis.mod.client.renderers.items.RenderItemSpaceHelm;
import net.tardis.mod.client.renderers.items.RenderItemTardis02;
import net.tardis.mod.client.renderers.items.RenderItemTardis03;
import net.tardis.mod.client.renderers.items.RenderTEISRItem;
import net.tardis.mod.client.renderers.items.RendererItemDemat;
import net.tardis.mod.client.renderers.items.RendererItemTardis;
import net.tardis.mod.client.renderers.items.RendererKey;
import net.tardis.mod.client.renderers.layers.RenderLayerVortexM;
import net.tardis.mod.client.renderers.tiles.RenderAlembic;
import net.tardis.mod.client.renderers.tiles.RenderFoodMachine;
import net.tardis.mod.client.renderers.tiles.RenderJsonHelper;
import net.tardis.mod.client.renderers.tiles.RenderTemporalLab;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.renderers.tiles.RenderTileHolo;
import net.tardis.mod.client.renderers.tiles.RenderUmbrellaStand;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityCorridor;
import net.tardis.mod.common.entities.EntityCybermanInvasion;
import net.tardis.mod.common.entities.EntityCybermanTomb;
import net.tardis.mod.common.entities.EntityDalek;
import net.tardis.mod.common.entities.EntityDalekCasing;
import net.tardis.mod.common.entities.EntityDalekRay;
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
import net.tardis.mod.common.entities.controls.ControlScreen;
import net.tardis.mod.common.entities.controls.ControlTelepathicCircuts;
import net.tardis.mod.common.entities.controls.ControlX;
import net.tardis.mod.common.entities.controls.ControlY;
import net.tardis.mod.common.entities.controls.ControlZ;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.TileEntityAlembic;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityFoodMachine;
import net.tardis.mod.common.tileentity.TileEntityHoloprojector;
import net.tardis.mod.common.tileentity.TileEntityJsonTester;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityTemporalLab;
import net.tardis.mod.common.tileentity.TileEntityUmbrellaStand;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;
import net.tardis.mod.config.TardisConfig;

public class ClientProxy extends ServerProxy {
	
	public static HashMap<Integer, Class<? extends IRenderHandler>> skyRenderers = new HashMap<>();
	
	@Override
	public void renderEntities() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis.class, new RenderConsole());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmbrellaStand.class, new RenderUmbrellaStand());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor.class, new RenderTileDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFoodMachine.class, new RenderFoodMachine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTemporalLab.class, new RenderTemporalLab());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloprojector.class, new RenderTileHolo());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlembic.class, new RenderAlembic());
		
		//Consoles
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis01.class, new RenderConsole01());
		
		//Exteriors
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor01.class, new RendererTileDoor01());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor03.class, new RenderTileDoor03());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJsonTester.class, new RenderJsonHelper());
		
		// Controls
		RenderingRegistry.registerEntityRenderingHandler(ControlScreen.class, new RenderScreen());
		RenderingRegistry.registerEntityRenderingHandler(ControlDoor.class, new RenderDoor());
		RenderingRegistry.registerEntityRenderingHandler(ControlX.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlY.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlZ.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlLaunch.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlDimChange.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlFlight.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlRandom.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlSTCLoad.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlSTCButton.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlFuel.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlLandType.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlDirection.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlFastReturn.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlTelepathicCircuts.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlDoorSwitch.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlMag.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlPhone.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(EntityCorridor.class, new RenderCorridor());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekCasing.class, new RenderDalekCaseing());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTardis.class, new RenderTardis());
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekRay.class, new RenderRay());
		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanInvasion.class, new RenderCybermanInvasion());
		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanTomb.class, new RenderCybermanTomb());
		RenderingRegistry.registerEntityRenderingHandler(EntityRayCyberman.class, new RenderCyberRay());

		RenderingRegistry.registerEntityRenderingHandler(EntityDalek.class, new RenderDalek());

		TItems.sonic_pen.setTileEntityItemStackRenderer(new RenderItemSonicPen());
		TItems.space_helm.setTileEntityItemStackRenderer(new RenderItemSpaceHelm());
		TItems.vortex_manip.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelVortexM(), ModelVortexM.TEXTURE));
		TItems.key_01.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelKey01(), ModelKey01.TEXTURE));
		TItems.first_cane.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelFirstCane(), ModelFirstCane.TEXTURE));
		TItems.demat_circut.setTileEntityItemStackRenderer(new RendererItemDemat());
		TItems.key.setTileEntityItemStackRenderer(new RendererKey());
		
		Item.getItemFromBlock(TBlocks.tardis_top).setTileEntityItemStackRenderer(new RendererItemTardis());

		Item.getItemFromBlock(TBlocks.food_machine).setTileEntityItemStackRenderer(new RenderItemFoodMachine());
		Item.getItemFromBlock(TBlocks.tardis_top_01).setTileEntityItemStackRenderer(new RenderItemTardis02());
		Item.getItemFromBlock(TBlocks.tardis_top_02).setTileEntityItemStackRenderer(new RenderItemTardis03());
		Item.getItemFromBlock(TBlocks.alembic).setTileEntityItemStackRenderer(new RenderItemAlembic());
	}

	@Override
	public void preInit() {

		EnumClothes.ClothingHandler.init();

		OverlayHandler.init();

		if(!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
			Minecraft.getMinecraft().getFramebuffer().enableStencil();
		}
		
		for(int i : DimensionManager.getStaticDimensionIDs()) {
			WorldProvider wp = DimensionManager.createProviderFor(i);
			if(wp != null && wp.getSkyRenderer() != null) {
				skyRenderers.put(i, wp.getSkyRenderer().getClass());
			}
		}
	}
	
	public static boolean getRenderBOTI() {
		return Minecraft.getMinecraft().getFramebuffer().isStencilEnabled() && TardisConfig.BOTI.enable;
	}


    public static ArrayList<EntityPlayer> layerPlayers = new ArrayList<>();

    @SubscribeEvent
    public static void addLayers(RenderPlayerEvent.Pre e) {
        if (!layerPlayers.contains(e.getEntityPlayer())) {
            RenderPlayer render = e.getRenderer();
            addRenderLayer(new RenderLayerVortexM(render));
            layerPlayers.add(e.getEntityPlayer());
        }
    }

    private static void addRenderLayer(LayerRenderer layer) {
        for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
            playerRender.addLayer(layer);
        }
    }
}
