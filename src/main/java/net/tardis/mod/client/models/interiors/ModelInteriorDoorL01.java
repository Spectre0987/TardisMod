package net.tardis.mod.client.models.interiors;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInteriorDoorL01 extends ModelBase
{
  //fields
    ModelRenderer LD1;
    ModelRenderer LD2;
    ModelRenderer LD3;
    ModelRenderer LD4;
    ModelRenderer LD5;
    ModelRenderer LD6;
    ModelRenderer LD7;
    ModelRenderer LD8;
    ModelRenderer LD9;
    ModelRenderer LD10;
    ModelRenderer LD11;
    ModelRenderer LD12;
  
  public ModelInteriorDoorL01()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      LD1 = new ModelRenderer(this, 0, 0);
      LD1.addBox(-7.5F, 23F, -9F, 7, 1, 1);
      LD1.setRotationPoint(0F, 0F, 0F);
      LD1.setTextureSize(128, 128);
      LD1.mirror = true;
      setRotation(LD1, 0F, 0F, 0F);
      LD2 = new ModelRenderer(this, 0, 0);
      LD2.addBox(-7.5F, 8F, -9F, 7, 1, 1);
      LD2.setRotationPoint(0F, 0F, 0F);
      LD2.setTextureSize(128, 128);
      LD2.mirror = true;
      setRotation(LD2, 0F, 0F, 0F);
      LD3 = new ModelRenderer(this, 0, 0);
      LD3.addBox(-7.5F, 15.5F, -9F, 7, 1, 1);
      LD3.setRotationPoint(0F, 0F, 0F);
      LD3.setTextureSize(128, 128);
      LD3.mirror = true;
      setRotation(LD3, 0F, 0F, 0F);
      LD4 = new ModelRenderer(this, 16, 0);
      LD4.addBox(-1.5F, -7F, -9F, 1, 31, 1);
      LD4.setRotationPoint(0F, 0F, 0F);
      LD4.setTextureSize(128, 128);
      LD4.mirror = true;
      setRotation(LD4, 0F, 0F, 0F);
      LD5 = new ModelRenderer(this, 0, 0);
      LD5.addBox(-7.5F, -7F, -9F, 7, 1, 1);
      LD5.setRotationPoint(0F, 0F, 0F);
      LD5.setTextureSize(128, 128);
      LD5.mirror = true;
      setRotation(LD5, 0F, 0F, 0F);
      LD6 = new ModelRenderer(this, 16, 0);
      LD6.addBox(-7.5F, -7F, -9F, 1, 31, 1);
      LD6.setRotationPoint(0F, 0F, 0F);
      LD6.setTextureSize(128, 128);
      LD6.mirror = true;
      setRotation(LD6, 0F, 0F, 0F);
      LD7 = new ModelRenderer(this, 40, 0);
      LD7.addBox(-6.5F, -6.75F, -8.7F, 5, 30, 0);
      LD7.setRotationPoint(0F, 0F, 0F);
      LD7.setTextureSize(128, 128);
      LD7.mirror = true;
      setRotation(LD7, 0F, 0F, 0F);
      LD8 = new ModelRenderer(this, 0, 26);
      LD8.addBox(-6.5F, 0.2F, -8.95F, 5, 1, 1);
      LD8.setRotationPoint(0F, 0F, 0F);
      LD8.setTextureSize(128, 128);
      LD8.mirror = true;
      setRotation(LD8, 0F, 0F, 0F);
      LD9 = new ModelRenderer(this, 0, 0);
      LD9.addBox(-7.5F, 0.5F, -9F, 7, 1, 1);
      LD9.setRotationPoint(0F, 0F, 0F);
      LD9.setTextureSize(128, 128);
      LD9.mirror = true;
      setRotation(LD9, 0F, 0F, 0F);
      LD10 = new ModelRenderer(this, 0, 28);
      LD10.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
      LD10.setRotationPoint(-4F, -2.75F, -8.2F);
      LD10.setTextureSize(128, 128);
      LD10.mirror = true;
      setRotation(LD10, 0.7853982F, 0F, 0F);
      LD11 = new ModelRenderer(this, 0, 30);
      LD11.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
      LD11.setRotationPoint(-4.9F, -2.75F, -8.2F);
      LD11.setTextureSize(128, 128);
      LD11.mirror = true;
      setRotation(LD11, 0F, 0.7853982F, 0F);
      LD12 = new ModelRenderer(this, 0, 30);
      LD12.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
      LD12.setRotationPoint(-3.1F, -2.75F, -8.2F);
      LD12.setTextureSize(128, 128);
      LD12.mirror = true;
      setRotation(LD12, 0F, 0.7853982F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    LD1.render(f5);
    LD2.render(f5);
    LD3.render(f5);
    LD4.render(f5);
    LD5.render(f5);
    LD6.render(f5);
    LD7.render(f5);
    LD8.render(f5);
    LD9.render(f5);
    LD10.render(f5);
    LD11.render(f5);
    LD12.render(f5);
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
