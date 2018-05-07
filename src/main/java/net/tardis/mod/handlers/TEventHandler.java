package net.tardis.mod.handlers;

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
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.api.controls.IUnbreakable;
import net.tardis.mod.blocks.TBlocks;
import net.tardis.mod.common.entities.EntityCam;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.recipes.RecipeKey;
import net.tardis.mod.common.world.TardisWorldSavedData;

public class TEventHandler {
	
	public static TardisWorldSavedData data;
	
	public static Random rand = new Random();
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Block block : TBlocks.blocks) {
			event.getRegistry().register(block);
		}
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		for (Item Item : TItems.items) {
			event.getRegistry().register(Item);
		}
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		for (Block block : TBlocks.blocks) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
		}
		for (Item item : TItems.items) {
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
