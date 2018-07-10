package net.tardis.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class BodyPartHook extends ModelRenderer {

    public ILimbHook render;

    public BodyPartHook(ModelBase model, String boxName) {
        super(model, boxName);
    }

    public BodyPartHook(ModelBase model, int texOffX, int texOffY) {
        super(model, texOffX, texOffY);
    }

    public BodyPartHook(ModelBase model) {
        super(model);
    }

    public ILimbHook getRender() {
        return render;
    }

    public void setRender(ILimbHook render) {
        this.render = render;
    }

    @Override
    public void render(float scale) {
        if (!this.isHidden) {
            if (this.showModel) {
                GlStateManager.translate(this.offsetX, this.offsetY, this.offsetZ);
                if (this.rotateAngleX == 0.0F && this.rotateAngleY == 0.0F && this.rotateAngleZ == 0.0F) {
                    if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F) {
                        getRender().render(scale);
                    } else {
                        GlStateManager.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);

                        getRender().render(scale);

                        GlStateManager.translate(-this.rotationPointX * scale, -this.rotationPointY * scale, -this.rotationPointZ * scale);
                    }
                } else {
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(this.rotationPointX * scale, this.rotationPointY * scale, this.rotationPointZ * scale);

                    if (this.rotateAngleZ != 0.0F) {
                        GlStateManager.rotate(this.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
                    }

                    if (this.rotateAngleY != 0.0F) {
                        GlStateManager.rotate(this.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
                    }

                    if (this.rotateAngleX != 0.0F) {
                        GlStateManager.rotate(this.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
                    }

                    GlStateManager.scale(1.025f, 1.025f, 1.025f);
                    getRender().render(scale);

                    GlStateManager.popMatrix();
                }

                GlStateManager.translate(-this.offsetX, -this.offsetY, -this.offsetZ);
            }
        }
    }

    public interface ILimbHook {
        void render(float scale);
    }

}
