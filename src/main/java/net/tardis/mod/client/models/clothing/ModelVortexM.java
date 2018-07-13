package net.tardis.mod.client.models.clothing;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class ModelVortexM extends ModelBiped
{
  //fields
    public ModelRenderer Cuff;
    
    public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/items/vortex_manip.png");
  
    public ModelVortexM(){
		textureWidth = 64;
		textureHeight = 32;
    
		Cuff = new ModelRenderer(this, 0, 0);
		Cuff.addBox(-3F, 3F, -2.5F, 5, 6, 5);//-3.5F, 3F, -2.5F, 5, 6, 5
		Cuff.setRotationPoint(-5F, 2F, 0F);//-5F, 2F, 0F
		Cuff.setTextureSize(64, 32);
		Cuff.mirror = true;
		setRotation(Cuff, 0F, 0F, 0F);
      
    }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  if (entity != null && entity.isSneaking())
      {
          GlStateManager.translate(0.0F, 0.2F, 0.0F);
      }
	  Cuff.render(f5);
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
