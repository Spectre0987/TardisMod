package net.tardis.mod.client.renderers.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.entity.ModelAdipose;
import net.tardis.mod.common.entities.EntityAdipose;
import net.tardis.mod.common.entities.EntityCybermanInvasion;

import javax.annotation.Nullable;

public class RenderAdipose extends RenderLiving<EntityAdipose> {
    Minecraft mc;
    public ModelAdipose adipose = new ModelAdipose();
    public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/adipose.png");

    public RenderAdipose(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAdipose(), 0.1F);
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void doRender(EntityAdipose entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y + 1.5 , z);
        mc.getTextureManager().bindTexture(TEXTURE);
        GlStateManager.rotate(180,1,0,0);
        adipose.render(entity, entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted,entity.getRotationYawHead(), entity.rotationPitch, 0.0625F);
        GlStateManager.popMatrix();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAdipose entity) {
        return TEXTURE;
    }
}
