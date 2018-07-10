package net.tardis.mod.client.models.exteriors;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLeftDoor01 extends ModelBase
{
  //fields
    ModelRenderer LeftDoor01;
    ModelRenderer LeftDoor02;
    ModelRenderer LeftDoor03;
    ModelRenderer LeftDoor04;
    ModelRenderer LeftDoor05;
    ModelRenderer LeftDoor06;
    ModelRenderer LeftDoor07;
    ModelRenderer LeftDoor08;
    ModelRenderer LeftDoor09;
    ModelRenderer LeftDoor10;
    ModelRenderer LeftDoor11;
    ModelRenderer LeftDoor12;
    ModelRenderer LeftDoor13;
    ModelRenderer LeftDoor14;
    ModelRenderer LeftDoor15;
    ModelRenderer LeftDoor16;
    ModelRenderer LeftDoor17;
    ModelRenderer LeftDoor18;
    ModelRenderer LeftDoor19;
    ModelRenderer LeftDoor20;
    ModelRenderer LeftDoor21;
    ModelRenderer LeftDoor22;
    ModelRenderer LeftDoor23;
    ModelRenderer LeftDoor24;
  
  public ModelLeftDoor01()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      LeftDoor01 = new ModelRenderer(this, 18, 72);
      LeftDoor01.addBox(-6.5F, 0F, 0F, 5, 1, 1);
      LeftDoor01.setRotationPoint(0F, 0F, -9F);
      LeftDoor01.setTextureSize(256, 256);
      LeftDoor01.mirror = true;
      setRotation(LeftDoor01, 1.047198F, 0F, 0F);
      LeftDoor02 = new ModelRenderer(this, 18, 72);
      LeftDoor02.addBox(-6.5F, -1F, 0F, 5, 1, 1);
      LeftDoor02.setRotationPoint(0F, 6.5F, -9F);
      LeftDoor02.setTextureSize(256, 256);
      LeftDoor02.mirror = true;
      setRotation(LeftDoor02, -1.047198F, 0F, 0F);
      LeftDoor03 = new ModelRenderer(this, 18, 72);
      LeftDoor03.addBox(-6.5F, 0F, 0F, 5, 1, 1);
      LeftDoor03.setRotationPoint(0F, 7.5F, -9F);
      LeftDoor03.setTextureSize(256, 256);
      LeftDoor03.mirror = true;
      setRotation(LeftDoor03, 1.047198F, 0F, 0F);
      LeftDoor04 = new ModelRenderer(this, 18, 72);
      LeftDoor04.addBox(-6.5F, -1F, 0F, 5, 1, 1);
      LeftDoor04.setRotationPoint(0F, 14F, -9F);
      LeftDoor04.setTextureSize(256, 256);
      LeftDoor04.mirror = true;
      setRotation(LeftDoor04, -1.047198F, 0F, 0F);
      LeftDoor05 = new ModelRenderer(this, 18, 72);
      LeftDoor05.addBox(-6.5F, 0F, 0F, 5, 1, 1);
      LeftDoor05.setRotationPoint(0F, 15F, -9F);
      LeftDoor05.setTextureSize(256, 256);
      LeftDoor05.mirror = true;
      setRotation(LeftDoor05, 1.047198F, 0F, 0F);
      LeftDoor06 = new ModelRenderer(this, 18, 72);
      LeftDoor06.addBox(-6.5F, -1F, 0F, 5, 1, 1);
      LeftDoor06.setRotationPoint(0F, 21.5F, -9F);
      LeftDoor06.setTextureSize(256, 256);
      LeftDoor06.mirror = true;
      setRotation(LeftDoor06, -1.047198F, 0F, 0F);
      LeftDoor07 = new ModelRenderer(this, 13, 72);
      LeftDoor07.addBox(-1.5F, -9F, -8.5F, 1, 31, 1);
      LeftDoor07.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor07.setTextureSize(256, 256);
      LeftDoor07.mirror = true;
      setRotation(LeftDoor07, 0F, 0F, 0F);
      LeftDoor08 = new ModelRenderer(this, 187, 0);
      LeftDoor08.addBox(-0.5F, -8.5F, -8.7F, 1, 31, 1);
      LeftDoor08.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor08.setTextureSize(256, 256);
      LeftDoor08.mirror = true;
      setRotation(LeftDoor08, 0F, 0F, 0F);
      LeftDoor09 = new ModelRenderer(this, 18, 72);
      LeftDoor09.addBox(-7.5F, -8.5F, -8.5F, 7, 1, 1);
      LeftDoor09.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor09.setTextureSize(256, 256);
      LeftDoor09.mirror = true;
      setRotation(LeftDoor09, 0F, 0F, 0F);
      LeftDoor10 = new ModelRenderer(this, 18, 72);
      LeftDoor10.addBox(-7.5F, 6.5F, -8.5F, 7, 1, 1);
      LeftDoor10.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor10.setTextureSize(256, 256);
      LeftDoor10.mirror = true;
      setRotation(LeftDoor10, 0F, 0F, 0F);
      LeftDoor11 = new ModelRenderer(this, 18, 72);
      LeftDoor11.addBox(-7.5F, 14F, -8.5F, 7, 1, 1);
      LeftDoor11.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor11.setTextureSize(256, 256);
      LeftDoor11.mirror = true;
      setRotation(LeftDoor11, 0F, 0F, 0F);
      LeftDoor12 = new ModelRenderer(this, 18, 72);
      LeftDoor12.addBox(-7.5F, 21.5F, -8.5F, 7, 1, 1);
      LeftDoor12.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor12.setTextureSize(256, 256);
      LeftDoor12.mirror = true;
      setRotation(LeftDoor12, 0F, 0F, 0F);
      LeftDoor13 = new ModelRenderer(this, 0, 0);
      LeftDoor13.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
      LeftDoor13.setRotationPoint(-3.2F, -4.45F, -8.2F);
      LeftDoor13.setTextureSize(256, 256);
      LeftDoor13.mirror = true;
      setRotation(LeftDoor13, 0F, 0.7853982F, 0F);
      LeftDoor14 = new ModelRenderer(this, 0, 0);
      LeftDoor14.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
      LeftDoor14.setRotationPoint(-4.8F, -4.45F, -8.2F);
      LeftDoor14.setTextureSize(256, 256);
      LeftDoor14.mirror = true;
      setRotation(LeftDoor14, 0F, 0.7853982F, 0F);
      LeftDoor15 = new ModelRenderer(this, 0, 0);
      LeftDoor15.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
      LeftDoor15.setRotationPoint(-4F, -4.25F, -8.2F);
      LeftDoor15.setTextureSize(256, 256);
      LeftDoor15.mirror = true;
      setRotation(LeftDoor15, 0.7853982F, 0F, 0F);
      LeftDoor16 = new ModelRenderer(this, 0, 72);
      LeftDoor16.addBox(-6.5F, -7.75F, -8.2F, 5, 30, 0);
      LeftDoor16.setRotationPoint(0F, -0.5F, -0.5F);
      LeftDoor16.setTextureSize(256, 256);
      LeftDoor16.mirror = true;
      setRotation(LeftDoor16, 0F, 0F, 0F);
      LeftDoor17 = new ModelRenderer(this, 13, 33);
      LeftDoor17.addBox(0F, -8F, 0F, 1, 31, 1);
      LeftDoor17.setRotationPoint(-6.5F, -0.5F, -9F);
      LeftDoor17.setTextureSize(256, 256);
      LeftDoor17.mirror = true;
      setRotation(LeftDoor17, 0F, -1.047198F, 0F);
      LeftDoor18 = new ModelRenderer(this, 13, 33);
      LeftDoor18.addBox(-1F, -8F, 0F, 1, 31, 1);
      LeftDoor18.setRotationPoint(-1.5F, -0.5F, -9F);
      LeftDoor18.setTextureSize(256, 256);
      LeftDoor18.mirror = true;
      setRotation(LeftDoor18, 0F, 1.047198F, 0F);
      LeftDoor19 = new ModelRenderer(this, 18, 68);
      LeftDoor19.addBox(-6.5F, 0F, 0F, 5, 1, 1);
      LeftDoor19.setRotationPoint(0F, -7.5F, -9F);
      LeftDoor19.setTextureSize(256, 256);
      LeftDoor19.mirror = true;
      setRotation(LeftDoor19, 1.047198F, 0F, 0F);
      LeftDoor20 = new ModelRenderer(this, 18, 68);
      LeftDoor20.addBox(-6.5F, -1F, 0F, 5, 1, 1);
      LeftDoor20.setRotationPoint(0F, -1F, -9F);
      LeftDoor20.setTextureSize(256, 256);
      LeftDoor20.mirror = true;
      setRotation(LeftDoor20, -1.047198F, 0F, 0F);
      LeftDoor21 = new ModelRenderer(this, 13, 72);
      LeftDoor21.addBox(-7.5F, -8.5F, -8.5F, 1, 31, 1);
      LeftDoor21.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor21.setTextureSize(256, 256);
      LeftDoor21.mirror = true;
      setRotation(LeftDoor21, 0F, 0F, 0F);
      LeftDoor22 = new ModelRenderer(this, 13, 106);
      LeftDoor22.addBox(-1.5F, 6.5F, -8.9F, 1, 1, 1);
      LeftDoor22.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor22.setTextureSize(256, 256);
      LeftDoor22.mirror = true;
      setRotation(LeftDoor22, 0F, 0F, 0F);
      LeftDoor23 = new ModelRenderer(this, 18, 72);
      LeftDoor23.addBox(-7.5F, -1F, -8.5F, 7, 1, 1);
      LeftDoor23.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor23.setTextureSize(256, 256);
      LeftDoor23.mirror = true;
      setRotation(LeftDoor23, 0F, 0F, 0F);
      LeftDoor24 = new ModelRenderer(this, 18, 68);
      LeftDoor24.addBox(-6.5F, -1.3F, -8.45F, 5, 1, 1);
      LeftDoor24.setRotationPoint(0F, 0F, -0.5F);
      LeftDoor24.setTextureSize(256, 256);
      LeftDoor24.mirror = true;
      setRotation(LeftDoor24, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    LeftDoor01.render(f5);
    LeftDoor02.render(f5);
    LeftDoor03.render(f5);
    LeftDoor04.render(f5);
    LeftDoor05.render(f5);
    LeftDoor06.render(f5);
    LeftDoor07.render(f5);
    LeftDoor08.render(f5);
    LeftDoor09.render(f5);
    LeftDoor10.render(f5);
    LeftDoor11.render(f5);
    LeftDoor12.render(f5);
    LeftDoor13.render(f5);
    LeftDoor14.render(f5);
    LeftDoor15.render(f5);
    LeftDoor16.render(f5);
    LeftDoor17.render(f5);
    LeftDoor18.render(f5);
    LeftDoor19.render(f5);
    LeftDoor20.render(f5);
    LeftDoor21.render(f5);
    LeftDoor22.render(f5);
    LeftDoor23.render(f5);
    LeftDoor24.render(f5);
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
