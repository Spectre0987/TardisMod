package net.tardis.mod.client.boti;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.tardis.mod.Tardis;


@Mod.EventBusSubscriber(Side.CLIENT)
public class BOTIHandler {
	
	private static boolean rendering;
	
	// TODO interior doesn't render until you visit the dimension once?
	// TODO clouds are rendering?
	@SubscribeEvent
	public static void onRenderTick(TickEvent.RenderTickEvent event) {
		
		if (event.phase != TickEvent.Phase.END) return;
		WorldClient worldClient = Minecraft.getMinecraft().world;
		if (worldClient != null && !rendering) {
			RenderGlobal renderGlobal = Minecraft.getMinecraft().renderGlobal;
			worldClient.loadedTileEntityList.forEach(tileEntity -> {
				if (tileEntity instanceof ICameraInterface) {
					FakeWorld fakeWorld = FakeWorld.getFakeWorld(((ICameraInterface) tileEntity).getRenderDimension());
					
					EntityCamera camera = fakeWorld.getCamera((ICameraInterface) tileEntity);
					if (camera == null || camera.image != null) return; // Rendering over an image that hasn't been shown yet is a waste
					
					Minecraft.getMinecraft().world = fakeWorld;
					Minecraft.getMinecraft().getRenderManager().setWorld(fakeWorld);
					Minecraft.getMinecraft().renderGlobal = fakeWorld.renderGlobal;
					
					GlStateManager.pushMatrix();
					GlStateManager.pushAttrib();
					rendering = true;
					camera.renderWorldToTexture((ICameraInterface) tileEntity, ((ICameraInterface) tileEntity).getResolution(), new Vec3d(tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ()), event.renderTickTime);
					rendering = false;
					GlStateManager.popAttrib();
					GlStateManager.popMatrix();
					
					GlStateManager.enableBlend();
					GlStateManager.disableTexture2D();
					GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
					GlStateManager.enableTexture2D();
					GlStateManager.disableBlend();
					
					Minecraft.getMinecraft().world = worldClient;
					Minecraft.getMinecraft().renderGlobal = renderGlobal;
					Minecraft.getMinecraft().getRenderManager().setWorld(worldClient);
					
					if (Minecraft.getMinecraft().world.getTotalWorldTime() % 200 == 0 || ((ICameraInterface) tileEntity).isChunkEmpty(fakeWorld)) { // TODO optimize
						Tardis.NETWORK.sendToServer(((ICameraInterface) tileEntity).requestChunks());
					}
				}
			});
		} else if (!FakeWorld.fakeWorlds.isEmpty()) {
			FakeWorld.fakeWorlds.clear();
		}
	}
	
	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().world != null) for (FakeWorld fakeWorld : FakeWorld.fakeWorlds) {
			fakeWorld.updateEntities();
		}
	}
	
}
