package net.tardis.mod.client.models.clothing;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelSonicShades extends ModelBiped
{
  //fields
    ModelRenderer piece01;
    ModelRenderer piece02;
    ModelRenderer piece03;
    ModelRenderer piece04;
    ModelRenderer piece05;
    ModelRenderer piece06;
    ModelRenderer piece07;
    ModelRenderer piece08;
    ModelRenderer piece09;
    ModelRenderer piece10;
    ModelRenderer piece11;
    ModelRenderer piece12;
    ModelRenderer piece13;
    ModelRenderer piece14;
    ModelRenderer piece15;
    ModelRenderer piece16;
    ModelRenderer piece17;
    ModelRenderer piece18;
    ModelRenderer piece19;
    ModelRenderer piece20;
  
  public ModelSonicShades()
  {
    textureWidth = 33;
    textureHeight = 15;
    
      piece01 = new ModelRenderer(this, 11, 0);
      piece01.addBox(-4.2F, 4F, -0.8F, 1, 1, 1);
      piece01.setRotationPoint(0F, -8F, 0F);
      piece01.setTextureSize(33, 15);
      piece01.mirror = true;
      setRotation(piece01, 0F, 0F, 0F);
      piece02 = new ModelRenderer(this, 0, 5);
      piece02.addBox(2.9F, 4F, -4.2F, 1, 1, 1);
      piece02.setRotationPoint(0F, -8F, 0F);
      piece02.setTextureSize(33, 15);
      piece02.mirror = true;
      setRotation(piece02, 0F, 0F, 0F);
      piece03 = new ModelRenderer(this, 7, 9);
      piece03.addBox(3.2F, 4F, -4.4F, 1, 0, 1);
      piece03.setRotationPoint(0F, -8F, 0F);
      piece03.setTextureSize(33, 15);
      piece03.mirror = true;
      setRotation(piece03, 0F, 0F, 0F);
      piece04 = new ModelRenderer(this, 13, 7);
      piece04.addBox(-0.6F, 4F, -4.2F, 1, 1, 1);
      piece04.setRotationPoint(0F, -8F, 0F);
      piece04.setTextureSize(33, 15);
      piece04.mirror = true;
      setRotation(piece04, 0F, 0F, 0F);
      piece05 = new ModelRenderer(this, 5, 6);
      piece05.addBox(1.1F, 3.8F, -4.3F, 2, 1, 1);
      piece05.setRotationPoint(0F, -8F, 0F);
      piece05.setTextureSize(33, 15);
      piece05.mirror = true;
      setRotation(piece05, 0F, 0F, 0F);
      piece06 = new ModelRenderer(this, 0, 5);
      piece06.addBox(-3.9F, 4F, -4.2F, 1, 1, 1);
      piece06.setRotationPoint(0F, -8F, 0F);
      piece06.setTextureSize(33, 15);
      piece06.mirror = true;
      setRotation(piece06, 0F, 0F, 0F);
      piece07 = new ModelRenderer(this, 13, 7);
      piece07.addBox(-0.4F, 4F, -4.2F, 1, 1, 1);
      piece07.setRotationPoint(0F, -8F, 0F);
      piece07.setTextureSize(33, 15);
      piece07.mirror = true;
      setRotation(piece07, 0F, 0F, 0F);
      piece08 = new ModelRenderer(this, 11, 3);
      piece08.addBox(-4.2F, 3.5F, -4.2F, 4, 1, 1);
      piece08.setRotationPoint(0F, -8F, 0F);
      piece08.setTextureSize(33, 15);
      piece08.mirror = true;
      setRotation(piece08, 0F, 0F, 0F);
      piece09 = new ModelRenderer(this, 7, 9);
      piece09.addBox(-4.2F, 4F, -4.4F, 1, 0, 1);
      piece09.setRotationPoint(0F, -8F, 0F);
      piece09.setTextureSize(33, 15);
      piece09.mirror = true;
      setRotation(piece09, 0F, 0F, 0F);
      piece10 = new ModelRenderer(this, 16, 0);
      piece10.addBox(-3.6F, 4.5F, -4.2F, 3, 1, 1);
      piece10.setRotationPoint(0F, -8F, 0F);
      piece10.setTextureSize(33, 15);
      piece10.mirror = true;
      setRotation(piece10, 0F, 0F, 0F);
      piece11 = new ModelRenderer(this, 16, 0);
      piece11.addBox(0.6F, 4.5F, -4.2F, 3, 1, 1);
      piece11.setRotationPoint(0F, -8F, 0F);
      piece11.setTextureSize(33, 15);
      piece11.mirror = true;
      setRotation(piece11, 0F, 0F, 0F);
      piece12 = new ModelRenderer(this, 5, 6);
      piece12.addBox(-3.1F, 3.8F, -4.3F, 2, 1, 1);
      piece12.setRotationPoint(0F, -8F, 0F);
      piece12.setTextureSize(33, 15);
      piece12.mirror = true;
      setRotation(piece12, 0F, 0F, 0F);
      piece13 = new ModelRenderer(this, 5, 6);
      piece13.addBox(-3.3F, 4.2F, -4.3F, 2, 1, 1);
      piece13.setRotationPoint(0F, -8F, 0F);
      piece13.setTextureSize(33, 15);
      piece13.mirror = true;
      setRotation(piece13, 0F, 0F, 0F);
      piece14 = new ModelRenderer(this, 5, 6);
      piece14.addBox(0.9F, 4.2F, -4.3F, 2, 1, 1);
      piece14.setRotationPoint(0F, -8F, 0F);
      piece14.setTextureSize(33, 15);
      piece14.mirror = true;
      setRotation(piece14, 0F, 0F, 0F);
      piece15 = new ModelRenderer(this, 5, 6);
      piece15.addBox(-2.9F, 4.2F, -4.3F, 2, 1, 1);
      piece15.setRotationPoint(0F, -8F, 0F);
      piece15.setTextureSize(33, 15);
      piece15.mirror = true;
      setRotation(piece15, 0F, 0F, 0F);
      piece16 = new ModelRenderer(this, 5, 6);
      piece16.addBox(1.3F, 4.2F, -4.3F, 2, 1, 1);
      piece16.setRotationPoint(0F, -8F, 0F);
      piece16.setTextureSize(33, 15);
      piece16.mirror = true;
      setRotation(piece16, 0F, 0F, 0F);
      piece17 = new ModelRenderer(this, 11, 3);
      piece17.addBox(0.2F, 3.5F, -4.2F, 4, 1, 1);
      piece17.setRotationPoint(0F, -8F, 0F);
      piece17.setTextureSize(33, 15);
      piece17.mirror = true;
      setRotation(piece17, 0F, 0F, 0F);
      piece18 = new ModelRenderer(this, 0, 0);
      piece18.addBox(-4.2F, 3.5F, -3.2F, 1, 1, 3);
      piece18.setRotationPoint(0F, -8F, 0F);
      piece18.setTextureSize(33, 15);
      piece18.mirror = true;
      setRotation(piece18, 0F, 0F, 0F);
      piece19 = new ModelRenderer(this, 0, 0);
      piece19.addBox(3.2F, 3.5F, -3.2F, 1, 1, 3);
      piece19.setRotationPoint(0F, -8F, 0F);
      piece19.setTextureSize(33, 15);
      piece19.mirror = true;
      setRotation(piece19, 0F, 0F, 0F);
      piece20 = new ModelRenderer(this, 11, 0);
      piece20.addBox(3.2F, 4F, -0.8F, 1, 1, 1);
      piece20.setRotationPoint(0F, -8F, 0F);
      piece20.setTextureSize(33, 15);
      piece20.mirror = true;
      setRotation(piece20, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
	GlStateManager.pushMatrix();
	if(entity != null && entity.isSneaking()) {
		GlStateManager.translate(0, 0.25F, 0);
	}
	GlStateManager.rotate(f3, 0, 1, 0);
	GlStateManager.rotate(f4, 1, 0, 0);
    piece01.render(f5);
    piece02.render(f5);
    piece03.render(f5);
    piece04.render(f5);
    piece05.render(f5);
    piece06.render(f5);
    piece07.render(f5);
    piece08.render(f5);
    piece09.render(f5);
    piece10.render(f5);
    piece11.render(f5);
    piece12.render(f5);
    piece13.render(f5);
    piece14.render(f5);
    piece15.render(f5);
    piece16.render(f5);
    piece17.render(f5);
    piece18.render(f5);
    piece19.render(f5);
    piece20.render(f5);
    GlStateManager.popMatrix();
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
