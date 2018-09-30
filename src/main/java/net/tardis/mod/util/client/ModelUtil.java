package net.tardis.mod.util.client;

import net.minecraft.client.model.ModelRenderer;

public class ModelUtil {

	public static void copyAngle(ModelRenderer parent, ModelRenderer child) {
		//child.setRotationPoint(parent.rotationPointX, parent.rotationPointY, parent.rotationPointZ);
		child.rotateAngleX = parent.rotateAngleX;
		child.rotateAngleY = parent.rotateAngleY;
		child.rotateAngleZ = parent.rotateAngleZ;
	}
}
