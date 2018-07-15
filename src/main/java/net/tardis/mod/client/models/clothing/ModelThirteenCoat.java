package net.tardis.mod.client.models.clothing;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class ModelThirteenCoat extends ModelBiped
{
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/clothing/thirteen_coat.png");
  //fields
    ModelRenderer CoatBaseLeft;
    ModelRenderer CoatBaseLeft2;
    ModelRenderer CoatBaseRight;
    ModelRenderer CoatBaseRight2;
    ModelRenderer CoatBaseBack;
    ModelRenderer CoatArmFrontR;
    ModelRenderer CoatArmTopR;
    ModelRenderer CoatArmLeftR;
    ModelRenderer CoatArmRightR;
    ModelRenderer CoatArmBackR;
    ModelRenderer CoatArmFront;
    ModelRenderer CoatArmTop;
    ModelRenderer CoatArmBack;
    ModelRenderer CoatArmLeft;
    ModelRenderer CoatArmRight;
    ModelRenderer Button1;
    ModelRenderer Button2;
    ModelRenderer Button3;
    ModelRenderer Button4;
    ModelRenderer Button5;
    ModelRenderer Button6;
    ModelRenderer LapelRight1;
    ModelRenderer LapelRight2;
    ModelRenderer LapelLeft1;
    ModelRenderer LapelLeft2;
    ModelRenderer ButtonBack1;
    ModelRenderer ButtonBack2;
  
  public ModelThirteenCoat()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      CoatBaseLeft = new ModelRenderer(this, 22, 0);
      CoatBaseLeft.addBox(0F, 0F, 0F, 1, 15, 5);
      CoatBaseLeft.setRotationPoint(3.1F, -0.1F, -2.5F);
      CoatBaseLeft.setTextureSize(64, 64);
      CoatBaseLeft.mirror = true;
      setRotation(CoatBaseLeft, 0F, 0F, 0F);
      CoatBaseLeft2 = new ModelRenderer(this, 36, 0);
      CoatBaseLeft2.addBox(0F, 0F, 0F, 1, 14, 4);
      CoatBaseLeft2.setRotationPoint(2.1F, -0.1F, -2.5F);
      CoatBaseLeft2.setTextureSize(64, 64);
      CoatBaseLeft2.mirror = true;
      setRotation(CoatBaseLeft2, 0F, 0F, 0F);
      CoatBaseRight = new ModelRenderer(this, 22, 21);
      CoatBaseRight.addBox(0F, 0F, 0F, 1, 15, 5);
      CoatBaseRight.setRotationPoint(-4.1F, -0.1F, -2.5F);
      CoatBaseRight.setTextureSize(64, 64);
      CoatBaseRight.mirror = true;
      setRotation(CoatBaseRight, 0F, 0F, 0F);
      CoatBaseRight2 = new ModelRenderer(this, 36, 19);
      CoatBaseRight2.addBox(0F, 0F, 0F, 1, 14, 4);
      CoatBaseRight2.setRotationPoint(-3.1F, -0.1F, -2.5F);
      CoatBaseRight2.setTextureSize(64, 64);
      CoatBaseRight2.mirror = true;
      setRotation(CoatBaseRight2, 0F, 0F, 0F);
      CoatBaseBack = new ModelRenderer(this, 0, 0);
      CoatBaseBack.addBox(0F, 0F, 0F, 8, 15, 1);
      CoatBaseBack.setRotationPoint(-4F, -0.1F, 1.5F);
      CoatBaseBack.setTextureSize(64, 64);
      CoatBaseBack.mirror = true;
      setRotation(CoatBaseBack, 0F, 0F, 0F);
      CoatArmFrontR = new ModelRenderer(this, 0, 30);
      CoatArmFrontR.addBox(0F, 0F, 0F, 4, 10, 1);
      CoatArmFrontR.setRotationPoint(-8F, 0.3F, -2.5F);
      CoatArmFrontR.setTextureSize(64, 64);
      CoatArmFrontR.mirror = true;
      setRotation(CoatArmFrontR, 0F, 0F, 0F);
      CoatArmTopR = new ModelRenderer(this, 0, 24);
      CoatArmTopR.addBox(0F, 0F, 0F, 4, 1, 4);
      CoatArmTopR.setRotationPoint(-8F, 0.3F, -2F);
      CoatArmTopR.setTextureSize(64, 64);
      CoatArmTopR.mirror = true;
      setRotation(CoatArmTopR, 0F, 0F, 0F);
      CoatArmLeftR = new ModelRenderer(this, 0, 42);
      CoatArmLeftR.addBox(0F, 0F, 0F, 1, 10, 5);
      CoatArmLeftR.setRotationPoint(-8.1F, 0.3F, -2.5F);
      CoatArmLeftR.setTextureSize(64, 64);
      CoatArmLeftR.mirror = true;
      setRotation(CoatArmLeftR, 0F, 0F, 0F);
      CoatArmRightR = new ModelRenderer(this, 14, 42);
      CoatArmRightR.addBox(0F, 0F, 0F, 1, 10, 5);
      CoatArmRightR.setRotationPoint(-5F, 0.3F, -2.5F);
      CoatArmRightR.setTextureSize(64, 64);
      CoatArmRightR.mirror = true;
      setRotation(CoatArmRightR, 0F, 0F, 0F);
      CoatArmBackR = new ModelRenderer(this, 10, 30);
      CoatArmBackR.addBox(0F, 0F, 0F, 4, 10, 1);
      CoatArmBackR.setRotationPoint(-8F, 0.3F, 1.5F);
      CoatArmBackR.setTextureSize(64, 64);
      CoatArmBackR.mirror = true;
      setRotation(CoatArmBackR, 0F, 0F, 0F);
      CoatArmFront = new ModelRenderer(this, 47, 0);
      CoatArmFront.addBox(0F, 0F, 0F, 4, 10, 1);
      CoatArmFront.setRotationPoint(4F, 0.1F, -2.5F);
      CoatArmFront.setTextureSize(64, 64);
      CoatArmFront.mirror = true;
      setRotation(CoatArmFront, 0F, 0F, 0F);
      CoatArmTop = new ModelRenderer(this, 0, 18);
      CoatArmTop.addBox(0F, 0F, 0F, 4, 1, 4);
      CoatArmTop.setRotationPoint(4F, 0.1F, -2F);
      CoatArmTop.setTextureSize(64, 64);
      CoatArmTop.mirror = true;
      setRotation(CoatArmTop, 0F, 0F, 0F);
      CoatArmBack = new ModelRenderer(this, 47, 12);
      CoatArmBack.addBox(0F, 0F, 0F, 4, 10, 1);
      CoatArmBack.setRotationPoint(4F, 0.1F, 1.5F);
      CoatArmBack.setTextureSize(64, 64);
      CoatArmBack.mirror = true;
      setRotation(CoatArmBack, 0F, 0F, 0F);
      CoatArmLeft = new ModelRenderer(this, 27, 42);
      CoatArmLeft.addBox(0F, 0F, 0F, 1, 10, 5);
      CoatArmLeft.setRotationPoint(7.1F, 0.1F, -2.5F);
      CoatArmLeft.setTextureSize(64, 64);
      CoatArmLeft.mirror = true;
      setRotation(CoatArmLeft, 0F, 0F, 0F);
      CoatArmRight = new ModelRenderer(this, 41, 42);
      CoatArmRight.addBox(0F, 0F, 0F, 1, 10, 5);
      CoatArmRight.setRotationPoint(4F, 0.1F, -2.5F);
      CoatArmRight.setTextureSize(64, 64);
      CoatArmRight.mirror = true;
      setRotation(CoatArmRight, 0F, 0F, 0F);
      Button1 = new ModelRenderer(this, 58, 0);
      Button1.addBox(0F, 0F, -2F, 1, 1, 1);
      Button1.setRotationPoint(-4.2F, 11.5F, -0.7F);
      Button1.setTextureSize(64, 64);
      Button1.mirror = true;
      setRotation(Button1, 0F, 0F, 0F);
      Button2 = new ModelRenderer(this, 58, 3);
      Button2.addBox(0F, 0F, -2F, 1, 1, 1);
      Button2.setRotationPoint(-4.2F, 9.5F, -0.7F);
      Button2.setTextureSize(64, 64);
      Button2.mirror = true;
      setRotation(Button2, 0F, 0F, 0F);
      Button3 = new ModelRenderer(this, 58, 6);
      Button3.addBox(0F, 0F, -2F, 1, 1, 1);
      Button3.setRotationPoint(-4.2F, 7.5F, -0.7F);
      Button3.setTextureSize(64, 64);
      Button3.mirror = true;
      setRotation(Button3, 0F, 0F, 0F);
      Button4 = new ModelRenderer(this, 58, 9);
      Button4.addBox(0F, 0F, -2F, 1, 1, 1);
      Button4.setRotationPoint(3.2F, 11.5F, -0.7F);
      Button4.setTextureSize(64, 64);
      Button4.mirror = true;
      setRotation(Button4, 0F, 0F, 0F);
      Button5 = new ModelRenderer(this, 58, 12);
      Button5.addBox(0F, 0F, -2F, 1, 1, 1);
      Button5.setRotationPoint(3.2F, 9.5F, -0.7F);
      Button5.setTextureSize(64, 64);
      Button5.mirror = true;
      setRotation(Button5, 0F, 0F, 0F);
      Button6 = new ModelRenderer(this, 58, 15);
      Button6.addBox(0F, 0F, -2F, 1, 1, 1);
      Button6.setRotationPoint(3.2F, 7.5F, -0.7F);
      Button6.setTextureSize(64, 64);
      Button6.mirror = true;
      setRotation(Button6, 0F, 0F, 0F);
      LapelRight1 = new ModelRenderer(this, 47, 24);
      LapelRight1.addBox(0F, 0F, 0F, 1, 7, 1);
      LapelRight1.setRotationPoint(-3.2F, 0F, -2.8F);
      LapelRight1.setTextureSize(64, 64);
      LapelRight1.mirror = true;
      setRotation(LapelRight1, 0F, 0F, 0F);
      LapelRight2 = new ModelRenderer(this, 52, 24);
      LapelRight2.addBox(0F, 0F, 0F, 1, 4, 1);
      LapelRight2.setRotationPoint(-3.9F, 1F, -2.7F);
      LapelRight2.setTextureSize(64, 64);
      LapelRight2.mirror = true;
      setRotation(LapelRight2, 0F, 0F, 0F);
      LapelLeft1 = new ModelRenderer(this, 47, 33);
      LapelLeft1.addBox(0F, 0F, 0F, 1, 7, 1);
      LapelLeft1.setRotationPoint(2.2F, 0F, -2.8F);
      LapelLeft1.setTextureSize(64, 64);
      LapelLeft1.mirror = true;
      setRotation(LapelLeft1, 0F, 0F, 0F);
      LapelLeft2 = new ModelRenderer(this, 52, 30);
      LapelLeft2.addBox(0F, 0F, 0F, 1, 4, 1);
      LapelLeft2.setRotationPoint(2.9F, 1F, -2.7F);
      LapelLeft2.setTextureSize(64, 64);
      LapelLeft2.mirror = true;
      setRotation(LapelLeft2, 0F, 0F, 0F);
      ButtonBack1 = new ModelRenderer(this, 52, 36);
      ButtonBack1.addBox(0F, 0F, 0F, 1, 1, 1);
      ButtonBack1.setRotationPoint(1F, 9.1F, 1.7F);
      ButtonBack1.setTextureSize(64, 64);
      ButtonBack1.mirror = true;
      setRotation(ButtonBack1, 0F, 0F, 0F);
      ButtonBack2 = new ModelRenderer(this, 52, 39);
      ButtonBack2.addBox(0F, 0F, 0F, 1, 1, 1);
      ButtonBack2.setRotationPoint(-2F, 9.1F, 1.7F);
      ButtonBack2.setTextureSize(64, 64);
      ButtonBack2.mirror = true;
      setRotation(ButtonBack2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    CoatBaseLeft.render(f5);
    CoatBaseLeft2.render(f5);
    CoatBaseRight.render(f5);
    CoatBaseRight2.render(f5);
    CoatBaseBack.render(f5);
    CoatArmFrontR.render(f5);
    CoatArmTopR.render(f5);
    CoatArmLeftR.render(f5);
    CoatArmRightR.render(f5);
    CoatArmBackR.render(f5);
    CoatArmFront.render(f5);
    CoatArmTop.render(f5);
    CoatArmBack.render(f5);
    CoatArmLeft.render(f5);
    CoatArmRight.render(f5);
    Button1.render(f5);
    Button2.render(f5);
    Button3.render(f5);
    Button4.render(f5);
    Button5.render(f5);
    Button6.render(f5);
    LapelRight1.render(f5);
    LapelRight2.render(f5);
    LapelLeft1.render(f5);
    LapelLeft2.render(f5);
    ButtonBack1.render(f5);
    ButtonBack2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
