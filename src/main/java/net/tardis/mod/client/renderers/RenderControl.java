package net.tardis.mod.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.console.contols.ModelAllControls;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.info.TardisType;

public abstract class RenderControl extends Render {
	
	protected Minecraft mc;
	public static final ResourceLocation CONTROL_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/control_sheet.png");
	public ModelAllControls control_model = new ModelAllControls();
	
	public RenderControl() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
			renderControl((EntityControl) entity, entityYaw, partialTicks, TardisType.Type80s);
		GlStateManager.popMatrix();
	}
	
	public abstract void renderControl(EntityControl entity, float entityYaw, float partialTicks, TardisType tType);
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return CONTROL_TEXTURE;
	}
	
}
