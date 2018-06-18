package net.tardis.mod.client.models.clothing;

import java.lang.reflect.Field;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.tardis.mod.client.util.ModelUtil;

public class ModelSpaceLegs extends ModelBiped
{
  //fields
    ModelRenderer RightLeg1;
    ModelRenderer RightLeg2;
    ModelRenderer RightLeg3;
    ModelRenderer RightLeg4;
    ModelRenderer RightLeg5;
    ModelRenderer RightLeg6;
    ModelRenderer RightLeg7;
    ModelRenderer RightLeg8;
    ModelRenderer RightLeg9;
    ModelRenderer RightLeg10;
    ModelRenderer RightToe;
    ModelRenderer RightLeg11;
    ModelRenderer RightBoot;
    ModelRenderer RightLeg12;
    ModelRenderer RightLeg13;
    ModelRenderer RightLeg14;
    ModelRenderer RightLeg15;
    ModelRenderer Leftleg1;
    ModelRenderer LeftLeg2;
    ModelRenderer LeftLeg3;
    ModelRenderer LeftLeg4;
    ModelRenderer LeftLeg5;
    ModelRenderer LeftLeg6;
    ModelRenderer LeftToe;
    ModelRenderer LeftLeg7;
    ModelRenderer LeftLeg8;
    ModelRenderer LeftLeg9;
    ModelRenderer LeftBoot;
    ModelRenderer LeftLeg10;
    ModelRenderer LeftLeg11;
    ModelRenderer LeftLeg12;
    ModelRenderer LeftLeg13;
    ModelRenderer LeftLeg14;
    ModelRenderer LeftLeg15;
    ModelRenderer LeftLeg16;
  
  public ModelSpaceLegs()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      RightLeg1 = new ModelRenderer(this, 68, 29);
      RightLeg1.addBox(-2F, 0F, 1.5F, 4, 1, 1);
      RightLeg1.setRotationPoint(-2F, 12F, 0F);
      RightLeg1.setTextureSize(128, 128);
      RightLeg1.mirror = true;
      setRotation(RightLeg1, 0F, 0F, 0F);
      RightLeg2 = new ModelRenderer(this, 112, 50);
      RightLeg2.addBox(1.3F, 0F, -2F, 1, 9, 4);
      RightLeg2.setRotationPoint(-2F, 12F, 0F);
      RightLeg2.setTextureSize(128, 128);
      RightLeg2.mirror = true;
      setRotation(RightLeg2, 0F, 0F, 0F);
      RightLeg3 = new ModelRenderer(this, 29, 23);
      RightLeg3.addBox(-1.5F, 6F, -3.3F, 3, 3, 1);
      RightLeg3.setRotationPoint(-2F, 12F, 0F);
      RightLeg3.setTextureSize(128, 128);
      RightLeg3.mirror = true;
      setRotation(RightLeg3, 0F, 0F, 0F);
      RightLeg4 = new ModelRenderer(this, 24, 16);
      RightLeg4.addBox(-2.5F, 0F, -2F, 1, 1, 4);
      RightLeg4.setRotationPoint(-2F, 12F, 0F);
      RightLeg4.setTextureSize(128, 128);
      RightLeg4.mirror = true;
      setRotation(RightLeg4, 0F, 0F, 0F);
      RightLeg5 = new ModelRenderer(this, 100, 44);
      RightLeg5.addBox(-2F, 9F, 1.2F, 4, 3, 1);
      RightLeg5.setRotationPoint(-2F, 12F, 0F);
      RightLeg5.setTextureSize(128, 128);
      RightLeg5.mirror = true;
      setRotation(RightLeg5, 0F, 0F, 0F);
      RightLeg6 = new ModelRenderer(this, 0, 77);
      RightLeg6.addBox(-1.5F, 1F, -2.4F, 3, 5, 1);
      RightLeg6.setRotationPoint(-2F, 12F, 0F);
      RightLeg6.setTextureSize(128, 128);
      RightLeg6.mirror = true;
      setRotation(RightLeg6, 0F, 0F, 0F);
      RightLeg7 = new ModelRenderer(this, 10, 77);
      RightLeg7.addBox(-2F, -0.3F, -2F, 4, 1, 4);
      RightLeg7.setRotationPoint(-2F, 12F, 0F);
      RightLeg7.setTextureSize(128, 128);
      RightLeg7.mirror = true;
      setRotation(RightLeg7, 0F, 0F, 0F);
      RightLeg8 = new ModelRenderer(this, 68, 72);
      RightLeg8.addBox(1.2F, 9F, -2F, 1, 3, 4);
      RightLeg8.setRotationPoint(-2F, 12F, 0F);
      RightLeg8.setTextureSize(128, 128);
      RightLeg8.mirror = true;
      setRotation(RightLeg8, 0F, 0F, 0F);
      RightLeg9 = new ModelRenderer(this, 81, 67);
      RightLeg9.addBox(-2F, 11.2F, -2F, 4, 1, 4);
      RightLeg9.setRotationPoint(-2F, 12F, 0F);
      RightLeg9.setTextureSize(128, 128);
      RightLeg9.mirror = true;
      setRotation(RightLeg9, 0F, 0F, 0F);
      RightLeg10 = new ModelRenderer(this, 68, 72);
      RightLeg10.addBox(-2.2F, 9F, -2F, 1, 3, 4);
      RightLeg10.setRotationPoint(-2F, 12F, 0F);
      RightLeg10.setTextureSize(128, 128);
      RightLeg10.mirror = true;
      setRotation(RightLeg10, 0F, 0F, 0F);
      RightToe = new ModelRenderer(this, 10, 30);
      RightToe.addBox(-1.5F, 10.5F, -2.5F, 3, 1, 1);
      RightToe.setRotationPoint(-2F, 12F, 0F);
      RightToe.setTextureSize(128, 128);
      RightToe.mirror = true;
      setRotation(RightToe, 0F, 0F, 0F);
      RightLeg11 = new ModelRenderer(this, 68, 65);
      RightLeg11.addBox(-2F, 9F, -2.2F, 4, 3, 1);
      RightLeg11.setRotationPoint(-2F, 12F, 0F);
      RightLeg11.setTextureSize(128, 128);
      RightLeg11.mirror = true;
      setRotation(RightLeg11, 0F, 0F, 0F);
      RightBoot = new ModelRenderer(this, 84, 74);
      RightBoot.addBox(-2F, 11F, -3F, 4, 1, 1);
      RightBoot.setRotationPoint(-2F, 12F, 0F);
      RightBoot.setTextureSize(128, 128);
      RightBoot.mirror = true;
      setRotation(RightBoot, 0F, 0F, 0F);
      RightLeg12 = new ModelRenderer(this, 97, 73);
      RightLeg12.addBox(-2F, 0F, -2.3F, 4, 9, 1);
      RightLeg12.setRotationPoint(-2F, 12F, 0F);
      RightLeg12.setTextureSize(128, 128);
      RightLeg12.mirror = true;
      setRotation(RightLeg12, 0F, 0F, 0F);
      RightLeg13 = new ModelRenderer(this, 112, 50);
      RightLeg13.addBox(-2.3F, 0F, -2F, 1, 9, 4);
      RightLeg13.setRotationPoint(-2F, 12F, 0F);
      RightLeg13.setTextureSize(128, 128);
      RightLeg13.mirror = true;
      setRotation(RightLeg13, 0F, 0F, 0F);
      RightLeg14 = new ModelRenderer(this, 97, 73);
      RightLeg14.addBox(-2F, 0F, 1.3F, 4, 9, 1);
      RightLeg14.setRotationPoint(-2F, 12F, 0F);
      RightLeg14.setTextureSize(128, 128);
      RightLeg14.mirror = true;
      setRotation(RightLeg14, 0F, 0F, 0F);
      RightLeg15 = new ModelRenderer(this, 0, 25);
      RightLeg15.addBox(-1.5F, 0F, -2.5F, 3, 1, 1);
      RightLeg15.setRotationPoint(-2F, 12F, 0F);
      RightLeg15.setTextureSize(128, 128);
      RightLeg15.mirror = true;
      setRotation(RightLeg15, 0F, 0F, 0F);
      Leftleg1 = new ModelRenderer(this, 97, 73);
      Leftleg1.addBox(-2F, 0F, 1.3F, 4, 9, 1);
      Leftleg1.setRotationPoint(2F, 12F, 0F);
      Leftleg1.setTextureSize(128, 128);
      Leftleg1.mirror = true;
      setRotation(Leftleg1, 0F, 0F, 0F);
      Leftleg1.mirror = false;
      LeftLeg2 = new ModelRenderer(this, 81, 67);
      LeftLeg2.addBox(-2F, 11.2F, -2F, 4, 1, 4);
      LeftLeg2.setRotationPoint(2F, 12F, 0F);
      LeftLeg2.setTextureSize(128, 128);
      LeftLeg2.mirror = true;
      setRotation(LeftLeg2, 0F, 0F, 0F);
      LeftLeg3 = new ModelRenderer(this, 100, 44);
      LeftLeg3.addBox(-2F, 9F, 1.2F, 4, 3, 1);
      LeftLeg3.setRotationPoint(2F, 12F, 0F);
      LeftLeg3.setTextureSize(128, 128);
      LeftLeg3.mirror = true;
      setRotation(LeftLeg3, 0F, 0F, 0F);
      LeftLeg4 = new ModelRenderer(this, 11, 10);
      LeftLeg4.addBox(-1.5F, 0.5F, 1.7F, 3, 2, 1);
      LeftLeg4.setRotationPoint(2F, 12F, 0F);
      LeftLeg4.setTextureSize(128, 128);
      LeftLeg4.mirror = true;
      setRotation(LeftLeg4, 0F, 0F, 0F);
      LeftLeg5 = new ModelRenderer(this, 0, 25);
      LeftLeg5.addBox(-1.5F, 0F, -2.5F, 3, 1, 1);
      LeftLeg5.setRotationPoint(2F, 12F, 0F);
      LeftLeg5.setTextureSize(128, 128);
      LeftLeg5.mirror = true;
      setRotation(LeftLeg5, 0F, 0F, 0F);
      LeftLeg6 = new ModelRenderer(this, 112, 50);
      LeftLeg6.addBox(-2.3F, 0F, -2F, 1, 9, 4);
      LeftLeg6.setRotationPoint(2F, 12F, 0F);
      LeftLeg6.setTextureSize(128, 128);
      LeftLeg6.mirror = true;
      setRotation(LeftLeg6, 0F, 0F, 0F);
      LeftToe = new ModelRenderer(this, 10, 30);
      LeftToe.addBox(-1.5F, 10.5F, -2.5F, 3, 1, 1);
      LeftToe.setRotationPoint(2F, 12F, 0F);
      LeftToe.setTextureSize(128, 128);
      LeftToe.mirror = true;
      setRotation(LeftToe, 0F, 0F, 0F);
      LeftLeg7 = new ModelRenderer(this, 0, 16);
      LeftLeg7.addBox(-2.2F, 9F, -2F, 1, 3, 4);
      LeftLeg7.setRotationPoint(2F, 12F, 0F);
      LeftLeg7.setTextureSize(128, 128);
      LeftLeg7.mirror = true;
      setRotation(LeftLeg7, 0F, 0F, 0F);
      LeftLeg8 = new ModelRenderer(this, 68, 72);
      LeftLeg8.addBox(1.2F, 9F, -2F, 1, 3, 4);
      LeftLeg8.setRotationPoint(2F, 12F, 0F);
      LeftLeg8.setTextureSize(128, 128);
      LeftLeg8.mirror = true;
      setRotation(LeftLeg8, 0F, 0F, 0F);
      LeftLeg9 = new ModelRenderer(this, 68, 65);
      LeftLeg9.addBox(-2F, 9F, -2.2F, 4, 3, 1);
      LeftLeg9.setRotationPoint(2F, 12F, 0F);
      LeftLeg9.setTextureSize(128, 128);
      LeftLeg9.mirror = true;
      setRotation(LeftLeg9, 0F, 0F, 0F);
      LeftLeg9.mirror = false;
      LeftBoot = new ModelRenderer(this, 84, 74);
      LeftBoot.addBox(-2F, 11F, -3F, 4, 1, 1);
      LeftBoot.setRotationPoint(2F, 12F, 0F);
      LeftBoot.setTextureSize(128, 128);
      LeftBoot.mirror = true;
      setRotation(LeftBoot, 0F, 0F, 0F);
      LeftLeg10 = new ModelRenderer(this, 97, 73);
      LeftLeg10.addBox(-2F, 0F, -2.3F, 4, 9, 1);
      LeftLeg10.setRotationPoint(2F, 12F, 0F);
      LeftLeg10.setTextureSize(128, 128);
      LeftLeg10.mirror = true;
      setRotation(LeftLeg10, 0F, 0F, 0F);
      LeftLeg10.mirror = false;
      LeftLeg11 = new ModelRenderer(this, 29, 23);
      LeftLeg11.addBox(-1.5F, 6F, -3.3F, 3, 3, 1);
      LeftLeg11.setRotationPoint(2F, 12F, 0F);
      LeftLeg11.setTextureSize(128, 128);
      LeftLeg11.mirror = true;
      setRotation(LeftLeg11, 0F, 0F, 0F);
      LeftLeg12 = new ModelRenderer(this, 29, 23);
      LeftLeg12.addBox(-1.5F, 2F, -3.3F, 3, 3, 1);
      LeftLeg12.setRotationPoint(2F, 12F, 0F);
      LeftLeg12.setTextureSize(128, 128);
      LeftLeg12.mirror = true;
      setRotation(LeftLeg12, 0F, 0F, 0F);
      LeftLeg13 = new ModelRenderer(this, 10, 77);
      LeftLeg13.addBox(-2F, -0.3F, -2F, 4, 1, 4);
      LeftLeg13.setRotationPoint(2F, 12F, 0F);
      LeftLeg13.setTextureSize(128, 128);
      LeftLeg13.mirror = true;
      setRotation(LeftLeg13, 0F, 0F, 0F);
      LeftLeg14 = new ModelRenderer(this, 112, 50);
      LeftLeg14.addBox(1.3F, 0F, -2F, 1, 9, 4);
      LeftLeg14.setRotationPoint(2F, 12F, 0F);
      LeftLeg14.setTextureSize(128, 128);
      LeftLeg14.mirror = true;
      setRotation(LeftLeg14, 0F, 0F, 0F);
      LeftLeg15 = new ModelRenderer(this, 24, 16);
      LeftLeg15.addBox(1.5F, 0F, -2F, 1, 1, 4);
      LeftLeg15.setRotationPoint(2F, 12F, 0F);
      LeftLeg15.setTextureSize(128, 128);
      LeftLeg15.mirror = true;
      setRotation(LeftLeg15, 0F, 0F, 0F);
      LeftLeg16 = new ModelRenderer(this, 68, 29);
      LeftLeg16.addBox(-2F, 0F, 1.5F, 4, 1, 1);
      LeftLeg16.setRotationPoint(2F, 12F, 0F);
      LeftLeg16.setTextureSize(128, 128);
      LeftLeg16.mirror = true;
      setRotation(LeftLeg16, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    try {
    	Field[] fields = ModelSpaceLegs.class.getDeclaredFields();
    	for(Field fi : fields) {
    		Object obj = fi.get(this);
    		if(obj !=null && obj instanceof ModelRenderer) {
    			ModelRenderer mr = (ModelRenderer)obj;
    			if(fi.getName().toLowerCase().contains("right")) {
    				ModelUtil.setChild(this.bipedRightLeg, mr);
    			}
    			if(fi.getName().toLowerCase().contains("left")) {
    				ModelUtil.setChild(this.bipedLeftLeg, mr);
    			}
    		}
    	}
    }
    catch(Exception e) {}
    RightLeg1.render(f5);
    RightLeg2.render(f5);
    RightLeg3.render(f5);
    RightLeg4.render(f5);
    RightLeg5.render(f5);
    RightLeg6.render(f5);
    RightLeg7.render(f5);
    RightLeg8.render(f5);
    RightLeg9.render(f5);
    RightLeg10.render(f5);
    RightToe.render(f5);
    RightLeg11.render(f5);
    RightBoot.render(f5);
    RightLeg12.render(f5);
    RightLeg13.render(f5);
    RightLeg14.render(f5);
    RightLeg15.render(f5);
    Leftleg1.render(f5);
    LeftLeg2.render(f5);
    LeftLeg3.render(f5);
    LeftLeg4.render(f5);
    LeftLeg5.render(f5);
    LeftLeg6.render(f5);
    LeftToe.render(f5);
    LeftLeg7.render(f5);
    LeftLeg8.render(f5);
    LeftLeg9.render(f5);
    LeftBoot.render(f5);
    LeftLeg10.render(f5);
    LeftLeg11.render(f5);
    LeftLeg12.render(f5);
    LeftLeg13.render(f5);
    LeftLeg14.render(f5);
    LeftLeg15.render(f5);
    LeftLeg16.render(f5);
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
