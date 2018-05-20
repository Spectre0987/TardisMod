package net.tardis.mod.handlers;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.tardis.mod.client.renderers.RenderItemFoodMachine;
import net.tardis.mod.client.renderers.RendererItemDemat;
import net.tardis.mod.client.renderers.RendererItemTardis;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.items.TItems;

public class CustomModels {
	
	public static void register() {
		try{
			register(Item.getItemFromBlock(TBlocks.tardis_top), new RendererItemTardis());
			register(Item.getItemFromBlock(TBlocks.food_machine),new RenderItemFoodMachine());
			register(TItems.demat_circut, new RendererItemDemat());
		}
		catch(Exception e) {
			System.err.println("TARDIS:An Item Has failed to register it's custom Model");
		}
		
	}
	
	public static void register(Item item, TileEntityItemStackRenderer renderer) {
		TEventHandler.TEISR.put(item, renderer);
	}

}
