package net.tardis.mod.client.renderers.entities.decoration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.decoration.ModelRoundelDoors02;
import net.tardis.mod.common.entities.brak.EntityDoorsBrakSecondary;

public class RenderBrakDoors extends Render<EntityDoorsBrakSecondary>{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/roundel_doors02.png");
	public static ModelRoundelDoors02 model = new ModelRoundelDoors02();
	
	public RenderBrakDoors(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDoorsBrakSecondary entity) {
		return TEXTURE;
	}

	@Override
	public void doRender(EntityDoorsBrakSecondary entity, double x, double y, double z, float entityYaw,float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 1.5, z);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		if(entity.hurtTime > 0)
			GlStateManager.translate(0, 0, -Math.cos(entity.hurtTime) * 0.01);
		model.render(entity, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}

}
