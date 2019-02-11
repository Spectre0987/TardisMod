package net.tardis.mod.proxy;

import java.util.ArrayList;

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
import net.tardis.mod.client.colorhandlers.BlockColorTelos;
import net.tardis.mod.client.models.clothing.ModelVortexM;
import net.tardis.mod.client.models.consoles.ModelConsole;
import net.tardis.mod.client.models.decoration.ModelBChair;
import net.tardis.mod.client.models.exteriors.TileEntityDoorTT;
import net.tardis.mod.client.models.items.ModelFirstCane;
import net.tardis.mod.client.models.items.ModelKey01;
import net.tardis.mod.client.models.items.ModelSonic13;
import net.tardis.mod.client.overlays.OverlayHandler;
import net.tardis.mod.client.renderers.RenderInvis;
import net.tardis.mod.client.renderers.consoles.RenderConsole01;
import net.tardis.mod.client.renderers.consoles.RenderConsole02;
import net.tardis.mod.client.renderers.consoles.RenderConsole03;
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
import net.tardis.mod.client.renderers.entities.RenderAdipose;
import net.tardis.mod.client.renderers.entities.RenderCompanion;
import net.tardis.mod.client.renderers.entities.RenderCybermanInvasion;
import net.tardis.mod.client.renderers.entities.RenderCybermanTomb;
import net.tardis.mod.client.renderers.entities.RenderDalek;
import net.tardis.mod.client.renderers.entities.RenderDalekCaseing;
import net.tardis.mod.client.renderers.entities.RenderDalekScaro;
import net.tardis.mod.client.renderers.entities.RenderItemMaterializer;
import net.tardis.mod.client.renderers.entities.RenderQuark;
import net.tardis.mod.client.renderers.entities.decoration.RenderBrakDoors;
import net.tardis.mod.client.renderers.entities.projectiles.RenderLaserRay;
import net.tardis.mod.client.renderers.entities.vehicles.RenderBessie;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor03;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor04;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor05;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorCC;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorClock;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorTT;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoorWood;
import net.tardis.mod.client.renderers.exteriors.RendererTileDoor01;
import net.tardis.mod.client.renderers.items.RenderItemAlembic;
import net.tardis.mod.client.renderers.items.RenderItemDemat;
import net.tardis.mod.client.renderers.items.RenderItemFoodMachine;
import net.tardis.mod.client.renderers.items.RenderItemSpaceHelm;
import net.tardis.mod.client.renderers.items.RenderItemTardis;
import net.tardis.mod.client.renderers.items.RenderItemTardis02;
import net.tardis.mod.client.renderers.items.RenderItemTardis03;
import net.tardis.mod.client.renderers.items.RenderTEISRItem;
import net.tardis.mod.client.renderers.items.RendererKey;
import net.tardis.mod.client.renderers.layers.RenderLayerVortexM;
import net.tardis.mod.client.renderers.tiles.RenderAlembic;
import net.tardis.mod.client.renderers.tiles.RenderCorridor;
import net.tardis.mod.client.renderers.tiles.RenderElectricPanel;
import net.tardis.mod.client.renderers.tiles.RenderFoodMachine;
import net.tardis.mod.client.renderers.tiles.RenderJsonHelper;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.renderers.tiles.RenderTileHolo;
import net.tardis.mod.client.renderers.tiles.RenderUmbrellaStand;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityAdipose;
import net.tardis.mod.common.entities.EntityChair;
import net.tardis.mod.common.entities.EntityCompanion;
import net.tardis.mod.common.entities.EntityCompanion.EnumCompanionType;
import net.tardis.mod.common.entities.EntityCorridor;
import net.tardis.mod.common.entities.EntityCybermanInvasion;
import net.tardis.mod.common.entities.EntityCybermanTomb;
import net.tardis.mod.common.entities.EntityDalek;
import net.tardis.mod.common.entities.EntityDalekCasing;
import net.tardis.mod.common.entities.EntityDalekSkaro;
import net.tardis.mod.common.entities.EntityItemMaterializer;
import net.tardis.mod.common.entities.EntityLaserRay;
import net.tardis.mod.common.entities.EntityQuark;
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
import net.tardis.mod.common.tileentity.TileEntityFoodMachine;
import net.tardis.mod.common.tileentity.TileEntityHellbentLight;
import net.tardis.mod.common.tileentity.TileEntityHoloprojector;
import net.tardis.mod.common.tileentity.TileEntityJsonTester;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityUmbrellaStand;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis03;
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
			addRenderLayer(new RenderLayerVortexM(render));
			layerPlayers.add(player);
		}
	}

	private static void addRenderLayer(LayerRenderer layer) {
		for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
			playerRender.addLayer(layer);
		}
	}

	public static boolean shouldRender(String modid) {
		for (String s : TardisConfig.BOTI.modids) {
			if (modid.equals(s)) return false;
		}
		return true;
	}

	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis.class, new RenderConsole());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmbrellaStand.class, new RenderUmbrellaStand());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor.class, new RenderTileDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFoodMachine.class, new RenderFoodMachine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloprojector.class, new RenderTileHolo());
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

		//Exteriors
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor01.class, new RendererTileDoor01());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor03.class, new RenderTileDoor03());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor04.class, new RenderTileDoor04());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor05.class, new RenderTileDoor05());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorCC.class, new RenderTileDoorCC());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorClock.class, new RenderTileDoorClock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorTT.class, new RenderTileDoorTT());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoorWood.class, new RenderTileDoorWood());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJsonTester.class, new RenderJsonHelper());

		// Controls
		if (!ClientProxy.disableControlRender) {
			RenderingRegistry.registerEntityRenderingHandler(ControlDoor.class, RenderDoor::new);
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

		RenderingRegistry.registerEntityRenderingHandler(EntityCorridor.class, RenderCorridor::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBessie.class, RenderBessie::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekSkaro.class, RenderDalekScaro::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityChair.class, RenderInvis::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityHellbentCorridor.class, RenderHellbentCorridor::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHellbentDoor.class, RenderHellbentDoor::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityDalekCasing.class, RenderDalekCaseing::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanInvasion.class, RenderCybermanInvasion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanTomb.class, RenderCybermanTomb::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserRay.class, new RenderLaserRay(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityItemMaterializer.class, RenderItemMaterializer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDoorsBrakSecondary.class, RenderBrakDoors::new);

		RenderingRegistry.registerEntityRenderingHandler(EntityDalek.class, RenderDalek::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAdipose.class, RenderAdipose::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCompanion.class, RenderCompanion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityQuark.class, RenderQuark::new);


	}

	@Override
	public void preInit() {
		registerRenders();
		EnumClothes.ClothingHandler.init();
		OverlayHandler.init();
		if (!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
			Minecraft.getMinecraft().getFramebuffer().enableStencil();
		}
	}

	@Override
	public void init() {
		TItems.space_helm.setTileEntityItemStackRenderer(new RenderItemSpaceHelm());
		TItems.vortex_manip.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelVortexM(), ModelVortexM.TEXTURE));
		TItems.key_01.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelKey01(), ModelKey01.TEXTURE));
		TItems.first_cane.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelFirstCane(), ModelFirstCane.TEXTURE));
		TItems.demat_circut.setTileEntityItemStackRenderer(new RenderItemDemat());
		TItems.key.setTileEntityItemStackRenderer(new RendererKey());
		TItems.sonic13th.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelSonic13()));

		Item.getItemFromBlock(TBlocks.tardis_top).setTileEntityItemStackRenderer(new RenderItemTardis());
		Item.getItemFromBlock(TBlocks.food_machine).setTileEntityItemStackRenderer(new RenderItemFoodMachine());
		Item.getItemFromBlock(TBlocks.tardis_top_01).setTileEntityItemStackRenderer(new RenderItemTardis02());
		Item.getItemFromBlock(TBlocks.tardis_top_02).setTileEntityItemStackRenderer(new RenderItemTardis03());
		Item.getItemFromBlock(TBlocks.alembic).setTileEntityItemStackRenderer(new RenderItemAlembic());

		Item.getItemFromBlock(TBlocks.br_chair).setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelBChair(), new ResourceLocation(Tardis.MODID, "textures/blocks/chair_br.png")));

		Item.getItemFromBlock(TBlocks.console).setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelConsole(), RenderConsole.CONSOLE_TEXTURE));

		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new BlockColorTelos(), TBlocks.telos_sand);

		EnumCompanionType.CLAIRE.setModel(new ModelPlayer(0.0625F, true));
		EnumCompanionType.ALEXA.setModel(new ModelPlayer(0.0625F, true));
		EnumCompanionType.PETER.setModel(new ModelPlayer(0.0625F, true));
		EnumCompanionType.VANDHAM.setModel(new ModelPlayer(0.0625F, true));
		EnumCompanionType.VASSILIS.setModel(new ModelPlayer(0.0625F, false));
		EnumCompanionType.WOLSEY.setModel(new ModelOcelot());
	}
}
