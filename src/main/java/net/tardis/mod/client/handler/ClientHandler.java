package net.tardis.mod.client.handler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
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
import net.tardis.mod.client.guis.GuiVortexM;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.blocks.interfaces.IRenderBox;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.vehicles.EntityBessie;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageUpdateBessie;

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
	
	@SubscribeEvent
	public static void flyRender(RenderPlayerEvent.Pre event) {
		EntityPlayer player = event.getEntityPlayer();
		ITardisCap data = CapabilityTardis.get(player);
		BlockPos pos = player.getPosition();
		
		///if (data.isInFlight()) {
			//IBlockState state = data.getExterior();
			IBlockState state = TBlocks.hellbent_glass04.getDefaultState();
			event.setCanceled(true);
			
			//Render
			GlStateManager.pushMatrix();
			if(!player.onGround) {
				GlStateManager.rotate(player.world.getTotalWorldTime(), 0, 1, 0);
			}
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			GlStateManager.color(1F, 1, 1, 1f);
			RenderHelper.enableStandardItemLighting();
			IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
			if (state.getRenderType() == EnumBlockRenderType.MODEL && model != null) {
				GlStateManager.pushMatrix();
				Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModelBrightness(model, state, 1, true);
				GlStateManager.popMatrix();
			}
		
			if(state.getBlock().hasTileEntity(state)){
				TileEntity tile = state.getBlock().createTileEntity(player.world, state);
				if(tile != null){
					TileEntitySpecialRenderer render = TileEntityRendererDispatcher.instance.getRenderer(tile);
					if(render != null)
						render.render(tile, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 1);
				}
			}
	
			RenderHelper.disableStandardItemLighting();
			GlStateManager.popMatrix();
	//	}
	}
}
