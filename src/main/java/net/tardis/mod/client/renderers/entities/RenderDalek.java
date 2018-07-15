package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tardis.mod.client.models.entity.ModelDalek;
import net.tardis.mod.common.entities.EntityDalek;

import javax.annotation.Nullable;

public class RenderDalek extends RenderLiving<EntityDalek> {

    private ModelDalek dalek = new ModelDalek();

    public RenderDalek() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelDalek(), 1.0F);
    }

    @Override
    protected void renderModel(EntityDalek entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        GlStateManager.pushMatrix();

        if (entity.world.isAirBlock(entity.getPosition().down())) {
            float offset = MathHelper.cos(entity.ticksExisted * 0.1F) * -0.05F;
            GlStateManager.translate(0, -offset, 0);
        }

        dalek.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        GlStateManager.popMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     *
     * @param entity
     */
    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDalek entity) {
        return null;
    }
}
