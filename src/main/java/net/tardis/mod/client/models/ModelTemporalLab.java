package net.tardis.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTemporalLab extends ModelBase
{
  //fields
    ModelRenderer Printer;
    ModelRenderer Base;
    ModelRenderer Rod;
  
  public ModelTemporalLab()
  {
    textureWidth = 128;
    textureHeight = 32;
    
      Printer = new ModelRenderer(this, 24, 0);
      Printer.addBox(0F, 0F, 0F, 6, 4, 6);
      Printer.setRotationPoint(-3F, 10F, -5F);
      Printer.setTextureSize(128, 32);
      Printer.mirror = true;
      setRotation(Printer, 0F, 0F, 0F);
      Base = new ModelRenderer(this, 10, 17);
      Base.addBox(0F, 0F, 0F, 14, 1, 14);
      Base.setRotationPoint(-7F, 23F, -7F);
      Base.setTextureSize(128, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Rod = new ModelRenderer(this, 0, 0);
      Rod.addBox(0F, 0F, 0F, 6, 13, 6);
      Rod.setRotationPoint(-3F, 10F, 1F);
      Rod.setTextureSize(128, 32);
      Rod.mirror = true;
      setRotation(Rod, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    Printer.render(f5);
    Base.render(f5);
    Rod.render(f5);
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
