package net.tardis.mod.client.models.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAdipose extends ModelBiped
{
    //fields
    ModelRenderer LeftEye;
    ModelRenderer Mouth1;
    ModelRenderer RightHand;
    ModelRenderer RightEye;
    ModelRenderer Mouth2;
    ModelRenderer BodyHeadThingy;
    ModelRenderer Body1;
    ModelRenderer Body2;
    ModelRenderer Body3;
    ModelRenderer Body4;
    ModelRenderer Body5;
    ModelRenderer Body6;
    ModelRenderer LeftHand;
    ModelRenderer RightFoot;
    ModelRenderer LeftFoot;
    ModelRenderer LeftHeel;
    ModelRenderer RightHeel;

    public ModelAdipose()
    {
        textureWidth = 64;
        textureHeight = 64;

        LeftEye = new ModelRenderer(this, 24, 0);
        LeftEye.addBox(1.25F, -2F, -2.2F, 1, 1, 1);
        LeftEye.setRotationPoint(0F, 19F, 0F);
        LeftEye.setTextureSize(64, 64);
        LeftEye.mirror = true;
        setRotation(LeftEye, 0F, 0F, 0F);
        Mouth1 = new ModelRenderer(this, 28, 0);
        Mouth1.addBox(-0.85F, -0.8F, -2F, 1, 1, 1);
        Mouth1.setRotationPoint(0F, 19F, 0F);
        Mouth1.setTextureSize(64, 64);
        Mouth1.mirror = true;
        setRotation(Mouth1, 0.2792527F, 0F, 0F);
        RightHand = new ModelRenderer(this, 20, 2);
        RightHand.addBox(-1F, 0F, -1.5F, 1, 3, 3);
        RightHand.setRotationPoint(-2F, 19F, 0F);
        RightHand.setTextureSize(64, 64);
        RightHand.mirror = true;
        setRotation(RightHand, 0F, 0F, 0.5585054F);
        RightEye = new ModelRenderer(this, 24, 0);
        RightEye.addBox(-2.25F, -2F, -2.2F, 1, 1, 1);
        RightEye.setRotationPoint(0F, 19F, 0F);
        RightEye.setTextureSize(64, 64);
        RightEye.mirror = true;
        setRotation(RightEye, 0F, 0F, 0F);
        Mouth2 = new ModelRenderer(this, 28, 0);
        Mouth2.addBox(-0.25F, -0.8F, -2F, 1, 1, 1);
        Mouth2.setRotationPoint(0F, 19F, 0F);
        Mouth2.setTextureSize(64, 64);
        Mouth2.mirror = true;
        setRotation(Mouth2, 0.2792527F, 0F, 0F);
        BodyHeadThingy = new ModelRenderer(this, 0, 0);
        BodyHeadThingy.addBox(-3F, -3.5F, -2F, 6, 7, 4);
        BodyHeadThingy.setRotationPoint(0F, 19F, 0F);
        BodyHeadThingy.setTextureSize(64, 64);
        BodyHeadThingy.mirror = true;
        setRotation(BodyHeadThingy, 0F, 0F, 0F);
        Body1 = new ModelRenderer(this, 28, 2);
        Body1.addBox(1.85F, 2.85F, -2F, 1, 1, 4);
        Body1.setRotationPoint(0F, 19F, 0F);
        Body1.setTextureSize(64, 64);
        Body1.mirror = true;
        setRotation(Body1, 0F, 0F, 0F);
        Body2 = new ModelRenderer(this, 20, 8);
        Body2.addBox(-2.5F, 3F, -2F, 5, 1, 4);
        Body2.setRotationPoint(0F, 19F, 0F);
        Body2.setTextureSize(64, 64);
        Body2.mirror = true;
        setRotation(Body2, 0F, 0F, 0F);
        Body3 = new ModelRenderer(this, 20, 8);
        Body3.addBox(-2.5F, -4F, -2F, 5, 1, 4);
        Body3.setRotationPoint(0F, 19F, 0F);
        Body3.setTextureSize(64, 64);
        Body3.mirror = true;
        setRotation(Body3, 0F, 0F, 0F);
        Body4 = new ModelRenderer(this, 28, 2);
        Body4.addBox(-2.85F, 2.85F, -2F, 1, 1, 4);
        Body4.setRotationPoint(0F, 19F, 0F);
        Body4.setTextureSize(64, 64);
        Body4.mirror = true;
        setRotation(Body4, 0F, 0F, 0F);
        Body5 = new ModelRenderer(this, 28, 2);
        Body5.addBox(1.85F, -3.85F, -2F, 1, 1, 4);
        Body5.setRotationPoint(0F, 19F, 0F);
        Body5.setTextureSize(64, 64);
        Body5.mirror = true;
        setRotation(Body5, 0F, 0F, 0F);
        Body6 = new ModelRenderer(this, 28, 2);
        Body6.addBox(-2.85F, -3.85F, -2F, 1, 1, 4);
        Body6.setRotationPoint(0F, 19F, 0F);
        Body6.setTextureSize(64, 64);
        Body6.mirror = true;
        setRotation(Body6, 0F, 0F, 0F);
        LeftHand = new ModelRenderer(this, 20, 2);
        LeftHand.addBox(0F, 0F, -1.5F, 1, 3, 3);
        LeftHand.setRotationPoint(2F, 19F, 0F);
        LeftHand.setTextureSize(64, 64);
        LeftHand.mirror = true;
        setRotation(LeftHand, 0F, 0F, -0.5585054F);
        LeftHand.mirror = false;
        RightFoot = new ModelRenderer(this, 38, 0);
        RightFoot.addBox(-1F, 0F, -1.5F, 2, 1, 2);
        RightFoot.setRotationPoint(-2F, 23F, 0F);
        RightFoot.setTextureSize(64, 64);
        RightFoot.mirror = true;
        setRotation(RightFoot, 0F, 0.3490659F, 0F);
        LeftFoot = new ModelRenderer(this, 38, 0);
        LeftFoot.addBox(-1F, 0F, -1.5F, 2, 1, 2);
        LeftFoot.setRotationPoint(2F, 23F, 0F);
        LeftFoot.setTextureSize(64, 64);
        LeftFoot.mirror = true;
        setRotation(LeftFoot, 0F, -0.3490659F, 0F);
        LeftHeel = new ModelRenderer(this, 20, 0);
        LeftHeel.addBox(-0.5F, 0F, 0.5F, 1, 1, 1);
        LeftHeel.setRotationPoint(2F, 23F, 0F);
        LeftHeel.setTextureSize(64, 64);
        LeftHeel.mirror = true;
        setRotation(LeftHeel, 0F, -0.3490659F, 0F);
        RightHeel = new ModelRenderer(this, 20, 0);
        RightHeel.addBox(-0.5F, 0F, 0.5F, 1, 1, 1);
        RightHeel.setRotationPoint(-2F, 23F, 0F);
        RightHeel.setTextureSize(64, 64);
        RightHeel.mirror = true;
        setRotation(RightHeel, 0F, 0.3490659F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        
        GlStateManager.pushMatrix();
        GlStateManager.rotate((float)MathHelper.clamp(Math.toDegrees(this.bipedLeftLeg.rotateAngleX), -5, 5), 1, 0, 0);
        LeftFoot.render(f5);
        LeftHeel.render(f5);
        GlStateManager.popMatrix();
        LeftEye.render(f5);
        Mouth1.render(f5);
        RightHand.render(f5);
        RightEye.render(f5);
        Mouth2.render(f5);
        BodyHeadThingy.render(f5);
        Body1.render(f5);
        Body2.render(f5);
        Body3.render(f5);
        Body4.render(f5);
        Body5.render(f5);
        Body6.render(f5);
        LeftHand.render(f5);
        RightFoot.render(f5);
        RightHeel.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        //TODO Add an animation on Adiposes parts
        /*float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.LeftFoot.rotateAngleX = -(0.1F - f * 0.6F);
        this.LeftFoot.rotateAngleZ = 0.0F;
        this.RightFoot.rotateAngleX = 0.1F - f * 0.6F;
        this.RightFoot.rotateAngleZ = 0.0F;
        this.LeftHand.rotateAngleX = 0.1F - f * 0.6F;
        this.LeftHand.rotateAngleZ = 0.0F;
        this.RightHand.rotateAngleX = -(0.1F - f * 0.6F);
        this.RightHand.rotateAngleZ = 0.0F;
        float f2 = -(float)Math.PI / ( 2.25F);
        this.RightHand.rotateAngleX = f2;
        this.LeftHand.rotateAngleX = f2;
        this.RightHand.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.LeftHand.rotateAngleX += f * 1.2F - f1 * 0.4F;
        this.RightHand.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.LeftHand.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.RightHand.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.LeftHand.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;*/
    }



}
