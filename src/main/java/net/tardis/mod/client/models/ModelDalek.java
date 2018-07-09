package net.tardis.mod.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDalek extends ModelBase {
	// fields
	ModelRenderer eye;
	ModelRenderer base;
	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer gunStick;
	ModelRenderer Head1;
	ModelRenderer Head2;
	ModelRenderer eyestalk;
	ModelRenderer body;
	
	public ModelDalek() {
		textureWidth = 86;
		textureHeight = 107;
		
		eye = new ModelRenderer(this, 65, 8);
		eye.addBox(0F, 0F, 0F, 2, 2, 1);
		eye.setRotationPoint(-1F, -5.5F, -9F);
		eye.setTextureSize(86, 107);
		eye.mirror = true;
		setRotation(eye, 0F, 0F, 0F);
		base = new ModelRenderer(this, 0, 0);
		base.addBox(0F, 0F, 0F, 16, 1, 16);
		base.setRotationPoint(-8F, 23F, -8F);
		base.setTextureSize(86, 107);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		base1 = new ModelRenderer(this, 0, 21);
		base1.addBox(0F, 0F, 0F, 14, 6, 14);
		base1.setRotationPoint(-7F, 17F, -7F);
		base1.setTextureSize(86, 107);
		base1.mirror = true;
		setRotation(base1, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 0, 41);
		base2.addBox(0F, 0F, 0F, 12, 6, 12);
		base2.setRotationPoint(-6F, 11F, -6F);
		base2.setTextureSize(86, 107);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
		gunStick = new ModelRenderer(this, 78, 0);
		gunStick.addBox(0F, 0F, 0F, 1, 1, 3);
		gunStick.setRotationPoint(3F, 3F, -8F);
		gunStick.setTextureSize(86, 107);
		gunStick.mirror = true;
		setRotation(gunStick, 0F, 0F, 0F);
		Head1 = new ModelRenderer(this, 0, 81);
		Head1.addBox(0F, 0F, 0F, 10, 4, 10);
		Head1.setRotationPoint(-5F, -3F, -5F);
		Head1.setTextureSize(86, 107);
		Head1.mirror = true;
		setRotation(Head1, 0F, 0F, 0F);
		Head2 = new ModelRenderer(this, 0, 96);
		Head2.addBox(0F, 0F, 0F, 8, 3, 8);
		Head2.setRotationPoint(-4F, -6F, -4F);
		Head2.setTextureSize(86, 107);
		Head2.mirror = true;
		setRotation(Head2, 0F, 0F, 0F);
		eyestalk = new ModelRenderer(this, 65, 0);
		eyestalk.addBox(0F, 0F, 0F, 1, 1, 4);
		eyestalk.setRotationPoint(-0.5F, -5F, -8F);
		eyestalk.setTextureSize(86, 107);
		eyestalk.mirror = true;
		setRotation(eyestalk, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 60);
		body.addBox(0F, 0F, 0F, 10, 10, 10);
		body.setRotationPoint(-5F, 1F, -5F);
		body.setTextureSize(86, 107);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, 0, f1, f2, f3, f4, f5);
		setRotationAngles(0, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		base1.render(f5);
		base2.render(f5);
		gunStick.render(f5);
		Head1.render(f5);
		Head2.render(f5);
		eyestalk.addChild(eye);
		eyestalk.render(f5);
		setOffset(eye, (float) 0.5 / 16, (float) 5 / 16, 0.5F);
		setRotation(eyestalk, (float) Math.toRadians((double) f), 0F, 0F);
		body.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	public void setOffset(ModelRenderer mr, float x, float y, float z) {
		mr.offsetX = x;
		mr.offsetY = y;
		mr.offsetZ = z;
	}
}
