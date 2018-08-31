package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.entity.ModelAdipose;
import net.tardis.mod.common.entities.EntityAdipose;

import javax.annotation.Nullable;

public class RenderAdipose extends RenderLiving<EntityAdipose> {
    Minecraft mc;
    public ModelAdipose adipose = new ModelAdipose();
    public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/adipose.png");

    public RenderAdipose(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAdipose(), 0.1F);
        mc = Minecraft.getMinecraft();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAdipose entity) {
        return TEXTURE;
    }

	@Override
	protected void renderModel(EntityAdipose entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		// TODO Auto-generated method stub
		super.renderModel(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	}
}
