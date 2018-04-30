package net.tardis.mod.proxy;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.tardis.mod.Tardis;
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
import net.tardis.mod.entities.EntityAngel;
import net.tardis.mod.entities.EntityDalek;
import net.tardis.mod.entities.EntityDalekRay;
import net.tardis.mod.entities.EntityForceField;
import net.tardis.mod.entities.EntityTardis;
import net.tardis.mod.renderers.RenderAngel;
import net.tardis.mod.renderers.RenderBigLever;
import net.tardis.mod.renderers.RenderButton;
import net.tardis.mod.renderers.RenderConsole;
import net.tardis.mod.renderers.RenderDalek;
import net.tardis.mod.renderers.RenderDirectionalControl;
import net.tardis.mod.renderers.RenderDoor;
import net.tardis.mod.renderers.RenderFField;
import net.tardis.mod.renderers.RenderFuel;
import net.tardis.mod.renderers.RenderLever;
import net.tardis.mod.renderers.RenderRay;
import net.tardis.mod.renderers.RenderScanner;
import net.tardis.mod.renderers.RenderScreen;
import net.tardis.mod.renderers.RenderTardis;
import net.tardis.mod.renderers.RenderTimeRotor;
import net.tardis.mod.renderers.RenderUmbrellaStand;
import net.tardis.mod.tileentity.TileEntityTardis;
import net.tardis.mod.tileentity.TileEntityTimeRotor;
import net.tardis.mod.tileentity.TileEntityUmbrellaStand;

public class ClientProxy extends ServerProxy{

	@Override
	public void renderEntities() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTardis.class, new RenderConsole());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityUmbrellaStand.class, new RenderUmbrellaStand());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeRotor.class, new RenderTimeRotor());
		
		//Controls
		RenderingRegistry.registerEntityRenderingHandler(ControlLaunch.class, new RenderLever());
		RenderingRegistry.registerEntityRenderingHandler(ControlX.class, new RenderDirectionalControl());
		RenderingRegistry.registerEntityRenderingHandler(ControlY.class, new RenderDirectionalControl());
		RenderingRegistry.registerEntityRenderingHandler(ControlZ.class, new RenderDirectionalControl());
		RenderingRegistry.registerEntityRenderingHandler(ControlDimChange.class, new RenderButton());
		RenderingRegistry.registerEntityRenderingHandler(ControlScreen.class, new RenderScreen());
		RenderingRegistry.registerEntityRenderingHandler(ControlRandom.class, new RenderButton());
		RenderingRegistry.registerEntityRenderingHandler(ControlDoor.class, new RenderDoor());
		RenderingRegistry.registerEntityRenderingHandler(ControlSTCLoad.class, new RenderButton());
		RenderingRegistry.registerEntityRenderingHandler(ControlSTCButton.class, new RenderButton());
		RenderingRegistry.registerEntityRenderingHandler(ControlScanner.class, new RenderScanner());
		RenderingRegistry.registerEntityRenderingHandler(ControlFlight.class, new RenderBigLever());
		RenderingRegistry.registerEntityRenderingHandler(ControlFuel.class, new RenderFuel());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityDalek.class, new RenderDalek());
		RenderingRegistry.registerEntityRenderingHandler(EntityTardis.class, new RenderTardis());
		RenderingRegistry.registerEntityRenderingHandler(EntityAngel.class, new RenderAngel());
		RenderingRegistry.registerEntityRenderingHandler(EntityDalekRay.class, new RenderRay());
		RenderingRegistry.registerEntityRenderingHandler(EntityForceField.class, new RenderFField());
		
		OBJLoader.INSTANCE.addDomain(Tardis.MODID);
	}

}
