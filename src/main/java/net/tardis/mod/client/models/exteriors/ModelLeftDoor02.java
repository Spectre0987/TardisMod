package net.tardis.mod.client.models.exteriors;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLeftDoor02 extends ModelBase
{
  //fields
    ModelRenderer AlteredLeftDoor01;
    ModelRenderer AlteredLeftDoor02;
    ModelRenderer AlteredLeftDoor03;
    ModelRenderer AlteredLeftDoor04;
    ModelRenderer AlteredLeftDoor05;
    ModelRenderer AlteredLeftDoor06;
    ModelRenderer AlteredLeftDoor07;
    ModelRenderer AlteredLeftDoor08;
    ModelRenderer AlteredLeftDoor09;
    ModelRenderer AlteredLeftDoor10;
    ModelRenderer AlteredLeftDoor11;
    ModelRenderer AlteredLeftDoor12;
    ModelRenderer AlteredLeftDoor13;
    ModelRenderer AlteredLeftDoor14;
    ModelRenderer AlteredLeftDoor15;
    ModelRenderer AlteredLeftDoor16;
    ModelRenderer AlteredLeftDoor17;
    ModelRenderer AlteredLeftDoor18;
    ModelRenderer AlteredLeftDoor19;
    ModelRenderer AlteredLeftDoor20;
    ModelRenderer AlteredLeftDoor21;
    ModelRenderer AlteredLeftDoor22;
  
  public ModelLeftDoor02()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      AlteredLeftDoor01 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor01.addBox(-6.5F, 0F, 0F, 5, 1, 1);
      AlteredLeftDoor01.setRotationPoint(0F, 0F, -9F);
      AlteredLeftDoor01.setTextureSize(256, 256);
      AlteredLeftDoor01.mirror = true;
      setRotation(AlteredLeftDoor01, 1.047198F, 0F, 0F);
      AlteredLeftDoor02 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor02.addBox(-6.5F, -1F, 0F, 5, 1, 1);
      AlteredLeftDoor02.setRotationPoint(0F, 6.5F, -9F);
      AlteredLeftDoor02.setTextureSize(256, 256);
      AlteredLeftDoor02.mirror = true;
      setRotation(AlteredLeftDoor02, -1.047198F, 0F, 0F);
      AlteredLeftDoor03 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor03.addBox(-6.5F, 0F, 0F, 5, 1, 1);
      AlteredLeftDoor03.setRotationPoint(0F, 7.5F, -9F);
      AlteredLeftDoor03.setTextureSize(256, 256);
      AlteredLeftDoor03.mirror = true;
      setRotation(AlteredLeftDoor03, 1.047198F, 0F, 0F);
      AlteredLeftDoor04 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor04.addBox(-6.5F, -1F, 0F, 5, 1, 1);
      AlteredLeftDoor04.setRotationPoint(0F, 14F, -9F);
      AlteredLeftDoor04.setTextureSize(256, 256);
      AlteredLeftDoor04.mirror = true;
      setRotation(AlteredLeftDoor04, -1.047198F, 0F, 0F);
      AlteredLeftDoor05 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor05.addBox(-6.5F, 0F, 0F, 5, 1, 1);
      AlteredLeftDoor05.setRotationPoint(0F, 15F, -9F);
      AlteredLeftDoor05.setTextureSize(256, 256);
      AlteredLeftDoor05.mirror = true;
      setRotation(AlteredLeftDoor05, 1.047198F, 0F, 0F);
      AlteredLeftDoor06 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor06.addBox(-6.5F, -1F, 0F, 5, 1, 1);
      AlteredLeftDoor06.setRotationPoint(0F, 21.5F, -9F);
      AlteredLeftDoor06.setTextureSize(256, 256);
      AlteredLeftDoor06.mirror = true;
      setRotation(AlteredLeftDoor06, -1.047198F, 0F, 0F);
      AlteredLeftDoor07 = new ModelRenderer(this, 13, 72);
      AlteredLeftDoor07.addBox(-1.5F, -9F, -8.5F, 1, 31, 1);
      AlteredLeftDoor07.setRotationPoint(0F, 0F, -0.5F);
      AlteredLeftDoor07.setTextureSize(256, 256);
      AlteredLeftDoor07.mirror = true;
      setRotation(AlteredLeftDoor07, 0F, 0F, 0F);
      AlteredLeftDoor08 = new ModelRenderer(this, 187, 0);
      AlteredLeftDoor08.addBox(-0.5F, -8.5F, -8.7F, 1, 31, 1);
      AlteredLeftDoor08.setRotationPoint(0F, 0F, -0.5F);
      AlteredLeftDoor08.setTextureSize(256, 256);
      AlteredLeftDoor08.mirror = true;
      setRotation(AlteredLeftDoor08, 0F, 0F, 0F);
      AlteredLeftDoor09 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor09.addBox(-7.5F, -8.5F, -8.5F, 7, 1, 1);
      AlteredLeftDoor09.setRotationPoint(0F, 0F, -0.5F);
      AlteredLeftDoor09.setTextureSize(256, 256);
      AlteredLeftDoor09.mirror = true;
      setRotation(AlteredLeftDoor09, 0F, 0F, 0F);
      AlteredLeftDoor10 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor10.addBox(-7.5F, 6.5F, -8.5F, 7, 1, 1);
      AlteredLeftDoor10.setRotationPoint(0F, 0F, -0.5F);
      AlteredLeftDoor10.setTextureSize(256, 256);
      AlteredLeftDoor10.mirror = true;
      setRotation(AlteredLeftDoor10, 0F, 0F, 0F);
      AlteredLeftDoor11 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor11.addBox(-7.5F, 14F, -8.5F, 7, 1, 1);
      AlteredLeftDoor11.setRotationPoint(0F, 0F, -0.5F);
      AlteredLeftDoor11.setTextureSize(256, 256);
      AlteredLeftDoor11.mirror = true;
      setRotation(AlteredLeftDoor11, 0F, 0F, 0F);
      AlteredLeftDoor12 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor12.addBox(-7.5F, 21.5F, -8.5F, 7, 1, 1);
      AlteredLeftDoor12.setRotationPoint(0F, 0F, -0.5F);
      AlteredLeftDoor12.setTextureSize(256, 256);
      AlteredLeftDoor12.mirror = true;
      setRotation(AlteredLeftDoor12, 0F, 0F, 0F);
      AlteredLeftDoor13 = new ModelRenderer(this, 0, 0);
      AlteredLeftDoor13.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
      AlteredLeftDoor13.setRotationPoint(-3.2F, -4.45F, -8.2F);
      AlteredLeftDoor13.setTextureSize(256, 256);
      AlteredLeftDoor13.mirror = true;
      setRotation(AlteredLeftDoor13, 0F, 0.7853982F, 0F);
      AlteredLeftDoor14 = new ModelRenderer(this, 0, 0);
      AlteredLeftDoor14.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
      AlteredLeftDoor14.setRotationPoint(-4.8F, -4.45F, -8.2F);
      AlteredLeftDoor14.setTextureSize(256, 256);
      AlteredLeftDoor14.mirror = true;
      setRotation(AlteredLeftDoor14, 0F, 0.7853982F, 0F);
      AlteredLeftDoor15 = new ModelRenderer(this, 0, 0);
      AlteredLeftDoor15.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
      AlteredLeftDoor15.setRotationPoint(-4F, -4.25F, -8.2F);
      AlteredLeftDoor15.setTextureSize(256, 256);
      AlteredLeftDoor15.mirror = true;
      setRotation(AlteredLeftDoor15, 0.7853982F, 0F, 0F);
      AlteredLeftDoor16 = new ModelRenderer(this, 0, 72);
      AlteredLeftDoor16.addBox(-6.5F, -7.75F, -8.2F, 5, 30, 0);
      AlteredLeftDoor16.setRotationPoint(0F, -0.5F, -0.5F);
      AlteredLeftDoor16.setTextureSize(256, 256);
      AlteredLeftDoor16.mirror = true;
      setRotation(AlteredLeftDoor16, 0F, 0F, 0F);
      AlteredLeftDoor17 = new ModelRenderer(this, 13, 33);
      AlteredLeftDoor17.addBox(0F, -8F, 0F, 1, 31, 1);
      AlteredLeftDoor17.setRotationPoint(-6.5F, -0.5F, -9F);
      AlteredLeftDoor17.setTextureSize(256, 256);
      AlteredLeftDoor17.mirror = true;
      setRotation(AlteredLeftDoor17, 0F, -1.047198F, 0F);
      AlteredLeftDoor18 = new ModelRenderer(this, 13, 33);
      AlteredLeftDoor18.addBox(-1F, -8F, 0F, 1, 31, 1);
      AlteredLeftDoor18.setRotationPoint(-1.5F, -0.5F, -9F);
      AlteredLeftDoor18.setTextureSize(256, 256);
      AlteredLeftDoor18.mirror = true;
      setRotation(AlteredLeftDoor18, 0F, 1.047198F, 0F);
      AlteredLeftDoor19 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor19.addBox(-6.5F, 0F, 0F, 5, 1, 1);
      AlteredLeftDoor19.setRotationPoint(0F, -7.5F, -9F);
      AlteredLeftDoor19.setTextureSize(256, 256);
      AlteredLeftDoor19.mirror = true;
      setRotation(AlteredLeftDoor19, 1.047198F, 0F, 0F);
      AlteredLeftDoor20 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor20.addBox(-6.5F, -1F, 0F, 5, 1, 1);
      AlteredLeftDoor20.setRotationPoint(0F, -1F, -9F);
      AlteredLeftDoor20.setTextureSize(256, 256);
      AlteredLeftDoor20.mirror = true;
      setRotation(AlteredLeftDoor20, -1.047198F, 0F, 0F);
      AlteredLeftDoor21 = new ModelRenderer(this, 13, 72);
      AlteredLeftDoor21.addBox(-7.5F, -8.5F, -8.5F, 1, 31, 1);
      AlteredLeftDoor21.setRotationPoint(0F, 0F, -0.5F);
      AlteredLeftDoor21.setTextureSize(256, 256);
      AlteredLeftDoor21.mirror = true;
      setRotation(AlteredLeftDoor21, 0F, 0F, 0F);
      AlteredLeftDoor22 = new ModelRenderer(this, 18, 72);
      AlteredLeftDoor22.addBox(-7.5F, -1F, -8.5F, 7, 1, 1);
      AlteredLeftDoor22.setRotationPoint(0F, 0F, -0.5F);
      AlteredLeftDoor22.setTextureSize(256, 256);
      AlteredLeftDoor22.mirror = true;
      setRotation(AlteredLeftDoor22, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    AlteredLeftDoor01.render(f5);
    AlteredLeftDoor02.render(f5);
    AlteredLeftDoor03.render(f5);
    AlteredLeftDoor04.render(f5);
    AlteredLeftDoor05.render(f5);
    AlteredLeftDoor06.render(f5);
    AlteredLeftDoor07.render(f5);
    AlteredLeftDoor08.render(f5);
    AlteredLeftDoor09.render(f5);
    AlteredLeftDoor10.render(f5);
    AlteredLeftDoor11.render(f5);
    AlteredLeftDoor12.render(f5);
    AlteredLeftDoor13.render(f5);
    AlteredLeftDoor14.render(f5);
    AlteredLeftDoor15.render(f5);
    AlteredLeftDoor16.render(f5);
    AlteredLeftDoor17.render(f5);
    AlteredLeftDoor18.render(f5);
    AlteredLeftDoor19.render(f5);
    AlteredLeftDoor20.render(f5);
    AlteredLeftDoor21.render(f5);
    AlteredLeftDoor22.render(f5);
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
