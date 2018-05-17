package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelTardis;
import net.tardis.mod.client.models.console.contols.ModelExteriorDoorL;
import net.tardis.mod.client.models.console.contols.ModelExteriorDoorR;
import net.tardis.mod.common.entities.EntityTardis;

public class RenderTardis extends Render {
	
	Minecraft mc;
	public static final ResourceLocation TEXTURE=new ResourceLocation(Tardis.MODID,"textures/controls/tardis.png");
	public ModelTardis model=new ModelTardis();
	public ModelExteriorDoorR door_r=new ModelExteriorDoorR();
	public ModelExteriorDoorL door_l=new ModelExteriorDoorL();
	
	public RenderTardis() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(((EntityTardis) entity).renderRotation, 0, 1, 0);
		mc.getTextureManager().bindTexture(TEXTURE);
		model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
		door_l.render(null, 0, 0, 0, 0, 0, 0.0625F);
		door_r.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
}
