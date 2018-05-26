package net.tardis.mod.client.models.console.contols;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRandom extends ModelBase
{
  //fields
    ModelRenderer Glow2;
  
  public ModelRandom()
  {
    textureWidth = 80;
    textureHeight = 80;
    
      Glow2 = new ModelRenderer(this, 0, 4);
      Glow2.addBox(0F, 0F, 0F, 3, 3, 1);
      Glow2.setRotationPoint(0F, 0F, 0F);
      Glow2.setTextureSize(80, 80);
      Glow2.mirror = true;
      setRotation(Glow2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    Minecraft.getMinecraft().entityRenderer.disableLightmap();
    Glow2.render(f5);
    Minecraft.getMinecraft().entityRenderer.enableLightmap();
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
