package net.tardis.mod.client.models.exteriors;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.tardis.mod.util.client.RenderHelper;
import net.tardis.mod.util.common.helpers.Helper;

public class ModelTardisTT extends ModelBase implements IExteriorModel{

	ModelRenderer MS1;
	ModelRenderer MS2;
	ModelRenderer MS3;
	ModelRenderer MS4;
	ModelRenderer MS5;
	ModelRenderer Shape1;
	ModelRenderer Gen0;
	ModelRenderer Gen1;
	ModelRenderer Gen2;
	ModelRenderer Gen3;
	ModelRenderer Gen4;
	ModelRenderer Gen5;
	ModelRenderer Gen6;
	ModelRenderer Gen7;
	ModelRenderer Gen8;
	ModelRenderer Gen9;
	ModelRenderer Gen10;
	ModelRenderer Gen11;
	ModelRenderer Door1;
	ModelRenderer Door2;
	ModelRenderer Gen12;
	ModelRenderer Gen13;
	ModelRenderer Gen14;

	public ModelTardisTT() { 

		textureWidth = 256;
		textureHeight = 256;

		MS1 = new ModelRenderer(this, 0, 0);
		MS1.addBox(-4.0F, -21.0F, -10.0F, 8, 7, 20);
		MS1.setTextureSize(256, 256);
		MS1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(MS1, 0.0F, 0.0F, 0.0F);
		MS1.mirror = false;

		MS2 = new ModelRenderer(this, 56, 0);
		MS2.addBox(-7.0F, -21.0F, -9.0F, 14, 7, 18);
		MS2.setTextureSize(256, 256);
		MS2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(MS2, 0.0F, 0.0F, 0.0F);
		MS2.mirror = false;

		MS3 = new ModelRenderer(this, 120, 0);
		MS3.addBox(-8.0F, -21.0F, -8.0F, 16, 7, 16);
		MS3.setTextureSize(256, 256);
		MS3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(MS3, 0.0F, 0.0F, 0.0F);
		MS3.mirror = false;

		MS4 = new ModelRenderer(this, 184, 0);
		MS4.addBox(-9.0F, -21.0F, -7.0F, 18, 7, 14);
		MS4.setTextureSize(256, 256);
		MS4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(MS4, 0.0F, 0.0F, 0.0F);
		MS4.mirror = false;

		MS5 = new ModelRenderer(this, 0, 27);
		MS5.addBox(-10.0F, -21.0F, -4.0F, 20, 7, 8);
		MS5.setTextureSize(256, 256);
		MS5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(MS5, 0.0F, 0.0F, 0.0F);
		MS5.mirror = false;

		Shape1 = new ModelRenderer(this, 18, 42);
		Shape1.addBox(-9.0F, -14.0F, -7.0F, 1, 34, 14);
		Shape1.setTextureSize(256, 256);
		Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Shape1, 0.0F, 0.0F, 0.0F);
		Shape1.mirror = false;

		Gen0 = new ModelRenderer(this, 0, 0);
		Gen0.addBox(-4.0F, 20.0F, -10.0F, 8, 4, 20);
		Gen0.setTextureSize(256, 256);
		Gen0.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen0, 0.0F, 0.0F, 0.0F);
		Gen0.mirror = false;

		Gen1 = new ModelRenderer(this, 112, 42);
		Gen1.addBox(-4.0F, -14.0F, 9.0F, 8, 34, 1);
		Gen1.setTextureSize(256, 256);
		Gen1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen1, 0.0F, 0.0F, 0.0F);
		Gen1.mirror = false;

		Gen2 = new ModelRenderer(this, 120, 0);
		Gen2.addBox(-8.0F, 20.0F, -8.0F, 16, 4, 16);
		Gen2.setTextureSize(256, 256);
		Gen2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen2, 0.0F, 0.0F, 0.0F);
		Gen2.mirror = false;

		Gen3 = new ModelRenderer(this, 184, 0);
		Gen3.addBox(-9.0F, 20.0F, -7.0F, 18, 4, 14);
		Gen3.setTextureSize(256, 256);
		Gen3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen3, 0.0F, 0.0F, 0.0F);
		Gen3.mirror = false;

		Gen4 = new ModelRenderer(this, 0, 27);
		Gen4.addBox(-10.0F, 20.0F, -4.0F, 20, 4, 8);
		Gen4.setTextureSize(256, 256);
		Gen4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen4, 0.0F, 0.0F, 0.0F);
		Gen4.mirror = false;

		Gen5 = new ModelRenderer(this, 0, 42);
		Gen5.addBox(-10.0F, -14.0F, -4.0F, 1, 34, 8);
		Gen5.setTextureSize(256, 256);
		Gen5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen5, 0.0F, 0.0F, 0.0F);
		Gen5.mirror = false;

		Gen6 = new ModelRenderer(this, 0, 42);
		Gen6.addBox(9.0F, -14.0F, -4.0F, 1, 34, 8);
		Gen6.setTextureSize(256, 256);
		Gen6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen6, 0.0F, 0.0F, 0.0F);
		Gen6.mirror = false;

		Gen7 = new ModelRenderer(this, 18, 42);
		Gen7.addBox(8.0F, -14.0F, -7.0F, 1, 34, 14);
		Gen7.setTextureSize(256, 256);
		Gen7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen7, 0.0F, 0.0F, 0.0F);
		Gen7.mirror = false;

		Gen8 = new ModelRenderer(this, 48, 92);
		Gen8.addBox(-1.0F, -14.0F, 0.0F, 1, 34, 3);
		Gen8.setTextureSize(256, 256);
		Gen8.setRotationPoint(-7.0F, 0.0F, -8.0F);
		setRotation(Gen8, 0.0F, 0.5235987755982988F, 0.0F);
		Gen8.mirror = false;

		Gen9 = new ModelRenderer(this, 48, 42);
		Gen9.addBox(-8.0F, -14.0F, -8.0F, 1, 34, 16);
		Gen9.setTextureSize(256, 256);
		Gen9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen9, 0.0F, 0.0F, 0.0F);
		Gen9.mirror = false;

		Gen10 = new ModelRenderer(this, 56, 0);
		Gen10.addBox(-7.0F, 20.0F, -9.0F, 14, 4, 18);
		Gen10.setTextureSize(256, 256);
		Gen10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen10, 0.0F, 0.0F, 0.0F);
		Gen10.mirror = false;

		Gen11 = new ModelRenderer(this, 82, 77);
		Gen11.addBox(-5.0F, -14.0F, 1.0F, 10, 34, 1);
		Gen11.setTextureSize(256, 256);
		Gen11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen11, 0.0F, 0.0F, 0.0F);
		Gen11.mirror = false;

		Door1 = new ModelRenderer(this, 0, 91);
		Door1.addBox(0.0F, -14.0F, 0.0F, 6, 34, 1);
		Door1.setTextureSize(256, 256);
		Door1.setRotationPoint(-6.0F, 0.0F, -6.0F);
		setRotation(Door1, 0.0F, 0.0F, 0.0F);
		Door1.mirror = false;

		Door2 = new ModelRenderer(this, 14, 91);
		Door2.addBox(-6.0F, -14.0F, 0.0F, 6, 34, 1);
		Door2.setTextureSize(256, 256);
		Door2.setRotationPoint(6.0F, 0.0F, -6.0F);
		setRotation(Door2, 0.0F, 0.0F, 0.0F);
		Door2.mirror = false;

		Gen12 = new ModelRenderer(this, 48, 42);
		Gen12.addBox(7.0F, -14.0F, -8.0F, 1, 34, 16);
		Gen12.setTextureSize(256, 256);
		Gen12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen12, 0.0F, 0.0F, 0.0F);
		Gen12.mirror = false;

		Gen13 = new ModelRenderer(this, 48, 92);
		Gen13.addBox(0.0F, -14.0F, 0.0F, 1, 34, 3);
		Gen13.setTextureSize(256, 256);
		Gen13.setRotationPoint(7.0F, 0.0F, -8.0F);
		setRotation(Gen13, 0.0F, -0.5235987755982988F, 0.0F);
		Gen13.mirror = false;

		Gen14 = new ModelRenderer(this, 82, 42);
		Gen14.addBox(-7.0F, -14.0F, 8.0F, 14, 34, 1);
		Gen14.setTextureSize(256, 256);
		Gen14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen14, 0.0F, 0.0F, 0.0F);
		Gen14.mirror = false;

	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		MS1.render(scale);
		MS2.render(scale);
		MS3.render(scale);
		MS4.render(scale);
		MS5.render(scale);
		Shape1.render(scale);
		Gen0.render(scale);
		Gen1.render(scale);
		Gen2.render(scale);
		Gen3.render(scale);
		Gen4.render(scale);
		Gen5.render(scale);
		Gen6.render(scale);
		Gen7.render(scale);
		Gen8.render(scale);
		Gen9.render(scale);
		Gen10.render(scale);
		Gen11.render(scale);
		Gen12.render(scale);
		Gen13.render(scale);
		Gen14.render(scale);
	}
	
	public void renderDoors(boolean open, float scale) {
		if(open) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(-0.375, 0, -0.34375);
			GlStateManager.rotate(-80, 0, 1, 0);
			GlStateManager.translate(0.375, 0, 0.34375);
			Door1.render(scale);
			GlStateManager.popMatrix();
			
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.375, 0, -0.34375);
			GlStateManager.rotate(80, 0, 1, 0);
			GlStateManager.translate(-0.375, 0, 0.34375);
			Door2.render(scale);
			GlStateManager.popMatrix();
		}
		else {
			Door1.render(scale);
			Door2.render(scale);
		}
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entity){
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, scaleFactor, entity);
	}
	
	@Override
	public void renderClosed(float scale) {
		this.render(null, 0, 0, 0, 0, 0, scale);
		this.renderDoors(false, scale);
	}
	@Override
	public void renderOpen(float scale) {
		this.render(null, 0, 0, 0, 0, 0, scale);
		this.renderDoors(true, scale);
	}
}