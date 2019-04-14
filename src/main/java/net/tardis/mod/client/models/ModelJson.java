package net.tardis.mod.client.models;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;

@Mod.EventBusSubscriber(modid = Tardis.MODID)
public class ModelJson {

	
	@SubscribeEvent
	public static void registerModel(TextureStitchEvent.Pre event) {
		
	}
}
