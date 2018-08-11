package net.tardis.mod.client.models.decoration;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHellbentLight extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
  
  public ModelHellbentLight()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      Shape1 = new ModelRenderer(this, 0, 99);
      Shape1.addBox(-10F, 8F, -13F, 20, 15, 18);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(256, 256);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(-16F, 19F, -16F, 32, 3, 24);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(256, 256);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 27);
      Shape3.addBox(-7F, 8F, -15F, 14, 15, 22);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(256, 256);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 64);
      Shape4.addBox(-9F, 8F, -14F, 18, 15, 20);
      Shape4.setRotationPoint(0F, 0F, 0F);
      Shape4.setTextureSize(256, 256);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 72, 27);
      Shape5.addBox(-11F, 8F, -11F, 22, 15, 14);
      Shape5.setRotationPoint(0F, 0F, 0F);
      Shape5.setTextureSize(256, 256);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
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
