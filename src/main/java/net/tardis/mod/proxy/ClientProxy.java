package net.tardis.mod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.tardis.mod.client.renderers.RenderConsole;
import net.tardis.mod.client.renderers.RenderCyberRay;
import net.tardis.mod.client.renderers.RenderCybermanInvasion;
import net.tardis.mod.client.renderers.RenderDoor;
import net.tardis.mod.client.renderers.RenderFoodMachine;
import net.tardis.mod.client.renderers.RenderInvis;
import net.tardis.mod.client.renderers.RenderItemSonicPen;
import net.tardis.mod.client.renderers.RenderItemSpaceHelm;
import net.tardis.mod.client.renderers.RenderJsonHelper;
import net.tardis.mod.client.renderers.RenderRay;
import net.tardis.mod.client.renderers.RenderScreen;
import net.tardis.mod.client.renderers.RenderTardis;
import net.tardis.mod.client.renderers.RenderTileDoor;
import net.tardis.mod.client.renderers.RenderUmbrellaStand;
import net.tardis.mod.client.renderers.controls.RenderLever;
import net.tardis.mod.client.renderers.controls.RenderRandom;
import net.tardis.mod.client.renderers.controls.RenderZ;
import net.tardis.mod.client.renderers.items.RenderItemSpaceChest;
import net.tardis.mod.client.renderers.items.RenderItemSpaceLegs;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.entities.EntityCam;
import net.tardis.mod.common.entities.EntityCybermanInvasion;
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
import net.tardis.mod.common.entities.controls.ControlRandom;
import net.tardis.mod.common.entities.controls.ControlSTCButton;
import net.tardis.mod.common.entities.controls.ControlSTCLoad;
import net.tardis.mod.common.entities.controls.ControlScreen;
import net.tardis.mod.common.entities.controls.ControlTelepathicCircuts;
import net.tardis.mod.common.entities.controls.ControlX;
import net.tardis.mod.common.entities.controls.ControlY;
import net.tardis.mod.common.entities.controls.ControlZ;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityFoodMachine;
import net.tardis.mod.common.tileentity.TileEntityJsonTester;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityUmbrellaStand;

public class ClientProxy extends ServerProxy {
	
	@Override
	public void renderEntities() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis.class, new RenderConsole());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmbrellaStand.class, new RenderUmbrellaStand());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDoor.class, new RenderTileDoor());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFoodMachine.class, new RenderFoodMachine());
		
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
		
		RenderingRegistry.registerEntityRenderingHandler(EntityTardis.class, new RenderTardis());
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekRay.class, new RenderRay());
		RenderingRegistry.registerEntityRenderingHandler(EntityCybermanInvasion.class, new RenderCybermanInvasion());
		RenderingRegistry.registerEntityRenderingHandler(EntityRayCyberman.class, new RenderCyberRay());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityCam.class, new RenderWorldShell(Minecraft.getMinecraft().getRenderManager()));
		
		TItems.sonic_pen.setTileEntityItemStackRenderer(new RenderItemSonicPen());
		TItems.space_helm.setTileEntityItemStackRenderer(new RenderItemSpaceHelm());
		TItems.space_chest.setTileEntityItemStackRenderer(new RenderItemSpaceChest());
		TItems.space_legs.setTileEntityItemStackRenderer(new RenderItemSpaceLegs());
		
		// Not needed currently.
		// OBJLoader.INSTANCE.addDomain(Tardis.MODID);
	}
	
}
