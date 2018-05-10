package net.tardis.mod.handlers;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.api.controls.IUnbreakable;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityCam;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.recipes.RecipeKey;
import net.tardis.mod.common.world.TardisWorldSavedData;

public class TEventHandler {
	
	public static TardisWorldSavedData data;
	
	public static Random rand = new Random();

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {

		//Blocks
		for(Field field : TBlocks.class.getDeclaredFields()) {
			Block block = null;
			try {
				block = (Block) field.get(null);
			} catch (IllegalAccessException e) {
				//No log spam
			}
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
		}

		for(Field field : TItems.class.getDeclaredFields()) {
			Item item = null;
			try {
				item = (Item) field.get(null);
			} catch (IllegalAccessException e) {

			}
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
	
	@SubscribeEvent
	public void loadTardises(WorldEvent.Load event) {
		data = (TardisWorldSavedData) event.getWorld().getMapStorage().getOrLoadData(TardisWorldSavedData.class, "tardis");
		if (data == null) data = new TardisWorldSavedData("tardis");
	}
	
	@SubscribeEvent
	public void saveTardises(WorldEvent.Save event) {
		event.getWorld().getMapStorage().setData("tardis", data);
	}
	
	@SubscribeEvent
	public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		event.getRegistry().register(new RecipeKey("spare_key"));
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
	public void stopRender(RenderPlayerEvent.Pre event) {
		if (event.getEntityPlayer().getRidingEntity() != null && event.getEntityPlayer().getRidingEntity() instanceof EntityCam || event.getEntityPlayer().getRidingEntity() instanceof EntityTardis) {
			Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void stopHurt(LivingHurtEvent event) {
		if (event.getEntityLiving().getRidingEntity() != null) {
			Entity e = event.getEntityLiving().getRidingEntity();
			if (e instanceof EntityTardis || e instanceof EntityCam) event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void makeTrueUnbreakable(BlockEvent.BreakEvent e) {
		if (e.getState().getBlock() instanceof IUnbreakable) {
			e.setCanceled(true);
		}
	}
	
}
