package net.tardis.mod.client.models.decoration;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRoundelDoors02 extends ModelBase {
	private final ModelRenderer RoundelDoor;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer LeftDoor;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer RightDoor;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;

	public ModelRoundelDoors02() {
		textureWidth = 96;
		textureHeight = 96;

		RoundelDoor = new ModelRenderer(this);
		RoundelDoor.setRotationPoint(0.0F, 24.0F, 0.0F);
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 21.0F, -33.0F, -8.0F, 3, 2, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 0, 0, 22.0F, -47.6F, -8.0F, 1, 3, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 23.0F, -48.0F, -8.0F, 1, 48, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 21.0F, -1.0F, -8.0F, 3, 1, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 21.0F, -48.0F, -8.0F, 3, 1, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 22.0F, -3.4F, -8.0F, 1, 3, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 21.0F, -33.0F, -8.0F, 3, 2, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 21.0F, -17.0F, -8.0F, 3, 2, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 22.0F, -15.6F, -8.0F, 1, 3, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 22.0F, -47.6F, -8.0F, 1, 3, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, -8.0F, -48.0F, -8.0F, 1, 48, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, -8.0F, -1.0F, -8.0F, 3, 1, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, -8.0F, -48.0F, -8.0F, 3, 1, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 55, 3, -7.0F, -3.4F, -8.0F, 1, 3, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, -8.0F, -33.0F, -8.0F, 3, 2, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, -8.0F, -17.0F, -8.0F, 3, 2, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 56, 6, -7.0F, -31.6F, -8.0F, 1, 3, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, -7.0F, -47.6F, -8.0F, 1, 3, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 56, 6, -7.0F, -35.3F, -8.0F, 1, 3, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 56, 6, -7.0F, -15.6F, -8.0F, 1, 3, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 56, 6, -7.0F, -19.3F, -8.0F, 1, 3, 16, 0.0F, true));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 22.0F, -19.3F, -8.0F, 1, 3, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 22.0F, -35.3F, -8.0F, 1, 3, 16, 0.0F, false));
		RoundelDoor.cubeList.add(new ModelBox(RoundelDoor, 58, 0, 22.0F, -31.6F, -8.0F, 1, 3, 16, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(21.0F, -1.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, 0.3927F);
		RoundelDoor.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 53, 10, 0.0F, -5.0F, -8.0F, 1, 5, 16, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 58, 0, 0.0F, -5.0F, -8.0F, 1, 5, 16, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(21.0F, -15.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.3927F);
		RoundelDoor.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 57, 8, 0.0F, 0.4F, -8.0F, 1, 5, 16, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(21.0F, -17.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.3927F);
		RoundelDoor.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 55, 6, 0.0F, -5.0F, -8.0F, 1, 5, 16, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(21.0F, -33.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.3927F);
		RoundelDoor.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 56, 2, 0.0F, -5.0F, -8.0F, 1, 5, 16, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(21.0F, -31.0F, 0.0F);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.3927F);
		RoundelDoor.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 55, 5, 0.0F, 0.4F, -8.0F, 1, 5, 16, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(21.0F, -47.0F, 0.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.3927F);
		RoundelDoor.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, 0.0F, 0.4F, -8.0F, 1, 5, 16, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 57, 4, 0.0F, 0.4F, -8.0F, 1, 5, 16, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-5.0F, 23.0F, 0.0F);
		setRotationAngle(bone7, 0.0F, 0.0F, -0.3927F);
		bone7.cubeList.add(new ModelBox(bone7, 52, 12, -1.0F, -5.0F, -8.0F, 1, 5, 16, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-5.0F, 7.0F, 0.0F);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.3927F);
		bone8.cubeList.add(new ModelBox(bone8, 57, 7, -1.0F, -5.0F, -8.0F, 1, 5, 16, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-5.0F, -9.0F, 0.0F);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.3927F);
		bone9.cubeList.add(new ModelBox(bone9, 55, 7, -1.0F, -5.0F, -8.0F, 1, 5, 16, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-5.0F, 9.0F, 0.0F);
		setRotationAngle(bone10, 0.0F, 0.0F, 0.3927F);
		bone10.cubeList.add(new ModelBox(bone10, 52, 9, -1.0F, 0.4F, -8.0F, 1, 5, 16, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-5.0F, -7.0F, 0.0F);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.3927F);
		bone11.cubeList.add(new ModelBox(bone11, 55, 11, -1.0F, 0.4F, -8.0F, 1, 5, 16, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-5.0F, -23.0F, 0.0F);
		setRotationAngle(bone12, 0.0F, 0.0F, 0.3927F);
		bone12.cubeList.add(new ModelBox(bone12, 55, 5, -1.0F, 0.4F, -8.0F, 1, 5, 16, 0.0F, false));

		LeftDoor = new ModelRenderer(this);
		LeftDoor.setRotationPoint(-7.0F, 24.0F, -7.8F);
		LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, 0.0F, -26.0F, 0.0F, 15, 4, 3, 0.0F, false));
		LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, 0.0F, -10.0F, 0.0F, 15, 4, 3, 0.0F, false));
		LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, 0.0F, -42.0F, 0.0F, 15, 4, 3, 0.0F, false));
		LeftDoor.cubeList.add(new ModelBox(LeftDoor, 4, 3, 2.0F, -48.0F, 0.0F, 13, 48, 3, 0.0F, false));
		LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 81, 1.0F, -15.0F, -0.1F, 14, 14, 0, 0.0F, false));
		LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 81, 1.0F, -31.0F, -0.1F, 14, 14, 0, 0.0F, false));
		LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 81, 1.0F, -47.0F, -0.1F, 14, 14, 0, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone13, 0.0F, 0.0F, -0.3927F);
		LeftDoor.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 0.0F, -0.0F, 0.0F, 3, 6, 3, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, -22.0F, 0.0F);
		setRotationAngle(bone14, 0.0F, 0.0F, -0.3927F);
		LeftDoor.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 8, 10, 0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, -38.0F, 0.0F);
		setRotationAngle(bone15, 0.0F, 0.0F, -0.3927F);
		LeftDoor.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 13, 14, 0.0F, 0.0F, 0.0F, 3, 6, 3, 0.0F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, -42.0F, 0.0F);
		setRotationAngle(bone16, 0.0F, 0.0F, 0.3927F);
		LeftDoor.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 3, 3, 0.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, -26.0F, 0.0F);
		setRotationAngle(bone17, 0.0F, 0.0F, 0.3927F);
		LeftDoor.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 2, 6, 0.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, -10.0F, 0.0F);
		setRotationAngle(bone18, 0.0F, 0.0F, 0.3927F);
		LeftDoor.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 4, 22, 0.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));

		RightDoor = new ModelRenderer(this);
		RightDoor.setRotationPoint(23.0F, 24.0F, -7.8F);
		RightDoor.cubeList.add(new ModelBox(RightDoor, 3, 4, -15.0F, -48.0F, 0.0F, 13, 48, 3, 0.0F, false));
		RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, -15.0F, -10.0F, 0.0F, 15, 4, 3, 0.0F, false));
		RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, -15.0F, -26.0F, 0.0F, 15, 4, 3, 0.0F, false));
		RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, -15.0F, -42.0F, 0.0F, 15, 4, 3, 0.0F, false));
		RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 81, -15.0F, -15.0F, -0.1F, 14, 14, 0, 0.0F, false));
		RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 81, -15.0F, -31.0F, -0.1F, 14, 14, 0, 0.0F, false));
		RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 81, -15.0F, -47.0F, -0.1F, 14, 14, 0, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone19, 0.0F, 0.0F, 0.3927F);
		RightDoor.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 9, 14, -3.0F, 0.4F, 0.0F, 3, 5, 3, 0.0F, false));

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, -22.0F, 0.0F);
		setRotationAngle(bone20, 0.0F, 0.0F, 0.3927F);
		RightDoor.addChild(bone20);
		bone20.cubeList.add(new ModelBox(bone20, 6, 9, -3.0F, -0.0F, 0.0F, 3, 6, 3, 0.0F, false));

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(0.0F, -38.0F, 0.0F);
		setRotationAngle(bone21, 0.0F, 0.0F, 0.3927F);
		RightDoor.addChild(bone21);
		bone21.cubeList.add(new ModelBox(bone21, 5, 5, -3.0F, -0.2F, 0.0F, 3, 6, 3, 0.0F, false));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.0F, -10.0F, 0.0F);
		setRotationAngle(bone22, 0.0F, 0.0F, -0.3927F);
		RightDoor.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 2, 2, -3.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(0.0F, -26.0F, 0.0F);
		setRotationAngle(bone23, 0.0F, 0.0F, -0.3927F);
		RightDoor.addChild(bone23);
		bone23.cubeList.add(new ModelBox(bone23, 6, 6, -3.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, -42.0F, 0.0F);
		setRotationAngle(bone24, 0.0F, 0.0F, -0.3927F);
		RightDoor.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 3, 7, -3.0F, -6.0F, 0.0F, 3, 6, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		RoundelDoor.render(f5);
		bone7.render(f5);
		bone8.render(f5);
		bone9.render(f5);
		bone10.render(f5);
		bone11.render(f5);
		bone12.render(f5);
		LeftDoor.render(f5);
		RightDoor.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}