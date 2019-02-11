package net.tardis.mod.client.overlays;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public interface IOverlay {

	void pre(RenderGameOverlayEvent.Pre e, float partialTicks, ScaledResolution resolution);


	void post(RenderGameOverlayEvent.Post e, float partialTicks, ScaledResolution resolution);

	void renderUpdate();
}
