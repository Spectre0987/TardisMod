package net.tardis.mod.client.models.console.contols;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelZ extends ModelBase {
	// fields
	ModelRenderer Panel6DNG6;

	public ModelZ() {
		textureWidth = 80;
		textureHeight = 80;

		Panel6DNG6 = new ModelRenderer(this, 10, 4);
		Panel6DNG6.addBox(0F, 0F, 0F, 1, 1, 1);
		Panel6DNG6.setRotationPoint(0F, 0F, 0F);
		Panel6DNG6.setTextureSize(80, 80);
		Panel6DNG6.mirror = true;
		setRotation(Panel6DNG6, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Panel6DNG6.render(f5);
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
