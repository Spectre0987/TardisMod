package net.tardis.mod.client.models.exteriors;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.tardis.mod.util.client.RenderHelper;
import net.tardis.mod.util.common.helpers.Helper;

public class ModelTardisClock extends ModelBase implements IExteriorModel{

	ModelRenderer MS1;
	ModelRenderer MS2;
	ModelRenderer MS3;
	ModelRenderer MS4;
	ModelRenderer Hour1;
	ModelRenderer Hour2;
	ModelRenderer Hour3;
	ModelRenderer Hour4;
	ModelRenderer Hour5;
	ModelRenderer Hour6;
	ModelRenderer Hour7;
	ModelRenderer Hour8;
	ModelRenderer Hour9;
	ModelRenderer Hour10;
	ModelRenderer Hour11;
	ModelRenderer Hour12;
	ModelRenderer ClockCenter;
	ModelRenderer ClockHandMinute;
	ModelRenderer ClockHandHour;
	ModelRenderer DoorTop;
	ModelRenderer DoorLeft;
	ModelRenderer DoorRight;
	ModelRenderer DoorBottom;
	ModelRenderer DoorMid;
	ModelRenderer DoorGlass;
	ModelRenderer Door1;
	ModelRenderer Door2;
	ModelRenderer Door3;
	ModelRenderer Door4;
	ModelRenderer Door5;
	ModelRenderer Door6;
	ModelRenderer Door7;
	ModelRenderer Door8;
	ModelRenderer Door9;
	ModelRenderer Door10;
	ModelRenderer Door11;
	ModelRenderer Door12;
	ModelRenderer BottomBoy;
	ModelRenderer Leg1T;
	ModelRenderer Leg1B;
	ModelRenderer Leg2T;
	ModelRenderer Leg2B;
	ModelRenderer Leg3T;
	ModelRenderer Leg3B;
	ModelRenderer Leg4T;
	ModelRenderer Leg4B;

	public ModelTardisClock() { 

		textureWidth = 128;
		textureHeight = 128;

		MS1 = new ModelRenderer(this, 32, 0);
		MS1.addBox(0.0F, -12.0F, 0.0F, 0, 30, 1);
		MS1.setTextureSize(128, 128);
		MS1.setRotationPoint(-5.5F, 0.0F, -2.5F);
		setRotation(MS1, 0.0F, 0.08726646259971647F, 0.0F);
		MS1.mirror = false;

		MS2 = new ModelRenderer(this, 0, 0);
		MS2.addBox(-5.5F, -12.0F, -2.0F, 11, 30, 5);
		MS2.setTextureSize(128, 128);
		MS2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(MS2, 0.0F, 0.0F, 0.0F);
		MS2.mirror = false;

		MS3 = new ModelRenderer(this, 34, 0);
		MS3.addBox(-5.5F, 0.0F, 0.0F, 11, 0, 1);
		MS3.setTextureSize(128, 128);
		MS3.setRotationPoint(0.0F, -12.0F, -2.5F);
		setRotation(MS3, -0.08726646259971647F, 0.0F, 0.0F);
		MS3.mirror = false;

		MS4 = new ModelRenderer(this, 34, 1);
		MS4.addBox(-11.0F, -12.0F, -0.5F, 11, 30, 0);
		MS4.setTextureSize(128, 128);
		MS4.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(MS4, 0.0F, 0.0F, 0.0F);
		MS4.mirror = false;

		Hour1 = new ModelRenderer(this, 0, 0);
		Hour1.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour1.setTextureSize(128, 128);
		Hour1.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour1, 0.0F, 0.0F, 0.5235987755982988F);
		Hour1.mirror = false;

		Hour2 = new ModelRenderer(this, 0, 0);
		Hour2.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour2.setTextureSize(128, 128);
		Hour2.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour2, 0.0F, 0.0F, 1.0471975511965976F);
		Hour2.mirror = false;

		Hour3 = new ModelRenderer(this, 0, 0);
		Hour3.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour3.setTextureSize(128, 128);
		Hour3.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour3, 0.0F, 0.0F, 1.5707963267948966F);
		Hour3.mirror = false;

		Hour4 = new ModelRenderer(this, 0, 0);
		Hour4.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour4.setTextureSize(128, 128);
		Hour4.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour4, 0.0F, 0.0F, 2.0943951023931953F);
		Hour4.mirror = false;

		Hour5 = new ModelRenderer(this, 0, 0);
		Hour5.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour5.setTextureSize(128, 128);
		Hour5.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour5, 0.0F, 0.0F, 2.6179938779914944F);
		Hour5.mirror = false;

		Hour6 = new ModelRenderer(this, 0, 0);
		Hour6.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour6.setTextureSize(128, 128);
		Hour6.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour6, 0.0F, 0.0F, 3.141592653589793F);
		Hour6.mirror = false;

		Hour7 = new ModelRenderer(this, 0, 0);
		Hour7.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour7.setTextureSize(128, 128);
		Hour7.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour7, 0.0F, 0.0F, -2.6179938779914944F);
		Hour7.mirror = false;

		Hour8 = new ModelRenderer(this, 0, 0);
		Hour8.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour8.setTextureSize(128, 128);
		Hour8.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour8, 0.0F, 0.0F, -2.0943951023931953F);
		Hour8.mirror = false;

		Hour9 = new ModelRenderer(this, 0, 0);
		Hour9.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour9.setTextureSize(128, 128);
		Hour9.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour9, 0.0F, 0.0F, -1.5707963267948966F);
		Hour9.mirror = false;

		Hour10 = new ModelRenderer(this, 0, 0);
		Hour10.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour10.setTextureSize(128, 128);
		Hour10.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour10, 0.0F, 0.0F, -1.0471975511965976F);
		Hour10.mirror = false;

		Hour11 = new ModelRenderer(this, 0, 0);
		Hour11.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour11.setTextureSize(128, 128);
		Hour11.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour11, 0.0F, 0.0F, -0.5235987755982988F);
		Hour11.mirror = false;

		Hour12 = new ModelRenderer(this, 0, 0);
		Hour12.addBox(-0.5F, -3.0F, -0.6F, 1, 1, 1);
		Hour12.setTextureSize(128, 128);
		Hour12.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(Hour12, 0.0F, 0.0F, 0.0F);
		Hour12.mirror = false;

		ClockCenter = new ModelRenderer(this, 58, 0);
		ClockCenter.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1);
		ClockCenter.setTextureSize(128, 128);
		ClockCenter.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(ClockCenter, 0.0F, 0.0F, 0.0F);
		ClockCenter.mirror = false;

		ClockHandMinute = new ModelRenderer(this, 62, 0);
		ClockHandMinute.addBox(-0.5F, -2.0F, -0.65F, 1, 2, 1);
		ClockHandMinute.setTextureSize(128, 128);
		ClockHandMinute.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(ClockHandMinute, 0.0F, 0.0F, 0.0F);
		ClockHandMinute.mirror = false;

		ClockHandHour = new ModelRenderer(this, 62, 0);
		ClockHandHour.addBox(-0.5F, -2.0F, -0.7F, 1, 2, 1);
		ClockHandHour.setTextureSize(128, 128);
		ClockHandHour.setRotationPoint(0.0F, -6.5F, -2.0F);
		setRotation(ClockHandHour, 0.0F, 0.0F, 0.0F);
		ClockHandHour.mirror = false;

		DoorTop = new ModelRenderer(this, 66, 0);
		DoorTop.addBox(-11.0F, -12.0F, -1.0F, 11, 2, 1);
		DoorTop.setTextureSize(128, 128);
		DoorTop.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(DoorTop, 0.0F, 0.0F, 0.0F);
		DoorTop.mirror = false;

		DoorLeft = new ModelRenderer(this, 0, 35);
		DoorLeft.addBox(-11.0F, -10.0F, -1.0F, 2, 28, 1);
		DoorLeft.setTextureSize(128, 128);
		DoorLeft.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(DoorLeft, 0.0F, 0.0F, 0.0F);
		DoorLeft.mirror = false;

		DoorRight = new ModelRenderer(this, 0, 64);
		DoorRight.addBox(-2.0F, -10.0F, -1.0F, 2, 28, 1);
		DoorRight.setTextureSize(128, 128);
		DoorRight.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(DoorRight, 0.0F, 0.0F, 0.0F);
		DoorRight.mirror = false;

		DoorBottom = new ModelRenderer(this, 16, 35);
		DoorBottom.addBox(-9.0F, 13.0F, -1.0F, 7, 5, 1);
		DoorBottom.setTextureSize(128, 128);
		DoorBottom.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(DoorBottom, 0.0F, 0.0F, 0.0F);
		DoorBottom.mirror = false;

		DoorMid = new ModelRenderer(this, 16, 37);
		DoorMid.addBox(-9.0F, -3.0F, -1.0F, 7, 1, 1);
		DoorMid.setTextureSize(128, 128);
		DoorMid.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(DoorMid, 0.0F, 0.0F, 0.0F);
		DoorMid.mirror = false;

		DoorGlass = new ModelRenderer(this, 32, 31);
		DoorGlass.addBox(-9.0F, -2.0F, -0.7F, 7, 15, 0);
		DoorGlass.setTextureSize(128, 128);
		DoorGlass.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(DoorGlass, 0.0F, 0.0F, 0.0F);
		DoorGlass.mirror = false;

		Door1 = new ModelRenderer(this, 6, 35);
		Door1.addBox(-8.5F, -10.5F, -1.0F, 2, 1, 1);
		Door1.setTextureSize(128, 128);
		Door1.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door1, 0.0F, 0.0F, 0.0F);
		Door1.mirror = false;

		Door2 = new ModelRenderer(this, 12, 35);
		Door2.addBox(-9.0F, -10.0F, -1.0F, 1, 1, 1);
		Door2.setTextureSize(128, 128);
		Door2.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door2, 0.0F, 0.0F, 0.0F);
		Door2.mirror = false;

		Door3 = new ModelRenderer(this, 6, 37);
		Door3.addBox(-9.5F, -9.5F, -1.0F, 1, 2, 1);
		Door3.setTextureSize(128, 128);
		Door3.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door3, 0.0F, 0.0F, 0.0F);
		Door3.mirror = false;

		Door4 = new ModelRenderer(this, 6, 37);
		Door4.addBox(-9.5F, -5.5F, -1.0F, 1, 2, 1);
		Door4.setTextureSize(128, 128);
		Door4.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door4, 0.0F, 0.0F, 0.0F);
		Door4.mirror = false;

		Door5 = new ModelRenderer(this, 12, 35);
		Door5.addBox(-9.0F, -4.0F, -1.0F, 1, 1, 1);
		Door5.setTextureSize(128, 128);
		Door5.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door5, 0.0F, 0.0F, 0.0F);
		Door5.mirror = false;

		Door6 = new ModelRenderer(this, 6, 35);
		Door6.addBox(-8.5F, -3.5F, -1.0F, 2, 1, 1);
		Door6.setTextureSize(128, 128);
		Door6.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door6, 0.0F, 0.0F, 0.0F);
		Door6.mirror = false;

		Door7 = new ModelRenderer(this, 6, 35);
		Door7.addBox(-4.5F, -3.5F, -1.0F, 2, 1, 1);
		Door7.setTextureSize(128, 128);
		Door7.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door7, 0.0F, 0.0F, 0.0F);
		Door7.mirror = false;

		Door8 = new ModelRenderer(this, 12, 35);
		Door8.addBox(-3.0F, -4.0F, -1.0F, 1, 1, 1);
		Door8.setTextureSize(128, 128);
		Door8.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door8, 0.0F, 0.0F, 0.0F);
		Door8.mirror = false;

		Door9 = new ModelRenderer(this, 6, 37);
		Door9.addBox(-2.5F, -5.5F, -1.0F, 1, 2, 1);
		Door9.setTextureSize(128, 128);
		Door9.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door9, 0.0F, 0.0F, 0.0F);
		Door9.mirror = false;

		Door10 = new ModelRenderer(this, 6, 37);
		Door10.addBox(-2.5F, -9.5F, -1.0F, 1, 2, 1);
		Door10.setTextureSize(128, 128);
		Door10.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door10, 0.0F, 0.0F, 0.0F);
		Door10.mirror = false;

		Door11 = new ModelRenderer(this, 12, 35);
		Door11.addBox(-3.0F, -10.0F, -1.0F, 1, 1, 1);
		Door11.setTextureSize(128, 128);
		Door11.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door11, 0.0F, 0.0F, 0.0F);
		Door11.mirror = false;

		Door12 = new ModelRenderer(this, 6, 35);
		Door12.addBox(-4.5F, -10.5F, -1.0F, 2, 1, 1);
		Door12.setTextureSize(128, 128);
		Door12.setRotationPoint(5.5F, 0.0F, -2.0F);
		setRotation(Door12, 0.0F, 0.0F, 0.0F);
		Door12.mirror = false;

		BottomBoy = new ModelRenderer(this, 6, 46);
		BottomBoy.addBox(-6.5F, 18.0F, -4.0F, 13, 5, 8);
		BottomBoy.setTextureSize(128, 128);
		BottomBoy.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(BottomBoy, 0.0F, 0.0F, 0.0F);
		BottomBoy.mirror = false;

		Leg1T = new ModelRenderer(this, 20, 41);
		Leg1T.addBox(-6.0F, 22.5F, -3.5F, 3, 1, 3);
		Leg1T.setTextureSize(128, 128);
		Leg1T.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Leg1T, 0.0F, 0.0F, 0.0F);
		Leg1T.mirror = false;

		Leg1B = new ModelRenderer(this, 6, 40);
		Leg1B.addBox(-5.5F, 23.0F, -3.0F, 2, 1, 2);
		Leg1B.setTextureSize(128, 128);
		Leg1B.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Leg1B, 0.0F, 0.0F, 0.0F);
		Leg1B.mirror = false;

		Leg2T = new ModelRenderer(this, 20, 41);
		Leg2T.addBox(3.0F, 22.5F, -3.5F, 3, 1, 3);
		Leg2T.setTextureSize(128, 128);
		Leg2T.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Leg2T, 0.0F, 0.0F, 0.0F);
		Leg2T.mirror = false;

		Leg2B = new ModelRenderer(this, 6, 40);
		Leg2B.addBox(3.5F, 23.0F, -3.0F, 2, 1, 2);
		Leg2B.setTextureSize(128, 128);
		Leg2B.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Leg2B, 0.0F, 0.0F, 0.0F);
		Leg2B.mirror = false;

		Leg3T = new ModelRenderer(this, 20, 41);
		Leg3T.addBox(3.0F, 22.5F, 0.5F, 3, 1, 3);
		Leg3T.setTextureSize(128, 128);
		Leg3T.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Leg3T, 0.0F, 0.0F, 0.0F);
		Leg3T.mirror = false;

		Leg3B = new ModelRenderer(this, 6, 40);
		Leg3B.addBox(3.5F, 23.0F, 1.0F, 2, 1, 2);
		Leg3B.setTextureSize(128, 128);
		Leg3B.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Leg3B, 0.0F, 0.0F, 0.0F);
		Leg3B.mirror = false;

		Leg4T = new ModelRenderer(this, 20, 41);
		Leg4T.addBox(-6.0F, 22.5F, 0.5F, 3, 1, 3);
		Leg4T.setTextureSize(128, 128);
		Leg4T.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Leg4T, 0.0F, 0.0F, 0.0F);
		Leg4T.mirror = false;

		Leg4B = new ModelRenderer(this, 6, 40);
		Leg4B.addBox(-5.5F, 23.0F, 1.0F, 2, 1, 2);
		Leg4B.setTextureSize(128, 128);
		Leg4B.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Leg4B, 0.0F, 0.0F, 0.0F);
		Leg4B.mirror = false;

	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		MS1.render(scale);
		MS2.render(scale);
		MS3.render(scale);
		MS4.render(scale);
		Hour1.render(scale);
		Hour2.render(scale);
		Hour3.render(scale);
		Hour4.render(scale);
		Hour5.render(scale);
		Hour6.render(scale);
		Hour7.render(scale);
		Hour8.render(scale);
		Hour9.render(scale);
		Hour10.render(scale);
		Hour11.render(scale);
		Hour12.render(scale);
		ClockCenter.render(scale);
		ClockHandMinute.render(scale);
		BottomBoy.render(scale);
		Leg1T.render(scale);
		Leg1B.render(scale);
		Leg2T.render(scale);
		Leg2B.render(scale);
		Leg3T.render(scale);
		Leg3B.render(scale);
		Leg4T.render(scale);
		Leg4B.render(scale);
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
	@Override
	public void renderClosed(float scale) {
		//this.render(null, 0, 0, 0, 0, 0, scale);
		this.renderDoor(scale, 0);
		this.renderClockHand(0.0625F);
	}
	@Override
	public void renderOpen(float scale) {
		this.render(null, 0, 0, 0, 0, 0, scale);
		this.renderDoor(scale, -50);
	}
	
	private void renderClockHand(float scale) {
		GlStateManager.pushMatrix();
		RenderHelper.renderTransformAxis();
		GlStateManager.translate(0, 0, 0);
		GlStateManager.rotate(45, 0, 0, 1);
		ClockHandHour.render(scale);
		GlStateManager.popMatrix();
	}
	
	public void renderDoor(float scale, float rotation) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(Helper.precentToPixels(5.5F), 0, -Helper.precentToPixels(2.5F));
		GlStateManager.rotate(rotation, 0, 1, 0);
		GlStateManager.translate(-Helper.precentToPixels(5.5F), 0, Helper.precentToPixels(2.5F));
		DoorTop.render(scale);
		DoorLeft.render(scale);
		DoorRight.render(scale);
		DoorBottom.render(scale);
		DoorMid.render(scale);
		DoorGlass.render(scale);
		Door1.render(scale);
		Door2.render(scale);
		Door3.render(scale);
		Door4.render(scale);
		Door5.render(scale);
		Door6.render(scale);
		Door7.render(scale);
		Door8.render(scale);
		Door9.render(scale);
		Door10.render(scale);
		Door11.render(scale);
		Door12.render(scale);
		GlStateManager.popMatrix();
	}
}