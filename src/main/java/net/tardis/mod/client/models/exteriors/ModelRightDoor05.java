package net.tardis.mod.client.models.exteriors;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRightDoor05 extends ModelBase {

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
	ModelRenderer Gen12;
	ModelRenderer Gen13;
	ModelRenderer WindowGlow1;

	public ModelRightDoor05() { 

		textureWidth = 256;
		textureHeight = 256;

		Shape1 = new ModelRenderer(this, 18, 72);
		Shape1.addBox(0.5F, -1.0F, -9.0F, 7, 1, 1);
		Shape1.setTextureSize(256, 256);
		Shape1.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Shape1, 0.0F, 0.0F, 0.0F);
		Shape1.mirror = false;

		Gen0 = new ModelRenderer(this, 18, 72);
		Gen0.addBox(0.5F, 21.5F, -9.0F, 7, 1, 1);
		Gen0.setTextureSize(256, 256);
		Gen0.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen0, 0.0F, 0.0F, 0.0F);
		Gen0.mirror = false;

		Gen1 = new ModelRenderer(this, 18, 72);
		Gen1.addBox(0.5F, 6.5F, -9.0F, 7, 1, 1);
		Gen1.setTextureSize(256, 256);
		Gen1.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen1, 0.0F, 0.0F, 0.0F);
		Gen1.mirror = false;

		Gen2 = new ModelRenderer(this, 18, 72);
		Gen2.addBox(0.5F, 14.0F, -9.0F, 7, 1, 1);
		Gen2.setTextureSize(256, 256);
		Gen2.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen2, 0.0F, 0.0F, 0.0F);
		Gen2.mirror = false;

		Gen3 = new ModelRenderer(this, 0, 0);
		Gen3.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
		Gen3.setTextureSize(256, 256);
		Gen3.setRotationPoint(4.0F, -4.25F, -8.7F);
		setRotation(Gen3, 0.7853981633974483F, 0.0F, 0.0F);
		Gen3.mirror = false;

		Gen4 = new ModelRenderer(this, 18, 72);
		Gen4.addBox(0.5F, -8.5F, -9.0F, 7, 1, 1);
		Gen4.setTextureSize(256, 256);
		Gen4.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen4, 0.0F, 0.0F, 0.0F);
		Gen4.mirror = false;

		Gen5 = new ModelRenderer(this, 13, 72);
		Gen5.addBox(0.5F, -8.5F, -7.0F, 1, 31, 1);
		Gen5.setTextureSize(256, 256);
		Gen5.setRotationPoint(0.0F, 0.0F, -2.5F);
		setRotation(Gen5, 0.0F, 0.0F, 0.0F);
		Gen5.mirror = false;

		Gen6 = new ModelRenderer(this, 13, 72);
		Gen6.addBox(6.5F, -8.0F, -9.0F, 1, 30, 1);
		Gen6.setTextureSize(256, 256);
		Gen6.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen6, 0.0F, 0.0F, 0.0F);
		Gen6.mirror = false;

		Gen7 = new ModelRenderer(this, 0, 0);
		Gen7.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen7.setTextureSize(256, 256);
		Gen7.setRotationPoint(3.1F, -4.45F, -8.7F);
		setRotation(Gen7, 0.0F, 0.7853981633974483F, 0.0F);
		Gen7.mirror = false;

		Gen8 = new ModelRenderer(this, 0, 0);
		Gen8.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen8.setTextureSize(256, 256);
		Gen8.setRotationPoint(4.9F, -4.45F, -8.7F);
		setRotation(Gen8, 0.0F, 0.7853981633974483F, 0.0F);
		Gen8.mirror = false;

		Gen9 = new ModelRenderer(this, 13, 112);
		Gen9.addBox(0.75F, 0.0F, 0.0F, 1, 0, 1);
		Gen9.setTextureSize(256, 256);
		Gen9.setRotationPoint(0.0F, 5.5F, -9.8F);
		setRotation(Gen9, -1.2217304763960306F, 0.0F, 0.0F);
		Gen9.mirror = false;

		Gen10 = new ModelRenderer(this, 46, 90);
		Gen10.addBox(1.5F, 0.0F, -8.2F, 5, 22, 0);
		Gen10.setTextureSize(256, 256);
		Gen10.setRotationPoint(0.0F, 0.0F, -1.0F);
		setRotation(Gen10, 0.0F, 0.0F, 0.0F);
		Gen10.mirror = false;

		Gen11 = new ModelRenderer(this, 13, 106);
		Gen11.addBox(0.5F, 3.25F, -9.1F, 1, 1, 1);
		Gen11.setTextureSize(256, 256);
		Gen11.setRotationPoint(0.0F, -0.5F, -0.5F);
		setRotation(Gen11, 0.0F, 0.0F, 0.0F);
		Gen11.mirror = false;

		Gen12 = new ModelRenderer(this, 13, 109);
		Gen12.addBox(0.75F, 6.5F, -9.3F, 1, 1, 0);
		Gen12.setTextureSize(256, 256);
		Gen12.setRotationPoint(0.0F, -2.0F, -0.5F);
		setRotation(Gen12, 0.0F, 0.0F, 0.0F);
		Gen12.mirror = false;

		Gen13 = new ModelRenderer(this, 13, 112);
		Gen13.addBox(0.75F, 0.0F, 0.0F, 1, 0, 1);
		Gen13.setTextureSize(256, 256);
		Gen13.setRotationPoint(0.0F, 4.5F, -9.8F);
		setRotation(Gen13, 1.2217304763960306F, 0.0F, 0.0F);
		Gen13.mirror = false;

		WindowGlow1 = new ModelRenderer(this, 46, 81);
		WindowGlow1.addBox(1.5F, -8.25F, -8.2F, 5, 8, 0);
		WindowGlow1.setTextureSize(256, 256);
		WindowGlow1.setRotationPoint(0.0F, 0.0F, -1.0F);
		setRotation(WindowGlow1, 0.0F, 0.0F, 0.0F);
		WindowGlow1.mirror = false;

	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
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
		WindowGlow1.render(scale);
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
}