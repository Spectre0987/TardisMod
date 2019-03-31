package net.tardis.mod.client.handler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.capability.ITardisCap;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.guis.GuiVortexM;
import net.tardis.mod.client.models.exteriors.IExteriorModel;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.interfaces.IRenderBox;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageUpdateBessie;
import net.tardis.mod.util.client.RenderHelper;
import net.tardis.mod.util.common.helpers.Helper;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = Tardis.MODID, value = Side.CLIENT)
public class ClientHandler {
	
	@SubscribeEvent
	public static void honkMyHorn(TickEvent.ClientTickEvent e) {
		if (Minecraft.getMinecraft().player == null) return;
		if (Minecraft.getMinecraft().player.getRidingEntity() instanceof EntityBessie) {
			EntityBessie bessie = (EntityBessie) Minecraft.getMinecraft().player.getRidingEntity();
			Entity driver = bessie.getControllingPassenger();
			if (driver.getEntityId() == Minecraft.getMinecraft().player.getEntityId()) {
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
	public static void fixLight(ModelBakeEvent event) {
		for (ModelResourceLocation loc : event.getModelRegistry().getKeys()) {
			
		}
	}
	
	//This is horrible, I know it is, just bare with me
	
	public static HashMap<EnumExterior, IExteriorModel> EXTERIOR_CACHE = new HashMap<>();
	public static void cacheFlightModels() {
		for (EnumExterior value : EnumExterior.values()) {
			EXTERIOR_CACHE.put(value, value.model);
		}
	}
	@SubscribeEvent
	public static void flyRender(RenderPlayerEvent.Pre e) {
		EntityPlayer player = e.getEntityPlayer();
		ITardisCap data = CapabilityTardis.get(player);
		BlockPos pos = player.getPosition();
		
		if (data.isInFlight()) {
			IBlockState exteriorState = data.getExterior();
			EnumExterior exterior = EnumExterior.getExteriorFromBlock(exteriorState.getBlock());
			if (player.world.isRemote) {
				if (player.collidedHorizontally || !data.hasFuel()) {
					for (int x = 0; x <= 13; x++) {
						player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX + (player.world.rand.nextDouble() - 0.5D) * (double) player.width, player.posY + player.world.rand.nextDouble() * (double) player.height, player.posZ + (player.world.rand.nextDouble() - 0.5D) * (double) player.width, 0.0D, 0.0D, 0.0D);
						player.world.spawnParticle(EnumParticleTypes.FLAME, player.posX + (player.world.rand.nextDouble() - 0.5D) * (double) player.width, player.posY + player.world.rand.nextDouble() * (double) player.height, player.posZ + (player.world.rand.nextDouble() - 0.5D) * (double) player.width, 0.0D, 0.0D, 0.0D);
					}
				}
			}
			e.setCanceled(true);
			//Render
			GlStateManager.pushMatrix();
			double x2 = ((player.prevPosX + (player.posX - player.prevPosX) * e.getPartialRenderTick()) - TileEntityRendererDispatcher.staticPlayerX);
			double y2 = ((player.prevPosY + (player.posY - player.prevPosY) * e.getPartialRenderTick()) - TileEntityRendererDispatcher.staticPlayerY);
			double z2 = ((player.prevPosZ + (player.posZ - player.prevPosZ) * e.getPartialRenderTick()) - TileEntityRendererDispatcher.staticPlayerZ);
			GlStateManager.translate(x2, y2, z2);
			GlStateManager.rotate(-180, 1, 0, 0);
			GlStateManager.translate(0, -1.5, 0);
			if (!player.onGround) {
				GlStateManager.rotate((float) (player.ticksExisted * 3.0f * Math.PI), 0, 1, 0);
				GlStateManager.rotate(player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset) * e.getPartialRenderTick(), 0, 1, 0);
				float offset = 0;
				if (player.world.isAirBlock(player.getPosition().down())) {
					if (!player.capabilities.isFlying) {
						float f = (float) (player.ticksExisted * 3.0f * Math.PI) + e.getPartialRenderTick();
						float f1 = MathHelper.clamp(f * f / 100.0F, 0.0F, 1.0F);
						GlStateManager.rotate(-f1 * (-90.0F - player.rotationPitch), 1.0F, 0.0F, 0.0F);
					}
					offset = MathHelper.cos(player.ticksExisted * 0.1F) * -0.67F;
				}
				
				GlStateManager.translate(0, -offset, 0);
			}
			Minecraft.getMinecraft().getTextureManager().bindTexture(exterior.tex);
			if (!data.isOpen()) {
				EXTERIOR_CACHE.get(exterior).renderClosed(0.0625F);
			} else {
				EXTERIOR_CACHE.get(exterior).renderOpen(0.0625F);
			}
			GlStateManager.popMatrix();
		}
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
}
