package net.tardis.mod.client.models.decoration;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHellbentPole extends ModelBase {
	//fields
	ModelRenderer Shape0;
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;

	public ModelHellbentPole() {
		textureWidth = 128;
		textureHeight = 128;

		Shape0 = new ModelRenderer(this, 0, 0);
		Shape0.addBox(-5F, -24F, -2F, 10, 48, 4);
		Shape0.setRotationPoint(0F, 0F, 0F);
		Shape0.setTextureSize(128, 128);
		Shape0.mirror = true;
		setRotation(Shape0, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-5F, -24F, -2F, 10, 48, 4);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 1.570796F, 0F);
		Shape2 = new ModelRenderer(this, 96, 0);
		Shape2.addBox(-2F, -26F, -2F, 4, 1, 4);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 28, 0);
		Shape3.addBox(-0.5F, -24F, -5.5F, 1, 48, 11);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, -0.445059F, 0F);
		Shape4 = new ModelRenderer(this, 28, 0);
		Shape4.addBox(-0.5F, -24F, -5.5F, 1, 48, 11);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0.445059F, 0F);
		Shape5 = new ModelRenderer(this, 28, 0);
		Shape5.addBox(-0.5F, -24F, -5.5F, 1, 48, 11);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 1.230457F, 0F);
		Shape6 = new ModelRenderer(this, 28, 0);
		Shape6.addBox(-0.5F, -24F, -5.5F, 1, 48, 11);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 2.015855F, 0F);
		Shape7 = new ModelRenderer(this, 0, 0);
		Shape7.addBox(-5F, -24F, -2F, 10, 48, 4);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(128, 128);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, -0.7853982F, 0F);
		Shape8 = new ModelRenderer(this, 52, 0);
		Shape8.addBox(-3F, -25F, -3F, 6, 1, 6);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(128, 128);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 76, 0);
		Shape9.addBox(-2.5F, -25.5F, -2.5F, 5, 1, 5);
		Shape9.setRotationPoint(0F, 0F, 0F);
		Shape9.setTextureSize(128, 128);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 0, 0);
		Shape10.addBox(-5F, -24F, -2F, 10, 48, 4);
		Shape10.setRotationPoint(0F, 0F, 0F);
		Shape10.setTextureSize(128, 128);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0.7853982F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape0.render(f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
