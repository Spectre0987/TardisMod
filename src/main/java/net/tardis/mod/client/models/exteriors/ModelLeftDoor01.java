package net.tardis.mod.client.models.exteriors;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.tardis.mod.client.renderers.RenderHelper;
import net.tardis.mod.util.helpers.Helper;

public class ModelLeftDoor01 extends ModelBase {

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

	public ModelLeftDoor01() { 

		textureWidth = 256;
		textureHeight = 256;

		Shape1 = new ModelRenderer(this, 18, 72);
		Shape1.addBox(0.5F, -1.0F, -8.5F, 7, 1, 1);
		Shape1.setTextureSize(256, 256);
		Shape1.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Shape1, 0.0F, 0.0F, 0.0F);
		Shape1.mirror = false;

		Gen0 = new ModelRenderer(this, 18, 72);
		Gen0.addBox(0.5F, 21.5F, -8.5F, 7, 1, 1);
		Gen0.setTextureSize(256, 256);
		Gen0.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen0, 0.0F, 0.0F, 0.0F);
		Gen0.mirror = false;

		Gen1 = new ModelRenderer(this, 18, 72);
		Gen1.addBox(0.5F, 6.5F, -8.5F, 7, 1, 1);
		Gen1.setTextureSize(256, 256);
		Gen1.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen1, 0.0F, 0.0F, 0.0F);
		Gen1.mirror = false;

		Gen2 = new ModelRenderer(this, 18, 72);
		Gen2.addBox(0.5F, 14.0F, -8.5F, 7, 1, 1);
		Gen2.setTextureSize(256, 256);
		Gen2.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen2, 0.0F, 0.0F, 0.0F);
		Gen2.mirror = false;

		Gen3 = new ModelRenderer(this, 0, 0);
		Gen3.addBox(-2.5F, -0.5F, -0.5F, 5, 1, 1);
		Gen3.setTextureSize(256, 256);
		Gen3.setRotationPoint(4.0F, -4.5F, -8.2F);
		setRotation(Gen3, 0.7853981633974483F, 0.0F, 0.0F);
		Gen3.mirror = false;

		Gen4 = new ModelRenderer(this, 18, 72);
		Gen4.addBox(0.5F, -8.5F, -8.5F, 7, 1, 1);
		Gen4.setTextureSize(256, 256);
		Gen4.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen4, 0.0F, 0.0F, 0.0F);
		Gen4.mirror = false;

		Gen5 = new ModelRenderer(this, 13, 72);
		Gen5.addBox(0.5F, -8.5F, -6.5F, 1, 31, 1);
		Gen5.setTextureSize(256, 256);
		Gen5.setRotationPoint(0.0F, 0.0F, -2.5F);
		setRotation(Gen5, 0.0F, 0.0F, 0.0F);
		Gen5.mirror = false;

		Gen6 = new ModelRenderer(this, 13, 72);
		Gen6.addBox(6.5F, -8.0F, -8.5F, 1, 30, 1);
		Gen6.setTextureSize(256, 256);
		Gen6.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen6, 0.0F, 0.0F, 0.0F);
		Gen6.mirror = false;

		Gen7 = new ModelRenderer(this, 0, 0);
		Gen7.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen7.setTextureSize(256, 256);
		Gen7.setRotationPoint(3.1F, -4.45F, -8.2F);
		setRotation(Gen7, 0.0F, 0.7853981633974483F, 0.0F);
		Gen7.mirror = false;

		Gen8 = new ModelRenderer(this, 0, 0);
		Gen8.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen8.setTextureSize(256, 256);
		Gen8.setRotationPoint(4.9F, -4.45F, -8.2F);
		setRotation(Gen8, 0.0F, 0.7853981633974483F, 0.0F);
		Gen8.mirror = false;

		Gen9 = new ModelRenderer(this, 62, 86);
		Gen9.addBox(3.8F, 3.55F, -8.4F, 1, 1, 1);
		Gen9.setTextureSize(256, 256);
		Gen9.setRotationPoint(0.0F, -0.5F, -0.5F);
		setRotation(Gen9, 0.0F, 0.0F, 0.0F);
		Gen9.mirror = false;

		Gen10 = new ModelRenderer(this, 18, 68);
		Gen10.addBox(1.5F, -1.5F, -8.45F, 5, 1, 1);
		Gen10.setTextureSize(256, 256);
		Gen10.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotation(Gen10, 0.0F, 0.0F, 0.0F);
		Gen10.mirror = false;

		Gen11 = new ModelRenderer(this, 46, 81);
		Gen11.addBox(1.5F, -7.0F, -8.2F, 5, 29, 0);
		Gen11.setTextureSize(256, 256);
		Gen11.setRotationPoint(0.0F, -0.5F, -0.5F);
		setRotation(Gen11, 0.0F, 0.0F, 0.0F);
		Gen11.mirror = false;

		Gen12 = new ModelRenderer(this, 57, 81);
		Gen12.addBox(3.0F, 2.75F, -8.3F, 2, 2, 1);
		Gen12.setTextureSize(256, 256);
		Gen12.setRotationPoint(0.0F, -0.5F, -0.5F);
		setRotation(Gen12, 0.0F, 0.0F, 0.0F);
		Gen12.mirror = false;

		Gen13 = new ModelRenderer(this, 57, 86);
		Gen13.addBox(3.5F, 3.25F, -8.5F, 1, 1, 1);
		Gen13.setTextureSize(256, 256);
		Gen13.setRotationPoint(0.0F, -0.5F, -0.5F);
		setRotation(Gen13, 0.0F, 0.0F, 0.0F);
		Gen13.mirror = false;

		Gen14 = new ModelRenderer(this, 62, 86);
		Gen14.addBox(3.2F, 3.55F, -8.4F, 1, 1, 1);
		Gen14.setTextureSize(256, 256);
		Gen14.setRotationPoint(0.0F, -0.5F, -0.5F);
		setRotation(Gen14, 0.0F, 0.0F, 0.0F);
		Gen14.mirror = false;

		Gen15 = new ModelRenderer(this, 62, 86);
		Gen15.addBox(3.8F, 2.95F, -8.4F, 1, 1, 1);
		Gen15.setTextureSize(256, 256);
		Gen15.setRotationPoint(0.0F, -0.5F, -0.5F);
		setRotation(Gen15, 0.0F, 0.0F, 0.0F);
		Gen15.mirror = false;

		Gen16 = new ModelRenderer(this, 62, 86);
		Gen16.addBox(3.2F, 2.95F, -8.4F, 1, 1, 1);
		Gen16.setTextureSize(256, 256);
		Gen16.setRotationPoint(0.0F, -0.5F, -0.5F);
		setRotation(Gen16, 0.0F, 0.0F, 0.0F);
		Gen16.mirror = false;

	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		RenderHelper.renderTransformAxis();
		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.46875, 0, 0.53125F);
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
		GlStateManager.popMatrix();
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