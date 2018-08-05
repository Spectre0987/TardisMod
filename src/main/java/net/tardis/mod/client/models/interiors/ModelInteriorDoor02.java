package net.tardis.mod.client.models.interiors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.IInteriorModel;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor02;
import net.tardis.mod.client.models.exteriors.ModelRightDoor02;
import net.tardis.mod.client.renderers.exteriors.RendererTileDoor01;

public class ModelInteriorDoor02 extends ModelBase implements IInteriorModel
{
  //fields
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/interiors/02.png");
    ModelRenderer M1;
    ModelRenderer M2;
    ModelRenderer M3;
    ModelRenderer M4;
    ModelRenderer M5;
    ModelRenderer M6;
    ModelRenderer M7;
    ModelRenderer M8;
    ModelRenderer M9;
    ModelRenderer M10;
  
  public ModelInteriorDoor02()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      M1 = new ModelRenderer(this, 24, 0);
      M1.addBox(7.5F, -12F, -10F, 2, 36, 2);
      M1.setRotationPoint(0F, 0F, 0F);
      M1.setTextureSize(128, 128);
      M1.mirror = true;
      setRotation(M1, 0F, 0F, 0F);
      M2 = new ModelRenderer(this, 0, 52);
      M2.addBox(-7.5F, -9F, -9.4F, 15, 1, 1);
      M2.setRotationPoint(0F, 0F, 0F);
      M2.setTextureSize(128, 128);
      M2.mirror = true;
      setRotation(M2, 0F, 0F, 0F);
      M3 = new ModelRenderer(this, 0, 48);
      M3.addBox(-7.5F, -8F, -9.2F, 15, 1, 1);
      M3.setRotationPoint(0F, 0F, 0F);
      M3.setTextureSize(128, 128);
      M3.mirror = true;
      setRotation(M3, 0F, 0F, 0F);
      M4 = new ModelRenderer(this, 0, 50);
      M4.addBox(-7.5F, -8.5F, -9.3F, 15, 1, 1);
      M4.setRotationPoint(0F, 0F, 0F);
      M4.setTextureSize(128, 128);
      M4.mirror = true;
      setRotation(M4, 0F, 0F, 0F);
      M5 = new ModelRenderer(this, 32, 0);
      M5.addBox(8F, -12F, -9.5F, 2, 36, 2);
      M5.setRotationPoint(0F, 0F, 0F);
      M5.setTextureSize(128, 128);
      M5.mirror = true;
      setRotation(M5, 0F, 0F, 0F);
      M6 = new ModelRenderer(this, 0, 46);
      M6.addBox(-6.5F, -10F, -10.6F, 13, 1, 1);
      M6.setRotationPoint(0F, 0F, 0F);
      M6.setTextureSize(128, 128);
      M6.mirror = true;
      setRotation(M6, 0F, 0F, 0F);
      M7 = new ModelRenderer(this, 32, 0);
      M7.addBox(-10F, -12F, -9.5F, 2, 36, 2);
      M7.setRotationPoint(0F, 0F, 0F);
      M7.setTextureSize(128, 128);
      M7.mirror = true;
      setRotation(M7, 0F, 0F, 0F);
      M8 = new ModelRenderer(this, 24, 0);
      M8.addBox(-9.5F, -12F, -10F, 2, 36, 2);
      M8.setRotationPoint(0F, 0F, 0F);
      M8.setTextureSize(128, 128);
      M8.mirror = true;
      setRotation(M8, 0F, 0F, 0F);
      M9 = new ModelRenderer(this, 0, 38);
      M9.addBox(-8.5F, -12F, -9F, 17, 2, 2);
      M9.setRotationPoint(0F, 0F, 0F);
      M9.setTextureSize(128, 128);
      M9.mirror = true;
      setRotation(M9, 0F, 0F, 0F);
      M10 = new ModelRenderer(this, 0, 42);
      M10.addBox(-8.5F, -10.5F, -10.5F, 17, 2, 2);
      M10.setRotationPoint(0F, 0F, 0F);
      M10.setTextureSize(128, 128);
      M10.mirror = true;
      setRotation(M10, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    M1.render(f5);
    M2.render(f5);
    M3.render(f5);
    M4.render(f5);
    M5.render(f5);
    M6.render(f5);
    M7.render(f5);
    M8.render(f5);
    M9.render(f5);
    M10.render(f5);
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

  	public ModelInteriorDoorR02 rd = new ModelInteriorDoorR02();
  	public ModelInteriorDoorL02 ld = new ModelInteriorDoorL02();
  	
	@Override
	public void renderClosed() {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.rotate(180,0,1,0);
		GlStateManager.translate(-0.25, -1.5, 0.5);
		render(null, 0, 0, 0, 0, 0, 0.0625F);
		rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
		ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
	@Override
	public void renderOpen() {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.rotate(180,1,0,0);
		GlStateManager.rotate(180,0,1,0);
		GlStateManager.translate(-0.5, -1.5, 0.5);
		render(null, 0, 0, 0, 0, 0, 0.0625F);
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.1, 0, -1);
			GlStateManager.rotate(-90,0,1,0);
			rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(RendererTileDoor01.TEXTURE);
			GlStateManager.translate(0, 0.095F, -1);
			GlStateManager.rotate(180, 0, 1, 0);
			new ModelLeftDoor02().render(null, 0, 0, 0, 0, 0, 0.0625F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			GlStateManager.popMatrix();
		}
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.1, 0, -1);
			GlStateManager.rotate(90,0,1,0);
			ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(RendererTileDoor01.TEXTURE);
			GlStateManager.translate(0, 0.095F, -1);
			GlStateManager.rotate(180, 0, 1, 0);
			new ModelRightDoor02().render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.popMatrix();
		}
		
		GlStateManager.popMatrix();
	}

}
