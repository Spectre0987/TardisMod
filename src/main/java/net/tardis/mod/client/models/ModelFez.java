package net.tardis.mod.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFez extends ModelBiped {
	// fields
	ModelRenderer Shape1;
	
	ModelBiped biped;
	
	public ModelFez(ModelBiped model) {
		this.biped = model;
		textureWidth = 64;
		textureHeight = 32;
		
		Shape1 = new ModelRenderer(this, 40, 0);
		Shape1.addBox(0F, 0F, 0F, 6, 4, 6);
		Shape1.setRotationPoint(0, 0, 0);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		
		this.bipedHeadwear.isHidden = true;
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.offsetY = -0.7F;
		Shape1.offsetX = -0.2F;
		Shape1.offsetZ = -0.2F;
		this.bipedHead.addChild(Shape1);
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
