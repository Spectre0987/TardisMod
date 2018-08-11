package net.tardis.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAlembic extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Base;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
  
  public ModelAlembic()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 36, 4);
      Shape1.addBox(4.5F, -0.5F, -1.5F, 1, 3, 3);
      Shape1.setRotationPoint(-2F, 15F, 0F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0.4363323F);
      Shape2 = new ModelRenderer(this, 20, 0);
      Shape2.addBox(-1F, 0F, -0.5F, 1, 3, 1);
      Shape2.setRotationPoint(7.5F, 21.5F, 0F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0.1745329F);
      Shape3 = new ModelRenderer(this, 0, 17);
      Shape3.addBox(-2F, 0F, -2F, 4, 4, 4);
      Shape3.setRotationPoint(5F, 18F, 0F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 16, 17);
      Shape4.addBox(-2F, -1F, -2F, 4, 3, 4);
      Shape4.setRotationPoint(-4F, 16F, 0F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 24, 0);
      Shape5.addBox(0F, 0F, -1F, 8, 2, 2);
      Shape5.setRotationPoint(-2F, 15F, 0F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0.4363323F);
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(-2.5F, 0F, -2.5F, 5, 5, 5);
      Base.setRotationPoint(-4F, 17F, 0F);
      Base.setTextureSize(64, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 10);
      Shape7.addBox(-3F, 3.5F, -3F, 6, 1, 6);
      Shape7.setRotationPoint(-4F, 17F, 0F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 24, 10);
      Shape8.addBox(6.5F, 3.5F, -2.5F, 5, 1, 5);
      Shape8.setRotationPoint(-4F, 17F, 0F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 20, 0);
      Shape9.addBox(-0.5F, 0F, 0F, 1, 3, 1);
      Shape9.setRotationPoint(5F, 21.5F, -2.5F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0.1745329F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 20, 0);
      Shape10.addBox(-0.5F, 0F, -1F, 1, 3, 1);
      Shape10.setRotationPoint(5F, 21.5F, 2.5F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, -0.1745329F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 20, 0);
      Shape11.addBox(0F, 0F, -0.5F, 1, 3, 1);
      Shape11.setRotationPoint(2.5F, 21.5F, 0F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, -0.1745329F);
      Shape12 = new ModelRenderer(this, 20, 0);
      Shape12.addBox(-0.5F, 0F, -1F, 1, 3, 1);
      Shape12.setRotationPoint(-4F, 21.5F, 3F);
      Shape12.setTextureSize(64, 64);
      Shape12.mirror = true;
      setRotation(Shape12, -0.1745329F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 20, 0);
      Shape13.addBox(-1F, 0F, -0.5F, 1, 3, 1);
      Shape13.setRotationPoint(-1F, 21.5F, 0F);
      Shape13.setTextureSize(64, 64);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0.1745329F);
      Shape14 = new ModelRenderer(this, 20, 0);
      Shape14.addBox(-0.5F, 0F, 0F, 1, 3, 1);
      Shape14.setRotationPoint(-4F, 21.5F, -3F);
      Shape14.setTextureSize(64, 64);
      Shape14.mirror = true;
      setRotation(Shape14, 0.1745329F, 0F, 0F);
      Shape15 = new ModelRenderer(this, 20, 0);
      Shape15.addBox(0F, 0F, -0.5F, 1, 3, 1);
      Shape15.setRotationPoint(-7F, 21.5F, 0F);
      Shape15.setTextureSize(64, 64);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, -0.1745329F);
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
    Base.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
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
