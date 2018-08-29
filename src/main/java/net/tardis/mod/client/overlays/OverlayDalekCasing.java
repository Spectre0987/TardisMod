package net.tardis.mod.client.overlays;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.tardis.mod.common.entities.EntityDalekCasing;

public class OverlayDalekCasing implements IOverlay {

	List<ElementType> types = new ArrayList<ElementType>();
	
	public OverlayDalekCasing() {
		types.add(ElementType.ARMOR);
		types.add(ElementType.EXPERIENCE);
		types.add(ElementType.FOOD);
		types.add(ElementType.HEALTH);
		types.add(ElementType.HELMET);
		types.add(ElementType.HOTBAR);
		types.add(ElementType.JUMPBAR);
	}
	
	@Override
	public void pre(Pre e, float partialTicks, ScaledResolution resolution) {
		if(Minecraft.getMinecraft().player.getRidingEntity() != null && Minecraft.getMinecraft().player.getRidingEntity() instanceof EntityDalekCasing) {
			if(types.contains(e.getType())){
				e.setCanceled(true);
			}
		}
	}

	@Override
	public void post(Post e, float partialTicks, ScaledResolution resolution) {}

	@Override
	public void renderUpdate() {}

}
