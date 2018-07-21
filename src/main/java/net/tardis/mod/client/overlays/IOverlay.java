package net.tardis.mod.client.overlays;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public interface IOverlay {
    void render(RenderGameOverlayEvent e, float partialTicks, ScaledResolution resolution);

    void renderUpdate();
}
