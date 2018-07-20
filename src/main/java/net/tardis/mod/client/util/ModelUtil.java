package net.tardis.mod.client.util;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.Vec3d;

public class ModelUtil {

	public static void copyAngle(ModelRenderer parent, ModelRenderer child) {
		child.setRotationPoint(parent.rotationPointX, parent.rotationPointY, parent.rotationPointZ);
		child.rotateAngleX = parent.rotateAngleX;
		child.rotateAngleY = parent.rotateAngleY;
		child.rotateAngleZ = parent.rotateAngleZ;
	}
		
	public static class ModelAnimator{
		
		ModelRenderer mr;
		Vec3d trans;
		Vec3d old;
		
		public ModelAnimator(ModelRenderer mr, Vec3d offset) {
			this.mr = mr;
			this.old = new Vec3d(mr.offsetX, mr.offsetY, mr.offsetZ);
			this.trans = offset.addVector(mr.offsetX, mr.offsetY, mr.offsetZ);
		}
		
		public void translate() {
			mr.offsetX = (float)trans.x;
			mr.offsetY = (float)trans.y;
			mr.offsetZ = (float)trans.z;
		}
		
		public void fix() {
			mr.offsetX = (float)old.x;
			mr.offsetY = (float)old.y;
			mr.offsetZ = (float)old.z;
		}
	}
}
