package net.tardis.mod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.Item;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.tardis.mod.client.models.ModelFirstCane;
import net.tardis.mod.client.models.ModelKey01;
import net.tardis.mod.client.models.clothing.ModelFourthHat;
import net.tardis.mod.client.models.clothing.ModelVortexM;
import net.tardis.mod.client.renderers.*;
import net.tardis.mod.client.renderers.controls.*;
import net.tardis.mod.client.renderers.entities.RenderCyberRay;
import net.tardis.mod.client.renderers.entities.RenderCybermanInvasion;
import net.tardis.mod.client.renderers.entities.RenderDalek;
import net.tardis.mod.client.renderers.entities.RenderRay;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor03;
import net.tardis.mod.client.renderers.exteriors.RendererTileDoor01;
import net.tardis.mod.client.renderers.items.*;
import net.tardis.mod.client.renderers.layers.RenderLayerVortexM;
import net.tardis.mod.client.renderers.tiles.*;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.*;
import net.tardis.mod.common.entities.controls.*;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.*;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor01;
import net.tardis.mod.common.tileentity.exteriors.TileEntityDoor03;
import net.tardis.mod.config.TardisConfig;

import java.util.HashMap;

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
		
		//Exteriors
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor01.class, new RendererTileDoor01());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor03.class, new RenderTileDoor03());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJsonTester.class, new RenderJsonHelper());
		
		// Controls
		RenderingRegistry.registerEntityRenderingHandler(ControlScreen.class, new RenderScreen());
		RenderingRegistry.registerEntityRenderingHandler(ControlDoor.class, new RenderDoor());
		RenderingRegistry.registerEntityRenderingHandler(ControlX.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlY.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlZ.class, new RenderZ());
		RenderingRegistry.registerEntityRenderingHandler(ControlLaunch.class, new RenderLever());
		RenderingRegistry.registerEntityRenderingHandler(ControlDimChange.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlFlight.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlRandom.class, new RenderRandom());
		RenderingRegistry.registerEntityRenderingHandler(ControlSTCLoad.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlSTCButton.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlFuel.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlLandType.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlDirection.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlFastReturn.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlTelepathicCircuts.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlDoorSwitch.class, new RenderInvis());
		RenderingRegistry.registerEntityRenderingHandler(ControlMag.class, new RenderInvis());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTardis.class, new RenderTardis());
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekRay.class, new RenderRay());
		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanInvasion.class, new RenderCybermanInvasion());
		RenderingRegistry.registerEntityRenderingHandler(EntityRayCyberman.class, new RenderCyberRay());

		RenderingRegistry.registerEntityRenderingHandler(EntityDalek.class, new RenderDalek());

		TItems.sonic_pen.setTileEntityItemStackRenderer(new RenderItemSonicPen());
		TItems.space_helm.setTileEntityItemStackRenderer(new RenderItemSpaceHelm());
		TItems.space_chest.setTileEntityItemStackRenderer(new RenderItemSpaceChest());
		TItems.space_legs.setTileEntityItemStackRenderer(new RenderItemSpaceLegs());
		TItems.vortex_manip.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelVortexM(), ModelVortexM.TEXTURE));
		TItems.key_01.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelKey01(), ModelKey01.TEXTURE));
		TItems.first_cane.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelFirstCane(), ModelFirstCane.TEXTURE));
		TItems.demat_circut.setTileEntityItemStackRenderer(new RendererItemDemat());
		TItems.key.setTileEntityItemStackRenderer(new RendererKey());
		TItems.fourth_hat.setTileEntityItemStackRenderer(new RenderTEISRItem(new ModelFourthHat(), ModelFourthHat.TEXTURE));
		
		Item.getItemFromBlock(TBlocks.tardis_top).setTileEntityItemStackRenderer(new RendererItemTardis());

		Item.getItemFromBlock(TBlocks.food_machine).setTileEntityItemStackRenderer(new RenderItemFoodMachine());
		Item.getItemFromBlock(TBlocks.tardis_top_01).setTileEntityItemStackRenderer(new RenderItemTardis02());
		Item.getItemFromBlock(TBlocks.tardis_top_02).setTileEntityItemStackRenderer(new RenderItemTardis03());
		
		ClientProxy.addRenderLayer(RenderLayerVortexM.class);
	}

	@Override
	public void preInit() {
		if(!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
			Minecraft.getMinecraft().getFramebuffer().enableStencil();
		}
		
		for(int i : DimensionManager.getStaticDimensionIDs()) {
			WorldProvider wp = DimensionManager.createProviderFor(i);
			if(wp != null && wp.getSkyRenderer() != null) {
				skyRenderers.put(i, wp.getSkyRenderer().getClass());
				System.out.println(DimensionManager.createProviderFor(i).getSkyRenderer().getClass() + " : " + i);
			}
			else System.out.println(i + " " + wp + " is null.");
		}
	}
	
	public static boolean getRenderBOTI() {
		return Minecraft.getMinecraft().getFramebuffer().isStencilEnabled() && TardisConfig.BOTI.enable;
	}
	
	public static void addRenderLayer(Class<? extends LayerRenderer> clazz) {
		try {
			RenderPlayer rp = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default"));
			RenderPlayer rp1 = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim"));
			
			rp.addLayer(clazz.getConstructor(RenderPlayer.class).newInstance(rp));
			rp1.addLayer(clazz.getConstructor(RenderPlayer.class).newInstance(rp1));
		}
		catch(Exception e) {}
	}
}
