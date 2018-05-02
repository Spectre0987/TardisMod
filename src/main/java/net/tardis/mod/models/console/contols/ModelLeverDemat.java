package net.tardis.mod.models.console.contols;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLeverDemat extends ModelBase
{
  //fields
    ModelRenderer base;
    ModelRenderer base1;
    ModelRenderer shaft;
    ModelRenderer handle;
  
  public ModelLeverDemat()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      base = new ModelRenderer(this, 0, 0);
      base.addBox(0F, 0F, 0F, 4, 2, 6);
      base.setRotationPoint(-2F, 0F, -3F);
      base.setTextureSize(64, 32);
      base.mirror = true;
      setRotation(base, 0F, 0F, 0F);
      base1 = new ModelRenderer(this, 0, 8);
      base1.addBox(0F, 0F, 0F, 4, 1, 4);
      base1.setRotationPoint(-2F, 2F, -2F);
      base1.setTextureSize(64, 32);
      base1.mirror = true;
      setRotation(base1, 0F, 0F, 0F);
      shaft = new ModelRenderer(this, 0, 13);
      shaft.addBox(0F, 0F, 0F, 1, 5, 1);
      shaft.setRotationPoint(2F, 1F, 0F);
      shaft.setTextureSize(64, 32);
      shaft.mirror = true;
      setRotation(shaft, 0F, 0F, 0F);
      handle = new ModelRenderer(this, 4, 13);
      handle.addBox(-4F, 0F, 0F, 4, 2, 2);
      handle.setRotationPoint(2F, 4.5F, -0.5F);
      handle.setTextureSize(64, 32);
      handle.mirror = true;
      setRotation(handle, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, 0, f1, f2, f3, f4, f5);
    setRotationAngles(0, f1, f2, f3, f4, f5,entity);
    base.render(f5);
    base1.render(f5);
    this.setRotation(shaft,f/20,0,0);
    shaft.render(f5);
    handle.offsetX=-0.1F;
    handle.offsetY=-0.03125F;
    shaft.addChild(handle);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }

}
