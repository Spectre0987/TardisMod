package net.tardis.mod.client.events;

import net.minecraft.block.Block;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.tardis.mod.blocks.SonicBlock;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {
	
	@SubscribeEvent
	public void highLightBlock(DrawBlockHighlightEvent e) {
		if (e.getTarget() != null && e.getTarget().getBlockPos() != null) {
			Block block = e.getPlayer().getEntityWorld().getBlockState(e.getTarget().getBlockPos()).getBlock();
			e.setCanceled(block instanceof SonicBlock);
		}
	}
}
