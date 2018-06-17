package net.tardis.mod.client.util;

import net.minecraft.client.model.ModelRenderer;

public class ModelUtil {

	public static void setChild(ModelRenderer parent, ModelRenderer child) {
		child.setRotationPoint(parent.rotationPointX, parent.rotationPointY, parent.rotationPointZ);
		child.rotateAngleX = parent.rotateAngleX;
		child.rotateAngleY = parent.rotateAngleY;
		child.rotateAngleZ = parent.rotateAngleZ;
	}
}
