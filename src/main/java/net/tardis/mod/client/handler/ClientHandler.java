package net.tardis.mod.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.GuiVortexM;
import net.tardis.mod.common.blocks.interfaces.IRenderBox;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.enums.EnumFlightState;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageTardisFlight;
import net.tardis.mod.network.packets.MessageTardisFlightChange;
import net.tardis.mod.network.packets.MessageUpdateBessie;

@Mod.EventBusSubscriber(modid = Tardis.MODID, value = Side.CLIENT)
public class ClientHandler {
	
	@SubscribeEvent
	public static void honkMyHorn(TickEvent.ClientTickEvent e) {
		if (e.phase != TickEvent.Phase.START) return;
		if (Minecraft.getMinecraft().player == null) return;
		
		if (Minecraft.getMinecraft().player.getRidingEntity() instanceof EntityBessie) {
			EntityBessie bessie = (EntityBessie) Minecraft.getMinecraft().player.getRidingEntity();
			Entity driver = bessie.getControllingPassenger();
			if (driver != null && driver.getEntityId() == Minecraft.getMinecraft().player.getEntityId()) {
				if (Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()) {
					NetworkHandler.NETWORK.sendToServer(new MessageUpdateBessie(bessie.getEntityId()));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void cancelBBRender(DrawBlockHighlightEvent event) {
		World world = event.getPlayer().world;
		BlockPos pos = event.getTarget().getBlockPos();
		if (pos != null && !pos.equals(BlockPos.ORIGIN)) {
			if (world.getBlockState(pos).getBlock() instanceof IRenderBox) {
				IRenderBox block = (IRenderBox) world.getBlockState(pos).getBlock();
				event.setCanceled(!block.shouldRenderBox());
			}
		}
	}
	
	@SubscribeEvent
	public static void useVortexM(PlayerInteractEvent.RightClickEmpty e) {
		if (e.getEntityPlayer().getHeldItemMainhand().isEmpty() && e.getEntityPlayer().dimension != TDimensions.TARDIS_ID && e.getEntityPlayer().inventory.hasItemStack(new ItemStack(TItems.vortex_manip))) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiVortexM());
		}
	}
	
	@SubscribeEvent
	public static void overlayFuckup(RenderGameOverlayEvent.Post event) {
		/*if(event.getType() == ElementType.ALL) {
			GlStateManager.disableTexture2D();
			double width = event.getResolution().getScaledWidth(),
			height = event.getResolution().getScaledHeight();
			BufferBuilder bb = Tessellator.getInstance().getBuffer();
			bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
			bb.pos(0, 0, 0).color(1F, 0.3F, 0.3F, 0.5F).endVertex();
			bb.pos(0, height, 0).color(1F, 0.3F, 0.3F, 0.5F).endVertex();
			bb.pos(width, height, 0).color(1F, 0.5F, 0.5F, 0.5F).endVertex();
			bb.pos(width, 0, 0).color(1F, 0.3F, 0.3F, 0.5F).endVertex();
			Tessellator.getInstance().draw();
			GlStateManager.enableTexture2D();
		}*/
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void hidePlayer(RenderPlayerEvent.Pre event) {
		Entity ride = event.getEntityPlayer().getRidingEntity();
		if(ride instanceof EntityTardis) {
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void flyTardis(InputUpdateEvent event) {
		if(event.getEntityPlayer().getRidingEntity() instanceof EntityTardis) {
			EntityTardis tardis = (EntityTardis)event.getEntityPlayer().getRidingEntity();
			if(event.getMovementInput().jump)
				NetworkHandler.NETWORK.sendToServer(new MessageTardisFlight(tardis.getEntityId(), true));
			else if(event.getMovementInput().sneak) {
				NetworkHandler.NETWORK.sendToServer(new MessageTardisFlight(tardis.getEntityId(), false));
				event.getMovementInput().sneak = false;
			}
		}
	}
	
	@SubscribeEvent
	public static void changeTardisFlightState(PlayerInteractEvent.RightClickEmpty event) {
		if(event.getEntityPlayer().getRidingEntity() instanceof EntityTardis) {
			EntityTardis entity = (EntityTardis)event.getEntityPlayer().getRidingEntity();
			NetworkHandler.NETWORK.sendToServer(new MessageTardisFlightChange(entity.getEntityId(), entity.getOpenState() == EnumFlightState.CLOSED ? EnumFlightState.SITTING : EnumFlightState.CLOSED));
		}
	}
}
