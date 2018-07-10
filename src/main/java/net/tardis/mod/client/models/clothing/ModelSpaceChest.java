package net.tardis.mod.client.models.clothing;

import java.lang.reflect.Field;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.tardis.mod.client.util.ModelUtil;

public class ModelSpaceChest extends ModelBiped
{
  //fields
    ModelRenderer Torso1;
    ModelRenderer Torso2;
    ModelRenderer Torso3;
    ModelRenderer Torso4;
    ModelRenderer Torso5;
    ModelRenderer Torso6;
    ModelRenderer Torso7;
    ModelRenderer Torso8;
    ModelRenderer Torso9;
    ModelRenderer Torso10;
    ModelRenderer Torso11;
    ModelRenderer RightArm1;
    ModelRenderer RightArm2;
    ModelRenderer RightArm3;
    ModelRenderer RightArm4;
    ModelRenderer RightArm5;
    ModelRenderer RightArm6;
    ModelRenderer RightArm7;
    ModelRenderer RightArm8;
    ModelRenderer RightArm9;
    ModelRenderer RightArm10;
    ModelRenderer LeftArm1;
    ModelRenderer LeftArm2;
    ModelRenderer LeftArm3;
    ModelRenderer LeftArm4;
    ModelRenderer LeftArm5;
    ModelRenderer LeftArm6;
    ModelRenderer LeftArm7;
    ModelRenderer LeftArm8;
    ModelRenderer LeftArm9;
    ModelRenderer LeftArm10;
    ModelRenderer LeftArm11;
  
  public ModelSpaceChest()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      Torso1 = new ModelRenderer(this, 115, 33);
      Torso1.addBox(2.5F, 0F, -2.5F, 1, 12, 1);
      Torso1.setRotationPoint(0F, 0F, 0F);
      Torso1.setTextureSize(128, 128);
      Torso1.mirror = true;
      setRotation(Torso1, 0F, 0F, 0.0174533F);
      Torso2 = new ModelRenderer(this, 0, 53);
      Torso2.addBox(3.3F, 0F, -2F, 1, 12, 4);
      Torso2.setRotationPoint(0F, 0F, 0F);
      Torso2.setTextureSize(128, 128);
      Torso2.mirror = true;
      setRotation(Torso2, 0F, 0F, 0F);
      Torso3 = new ModelRenderer(this, 13, 53);
      Torso3.addBox(-4F, 0F, 1.3F, 8, 12, 1);
      Torso3.setRotationPoint(0F, 0F, 0F);
      Torso3.setTextureSize(128, 128);
      Torso3.mirror = true;
      setRotation(Torso3, 0F, 0F, 0F);
      Torso4 = new ModelRenderer(this, 0, 53);
      Torso4.addBox(-4.3F, 0F, -2F, 1, 12, 4);
      Torso4.setRotationPoint(0F, 0F, 0F);
      Torso4.setTextureSize(128, 128);
      Torso4.mirror = true;
      setRotation(Torso4, 0F, 0F, 0F);
      Torso5 = new ModelRenderer(this, 0, 71);
      Torso5.addBox(-4F, -0.3F, -2F, 8, 1, 4);
      Torso5.setRotationPoint(0F, 0F, 0F);
      Torso5.setTextureSize(128, 128);
      Torso5.mirror = true;
      setRotation(Torso5, 0F, 0F, 0F);
      Torso6 = new ModelRenderer(this, 23, 22);
      Torso6.addBox(-0.5F, 4.1F, -2.5F, 1, 8, 1);
      Torso6.setRotationPoint(0F, 0F, 0F);
      Torso6.setTextureSize(128, 128);
      Torso6.mirror = true;
      setRotation(Torso6, 0F, 0F, 0F);
      Torso7 = new ModelRenderer(this, 34, 54);
      Torso7.addBox(-0.5F, 0F, 1.5F, 1, 12, 1);
      Torso7.setRotationPoint(0F, 0F, 0F);
      Torso7.setTextureSize(128, 128);
      Torso7.mirror = true;
      setRotation(Torso7, 0F, 0F, 0F);
      Torso8 = new ModelRenderer(this, 115, 33);
      Torso8.addBox(-3.5F, 0F, -2.5F, 1, 12, 1);
      Torso8.setRotationPoint(0F, 0F, 0F);
      Torso8.setTextureSize(128, 128);
      Torso8.mirror = true;
      setRotation(Torso8, 0F, 0F, -0.0174533F);
      Torso9 = new ModelRenderer(this, 16, 23);
      Torso9.addBox(1.5F, 0F, 1.5F, 1, 4, 1);
      Torso9.setRotationPoint(0F, 0F, 0F);
      Torso9.setTextureSize(128, 128);
      Torso9.mirror = true;
      setRotation(Torso9, 0F, 0F, 0.4886922F);
      Torso10 = new ModelRenderer(this, 13, 53);
      Torso10.addBox(-4F, 0F, -2.3F, 8, 12, 1);
      Torso10.setRotationPoint(0F, 0F, 0F);
      Torso10.setTextureSize(128, 128);
      Torso10.mirror = true;
      setRotation(Torso10, 0F, 0F, 0F);
      Torso11 = new ModelRenderer(this, 16, 23);
      Torso11.addBox(-2.5F, 0F, 1.5F, 1, 4, 1);
      Torso11.setRotationPoint(0F, 0F, 0F);
      Torso11.setTextureSize(128, 128);
      Torso11.mirror = true;
      setRotation(Torso11, 0F, 0F, -0.4886922F);
      RightArm1 = new ModelRenderer(this, 40, 53);
      RightArm1.addBox(-3.3F, -2F, -2F, 1, 9, 4);
      RightArm1.setRotationPoint(-5F, 2F, 0F);
      RightArm1.setTextureSize(128, 128);
      RightArm1.mirror = true;
      setRotation(RightArm1, 0F, 0F, 0F);
      RightArm2 = new ModelRenderer(this, 53, 54);
      RightArm2.addBox(-3F, -2F, 1.3F, 4, 9, 1);
      RightArm2.setRotationPoint(-5F, 2F, 0F);
      RightArm2.setTextureSize(128, 128);
      RightArm2.mirror = true;
      setRotation(RightArm2, 0F, 0F, 0F);
      RightArm3 = new ModelRenderer(this, 65, 51);
      RightArm3.addBox(-3F, 7F, 1.2F, 4, 3, 1);
      RightArm3.setRotationPoint(-5F, 2F, 0F);
      RightArm3.setTextureSize(128, 128);
      RightArm3.mirror = true;
      setRotation(RightArm3, 0F, 0F, 0F);
      RightArm4 = new ModelRenderer(this, 65, 57);
      RightArm4.addBox(-3.2F, 7F, -2F, 1, 3, 4);
      RightArm4.setRotationPoint(-5F, 2F, 0F);
      RightArm4.setTextureSize(128, 128);
      RightArm4.mirror = true;
      setRotation(RightArm4, 0F, 0F, 0F);
      RightArm5 = new ModelRenderer(this, 40, 53);
      RightArm5.addBox(0.3F, -2F, -2F, 1, 9, 4);
      RightArm5.setRotationPoint(-5F, 2F, 0F);
      RightArm5.setTextureSize(128, 128);
      RightArm5.mirror = true;
      setRotation(RightArm5, 0F, 0F, 0F);
      RightArm6 = new ModelRenderer(this, 77, 51);
      RightArm6.addBox(-3F, -2.3F, -2F, 4, 1, 4);
      RightArm6.setRotationPoint(-5F, 2F, 0F);
      RightArm6.setTextureSize(128, 128);
      RightArm6.mirror = true;
      setRotation(RightArm6, 0F, 0F, 0F);
      RightArm7 = new ModelRenderer(this, 53, 54);
      RightArm7.addBox(-3F, -2F, -2.3F, 4, 9, 1);
      RightArm7.setRotationPoint(-5F, 2F, 0F);
      RightArm7.setTextureSize(128, 128);
      RightArm7.mirror = true;
      setRotation(RightArm7, 0F, 0F, 0F);
      RightArm8 = new ModelRenderer(this, 78, 58);
      RightArm8.addBox(-3F, 9.2F, -2F, 4, 1, 4);
      RightArm8.setRotationPoint(-5F, 2F, 0F);
      RightArm8.setTextureSize(128, 128);
      RightArm8.mirror = true;
      setRotation(RightArm8, 0F, 0F, 0F);
      RightArm9 = new ModelRenderer(this, 65, 57);
      RightArm9.addBox(0.2F, 7F, -2F, 1, 3, 4);
      RightArm9.setRotationPoint(-5F, 2F, 0F);
      RightArm9.setTextureSize(128, 128);
      RightArm9.mirror = true;
      setRotation(RightArm9, 0F, 0F, 0F);
      RightArm10 = new ModelRenderer(this, 65, 51);
      RightArm10.addBox(-3F, 7F, -2.2F, 4, 3, 1);
      RightArm10.setRotationPoint(-5F, 2F, 0F);
      RightArm10.setTextureSize(128, 128);
      RightArm10.mirror = true;
      setRotation(RightArm10, 0F, 0F, 0F);
      LeftArm1 = new ModelRenderer(this, 65, 57);
      LeftArm1.addBox(2.2F, 7F, -2F, 1, 3, 4);
      LeftArm1.setRotationPoint(5F, 2F, 0F);
      LeftArm1.setTextureSize(128, 128);
      LeftArm1.mirror = true;
      setRotation(LeftArm1, 0F, 0F, 0F);
      LeftArm2 = new ModelRenderer(this, 114, 14);
      LeftArm2.addBox(2.5F, 2F, -1F, 1, 4, 2);
      LeftArm2.setRotationPoint(5F, 2F, 0F);
      LeftArm2.setTextureSize(128, 128);
      LeftArm2.mirror = true;
      setRotation(LeftArm2, 0F, 0F, 0F);
      LeftArm3 = new ModelRenderer(this, 77, 51);
      LeftArm3.addBox(-1F, -2.3F, -2F, 4, 1, 4);
      LeftArm3.setRotationPoint(5F, 2F, 0F);
      LeftArm3.setTextureSize(128, 128);
      LeftArm3.mirror = true;
      setRotation(LeftArm3, 0F, 0F, 0F);
      LeftArm3.mirror = false;
      LeftArm4 = new ModelRenderer(this, 53, 54);
      LeftArm4.addBox(-1F, -2F, 1.3F, 4, 9, 1);
      LeftArm4.setRotationPoint(5F, 2F, 0F);
      LeftArm4.setTextureSize(128, 128);
      LeftArm4.mirror = true;
      setRotation(LeftArm4, 0F, 0F, 0F);
      LeftArm4.mirror = false;
      LeftArm5 = new ModelRenderer(this, 65, 51);
      LeftArm5.addBox(-1F, 7F, 1.2F, 4, 3, 1);
      LeftArm5.setRotationPoint(5F, 2F, 0F);
      LeftArm5.setTextureSize(128, 128);
      LeftArm5.mirror = true;
      setRotation(LeftArm5, 0F, 0F, 0F);
      LeftArm5.mirror = false;
      LeftArm6 = new ModelRenderer(this, 53, 54);
      LeftArm6.addBox(-1F, -2F, -2.3F, 4, 9, 1);
      LeftArm6.setRotationPoint(5F, 2F, 0F);
      LeftArm6.setTextureSize(128, 128);
      LeftArm6.mirror = true;
      setRotation(LeftArm6, 0F, 0F, 0F);
      LeftArm6.mirror = false;
      LeftArm7 = new ModelRenderer(this, 40, 16);
      LeftArm7.addBox(-1.3F, -2F, -2F, 1, 9, 4);
      LeftArm7.setRotationPoint(5F, 2F, 0F);
      LeftArm7.setTextureSize(128, 128);
      LeftArm7.mirror = true;
      setRotation(LeftArm7, 0F, 0F, 0F);
      LeftArm8 = new ModelRenderer(this, 78, 58);
      LeftArm8.addBox(-1F, 9.2F, -2F, 4, 1, 4);
      LeftArm8.setRotationPoint(5F, 2F, 0F);
      LeftArm8.setTextureSize(128, 128);
      LeftArm8.mirror = true;
      setRotation(LeftArm8, 0F, 0F, 0F);
      LeftArm8.mirror = false;
      LeftArm9 = new ModelRenderer(this, 97, 56);
      LeftArm9.addBox(-1.2F, 7F, -2F, 1, 3, 4);
      LeftArm9.setRotationPoint(5F, 2F, 0F);
      LeftArm9.setTextureSize(128, 128);
      LeftArm9.mirror = true;
      setRotation(LeftArm9, 0F, 0F, 0F);
      LeftArm10 = new ModelRenderer(this, 65, 51);
      LeftArm10.addBox(-1F, 7F, -2.2F, 4, 3, 1);
      LeftArm10.setRotationPoint(5F, 2F, 0F);
      LeftArm10.setTextureSize(128, 128);
      LeftArm10.mirror = true;
      setRotation(LeftArm10, 0F, 0F, 0F);
      LeftArm10.mirror = false;
      LeftArm11 = new ModelRenderer(this, 40, 53);
      LeftArm11.addBox(2.3F, -2F, -2F, 1, 9, 4);
      LeftArm11.setRotationPoint(5F, 2F, 0F);
      LeftArm11.setTextureSize(128, 128);
      LeftArm11.mirror = true;
      setRotation(LeftArm11, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    if(entity != null)this.isSneak = entity.isSneaking();
    try {
  	  Field[] fields = ModelSpaceChest.class.getDeclaredFields();
  	  for(Field fi : fields) {
  		  Object obj = fi.get(this);
  		  if(obj != null && obj instanceof ModelRenderer) {
  			  ModelRenderer mr = (ModelRenderer)obj;
    		  if(fi.getName().toLowerCase().contains("right")) {
    			  ModelUtil.copyAngle(this.bipedRightArm, mr);
    		  }
    		  if(fi.getName().toLowerCase().contains("left")) {
    			  ModelUtil.copyAngle(this.bipedLeftArm, mr);
    		  }
  		  }
  	  }
    }
    catch(Exception e) {}
    Torso1.renderWithRotation(f5);
    Torso2.renderWithRotation(f5);
    Torso3.renderWithRotation(f5);
    Torso4.renderWithRotation(f5);
    Torso5.renderWithRotation(f5);
    Torso6.renderWithRotation(f5);
    Torso7.renderWithRotation(f5);
    Torso8.renderWithRotation(f5);
    Torso9.renderWithRotation(f5);
    Torso10.renderWithRotation(f5);
    Torso11.renderWithRotation(f5);
    RightArm1.renderWithRotation(f5);
    RightArm2.renderWithRotation(f5);
    RightArm3.renderWithRotation(f5);
    RightArm4.renderWithRotation(f5);
    RightArm5.renderWithRotation(f5);
    RightArm6.renderWithRotation(f5);
    RightArm7.renderWithRotation(f5);
    RightArm8.renderWithRotation(f5);
    RightArm9.renderWithRotation(f5);
    RightArm10.renderWithRotation(f5);
    LeftArm1.renderWithRotation(f5);
    LeftArm2.renderWithRotation(f5);
    LeftArm3.renderWithRotation(f5);
    LeftArm4.renderWithRotation(f5);
    LeftArm5.renderWithRotation(f5);
    LeftArm6.renderWithRotation(f5);
    LeftArm7.renderWithRotation(f5);
    LeftArm8.renderWithRotation(f5);
    LeftArm9.renderWithRotation(f5);
    LeftArm10.renderWithRotation(f5);
    LeftArm11.renderWithRotation(f5);
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
