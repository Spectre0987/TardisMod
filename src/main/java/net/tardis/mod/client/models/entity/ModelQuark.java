package net.tardis.mod.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.tardis.mod.common.entities.EntityQuark;

public class ModelQuark extends ModelBase {
    //fields
    private ModelRenderer Head1, Head2, Head3, Head4, Head5, Head6, Head7, Head8, Head9, Head10, Head11, Head12, Head13, Head14, Head15, Head16, Head17, Torso1, Torso2, Torso3, Torso4, Torso5, Torso6, Torso7, Torso8, Torso9, Torso10, Torso11, Torso12, Torso13, TopArm1, TopArm2, TopArm3, TopArm4, TopArm5, BottomArm1, BottomArm2, BottomArm3, BottomArm4, BottomArm5, LeftLeg1, LeftLeg2, LeftLeg3, RightLeg1, RightLeg2, RightLeg3;


    public ModelQuark() {
        textureWidth = 128;
        textureHeight = 128;

        Head1 = new ModelRenderer(this, 18, 0);
        Head1.addBox(-2F, -5.5F, -0.5F, 4, 5, 1);
        Head1.setRotationPoint(0F, 4.5F, 0F);
        Head1.setTextureSize(128, 128);
        Head1.mirror = true;
        setRotation(Head1, 0F, 0F, 0F);
        Head2 = new ModelRenderer(this, 38, 0);
        Head2.addBox(-3F, -3F, -2F, 6, 1, 4);
        Head2.setRotationPoint(0F, 4.5F, 0F);
        Head2.setTextureSize(128, 128);
        Head2.mirror = true;
        setRotation(Head2, 0F, 0F, 0F);
        Head3 = new ModelRenderer(this, 58, 0);
        Head3.addBox(-2.5F, -5F, -0.5F, 5, 5, 1);
        Head3.setRotationPoint(0F, 4.5F, 0F);
        Head3.setTextureSize(128, 128);
        Head3.mirror = true;
        setRotation(Head3, 0F, 0F, 0F);
        Head4 = new ModelRenderer(this, 38, 5);
        Head4.addBox(-0.5F, -8F, -0.5F, 1, 2, 1);
        Head4.setRotationPoint(0F, 5F, 0F);
        Head4.setTextureSize(128, 128);
        Head4.mirror = true;
        setRotation(Head4, 0F, 0.7853982F, 0F);
        Head5 = new ModelRenderer(this, 42, 5);
        Head5.addBox(-0.5F, -4.5F, -3F, 1, 4, 6);
        Head5.setRotationPoint(0F, 4.5F, 0F);
        Head5.setTextureSize(128, 128);
        Head5.mirror = true;
        setRotation(Head5, 0F, 0F, 0F);
        Head6 = new ModelRenderer(this, 28, 11);
        Head6.addBox(-3F, -4.5F, -0.5F, 6, 4, 1);
        Head6.setRotationPoint(0F, 4.5F, 0F);
        Head6.setTextureSize(128, 128);
        Head6.mirror = true;
        setRotation(Head6, 0F, 0F, 0F);
        Head7 = new ModelRenderer(this, 28, 0);
        Head7.addBox(-0.5F, -5.5F, -2F, 1, 5, 4);
        Head7.setRotationPoint(0F, 4.5F, 0F);
        Head7.setTextureSize(128, 128);
        Head7.mirror = true;
        setRotation(Head7, 0F, 0F, 0F);
        Head8 = new ModelRenderer(this, 28, 16);
        Head8.addBox(-2.5F, -3F, -2.5F, 5, 1, 5);
        Head8.setRotationPoint(0F, 4.5F, 0F);
        Head8.setTextureSize(128, 128);
        Head8.mirror = true;
        setRotation(Head8, 0F, 0F, 0F);
        Head9 = new ModelRenderer(this, 48, 15);
        Head9.addBox(-2F, 0.5F, -2F, 4, 1, 4);
        Head9.setRotationPoint(0F, 4.5F, 0F);
        Head9.setTextureSize(128, 128);
        Head9.mirror = true;
        setRotation(Head9, 0F, 0F, 0F);
        Head10 = new ModelRenderer(this, 70, 0);
        Head10.addBox(-0.5F, -5F, -2.5F, 1, 5, 5);
        Head10.setRotationPoint(0F, 4.5F, 0F);
        Head10.setTextureSize(128, 128);
        Head10.mirror = true;
        setRotation(Head10, 0F, 0F, 0F);
        Head11 = new ModelRenderer(this, 82, 0);
        Head11.addBox(-2.5F, -4.5F, -2F, 5, 4, 4);
        Head11.setRotationPoint(0F, 4.5F, 0F);
        Head11.setTextureSize(128, 128);
        Head11.mirror = true;
        setRotation(Head11, 0F, 0F, 0F);
        Head12 = new ModelRenderer(this, 0, 0);
        Head12.addBox(-2F, -4.5F, -2.5F, 4, 4, 5);
        Head12.setRotationPoint(0F, 4.5F, 0F);
        Head12.setTextureSize(128, 128);
        Head12.mirror = true;
        setRotation(Head12, 0F, 0F, 0F);
        Head13 = new ModelRenderer(this, 64, 10);
        Head13.addBox(-2F, -3F, -3F, 4, 1, 6);
        Head13.setRotationPoint(0F, 4.5F, 0F);
        Head13.setTextureSize(128, 128);
        Head13.mirror = true;
        setRotation(Head13, 0F, 0F, 0F);
        Head14 = new ModelRenderer(this, 100, 0);
        Head14.addBox(-0.5F, -0.5F, -5F, 1, 1, 10);
        Head14.setRotationPoint(0F, 2F, 0F);
        Head14.setTextureSize(128, 128);
        Head14.mirror = true;
        setRotation(Head14, 0F, 0F, 0.7853982F);
        Head15 = new ModelRenderer(this, 20, 9);
        Head15.addBox(-5F, -0.5F, -0.5F, 10, 1, 1);
        Head15.setRotationPoint(0F, 2F, 0F);
        Head15.setTextureSize(128, 128);
        Head15.mirror = true;
        setRotation(Head15, 0.7853982F, 0F, 0F);
        Head16 = new ModelRenderer(this, 84, 8);
        Head16.addBox(-2F, -5F, -2F, 4, 5, 4);
        Head16.setRotationPoint(0F, 4.5F, 0F);
        Head16.setTextureSize(128, 128);
        Head16.mirror = true;
        setRotation(Head16, 0F, 0F, 0F);
        Head17 = new ModelRenderer(this, 58, 6);
        Head17.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
        Head17.setRotationPoint(0F, 4.5F, 0F);
        Head17.setTextureSize(128, 128);
        Head17.mirror = true;
        setRotation(Head17, 0F, 0F, 0F);
        Torso1 = new ModelRenderer(this, 48, 20);
        Torso1.addBox(-0.5F, 12F, -4F, 1, 2, 8);
        Torso1.setRotationPoint(0F, 5F, 0F);
        Torso1.setTextureSize(128, 128);
        Torso1.mirror = true;
        setRotation(Torso1, 0F, 0F, 0F);
        Torso2 = new ModelRenderer(this, 30, 22);
        Torso2.addBox(-3.5F, 8.5F, -4F, 7, 1, 2);
        Torso2.setRotationPoint(0F, 5F, 0F);
        Torso2.setTextureSize(128, 128);
        Torso2.mirror = true;
        setRotation(Torso2, 0F, 0F, 0F);
        Torso3 = new ModelRenderer(this, 22, 25);
        Torso3.addBox(-3.5F, 1F, -2F, 7, 11, 6);
        Torso3.setRotationPoint(0F, 5F, 0F);
        Torso3.setTextureSize(128, 128);
        Torso3.mirror = true;
        setRotation(Torso3, 0F, 0F, 0F);
        Torso4 = new ModelRenderer(this, 66, 17);
        Torso4.addBox(-4F, 0.5F, -3.5F, 8, 1, 7);
        Torso4.setRotationPoint(0F, 5F, 0F);
        Torso4.setTextureSize(128, 128);
        Torso4.mirror = true;
        setRotation(Torso4, 0F, 0F, 0F);
        Torso5 = new ModelRenderer(this, 100, 11);
        Torso5.addBox(-3.5F, 9F, -4F, 7, 3, 2);
        Torso5.setRotationPoint(0F, 5F, 0F);
        Torso5.setTextureSize(128, 128);
        Torso5.mirror = true;
        setRotation(Torso5, 0F, 0F, 0F);
        Torso6 = new ModelRenderer(this, 48, 30);
        Torso6.addBox(-4.5F, 1F, -4F, 1, 13, 8);
        Torso6.setRotationPoint(0F, 5F, 0F);
        Torso6.setTextureSize(128, 128);
        Torso6.mirror = true;
        setRotation(Torso6, 0F, 0F, 0F);
        Torso7 = new ModelRenderer(this, 24, 6);
        Torso7.addBox(2.25F, 7.25F, -3.9F, 1, 1, 1);
        Torso7.setRotationPoint(0F, 5F, 0F);
        Torso7.setTextureSize(128, 128);
        Torso7.mirror = true;
        setRotation(Torso7, 0F, 0F, 0F);
        Torso8 = new ModelRenderer(this, 48, 30);
        Torso8.addBox(3.5F, 1F, -4F, 1, 13, 8);
        Torso8.setRotationPoint(0F, 5F, 0F);
        Torso8.setTextureSize(128, 128);
        Torso8.mirror = true;
        setRotation(Torso8, 0F, 0F, 0F);
        Torso9 = new ModelRenderer(this, 30, 22);
        Torso9.addBox(-3.5F, 3.5F, -4F, 7, 1, 2);
        Torso9.setRotationPoint(0F, 5F, 0F);
        Torso9.setTextureSize(128, 128);
        Torso9.mirror = true;
        setRotation(Torso9, 0F, 0F, 0F);
        Torso10 = new ModelRenderer(this, 30, 22);
        Torso10.addBox(-3.5F, 1F, -4F, 7, 1, 2);
        Torso10.setRotationPoint(0F, 5F, 0F);
        Torso10.setTextureSize(128, 128);
        Torso10.mirror = true;
        setRotation(Torso10, 0F, 0F, 0F);
        Torso11 = new ModelRenderer(this, 30, 22);
        Torso11.addBox(-3.5F, 6F, -4F, 7, 1, 2);
        Torso11.setRotationPoint(0F, 5F, 0F);
        Torso11.setTextureSize(128, 128);
        Torso11.mirror = true;
        setRotation(Torso11, 0F, 0F, 0F);
        Torso12 = new ModelRenderer(this, 100, 16);
        Torso12.addBox(-3.5F, 7F, -3.7F, 7, 2, 1);
        Torso12.setRotationPoint(0F, 5F, 0F);
        Torso12.setTextureSize(128, 128);
        Torso12.mirror = true;
        setRotation(Torso12, 0F, 0F, 0F);
        Torso13 = new ModelRenderer(this, 24, 6);
        Torso13.addBox(-3.25F, 7.25F, -3.9F, 1, 1, 1);
        Torso13.setRotationPoint(0F, 5F, 0F);
        Torso13.setTextureSize(128, 128);
        Torso13.mirror = true;
        setRotation(Torso13, 0F, 0F, 0F);
        TopArm1 = new ModelRenderer(this, 66, 25);
        TopArm1.addBox(0F, -1F, 0.5F, 7, 1, 1);
        TopArm1.setRotationPoint(-3.5F, 8F, -3.7F);
        TopArm1.setTextureSize(128, 128);
        TopArm1.mirror = true;
        setRotation(TopArm1, 0F, 0F, 0F);
        TopArm2 = new ModelRenderer(this, 20, 6);
        TopArm2.addBox(6.2F, -0.75F, 0.25F, 1, 1, 1);
        TopArm2.setRotationPoint(-3.5F, 8F, -3.7F);
        TopArm2.setTextureSize(128, 128);
        TopArm2.mirror = true;
        setRotation(TopArm2, 0F, 0F, 0F);
        TopArm3 = new ModelRenderer(this, 66, 25);
        TopArm3.addBox(0F, -1F, 0F, 7, 1, 1);
        TopArm3.setRotationPoint(-3.5F, 8F, -3.7F);
        TopArm3.setTextureSize(128, 128);
        TopArm3.mirror = true;
        setRotation(TopArm3, 0F, 0F, 0F);
        TopArm4 = new ModelRenderer(this, 66, 25);
        TopArm4.addBox(0F, -0.5F, 0F, 7, 1, 1);
        TopArm4.setRotationPoint(-3.5F, 8F, -3.7F);
        TopArm4.setTextureSize(128, 128);
        TopArm4.mirror = true;
        setRotation(TopArm4, 0F, 0F, 0F);
        TopArm5 = new ModelRenderer(this, 66, 25);
        TopArm5.addBox(0F, -0.5F, 0.5F, 7, 1, 1);
        TopArm5.setRotationPoint(-3.5F, 8F, -3.7F);
        TopArm5.setTextureSize(128, 128);
        TopArm5.mirror = true;
        setRotation(TopArm5, 0F, 0F, 0F);
        BottomArm1 = new ModelRenderer(this, 66, 25);
        BottomArm1.addBox(-7F, -0.5F, 0.5F, 7, 1, 1);
        BottomArm1.setRotationPoint(3.5F, 10.5F, -3.7F);
        BottomArm1.setTextureSize(128, 128);
        BottomArm1.mirror = true;
        setRotation(BottomArm1, 0F, 0F, 0F);
        BottomArm2 = new ModelRenderer(this, 66, 25);
        BottomArm2.addBox(-7F, -1F, 0.5F, 7, 1, 1);
        BottomArm2.setRotationPoint(3.5F, 10.5F, -3.7F);
        BottomArm2.setTextureSize(128, 128);
        BottomArm2.mirror = true;
        setRotation(BottomArm2, 0F, 0F, 0F);
        BottomArm3 = new ModelRenderer(this, 66, 25);
        BottomArm3.addBox(-7F, -0.5F, 0F, 7, 1, 1);
        BottomArm3.setRotationPoint(3.5F, 10.5F, -3.7F);
        BottomArm3.setTextureSize(128, 128);
        BottomArm3.mirror = true;
        setRotation(BottomArm3, 0F, 0F, 0F);
        BottomArm4 = new ModelRenderer(this, 20, 6);
        BottomArm4.addBox(-7.2F, -0.75F, 0.25F, 1, 1, 1);
        BottomArm4.setRotationPoint(3.5F, 10.5F, -3.7F);
        BottomArm4.setTextureSize(128, 128);
        BottomArm4.mirror = true;
        setRotation(BottomArm4, 0F, 0F, 0F);
        BottomArm5 = new ModelRenderer(this, 66, 25);
        BottomArm5.addBox(-7F, -1F, 0F, 7, 1, 1);
        BottomArm5.setRotationPoint(3.5F, 10.5F, -3.7F);
        BottomArm5.setTextureSize(128, 128);
        BottomArm5.mirror = true;
        setRotation(BottomArm5, 0F, 0F, 0F);
        LeftLeg1 = new ModelRenderer(this, 20, 11);
        LeftLeg1.addBox(-1F, -1F, -1F, 2, 3, 2);
        LeftLeg1.setRotationPoint(2F, 18F, 0F);
        LeftLeg1.setTextureSize(128, 128);
        LeftLeg1.mirror = true;
        setRotation(LeftLeg1, 0F, 0F, 0F);
        LeftLeg2 = new ModelRenderer(this, 66, 27);
        LeftLeg2.addBox(-1.5F, 2F, -1.5F, 3, 3, 3);
        LeftLeg2.setRotationPoint(2F, 18F, 0F);
        LeftLeg2.setTextureSize(128, 128);
        LeftLeg2.mirror = true;
        setRotation(LeftLeg2, 0F, 0F, 0F);
        LeftLeg3 = new ModelRenderer(this, 0, 9);
        LeftLeg3.addBox(-1.5F, 5F, -3F, 3, 1, 5);
        LeftLeg3.setRotationPoint(2F, 18F, 0F);
        LeftLeg3.setTextureSize(128, 128);
        LeftLeg3.mirror = true;
        setRotation(LeftLeg3, 0F, -0.0698132F, 0F);
        RightLeg1 = new ModelRenderer(this, 0, 9);
        RightLeg1.addBox(-1.5F, 5F, -3F, 3, 1, 5);
        RightLeg1.setRotationPoint(-2F, 18F, 0F);
        RightLeg1.setTextureSize(128, 128);
        RightLeg1.mirror = true;
        setRotation(RightLeg1, 0F, 0.0698132F, 0F);
        RightLeg2 = new ModelRenderer(this, 20, 11);
        RightLeg2.addBox(-1F, -1F, -1F, 2, 3, 2);
        RightLeg2.setRotationPoint(-2F, 18F, 0F);
        RightLeg2.setTextureSize(128, 128);
        RightLeg2.mirror = true;
        setRotation(RightLeg2, 0F, 0F, 0F);
        RightLeg3 = new ModelRenderer(this, 66, 27);
        RightLeg3.addBox(-1.5F, 2F, -1.5F, 3, 3, 3);
        RightLeg3.setRotationPoint(-2F, 18F, 0F);
        RightLeg3.setTextureSize(128, 128);
        RightLeg3.mirror = true;
        setRotation(RightLeg3, 0F, 0F, 0F);
    }


    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        GlStateManager.pushMatrix();
        GlStateManager.rotate(headPitch, 0, 1, 0);
        Head1.render(scale);
        Head2.render(scale);
        Head3.render(scale);
        Head4.render(scale);
        Head5.render(scale);
        Head6.render(scale);
        Head7.render(scale);
        Head8.render(scale);
        Head10.render(scale);
        Head11.render(scale);
        Head12.render(scale);
        Head13.render(scale);
        Head14.render(scale);
        Head15.render(scale);
        Head16.render(scale);
        GlStateManager.popMatrix();

        Head17.render(scale); //Neck
        Head9.render(scale); //Neck


        Torso1.render(scale);
        Torso2.render(scale);
        Torso3.render(scale);
        Torso4.render(scale);
        Torso5.render(scale);
        Torso6.render(scale);
        Torso7.render(scale);
        Torso8.render(scale);
        Torso9.render(scale);
        Torso10.render(scale);
        Torso11.render(scale);
        Torso12.render(scale);
        Torso13.render(scale);
        TopArm1.render(scale);
        TopArm2.render(scale);
        TopArm3.render(scale);
        TopArm4.render(scale);
        TopArm5.render(scale);

        if (entityIn instanceof EntityQuark) {
            EntityQuark quark = (EntityQuark) entityIn;
            GlStateManager.pushMatrix();

            GlStateManager.translate(0, 0, -0.2);
            GlStateManager.rotate(-81, 0, 1, 0);

            BottomArm1.render(scale);
            BottomArm2.render(scale);
            BottomArm3.render(scale);
            BottomArm4.render(scale);
            BottomArm5.render(scale);
            GlStateManager.popMatrix();
        }

        LeftLeg1.render(scale);
        LeftLeg2.render(scale);
        LeftLeg3.render(scale);
        RightLeg1.render(scale);
        RightLeg2.render(scale);
        RightLeg3.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}