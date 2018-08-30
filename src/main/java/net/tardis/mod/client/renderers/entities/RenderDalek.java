package net.tardis.mod.client.renderers.entities;

import javax.annotation.Nullable;

import org.lwjgl.util.glu.Sphere;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tardis.mod.client.models.entity.ModelDalek;
import net.tardis.mod.common.entities.EntityDalek;

public class RenderDalek extends RenderLiving<EntityDalek> {

    private ModelDalek dalek = new ModelDalek();
    private Sphere spehere = new Sphere();

    public RenderDalek(RenderManager manager) {
        super(manager, new ModelDalek(), 1.0F);
    }

    @Override
    protected void renderModel(EntityDalek entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        GlStateManager.pushMatrix();

        if (entity.world.isAirBlock(entity.getPosition().down())) {
            float offset = MathHelper.cos(entity.ticksExisted * 0.1F) * -0.05F;
            GlStateManager.translate(0, -offset, 0);
        }

        /*GlStateManager.pushMatrix();
        GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
        GlStateManager.color(0.2f, 0.2f, 1, 0.5f);
        spehere.setTextureFlag(false);
        spehere.draw(1.5f, 32, 32);
        GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
        GlStateManager.popMatrix();

        GlStateManager.color(1F, 1F, 1F, 1F);*/
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
