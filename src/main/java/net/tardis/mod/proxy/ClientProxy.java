package net.tardis.mod.proxy;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.EnumClothes;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.TardisKeyBinds;
import net.tardis.mod.client.colorhandlers.BlockColorTelos;
import net.tardis.mod.client.guis.GuiConsoleChange;
import net.tardis.mod.client.guis.GuiToggleHum;
import net.tardis.mod.client.models.ModelBlocks;
import net.tardis.mod.client.models.decoration.ModelBChair;
import net.tardis.mod.client.models.exteriors.ModelTardis01;
import net.tardis.mod.client.models.exteriors.ModelTardis02;
import net.tardis.mod.client.models.exteriors.ModelTardis03;
import net.tardis.mod.client.models.exteriors.ModelTardis04;
import net.tardis.mod.client.models.exteriors.ModelTardis05;
import net.tardis.mod.client.models.exteriors.ModelTardisClock;
import net.tardis.mod.client.models.exteriors.ModelTardisTT;
import net.tardis.mod.client.models.exteriors.ModelTardisWoodDoor;
import net.tardis.mod.client.models.exteriors.ModelWardrobe;
import net.tardis.mod.client.models.exteriors.TileEntityDoorTT;
import net.tardis.mod.client.models.interiors.ModelInteriorDoor01;
import net.tardis.mod.client.models.interiors.ModelInteriorDoor02;
import net.tardis.mod.client.models.interiors.ModelInteriorDoor03;
import net.tardis.mod.client.models.items.ModelFirstCane;
import net.tardis.mod.client.models.items.ModelSonic13;
import net.tardis.mod.client.overlays.OverlayHandler;
import net.tardis.mod.client.renderers.RenderInvis;
import net.tardis.mod.client.renderers.TEISRVictim;
import net.tardis.mod.client.renderers.consoles.RenderConsole01;
import net.tardis.mod.client.renderers.consoles.RenderConsole02;
import net.tardis.mod.client.renderers.consoles.RenderConsole03;
import net.tardis.mod.client.renderers.consoles.RenderConsole04;
import net.tardis.mod.client.renderers.consoles.RenderConsole05;
import net.tardis.mod.client.renderers.controls.RenderConsole;
import net.tardis.mod.client.renderers.controls.RenderDoor;
import net.tardis.mod.client.renderers.controls.RenderSonicSlot;
import net.tardis.mod.client.renderers.decorations.RenderAmSphere;
import net.tardis.mod.client.renderers.decorations.RenderToyotaSpin;
import net.tardis.mod.client.renderers.decorations.RendererChairBR;
import net.tardis.mod.client.renderers.decorations.hellbent.RenderHellbentCorridor;
import net.tardis.mod.client.renderers.decorations.hellbent.RenderHellbentDoor;
import net.tardis.mod.client.renderers.decorations.hellbent.RenderHellbentLight;
import net.tardis.mod.client.renderers.decorations.hellbent.RenderHellbentMonitor;
import net.tardis.mod.client.renderers.decorations.hellbent.RenderHellbentPole;
import net.tardis.mod.client.renderers.decorations.hellbent.RenderHellbentRoof;
import net.tardis.mod.client.renderers.entities.RenderCompanion;
import net.tardis.mod.client.renderers.entities.RenderCybermanInvasion;
import net.tardis.mod.client.renderers.entities.RenderDalek;
import net.tardis.mod.client.renderers.entities.RenderDalekScaro;
import net.tardis.mod.client.renderers.entities.decoration.RenderBrakDoors;
import net.tardis.mod.client.renderers.entities.projectiles.RenderLaserRay;
import net.tardis.mod.client.renderers.entities.vehicles.RenderBessie;
import net.tardis.mod.client.renderers.entities.vehicles.RenderTardisEntity;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor03;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor04;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor05;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorCC;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorClock;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorTT;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorWardrobe;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorWood;
import net.tardis.mod.client.renderers.exteriors.RendererTileDoor01;
import net.tardis.mod.client.renderers.items.RenderItemAlembic;
import net.tardis.mod.client.renderers.items.RenderItemTardis;
import net.tardis.mod.client.renderers.items.RenderItemTardis02;
import net.tardis.mod.client.renderers.items.RenderTEISRItem;
import net.tardis.mod.client.renderers.tiles.RenderAlembic;
import net.tardis.mod.client.renderers.tiles.RenderCorridor;
import net.tardis.mod.client.renderers.tiles.RenderElectricPanel;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.common.blocks.BlockConsole;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityChair;
import net.tardis.mod.common.entities.EntityCompanion;
import net.tardis.mod.common.entities.EntityCompanion.EnumCompanionType;
import net.tardis.mod.common.entities.EntityCorridor;
import net.tardis.mod.common.entities.EntityCybermanInvasion;
import net.tardis.mod.common.entities.EntityDalek;
import net.tardis.mod.common.entities.EntityDalekSkaro;
import net.tardis.mod.common.entities.EntityLaserRay;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.entities.brak.EntityDoorsBrakSecondary;
import net.tardis.mod.common.entities.controls.ControlDimChange;
import net.tardis.mod.common.entities.controls.ControlDirection;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.entities.controls.ControlDoorSwitch;
import net.tardis.mod.common.entities.controls.ControlFastReturn;
import net.tardis.mod.common.entities.controls.ControlFuel;
import net.tardis.mod.common.entities.controls.ControlLandType;
import net.tardis.mod.common.entities.controls.ControlLaunch;
import net.tardis.mod.common.entities.controls.ControlMag;
import net.tardis.mod.common.entities.controls.ControlMonitor;
import net.tardis.mod.common.entities.controls.ControlPhone;
import net.tardis.mod.common.entities.controls.ControlRandom;
import net.tardis.mod.common.entities.controls.ControlSonicSlot;
import net.tardis.mod.common.entities.controls.ControlStabilizers;
import net.tardis.mod.common.entities.controls.ControlTelepathicCircuts;
import net.tardis.mod.common.entities.controls.ControlWaypoint;
import net.tardis.mod.common.entities.controls.ControlX;
import net.tardis.mod.common.entities.controls.ControlY;
import net.tardis.mod.common.entities.controls.ControlZ;
import net.tardis.mod.common.entities.hellbent.EntityHellbentCorridor;
import net.tardis.mod.common.entities.hellbent.EntityHellbentDoor;
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.TileEntityAlembic;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityEPanel;
import net.tardis.mod.common.tileentity.TileEntityHellbentLight;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis03;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis04;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis05;
import net.tardis.mod.common.tileentity.decoration.TileEntityAmSphere;
import net.tardis.mod.common.tileentity.decoration.TileEntityChair;
import net.tardis.mod.common.tileentity.decoration.TileEntityHelbentRoof;
import net.tardis.mod.common.tileentity.decoration.TileEntityHellbentMonitor;
import net.tardis.mod.common.tileentity.decoration.TileEntityHellbentPole;
import net.tardis.mod.common.tileentity.decoration.TileEntityToyotaSpin;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor04;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor05;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoorCC;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoorClock;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoorWardrobe;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoorWood;
import net.tardis.mod.config.TardisConfig;

@EventBusSubscriber(modid = Tardis.MODID, value = Side.CLIENT)
public class ClientProxy extends ServerProxy {

	public static final boolean disableControlRender = false;
	public static ArrayList<EntityPlayer> layerPlayers = new ArrayList<>();

	public static boolean getRenderBOTI() {
		return Minecraft.getMinecraft().getFramebuffer().isStencilEnabled() && TardisConfig.BOTI.enabled;
	}

	@SubscribeEvent
	public static void addLayers(RenderPlayerEvent.Pre e) {
		EntityPlayer player = e.getEntityPlayer();

		if (!layerPlayers.contains(player)) {
			RenderPlayer render = e.getRenderer();
			//addRenderLayer(new RenderLayerVortexM(render));
			layerPlayers.add(player);
		}
	}

	private static void addRenderLayer(LayerRenderer layer) {
		for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
			playerRender.addLayer(layer);
		}
	}

	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis.class, new RenderConsole());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor.class, new RenderTileDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlembic.class, new RenderAlembic());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEPanel.class, new RenderElectricPanel());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHellbentLight.class, new RenderHellbentLight());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHellbentMonitor.class, new RenderHellbentMonitor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHellbentPole.class, new RenderHellbentPole());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHelbentRoof.class, new RenderHellbentRoof());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityToyotaSpin.class, new RenderToyotaSpin());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChair.class, new RendererChairBR());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAmSphere.class, new RenderAmSphere());

		//Consoles
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis01.class, new RenderConsole01());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis02.class, new RenderConsole02());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis03.class, new RenderConsole03());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis04.class, new RenderConsole04());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis05.class, new RenderConsole05());

		//Exteriors
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor01.class, new RendererTileDoor01());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor03.class, new RenderTileDoor03());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor04.class, new RenderTileDoor04());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor05.class, new RenderTileDoor05());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorCC.class, new RenderTileDoorCC());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorClock.class, new RenderTileDoorClock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorTT.class, new RenderTileDoorTT());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorWood.class, new RenderTileDoorWood());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorWardrobe.class, new RenderTileDoorWardrobe());

		// Controls
		if (!ClientProxy.disableControlRender) {
			RenderingRegistry.registerEntityRenderingHandler(ControlX.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlY.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlZ.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlLaunch.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlDimChange.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlRandom.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlFuel.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlLandType.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlDirection.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlFastReturn.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlTelepathicCircuts.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlDoorSwitch.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlMag.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlPhone.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlSonicSlot.class, RenderSonicSlot::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlStabilizers.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlMonitor.class, RenderInvis::new);
			RenderingRegistry.registerEntityRenderingHandler(ControlWaypoint.class, RenderInvis::new);
		}

		RenderingRegistry.registerEntityRenderingHandler(ControlDoor.class, RenderDoor::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCorridor.class, RenderCorridor::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBessie.class, RenderBessie::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekSkaro.class, RenderDalekScaro::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityChair.class, RenderInvis::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityHellbentCorridor.class, RenderHellbentCorridor::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHellbentDoor.class, RenderHellbentDoor::new);


		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanInvasion.class, RenderCybermanInvasion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserRay.class, RenderLaserRay::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoorsBrakSecondary.class, RenderBrakDoors::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityDalek.class, RenderDalek::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityCompanion.class, RenderCompanion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTardis.class, RenderTardisEntity::new);
		

		EnumExterior.FIRST.setupModels(new ModelTardis01(), new ModelInteriorDoor01(), RenderTileDoor.TEXTURE);
		EnumExterior.SECOND.setupModels(new ModelTardis02(), new ModelInteriorDoor02(), RendererTileDoor01.TEXTURE);
		EnumExterior.THIRD.setupModels(new ModelTardis03(), new ModelInteriorDoor03(), RenderTileDoor03.TEXTURE);
		EnumExterior.FOURTH.setupModels(new ModelTardis04(), new ModelInteriorDoor01(), RenderTileDoor04.TEXTURE);
		EnumExterior.FIFTH.setupModels(new ModelTardis05(), new ModelInteriorDoor01(), RenderTileDoor05.TEXTURE);
		EnumExterior.TT.setupModels(new ModelTardisTT(), new ModelInteriorDoor01(), RenderTileDoorTT.TEXTURE);
		EnumExterior.CC.setupModels(new ModelBlocks(new ResourceLocation(Tardis.MODID + ":shells/tree.json")), new ModelInteriorDoor01(), RenderTileDoor.TEXTURE);
		EnumExterior.CLOCK.setupModels(new ModelTardisClock(), new ModelInteriorDoor01(), RenderTileDoorClock.TEXTURE);
		EnumExterior.WARDROBE.setupModels(new ModelWardrobe(), new ModelInteriorDoor01(), RenderTileDoorWardrobe.TEXTURE);
		EnumExterior.WOOD_DOOR.setupModels(new ModelTardisWoodDoor(), new ModelInteriorDoor01(), RenderTileDoor.TEXTURE);

	}

	@Override
	public void openGui(int id, GuiContext context) {
		if(id == 0)
			Minecraft.getMinecraft().displayGuiScreen(new GuiToggleHum(context.pos, context.dimension));
	}

	@Override
	public void preInit() {
		registerRenders();
		EnumClothes.ClothingHandler.init();
		OverlayHandler.init();
		for(Block block : TBlocks.BLOCKS) {
			if(block instanceof BlockConsole) {
				GuiConsoleChange.TARDISes.put(block.getDefaultState(), 
						(TileEntityTardis)block.createTileEntity(Minecraft.getMinecraft().world, block.getDefaultState()));
			}
		}
	}

	@Override
	public void init() {
		TItems.first_cane.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelFirstCane(), ModelFirstCane.TEXTURE));
		TItems.sonic13th.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelSonic13()));
		TItems.player_victim.setTileEntityItemStackRenderer(new TEISRVictim());

		Item.getItemFromBlock(TBlocks.tardis_top).setTileEntityItemStackRenderer(new RenderItemTardis());
		Item.getItemFromBlock(TBlocks.tardis_top_01).setTileEntityItemStackRenderer(new RenderItemTardis02());
		Item.getItemFromBlock(TBlocks.alembic).setTileEntityItemStackRenderer(new RenderItemAlembic());

		Item.getItemFromBlock(TBlocks.br_chair).setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelBChair(), new ResourceLocation(Tardis.MODID, "textures/blocks/chair_br.png")));

		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new BlockColorTelos(), TBlocks.telos_sand);

		EnumCompanionType.CLAIRE.setModel(new ModelPlayer(0.0625F, true));
		EnumCompanionType.ALEXA.setModel(new ModelPlayer(0.0625F, true));
		EnumCompanionType.PETER.setModel(new ModelPlayer(0.0625F, true));
		EnumCompanionType.VANDHAM.setModel(new ModelPlayer(0.0625F, true));
		EnumCompanionType.VASSILIS.setModel(new ModelPlayer(0.0625F, false));
		EnumCompanionType.WOLSEY.setModel(new ModelOcelot());
		
		TardisKeyBinds.init();
	}
	
	@Override
	public void postInit() {
		super.postInit();
		if (!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
			Minecraft.getMinecraft().getFramebuffer().enableStencil();
		}
	}
}
