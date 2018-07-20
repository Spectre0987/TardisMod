package net.tardis.mod.client.overlays;

import net.minecraft.client.gui.ScaledResolution;

public interface IOverlay {
    void render(float partialTicks, ScaledResolution resolution);

    void renderUpdate();
}
