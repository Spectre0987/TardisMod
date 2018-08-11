package net.tardis.mod.client.models.interiors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.IInteriorModel;
import net.tardis.mod.client.models.exteriors.ModelLeftDoor01;
import net.tardis.mod.client.models.exteriors.ModelRightDoor01;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;

public class ModelInteriorDoor01 extends ModelBase implements IInteriorModel
{
  //fields
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/interiors/01.png");
    ModelRenderer Main1;
    ModelRenderer Main2;
    ModelRenderer Main3;
    ModelRenderer Main4;
    ModelRenderer Main5;
    ModelRenderer Main6;
    ModelRenderer Main7;
    ModelRenderer Main8;
    ModelRenderer Main9;
    ModelRenderer Main10;
  
  public ModelInteriorDoor01()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Main1 = new ModelRenderer(this, 24, 0);
      Main1.addBox(7.5F, -12F, -10F, 2, 36, 2);
      Main1.setRotationPoint(0F, 0F, 0F);
      Main1.setTextureSize(128, 128);
      Main1.mirror = true;
      setRotation(Main1, 0F, 0F, 0F);
      Main2 = new ModelRenderer(this, 0, 52);
      Main2.addBox(-7.5F, -9F, -9.4F, 15, 1, 1);
      Main2.setRotationPoint(0F, 0F, 0F);
      Main2.setTextureSize(128, 128);
      Main2.mirror = true;
      setRotation(Main2, 0F, 0F, 0F);
      Main3 = new ModelRenderer(this, 0, 48);
      Main3.addBox(-7.5F, -8F, -9.2F, 15, 1, 1);
      Main3.setRotationPoint(0F, 0F, 0F);
      Main3.setTextureSize(128, 128);
      Main3.mirror = true;
      setRotation(Main3, 0F, 0F, 0F);
      Main4 = new ModelRenderer(this, 0, 50);
      Main4.addBox(-7.5F, -8.5F, -9.3F, 15, 1, 1);
      Main4.setRotationPoint(0F, 0F, 0F);
      Main4.setTextureSize(128, 128);
      Main4.mirror = true;
      setRotation(Main4, 0F, 0F, 0F);
      Main5 = new ModelRenderer(this, 32, 0);
      Main5.addBox(8F, -12F, -9.5F, 2, 36, 2);
      Main5.setRotationPoint(0F, 0F, 0F);
      Main5.setTextureSize(128, 128);
      Main5.mirror = true;
      setRotation(Main5, 0F, 0F, 0F);
      Main6 = new ModelRenderer(this, 0, 46);
      Main6.addBox(-6.5F, -10F, -10.6F, 13, 1, 1);
      Main6.setRotationPoint(0F, 0F, 0F);
      Main6.setTextureSize(128, 128);
      Main6.mirror = true;
      setRotation(Main6, 0F, 0F, 0F);
      Main7 = new ModelRenderer(this, 32, 0);
      Main7.addBox(-10F, -12F, -9.5F, 2, 36, 2);
      Main7.setRotationPoint(0F, 0F, 0F);
      Main7.setTextureSize(128, 128);
      Main7.mirror = true;
      setRotation(Main7, 0F, 0F, 0F);
      Main8 = new ModelRenderer(this, 24, 0);
      Main8.addBox(-9.5F, -12F, -10F, 2, 36, 2);
      Main8.setRotationPoint(0F, 0F, 0F);
      Main8.setTextureSize(128, 128);
      Main8.mirror = true;
      setRotation(Main8, 0F, 0F, 0F);
      Main9 = new ModelRenderer(this, 0, 38);
      Main9.addBox(-8.5F, -12F, -9F, 17, 2, 2);
      Main9.setRotationPoint(0F, 0F, 0F);
      Main9.setTextureSize(128, 128);
      Main9.mirror = true;
      setRotation(Main9, 0F, 0F, 0F);
      Main10 = new ModelRenderer(this, 0, 42);
      Main10.addBox(-8.5F, -10.5F, -10.5F, 17, 2, 2);
      Main10.setRotationPoint(0F, 0F, 0F);
      Main10.setTextureSize(128, 128);
      Main10.mirror = true;
      setRotation(Main10, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Main1.render(f5);
    Main2.render(f5);
    Main3.render(f5);
    Main4.render(f5);
    Main5.render(f5);
    Main6.render(f5);
    Main7.render(f5);
    Main8.render(f5);
    Main9.render(f5);
    Main10.render(f5);
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

  	public ModelInteriorDoorR01 rd = new ModelInteriorDoorR01();
  	public ModelInteriorDoorL01 ld = new ModelInteriorDoorL01();
  	public ModelRightDoor01 erd = new ModelRightDoor01();
  	public ModelLeftDoor01 eld = new ModelLeftDoor01();
	@Override
	public void renderClosed() {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.translate(0.25, 1.5, 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(180, 0, 1, 0);
		render(null, 0, 0, 0, 0, 0, 0.0625F);
		rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
		ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
	@Override
	public void renderOpen() {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		GlStateManager.translate(0.5, 1.5, 0.5);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(180, 0, 1, 0);
		render(null, 0, 0, 0, 0, 0, 0.0625F);
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.0625F, 0, -1.03125F);
			GlStateManager.rotate(-90, 0, 1, 0);
			rd.render(null, 0, 0, 0, 0, 0, 0.0625F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(RenderTileDoor.TEXTURE);
			GlStateManager.translate(0.0F, 0.095F, -1.03125);
			GlStateManager.rotate(180, 0, 1, 0);
			eld.render(null, 0, 0, 0, 0, 0, 0.0625F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			GlStateManager.popMatrix();
		}
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 0, -1.0625);
			GlStateManager.rotate(90, 0, 1, 0);
			ld.render(null, 0, 0, 0, 0, 0, 0.0625F);
			Minecraft.getMinecraft().getTextureManager().bindTexture(RenderTileDoor.TEXTURE);
			GlStateManager.translate(0, 0.095F, -1);
			GlStateManager.rotate(180, 0, 1, 0);
			erd.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.popMatrix();
		}
		GlStateManager.popMatrix();
	}

}
