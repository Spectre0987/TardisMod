package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.entity.ModelQuark;
import net.tardis.mod.common.entities.EntityQuark;

public class RenderQuark extends RenderLiving<EntityQuark> {
    private ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/quark.png");

    public RenderQuark(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelQuark(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityQuark entity) {
        return TEXTURE;
    }
}
