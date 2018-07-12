package net.tardis.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;

public class ModelKey01 extends ModelBase
{
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/items/key01.png");
  //fields
    ModelRenderer KeyBase;
    ModelRenderer KeyBaseSide1;
    ModelRenderer KeyBaseSide2;
    ModelRenderer KeyTriad1;
    ModelRenderer KeyTriad1Detail;
    ModelRenderer KeyTriadSide1;
    ModelRenderer KeyTriadSide2;
    ModelRenderer KeyTriadSide3;
    ModelRenderer KeyTriadSide4;
    ModelRenderer KeyTriadSide5;
    ModelRenderer KeyTriadSide6;
    ModelRenderer SealBase1;
    ModelRenderer SealBase2;
    ModelRenderer KeyBackCover1;
    ModelRenderer KeyBackCover2;
    ModelRenderer KeyBackCover3;
    ModelRenderer KeyBackCover4;
    ModelRenderer KeyBack1;
    ModelRenderer KeyBack2;
    ModelRenderer KeyBack3;
    ModelRenderer KeyBack4;
    ModelRenderer KeyBack5;
    ModelRenderer KeyBack6;
  
  public ModelKey01()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      KeyBase = new ModelRenderer(this, 11, 1);
      KeyBase.addBox(0F, 0F, 0F, 3, 8, 1);
      KeyBase.setRotationPoint(-1.5F, 16F, 0F);
      KeyBase.setTextureSize(64, 64);
      KeyBase.mirror = true;
      setRotation(KeyBase, 0F, 0F, 0F);
      KeyBaseSide1 = new ModelRenderer(this, 0, 0);
      KeyBaseSide1.addBox(0F, 0F, 0F, 3, 9, 1);
      KeyBaseSide1.setRotationPoint(-2.5F, 12F, 0F);
      KeyBaseSide1.setTextureSize(64, 64);
      KeyBaseSide1.mirror = true;
      setRotation(KeyBaseSide1, 0F, 0F, 0F);
      KeyBaseSide2 = new ModelRenderer(this, 46, 21);
      KeyBaseSide2.addBox(0F, 0F, 0F, 3, 9, 1);
      KeyBaseSide2.setRotationPoint(-0.5F, 12F, 0F);
      KeyBaseSide2.setTextureSize(64, 64);
      KeyBaseSide2.mirror = true;
      setRotation(KeyBaseSide2, 0F, 0F, 0F);
      KeyTriad1 = new ModelRenderer(this, 0, 13);
      KeyTriad1.addBox(0F, 0F, 0F, 5, 7, 1);
      KeyTriad1.setRotationPoint(-2.5F, 11.5F, -0.5F);
      KeyTriad1.setTextureSize(64, 64);
      KeyTriad1.mirror = true;
      setRotation(KeyTriad1, 0F, 0F, 0F);
      KeyTriad1Detail = new ModelRenderer(this, 13, 15);
      KeyTriad1Detail.addBox(0F, 0F, 0F, 3, 5, 1);
      KeyTriad1Detail.setRotationPoint(-1.5F, 11.5F, -0.8F);
      KeyTriad1Detail.setTextureSize(64, 64);
      KeyTriad1Detail.mirror = true;
      setRotation(KeyTriad1Detail, 0F, 0F, 0F);
      KeyTriadSide1 = new ModelRenderer(this, 22, 14);
      KeyTriadSide1.addBox(0F, 0F, 0F, 1, 6, 1);
      KeyTriadSide1.setRotationPoint(-3.5F, 12.5F, -0.5F);
      KeyTriadSide1.setTextureSize(64, 64);
      KeyTriadSide1.mirror = true;
      setRotation(KeyTriadSide1, 0F, 0F, 0F);
      KeyTriadSide2 = new ModelRenderer(this, 27, 14);
      KeyTriadSide2.addBox(0F, 0F, 0F, 1, 6, 1);
      KeyTriadSide2.setRotationPoint(2.5F, 12.5F, -0.5F);
      KeyTriadSide2.setTextureSize(64, 64);
      KeyTriadSide2.mirror = true;
      setRotation(KeyTriadSide2, 0F, 0F, 0F);
      KeyTriadSide3 = new ModelRenderer(this, 32, 14);
      KeyTriadSide3.addBox(0F, 0F, 0F, 2, 6, 1);
      KeyTriadSide3.setRotationPoint(-3.5F, 10.5F, -0.3F);
      KeyTriadSide3.setTextureSize(64, 64);
      KeyTriadSide3.mirror = true;
      setRotation(KeyTriadSide3, 0F, 0F, 0.2617994F);
      KeyTriadSide4 = new ModelRenderer(this, 39, 14);
      KeyTriadSide4.addBox(0F, 0F, 0F, 2, 6, 1);
      KeyTriadSide4.setRotationPoint(1.5F, 11F, -0.3F);
      KeyTriadSide4.setTextureSize(64, 64);
      KeyTriadSide4.mirror = true;
      setRotation(KeyTriadSide4, 0F, 0F, -0.2617994F);
      KeyTriadSide5 = new ModelRenderer(this, 34, 22);
      KeyTriadSide5.addBox(0F, 0F, 0F, 1, 4, 1);
      KeyTriadSide5.setRotationPoint(-4.2F, 10.5F, 0F);
      KeyTriadSide5.setTextureSize(64, 64);
      KeyTriadSide5.mirror = true;
      setRotation(KeyTriadSide5, 0F, 0F, 0.3316126F);
      KeyTriadSide6 = new ModelRenderer(this, 39, 22);
      KeyTriadSide6.addBox(0F, 0F, 0F, 1, 4, 1);
      KeyTriadSide6.setRotationPoint(3.3F, 11F, 0F);
      KeyTriadSide6.setTextureSize(64, 64);
      KeyTriadSide6.mirror = true;
      setRotation(KeyTriadSide6, 0F, 0F, -0.3316126F);
      SealBase1 = new ModelRenderer(this, 15, 24);
      SealBase1.addBox(0F, 0F, 0F, 7, 5, 2);
      SealBase1.setRotationPoint(-3.5F, 6F, -1F);
      SealBase1.setTextureSize(64, 64);
      SealBase1.mirror = true;
      setRotation(SealBase1, 0F, 0F, 0F);
      SealBase2 = new ModelRenderer(this, 0, 22);
      SealBase2.addBox(0F, 0F, 0F, 5, 7, 2);
      SealBase2.setRotationPoint(-2.5F, 5F, -1F);
      SealBase2.setTextureSize(64, 64);
      SealBase2.mirror = true;
      setRotation(SealBase2, 0F, 0F, 0F);
      KeyBackCover1 = new ModelRenderer(this, 22, 4);
      KeyBackCover1.addBox(0F, 0F, 0F, 1, 6, 1);
      KeyBackCover1.setRotationPoint(-3.5F, 12.5F, 0F);
      KeyBackCover1.setTextureSize(64, 64);
      KeyBackCover1.mirror = true;
      setRotation(KeyBackCover1, 0F, 0F, 0F);
      KeyBackCover2 = new ModelRenderer(this, 27, 4);
      KeyBackCover2.addBox(0F, 0F, 0F, 1, 6, 1);
      KeyBackCover2.setRotationPoint(2.5F, 12.5F, 0F);
      KeyBackCover2.setTextureSize(64, 64);
      KeyBackCover2.mirror = true;
      setRotation(KeyBackCover2, 0F, 0F, 0F);
      KeyBackCover3 = new ModelRenderer(this, 32, 4);
      KeyBackCover3.addBox(0F, 0F, 0F, 2, 6, 1);
      KeyBackCover3.setRotationPoint(1.5F, 11F, 0F);
      KeyBackCover3.setTextureSize(64, 64);
      KeyBackCover3.mirror = true;
      setRotation(KeyBackCover3, 0F, 0F, -0.2617994F);
      KeyBackCover4 = new ModelRenderer(this, 39, 4);
      KeyBackCover4.addBox(0F, 0F, 0F, 2, 6, 1);
      KeyBackCover4.setRotationPoint(-3.5F, 10.5F, 0F);
      KeyBackCover4.setTextureSize(64, 64);
      KeyBackCover4.mirror = true;
      setRotation(KeyBackCover4, 0F, 0F, 0.2617994F);
      KeyBack1 = new ModelRenderer(this, 39, 0);
      KeyBack1.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack1.setRotationPoint(-2F, 6F, 0.2F);
      KeyBack1.setTextureSize(64, 64);
      KeyBack1.mirror = true;
      setRotation(KeyBack1, 0F, 0F, 0F);
      KeyBack2 = new ModelRenderer(this, 32, 0);
      KeyBack2.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack2.setRotationPoint(1F, 7F, 0.2F);
      KeyBack2.setTextureSize(64, 64);
      KeyBack2.mirror = true;
      setRotation(KeyBack2, 0F, 0F, 0F);
      KeyBack3 = new ModelRenderer(this, 25, 0);
      KeyBack3.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack3.setRotationPoint(-1F, 10F, 0.2F);
      KeyBack3.setTextureSize(64, 64);
      KeyBack3.mirror = true;
      setRotation(KeyBack3, 0F, 0F, 0F);
      KeyBack4 = new ModelRenderer(this, 46, 4);
      KeyBack4.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack4.setRotationPoint(0F, 14F, 0.2F);
      KeyBack4.setTextureSize(64, 64);
      KeyBack4.mirror = true;
      setRotation(KeyBack4, 0F, 0F, 0F);
      KeyBack5 = new ModelRenderer(this, 46, 8);
      KeyBack5.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack5.setRotationPoint(-3.5F, 12.5F, 0.2F);
      KeyBack5.setTextureSize(64, 64);
      KeyBack5.mirror = true;
      setRotation(KeyBack5, 0F, 0F, 0F);
      KeyBack6 = new ModelRenderer(this, 46, 12);
      KeyBack6.addBox(0F, 0F, 0F, 2, 2, 1);
      KeyBack6.setRotationPoint(-1F, 19F, 0.2F);
      KeyBack6.setTextureSize(64, 64);
      KeyBack6.mirror = true;
      setRotation(KeyBack6, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    KeyBase.render(f5);
    KeyBaseSide1.render(f5);
    KeyBaseSide2.render(f5);
    KeyTriad1.render(f5);
    KeyTriad1Detail.render(f5);
    KeyTriadSide1.render(f5);
    KeyTriadSide2.render(f5);
    KeyTriadSide3.render(f5);
    KeyTriadSide4.render(f5);
    KeyTriadSide5.render(f5);
    KeyTriadSide6.render(f5);
    SealBase1.render(f5);
    SealBase2.render(f5);
    KeyBackCover1.render(f5);
    KeyBackCover2.render(f5);
    KeyBackCover3.render(f5);
    KeyBackCover4.render(f5);
    KeyBack1.render(f5);
    KeyBack2.render(f5);
    KeyBack3.render(f5);
    KeyBack4.render(f5);
    KeyBack5.render(f5);
    KeyBack6.render(f5);
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
