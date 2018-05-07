package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.entities.EntityAngel;
import net.tardis.mod.packets.MessageAngel;

public class RenderAngel extends Render {
	
	Minecraft mc;
	public ModelPlayer model = new ModelPlayer(0.0625F, true);
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/angel.png");
	
	public RenderAngel() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y - 0.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.translate(0, -2, 0);
		model.isChild = false;
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(entity, 0, 0, 0, entityYaw, 0, 0.0625F);
		GlStateManager.popMatrix();
		if (((EntityAngel) entity).shouldMove) Tardis.NETWORK.sendToServer(new MessageAngel(entity, false));
	}
	
}
