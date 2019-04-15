package net.tardis.mod.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.client.TardisKeyBinds;
import net.tardis.mod.client.guis.GuiVortexM;
import net.tardis.mod.client.renderers.layers.RenderFlightMode;
import net.tardis.mod.common.blocks.interfaces.IRenderBox;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageDematAnim;
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
	
	@SideOnly(Side.CLIENT)
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
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void useVortexM(PlayerInteractEvent.RightClickEmpty e) {
		if (e.getEntityPlayer().getHeldItemMainhand().isEmpty() && e.getEntityPlayer().dimension != TDimensions.TARDIS_ID && e.getEntityPlayer().inventory.hasItemStack(new ItemStack(TItems.vortex_manip))) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiVortexM());
		}
	}
	
	
	@SubscribeEvent
	public static void flyRender(RenderPlayerEvent.Pre e) {
		RenderFlightMode.renderFlightMode(e);
	}
	
	@SubscribeEvent
	public static void freeze(InputUpdateEvent e) {
		if (Minecraft.getMinecraft().player == null) return;
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		if (CapabilityTardis.get(player).isInFlight() && player.onGround) {
			MovementInput moveType = e.getMovementInput();
			moveType.rightKeyDown = false;
			moveType.leftKeyDown = false;
			moveType.backKeyDown = false;
			moveType.moveForward = 0.0F;
			moveType.moveStrafe = 0.0F;
			if (!CapabilityTardis.get(player).hasFuel()) {
				moveType.jump = false;
			}
		}
	}
	
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent e) {
		if (TardisKeyBinds.DEMAT_REMAT_ANIM.isPressed()) {
			NetworkHandler.NETWORK.sendToServer(new MessageDematAnim());
		}
	}
	
}
