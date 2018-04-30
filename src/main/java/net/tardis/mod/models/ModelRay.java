package net.tardis.mod.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRay extends ModelBase
{
  //fields
    ModelRenderer ray0;
    ModelRenderer ray1;
  
  public ModelRay()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      ray0 = new ModelRenderer(this, 0, 0);
      ray0.addBox(0F, 0F, 0F, 0, 2, 7);
      ray0.setRotationPoint(-0.5F, 0F, -5F);
      ray0.setTextureSize(64, 32);
      ray0.mirror = true;
      setRotation(ray0, 0F, 0F, -0.7853982F);
      ray1 = new ModelRenderer(this, 0, 0);
      ray1.addBox(0F, 0F, 0F, 0, 2, 7);
      ray1.setRotationPoint(0.9666666F, 0F, -5F);
      ray1.setTextureSize(64, 32);
      ray1.mirror = true;
      setRotation(ray1, 0F, 0F, 0.7853982F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    ray0.render(f5);
    ray1.render(f5);
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
