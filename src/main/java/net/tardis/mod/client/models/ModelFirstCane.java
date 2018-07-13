package net.tardis.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class ModelFirstCane extends ModelBase
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/items/cane.png");
  //fields
    ModelRenderer Handle1;
    ModelRenderer Handle2;
    ModelRenderer Silver1;
    ModelRenderer Silver2;
    ModelRenderer CaneTop;
    ModelRenderer CaneCurve1;
    ModelRenderer CaneCurve2;
    ModelRenderer CaneCurve3;
    ModelRenderer CaneCurve4;
    ModelRenderer CaneCurve5;
    ModelRenderer CaneCurve6;
    ModelRenderer CaneCurve7;
    ModelRenderer CaneCurve8;
    ModelRenderer CaneBase;
  
  public ModelFirstCane()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Handle1 = new ModelRenderer(this, 13, 1);
      Handle1.addBox(0F, 0F, 0F, 6, 2, 2);
      Handle1.setRotationPoint(0F, 0F, 0F);
      Handle1.setTextureSize(64, 64);
      Handle1.mirror = true;
      setRotation(Handle1, 0F, 0F, 0F);
      Handle2 = new ModelRenderer(this, 30, 1);
      Handle2.addBox(0F, 0F, 0F, 2, 2, 2);
      Handle2.setRotationPoint(-0.5F, 1F, 0F);
      Handle2.setTextureSize(64, 64);
      Handle2.mirror = true;
      setRotation(Handle2, 0F, 0F, 0F);
      Silver1 = new ModelRenderer(this, 13, 6);
      Silver1.addBox(0F, 0F, 0F, 2, 3, 2);
      Silver1.setRotationPoint(-0.7F, 3F, 0.2F);
      Silver1.setTextureSize(64, 64);
      Silver1.mirror = true;
      setRotation(Silver1, 0F, 0F, 0F);
      Silver2 = new ModelRenderer(this, 22, 6);
      Silver2.addBox(0F, 0F, 0F, 2, 3, 2);
      Silver2.setRotationPoint(-0.4F, 3F, -0.2F);
      Silver2.setTextureSize(64, 64);
      Silver2.mirror = true;
      setRotation(Silver2, 0F, 0F, 0F);
      CaneTop = new ModelRenderer(this, 13, 13);
      CaneTop.addBox(0F, 0F, 0F, 2, 1, 2);
      CaneTop.setRotationPoint(-0.5F, 6F, 0F);
      CaneTop.setTextureSize(64, 64);
      CaneTop.mirror = true;
      setRotation(CaneTop, 0F, 0F, 0F);
      CaneCurve1 = new ModelRenderer(this, 30, 13);
      CaneCurve1.addBox(-2F, 0F, 0F, 2, 1, 2);
      CaneCurve1.setRotationPoint(0.8F, 6.5F, 0F);
      CaneCurve1.setTextureSize(64, 64);
      CaneCurve1.mirror = true;
      setRotation(CaneCurve1, 0F, 0F, -0.3490659F);
      CaneCurve2 = new ModelRenderer(this, 30, 17);
      CaneCurve2.addBox(0F, 0F, 0F, 2, 1, 2);
      CaneCurve2.setRotationPoint(-0.6F, 7.5F, 0F);
      CaneCurve2.setTextureSize(64, 64);
      CaneCurve2.mirror = true;
      setRotation(CaneCurve2, 0F, 0F, 0.3490659F);
      CaneCurve3 = new ModelRenderer(this, 30, 21);
      CaneCurve3.addBox(-2F, 0F, 0F, 2, 1, 2);
      CaneCurve3.setRotationPoint(0.8F, 8.5F, 0F);
      CaneCurve3.setTextureSize(64, 64);
      CaneCurve3.mirror = true;
      setRotation(CaneCurve3, 0F, 0F, -0.3490659F);
      CaneCurve4 = new ModelRenderer(this, 30, 25);
      CaneCurve4.addBox(0F, 0F, 0F, 2, 1, 2);
      CaneCurve4.setRotationPoint(-0.6F, 9.5F, 0F);
      CaneCurve4.setTextureSize(64, 64);
      CaneCurve4.mirror = true;
      setRotation(CaneCurve4, 0F, 0F, 0.3490659F);
      CaneCurve5 = new ModelRenderer(this, 39, 13);
      CaneCurve5.addBox(-2F, 0F, 0F, 2, 1, 2);
      CaneCurve5.setRotationPoint(0.8F, 10.5F, 0F);
      CaneCurve5.setTextureSize(64, 64);
      CaneCurve5.mirror = true;
      setRotation(CaneCurve5, 0F, 0F, -0.3490659F);
      CaneCurve6 = new ModelRenderer(this, 39, 17);
      CaneCurve6.addBox(0F, 0F, 0F, 2, 1, 2);
      CaneCurve6.setRotationPoint(-0.6F, 11.5F, 0F);
      CaneCurve6.setTextureSize(64, 64);
      CaneCurve6.mirror = true;
      setRotation(CaneCurve6, 0F, 0F, 0.3490659F);
      CaneCurve7 = new ModelRenderer(this, 39, 21);
      CaneCurve7.addBox(-2F, 0F, 0F, 2, 1, 2);
      CaneCurve7.setRotationPoint(0.8F, 12.5F, 0F);
      CaneCurve7.setTextureSize(64, 64);
      CaneCurve7.mirror = true;
      setRotation(CaneCurve7, 0F, 0F, -0.3490659F);
      CaneCurve8 = new ModelRenderer(this, 39, 25);
      CaneCurve8.addBox(0F, 0F, 0F, 2, 1, 2);
      CaneCurve8.setRotationPoint(-0.6F, 13.5F, 0F);
      CaneCurve8.setTextureSize(64, 64);
      CaneCurve8.mirror = true;
      setRotation(CaneCurve8, 0F, 0F, 0.3490659F);
      CaneBase = new ModelRenderer(this, 13, 17);
      CaneBase.addBox(0F, 0F, 0F, 2, 9, 2);
      CaneBase.setRotationPoint(-1F, 14.8F, 0F);
      CaneBase.setTextureSize(64, 64);
      CaneBase.mirror = true;
      setRotation(CaneBase, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Handle1.render(f5);
    Handle2.render(f5);
    Silver1.render(f5);
    Silver2.render(f5);
    CaneTop.render(f5);
    CaneCurve1.render(f5);
    CaneCurve2.render(f5);
    CaneCurve3.render(f5);
    CaneCurve4.render(f5);
    CaneCurve5.render(f5);
    CaneCurve6.render(f5);
    CaneCurve7.render(f5);
    CaneCurve8.render(f5);
    CaneBase.render(f5);
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
