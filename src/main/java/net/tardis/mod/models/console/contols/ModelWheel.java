package net.tardis.mod.models.console.contols;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWheel extends ModelBase
{
  //fields
    ModelRenderer stem;
    ModelRenderer arm0;
    ModelRenderer arm1;
    ModelRenderer arm2;
    ModelRenderer oh0;
    ModelRenderer oh1;
    ModelRenderer oh2;
    ModelRenderer oh3;
  
  public ModelWheel()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      stem = new ModelRenderer(this, 0, 16);
      stem.addBox(0F, 0F, 0F, 1, 2, 1);
      stem.setRotationPoint(0F, 0F, 0F);
      stem.setTextureSize(64, 32);
      stem.mirror = true;
      setRotation(stem, 0F, 0F, 0F);
      arm0 = new ModelRenderer(this, 8, 12);
      arm0.addBox(0F, 0F, 0F, 7, 1, 1);
      arm0.setRotationPoint(-3F, 2F, 0F);
      arm0.setTextureSize(64, 32);
      arm0.mirror = true;
      setRotation(arm0, 0F, 0F, 0F);
      arm1 = new ModelRenderer(this, 0, 10);
      arm1.addBox(0F, 0F, 1F, 1, 1, 3);
      arm1.setRotationPoint(0F, 2F, -4F);
      arm1.setTextureSize(64, 32);
      arm1.mirror = true;
      setRotation(arm1, 0F, 0F, 0F);
      arm2 = new ModelRenderer(this, 0, 10);
      arm2.addBox(0F, 0F, 0F, 1, 1, 3);
      arm2.setRotationPoint(0F, 2F, 1F);
      arm2.setTextureSize(64, 32);
      arm2.mirror = true;
      setRotation(arm2, 0F, 0F, 0F);
      oh0 = new ModelRenderer(this, 0, 0);
      oh0.addBox(0F, 0F, 0F, 1, 1, 9);
      oh0.setRotationPoint(-4F, 2F, -4F);
      oh0.setTextureSize(64, 32);
      oh0.mirror = true;
      setRotation(oh0, 0F, 0F, 0F);
      oh1 = new ModelRenderer(this, 0, 0);
      oh1.addBox(0F, 0F, 0F, 1, 1, 9);
      oh1.setRotationPoint(4F, 2F, -4F);
      oh1.setTextureSize(64, 32);
      oh1.mirror = true;
      setRotation(oh1, 0F, 0F, 0F);
      oh2 = new ModelRenderer(this, 0, 14);
      oh2.addBox(0F, 0F, 0F, 7, 1, 1);
      oh2.setRotationPoint(-3F, 2F, -4F);
      oh2.setTextureSize(64, 32);
      oh2.mirror = true;
      setRotation(oh2, 0F, 0F, 0F);
      oh3 = new ModelRenderer(this, 0, 14);
      oh3.addBox(0F, 0F, 0F, 7, 1, 1);
      oh3.setRotationPoint(-3F, 2F, 4F);
      oh3.setTextureSize(64, 32);
      oh3.mirror = true;
      setRotation(oh3, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    stem.render(f5);
    arm0.render(f5);
    arm1.render(f5);
    arm2.render(f5);
    oh0.render(f5);
    oh1.render(f5);
    oh2.render(f5);
    oh3.render(f5);
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
