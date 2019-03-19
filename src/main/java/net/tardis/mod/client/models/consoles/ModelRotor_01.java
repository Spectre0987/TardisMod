package net.tardis.mod.client.models.consoles;

//Mel made this!
//Paste this code into your mod.

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.tardis.mod.util.common.helpers.Helper;

public class ModelRotor_01 extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer MiddlePlinth5;
	private final ModelRenderer BottomPlinth3;
	private final ModelRenderer BottomPlinth2;
	private final ModelRenderer Glow6;
	private final ModelRenderer YellowBit4;
	private final ModelRenderer Glow3;
	private final ModelRenderer Glow4;
	private final ModelRenderer Strip3;
	private final ModelRenderer YellowBit6;
	private final ModelRenderer Strip1;
	private final ModelRenderer YellowBit2;
	private final ModelRenderer Strip2;
	private final ModelRenderer CentralPipe;
	private final ModelRenderer Glow1;
	private final ModelRenderer Glow5;
	private final ModelRenderer BottomPlinth1;
	private final ModelRenderer MiddlePlinth1;
	private final ModelRenderer MiddlePlinth3;
	private final ModelRenderer YellowBit1;
	private final ModelRenderer YellowBit5;
	private final ModelRenderer YellowBit3;
	private final ModelRenderer Glow2;
	private final ModelRenderer MiddlePlinth6;
	private final ModelRenderer MiddlePlinth2;
	private final ModelRenderer MiddlePlinth4;

	public ModelRotor_01() {
		textureWidth = 128;
		textureHeight = 128;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		MiddlePlinth5 = new ModelRenderer(this);
		MiddlePlinth5.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(MiddlePlinth5, 0.0F, 0.5236F, 0.0F);
		root.addChild(MiddlePlinth5);
		MiddlePlinth5.cubeList.add(new ModelBox(MiddlePlinth5, 0, 54, -0.5F, 5.0F, -4.5F, 1, 2, 9, 0.0F, true));

		BottomPlinth3 = new ModelRenderer(this);
		BottomPlinth3.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(BottomPlinth3, 0.0F, -2.0944F, 0.0F);
		root.addChild(BottomPlinth3);
		BottomPlinth3.cubeList.add(new ModelBox(BottomPlinth3, 0, 22, -3.0F, 6.5F, -5.0F, 6, 1, 10, 0.0F, true));

		BottomPlinth2 = new ModelRenderer(this);
		BottomPlinth2.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(BottomPlinth2, 0.0F, -1.0472F, 0.0F);
		root.addChild(BottomPlinth2);
		BottomPlinth2.cubeList.add(new ModelBox(BottomPlinth2, 0, 22, -3.0F, 6.5F, -5.0F, 6, 1, 10, 0.0F, true));

		Glow6 = new ModelRenderer(this);
		Glow6.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(Glow6, 0.0F, -3.1416F, 0.0F);
		root.addChild(Glow6);
		Glow6.cubeList.add(new ModelBox(Glow6, 108, 0, -0.5F, -3.0F, -2.5F, 1, 0, 2, 0.0F, true));

		YellowBit4 = new ModelRenderer(this);
		YellowBit4.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(YellowBit4, 0.0F, 2.0944F, 0.0F);
		root.addChild(YellowBit4);
		YellowBit4.cubeList.add(new ModelBox(YellowBit4, 96, 0, -2.0F, 3.5F, -2.0F, 4, 1, 0, 0.0F, true));

		Glow3 = new ModelRenderer(this);
		Glow3.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(Glow3, 0.0F, 1.0472F, 0.0F);
		root.addChild(Glow3);
		Glow3.cubeList.add(new ModelBox(Glow3, 108, 0, -0.5F, -3.0F, -2.5F, 1, 0, 2, 0.0F, true));

		Glow4 = new ModelRenderer(this);
		Glow4.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(Glow4, 0.0F, -1.0472F, 0.0F);
		root.addChild(Glow4);
		Glow4.cubeList.add(new ModelBox(Glow4, 108, 0, -0.5F, -3.0F, -2.5F, 1, 0, 2, 0.0F, true));

		Strip3 = new ModelRenderer(this);
		Strip3.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(Strip3, 0.0F, 2.0944F, 0.0F);
		root.addChild(Strip3);
		Strip3.cubeList.add(new ModelBox(Strip3, 104, 0, -1.0F, -3.0F, -1.5F, 2, 9, 0, 0.0F, true));

		YellowBit6 = new ModelRenderer(this);
		YellowBit6.setRotationPoint(0.0F, -24.0F, 0.0F);
		root.addChild(YellowBit6);
		YellowBit6.cubeList.add(new ModelBox(YellowBit6, 96, 0, -2.0F, 3.5F, -2.0F, 4, 1, 0, 0.0F, true));

		Strip1 = new ModelRenderer(this);
		Strip1.setRotationPoint(0.0F, -24.0F, 0.0F);
		root.addChild(Strip1);
		Strip1.cubeList.add(new ModelBox(Strip1, 104, 0, -1.0F, -3.0F, -1.5F, 2, 9, 0, 0.0F, true));

		YellowBit2 = new ModelRenderer(this);
		YellowBit2.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(YellowBit2, 0.0F, -2.0944F, 0.0F);
		root.addChild(YellowBit2);
		YellowBit2.cubeList.add(new ModelBox(YellowBit2, 96, 0, -2.0F, 3.5F, -2.0F, 4, 1, 0, 0.0F, true));

		Strip2 = new ModelRenderer(this);
		Strip2.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(Strip2, 0.0F, -2.0944F, 0.0F);
		root.addChild(Strip2);
		Strip2.cubeList.add(new ModelBox(Strip2, 104, 0, -1.0F, -3.0F, -1.5F, 2, 9, 0, 0.0F, true));

		CentralPipe = new ModelRenderer(this);
		CentralPipe.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(CentralPipe, 0.0F, -0.7854F, 0.0F);
		root.addChild(CentralPipe);
		CentralPipe.cubeList.add(new ModelBox(CentralPipe, 72, 3, -0.5F, -2.9F, -0.5F, 1, 8, 1, 0.0F, true));

		Glow1 = new ModelRenderer(this);
		Glow1.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(Glow1, 0.0F, 1.0472F, 0.0F);
		root.addChild(Glow1);
		Glow1.cubeList.add(new ModelBox(Glow1, 68, 3, -0.5F, -3.0F, -2.5F, 1, 8, 1, 0.0F, true));

		Glow5 = new ModelRenderer(this);
		Glow5.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(Glow5, 0.0F, -3.1416F, 0.0F);
		root.addChild(Glow5);
		Glow5.cubeList.add(new ModelBox(Glow5, 68, 3, -0.5F, -3.0F, -2.5F, 1, 8, 1, 0.0F, true));

		BottomPlinth1 = new ModelRenderer(this);
		BottomPlinth1.setRotationPoint(0.0F, -24.0F, 0.0F);
		root.addChild(BottomPlinth1);
		BottomPlinth1.cubeList.add(new ModelBox(BottomPlinth1, 0, 22, -3.0F, 6.5F, -5.0F, 6, 1, 10, 0.0F, true));

		MiddlePlinth1 = new ModelRenderer(this);
		MiddlePlinth1.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(MiddlePlinth1, 0.0F, -0.5236F, 0.0F);
		root.addChild(MiddlePlinth1);
		MiddlePlinth1.cubeList.add(new ModelBox(MiddlePlinth1, 0, 54, -0.5F, 5.0F, -4.5F, 1, 2, 9, 0.0F, true));

		MiddlePlinth3 = new ModelRenderer(this);
		MiddlePlinth3.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(MiddlePlinth3, 0.0F, -1.5708F, 0.0F);
		root.addChild(MiddlePlinth3);
		MiddlePlinth3.cubeList.add(new ModelBox(MiddlePlinth3, 0, 54, -0.5F, 5.0F, -4.5F, 1, 2, 9, 0.0F, true));

		YellowBit1 = new ModelRenderer(this);
		YellowBit1.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(YellowBit1, 0.0F, -2.0944F, 0.0F);
		root.addChild(YellowBit1);
		YellowBit1.cubeList.add(new ModelBox(YellowBit1, 96, 0, -2.0F, -2.5F, -2.0F, 4, 1, 0, 0.0F, true));

		YellowBit5 = new ModelRenderer(this);
		YellowBit5.setRotationPoint(0.0F, -24.0F, 0.0F);
		root.addChild(YellowBit5);
		YellowBit5.cubeList.add(new ModelBox(YellowBit5, 96, 0, -2.0F, -2.5F, -2.0F, 4, 1, 0, 0.0F, true));

		YellowBit3 = new ModelRenderer(this);
		YellowBit3.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(YellowBit3, 0.0F, 2.0944F, 0.0F);
		root.addChild(YellowBit3);
		YellowBit3.cubeList.add(new ModelBox(YellowBit3, 96, 0, -2.0F, -2.5F, -2.0F, 4, 1, 0, 0.0F, true));

		Glow2 = new ModelRenderer(this);
		Glow2.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(Glow2, 0.0F, -1.0472F, 0.0F);
		root.addChild(Glow2);
		Glow2.cubeList.add(new ModelBox(Glow2, 68, 3, -0.5F, -3.0F, -2.5F, 1, 8, 1, 0.0F, true));

		MiddlePlinth6 = new ModelRenderer(this);
		MiddlePlinth6.setRotationPoint(0.0F, -24.0F, 0.0F);
		root.addChild(MiddlePlinth6);
		MiddlePlinth6.cubeList.add(new ModelBox(MiddlePlinth6, 0, 44, -2.0F, 5.0F, -4.0F, 4, 2, 8, 0.0F, true));

		MiddlePlinth2 = new ModelRenderer(this);
		MiddlePlinth2.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(MiddlePlinth2, 0.0F, -1.0472F, 0.0F);
		root.addChild(MiddlePlinth2);
		MiddlePlinth2.cubeList.add(new ModelBox(MiddlePlinth2, 0, 44, -2.0F, 5.0F, -4.0F, 4, 2, 8, 0.0F, true));

		MiddlePlinth4 = new ModelRenderer(this);
		MiddlePlinth4.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(MiddlePlinth4, 0.0F, -2.0944F, 0.0F);
		root.addChild(MiddlePlinth4);
		MiddlePlinth4.cubeList.add(new ModelBox(MiddlePlinth4, 0, 44, -2.0F, 5.0F, -4.0F, 4, 2, 8, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		int time = (int)f2 % 60;
		float percent = (f2 % 30) / 30;
		root.offsetY = Helper.precentToPixels(4) * (time < 30 ? 1F - percent : percent);
		root.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}