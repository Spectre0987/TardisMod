package net.tardis.mod.client.models.decoration;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHellbentCorridor extends ModelBase {
	//fields
	ModelRenderer LeftDoor1;
	ModelRenderer LeftDoor2;
	ModelRenderer RightDoor;
	ModelRenderer P1;
	ModelRenderer P2;
	ModelRenderer P3;
	ModelRenderer P4;
	ModelRenderer P5;
	ModelRenderer P6;
	ModelRenderer P7;
	ModelRenderer P8;
	ModelRenderer P9;

	public ModelHellbentCorridor() {
		textureWidth = 256;
		textureHeight = 256;

		LeftDoor1 = new ModelRenderer(this, 146, 0);
		LeftDoor1.addBox(0F, -21F, 0F, 14, 45, 1);
		LeftDoor1.setRotationPoint(10F, 0F, -3F);
		LeftDoor1.setTextureSize(256, 256);
		LeftDoor1.mirror = true;
		setRotation(LeftDoor1, 0F, 0F, 0F);
		LeftDoor2 = new ModelRenderer(this, 209, 0);
		LeftDoor2.addBox(11F, 0.5F, -1F, 2, 2, 1);
		LeftDoor2.setRotationPoint(10F, 0F, -3F);
		LeftDoor2.setTextureSize(256, 256);
		LeftDoor2.mirror = true;
		setRotation(LeftDoor2, 0F, 0F, 0F);
		RightDoor = new ModelRenderer(this, 178, 0);
		RightDoor.addBox(-14F, -21F, 0F, 14, 45, 1);
		RightDoor.setRotationPoint(38F, 0F, -3F);
		RightDoor.setTextureSize(256, 256);
		RightDoor.mirror = true;
		setRotation(RightDoor, 0F, 0F, 0F);
		P1 = new ModelRenderer(this, 0, 0);
		P1.addBox(-8F, -56F, -8F, 64, 30, 8);
		P1.setRotationPoint(0F, 0F, 0F);
		P1.setTextureSize(256, 256);
		P1.mirror = true;
		setRotation(P1, 0F, 0F, 0F);
		P2 = new ModelRenderer(this, 0, 101);
		P2.addBox(-8F, -26F, -8F, 13, 50, 8);
		P2.setRotationPoint(0F, 0F, 0F);
		P2.setTextureSize(256, 256);
		P2.mirror = true;
		setRotation(P2, 0F, 0F, 0F);
		P3 = new ModelRenderer(this, 0, 39);
		P3.addBox(0F, 0F, 0F, 38, 8, 1);
		P3.setRotationPoint(5F, -26F, -8F);
		P3.setTextureSize(256, 256);
		P3.mirror = true;
		setRotation(P3, 0.7853982F, 0F, 0F);
		P4 = new ModelRenderer(this, 0, 168);
		P4.addBox(38F, -26F, -2F, 18, 50, 2);
		P4.setRotationPoint(0F, 0F, 0F);
		P4.setTextureSize(256, 256);
		P4.mirror = true;
		setRotation(P4, 0F, 0F, 0F);
		P5 = new ModelRenderer(this, 0, 160);
		P5.addBox(5F, -26F, -2F, 33, 5, 2);
		P5.setRotationPoint(0F, 0F, 0F);
		P5.setTextureSize(256, 256);
		P5.mirror = true;
		setRotation(P5, 0F, 0F, 0F);
		P6 = new ModelRenderer(this, 43, 101);
		P6.addBox(43F, -26F, -8F, 13, 50, 8);
		P6.setRotationPoint(0F, 0F, 0F);
		P6.setTextureSize(256, 256);
		P6.mirror = true;
		setRotation(P6, 0F, 0F, 0F);
		P7 = new ModelRenderer(this, 19, 49);
		P7.addBox(-8F, -26F, 0F, 8, 50, 1);
		P7.setRotationPoint(43F, 0F, -8F);
		P7.setTextureSize(256, 256);
		P7.mirror = true;
		setRotation(P7, 0F, 0.7853982F, 0F);
		P8 = new ModelRenderer(this, 0, 49);
		P8.addBox(0F, -26F, 0F, 8, 50, 1);
		P8.setRotationPoint(5F, 0F, -8F);
		P8.setTextureSize(256, 256);
		P8.mirror = true;
		setRotation(P8, 0F, -0.7853982F, 0F);
		P9 = new ModelRenderer(this, 0, 168);
		P9.addBox(-8F, -26F, -2F, 18, 50, 2);
		P9.setRotationPoint(0F, 0F, 0F);
		P9.setTextureSize(256, 256);
		P9.mirror = true;
		setRotation(P9, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		P1.render(f5);
		P2.render(f5);
		P3.render(f5);
		P4.render(f5);
		P5.render(f5);
		P6.render(f5);
		P7.render(f5);
		P8.render(f5);
		P9.render(f5);
	}

	public void renderRightDoor(float f5) {
		RightDoor.render(f5);
	}

	public void renderLeftDoor(float f5) {
		LeftDoor1.render(f5);
		LeftDoor2.render(f5);
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
