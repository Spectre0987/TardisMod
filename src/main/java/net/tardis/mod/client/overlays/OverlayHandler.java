package net.tardis.mod.client.overlays;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;

@Mod.EventBusSubscriber(Side.CLIENT)
public class OverlayHandler {

    private static ArrayList<IOverlay> OVERLAYS = new ArrayList<>();


    @SubscribeEvent
    public static void cancelOverlays(RenderGameOverlayEvent.Pre e) {
        for (IOverlay overlay : OVERLAYS) {
            overlay.pre(e, e.getPartialTicks(), e.getResolution());
        }
    }


    @SubscribeEvent
    public static void renderOverlays(RenderGameOverlayEvent.Post e) {
        for (IOverlay overlay : OVERLAYS) {
            overlay.renderUpdate();
            overlay.post(e, e.getPartialTicks(), e.getResolution());
        }
    }

    public static void init() {
        OVERLAYS.add(new OverlaySonicShades());
    }
}
