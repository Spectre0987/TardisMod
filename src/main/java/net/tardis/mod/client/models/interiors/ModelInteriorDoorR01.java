package net.tardis.mod.client.models.interiors;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInteriorDoorR01 extends ModelBase
{
  //fields
    ModelRenderer RD1;
    ModelRenderer RD2;
    ModelRenderer RD3;
    ModelRenderer RD4;
    ModelRenderer RD5;
    ModelRenderer RD6;
    ModelRenderer RD7;
    ModelRenderer RD8;
    ModelRenderer RD9;
    ModelRenderer RD10;
    ModelRenderer RD11;
    ModelRenderer RD12;
    ModelRenderer RD13;
  
  public ModelInteriorDoorR01()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      RD1 = new ModelRenderer(this, 16, 0);
      RD1.addBox(6.5F, -7F, -9F, 1, 31, 1);
      RD1.setRotationPoint(0F, 0F, 0F);
      RD1.setTextureSize(128, 128);
      RD1.mirror = true;
      setRotation(RD1, 0F, 0F, 0F);
      RD2 = new ModelRenderer(this, 0, 0);
      RD2.addBox(0.5F, 23F, -9F, 7, 1, 1);
      RD2.setRotationPoint(0F, 0F, 0F);
      RD2.setTextureSize(128, 128);
      RD2.mirror = true;
      setRotation(RD2, 0F, 0F, 0F);
      RD3 = new ModelRenderer(this, 20, 0);
      RD3.addBox(-0.5F, -7F, -9.2F, 1, 31, 1);
      RD3.setRotationPoint(0F, 0F, 0F);
      RD3.setTextureSize(128, 128);
      RD3.mirror = true;
      setRotation(RD3, 0F, 0F, 0F);
      RD4 = new ModelRenderer(this, 16, 0);
      RD4.addBox(0.5F, -7F, -9F, 1, 31, 1);
      RD4.setRotationPoint(0F, 0F, 0F);
      RD4.setTextureSize(128, 128);
      RD4.mirror = true;
      setRotation(RD4, 0F, 0F, 0F);
      RD5 = new ModelRenderer(this, 0, 0);
      RD5.addBox(0.5F, -7F, -9F, 7, 1, 1);
      RD5.setRotationPoint(0F, 0F, 0F);
      RD5.setTextureSize(128, 128);
      RD5.mirror = true;
      setRotation(RD5, 0F, 0F, 0F);
      RD6 = new ModelRenderer(this, 0, 0);
      RD6.addBox(0.5F, 0.5F, -9F, 7, 1, 1);
      RD6.setRotationPoint(0F, 0F, 0F);
      RD6.setTextureSize(128, 128);
      RD6.mirror = true;
      setRotation(RD6, 0F, 0F, 0F);
      RD7 = new ModelRenderer(this, 40, 0);
      RD7.addBox(1.5F, -6.75F, -8.7F, 5, 30, 0);
      RD7.setRotationPoint(0F, 0F, 0F);
      RD7.setTextureSize(128, 128);
      RD7.mirror = true;
      setRotation(RD7, 0F, 0F, 0F);
      RD8 = new ModelRenderer(this, 0, 0);
      RD8.addBox(0.5F, 8F, -9F, 7, 1, 1);
      RD8.setRotationPoint(0F, 0F, 0F);
      RD8.setTextureSize(128, 128);
      RD8.mirror = true;
      setRotation(RD8, 0F, 0F, 0F);
      RD9 = new ModelRenderer(this, 0, 0);
      RD9.addBox(0.5F, 15.5F, -9F, 7, 1, 1);
      RD9.setRotationPoint(0F, 0F, 0F);
      RD9.setTextureSize(128, 128);
      RD9.mirror = true;
      setRotation(RD9, 0F, 0F, 0F);
      RD10 = new ModelRenderer(this, 0, 26);
      RD10.addBox(1.5F, 0.2F, -8.95F, 5, 1, 1);
      RD10.setRotationPoint(0F, 0F, 0F);
      RD10.setTextureSize(128, 128);
      RD10.mirror = true;
      setRotation(RD10, 0F, 0F, 0F);
      RD11 = new ModelRenderer(this, 0, 28);
      RD11.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
      RD11.setRotationPoint(4F, -2.75F, -8.2F);
      RD11.setTextureSize(128, 128);
      RD11.mirror = true;
      setRotation(RD11, 0.7853982F, 0F, 0F);
      RD12 = new ModelRenderer(this, 0, 30);
      RD12.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
      RD12.setRotationPoint(3.1F, -2.75F, -8.2F);
      RD12.setTextureSize(128, 128);
      RD12.mirror = true;
      setRotation(RD12, 0F, 0.7853982F, 0F);
      RD13 = new ModelRenderer(this, 0, 30);
      RD13.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
      RD13.setRotationPoint(4.9F, -2.75F, -8.2F);
      RD13.setTextureSize(128, 128);
      RD13.mirror = true;
      setRotation(RD13, 0F, 0.7853982F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    RD1.render(f5);
    RD2.render(f5);
    RD3.render(f5);
    RD4.render(f5);
    RD5.render(f5);
    RD6.render(f5);
    RD7.render(f5);
    RD8.render(f5);
    RD9.render(f5);
    RD10.render(f5);
    RD11.render(f5);
    RD12.render(f5);
    RD13.render(f5);
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
