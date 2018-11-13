package net.tardis.mod.client.models.decoration;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelASphere extends ModelBase {

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
	ModelRenderer Gen14;
	ModelRenderer Gen15;
	ModelRenderer Gen16;
	ModelRenderer Gen17;
	ModelRenderer Gen18;
	ModelRenderer Gen19;
	ModelRenderer Gen20;

	public ModelASphere() { 

		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 43, 0);
		Shape1.addBox(-2.0F, 0.0F, -1.0F, 2, 4, 2);
		Shape1.setTextureSize(128, 128);
		Shape1.setRotationPoint(1.0F, 21.0F, 0.0F);
		setRotation(Shape1, 0.0F, 0.0F, -0.2617993877991494F);
		Shape1.mirror = false;

		Gen0 = new ModelRenderer(this, 0, 35);
		Gen0.addBox(-3.0F, -4.0F, -0.5F, 6, 8, 1);
		Gen0.setTextureSize(128, 128);
		Gen0.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen0, 0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen0.mirror = false;

		Gen1 = new ModelRenderer(this, 36, 0);
		Gen1.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 1);
		Gen1.setTextureSize(128, 128);
		Gen1.setRotationPoint(0.0F, 21.0F, -1.0F);
		setRotation(Gen1, -0.2617993877991494F, 0.0F, 0.0F);
		Gen1.mirror = false;

		Gen2 = new ModelRenderer(this, 36, 0);
		Gen2.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 1);
		Gen2.setTextureSize(128, 128);
		Gen2.setRotationPoint(0.0F, 21.0F, 1.0F);
		setRotation(Gen2, 0.2617993877991494F, 0.0F, 0.0F);
		Gen2.mirror = false;

		Gen3 = new ModelRenderer(this, 43, 0);
		Gen3.addBox(0.0F, 0.0F, -1.0F, 2, 4, 2);
		Gen3.setTextureSize(128, 128);
		Gen3.setRotationPoint(-1.0F, 21.0F, 0.0F);
		setRotation(Gen3, 0.0F, 0.0F, 0.2617993877991494F);
		Gen3.mirror = false;

		Gen4 = new ModelRenderer(this, 0, 25);
		Gen4.addBox(-2.5F, -4.5F, -0.5F, 5, 9, 1);
		Gen4.setTextureSize(128, 128);
		Gen4.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen4, 0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen4.mirror = false;

		Gen5 = new ModelRenderer(this, 0, 44);
		Gen5.addBox(-3.5F, -3.5F, -0.5F, 7, 7, 1);
		Gen5.setTextureSize(128, 128);
		Gen5.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen5, 0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen5.mirror = false;

		Gen6 = new ModelRenderer(this, 0, 10);
		Gen6.addBox(-3.5F, -7.5F, -0.25F, 7, 7, 1);
		Gen6.setTextureSize(128, 128);
		Gen6.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(Gen6, 0.0F, 0.0F, 0.0F);
		Gen6.mirror = false;

		Gen7 = new ModelRenderer(this, 0, 18);
		Gen7.addBox(-4.0F, -7.0F, -0.25F, 8, 6, 1);
		Gen7.setTextureSize(128, 128);
		Gen7.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(Gen7, 0.0F, 0.0F, 0.0F);
		Gen7.mirror = false;

		Gen8 = new ModelRenderer(this, 0, 0);
		Gen8.addBox(-3.0F, -8.0F, -0.25F, 6, 8, 1);
		Gen8.setTextureSize(128, 128);
		Gen8.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(Gen8, 0.0F, 0.0F, 0.0F);
		Gen8.mirror = false;

		Gen9 = new ModelRenderer(this, 0, 64);
		Gen9.addBox(-3.0F, -4.0F, -0.25F, 6, 8, 1);
		Gen9.setTextureSize(128, 128);
		Gen9.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen9, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen9.mirror = false;

		Gen10 = new ModelRenderer(this, 0, 74);
		Gen10.addBox(-3.5F, -3.5F, -0.25F, 7, 7, 1);
		Gen10.setTextureSize(128, 128);
		Gen10.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen10, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen10.mirror = false;

		Gen11 = new ModelRenderer(this, 0, 53);
		Gen11.addBox(-2.5F, -4.5F, -0.25F, 5, 9, 1);
		Gen11.setTextureSize(128, 128);
		Gen11.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen11, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen11.mirror = false;

		Gen12 = new ModelRenderer(this, 0, 74);
		Gen12.addBox(-3.5F, -3.5F, -0.75F, 7, 7, 1);
		Gen12.setTextureSize(128, 128);
		Gen12.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen12, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen12.mirror = false;

		Gen13 = new ModelRenderer(this, 0, 64);
		Gen13.addBox(-3.0F, -4.0F, -0.75F, 6, 8, 1);
		Gen13.setTextureSize(128, 128);
		Gen13.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen13, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen13.mirror = false;

		Gen14 = new ModelRenderer(this, 29, 0);
		Gen14.addBox(-1.0F, -7.5F, -0.5F, 2, 1, 1);
		Gen14.setTextureSize(128, 128);
		Gen14.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen14, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen14.mirror = false;

		Gen15 = new ModelRenderer(this, 0, 0);
		Gen15.addBox(-3.0F, -8.0F, -0.75F, 6, 8, 1);
		Gen15.setTextureSize(128, 128);
		Gen15.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(Gen15, 0.0F, 0.0F, 0.0F);
		Gen15.mirror = false;

		Gen16 = new ModelRenderer(this, 0, 10);
		Gen16.addBox(-3.5F, -7.5F, -0.75F, 7, 7, 1);
		Gen16.setTextureSize(128, 128);
		Gen16.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(Gen16, 0.0F, 0.0F, 0.0F);
		Gen16.mirror = false;

		Gen17 = new ModelRenderer(this, 0, 18);
		Gen17.addBox(-4.0F, -7.0F, -0.75F, 8, 6, 1);
		Gen17.setTextureSize(128, 128);
		Gen17.setRotationPoint(0.0F, 21.0F, 0.0F);
		setRotation(Gen17, 0.0F, 0.0F, 0.0F);
		Gen17.mirror = false;

		Gen18 = new ModelRenderer(this, 0, 53);
		Gen18.addBox(-2.5F, -4.5F, -0.75F, 5, 9, 1);
		Gen18.setTextureSize(128, 128);
		Gen18.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen18, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen18.mirror = false;

		Gen19 = new ModelRenderer(this, 17, 0);
		Gen19.addBox(-0.5F, -8.5F, -0.5F, 1, 14, 1);
		Gen19.setTextureSize(128, 128);
		Gen19.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen19, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen19.mirror = false;

		Gen20 = new ModelRenderer(this, 22, 0);
		Gen20.addBox(-0.5F, -7.5F, -1.0F, 1, 1, 2);
		Gen20.setTextureSize(128, 128);
		Gen20.setRotationPoint(0.0F, 17.0F, 0.0F);
		setRotation(Gen20, -0.7853981633974483F, 1.5707963267948966F, 0.0F);
		Gen20.mirror = false;

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
		Gen14.render(scale);
		Gen15.render(scale);
		Gen16.render(scale);
		Gen17.render(scale);
		Gen18.render(scale);
		Gen19.render(scale);
		Gen20.render(scale);
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