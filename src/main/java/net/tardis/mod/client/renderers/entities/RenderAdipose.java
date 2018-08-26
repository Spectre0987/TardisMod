package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.client.models.entity.ModelAdipose;
import net.tardis.mod.common.entities.EntityAdipose;

import javax.annotation.Nullable;

public class RenderAdipose extends RenderLiving<EntityAdipose> {
    ModelAdipose adipose = new ModelAdipose();

    public RenderAdipose(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAdipose(), 1.0F);
    }

    @Override
    protected void renderModel(EntityAdipose entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        GlStateManager.pushMatrix();
        adipose.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        GlStateManager.popMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     *
     * @param entity
     */
    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAdipose entity) {
        return null;
    }
}
