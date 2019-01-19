package net.tardis.mod.client.models.exteriors;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelTardis04 extends ModelBase implements IExteriorModel {

	ModelRenderer Shape1;
	ModelRenderer Gen0;
	ModelRenderer Gen1;
	ModelRenderer Gen2;
	ModelRenderer Gen3;
	ModelRenderer Gen4;
	ModelRenderer Gen5;
	ModelRenderer Gen6;
	ModelRenderer Gen7;
	ModelRenderer Gen8;
	ModelRenderer Gen9;
	ModelRenderer Gen10;
	ModelRenderer Gen11;
	ModelRenderer Gen12;
	ModelRenderer Gen13;
	ModelRenderer Gen14;
	ModelRenderer Gen15;
	ModelRenderer Gen16;
	ModelRenderer Gen17;
	ModelRenderer Gen18;
	ModelRenderer Gen19;
	ModelRenderer Gen20;
	ModelRenderer Gen21;
	ModelRenderer Gen22;
	ModelRenderer Gen23;
	ModelRenderer Gen24;
	ModelRenderer Gen25;
	ModelRenderer Gen26;
	ModelRenderer Gen27;
	ModelRenderer Gen28;
	ModelRenderer Gen29;
	ModelRenderer Gen30;
	ModelRenderer Gen31;
	ModelRenderer Gen32;
	ModelRenderer Gen33;
	ModelRenderer Gen34;
	ModelRenderer Gen35;
	ModelRenderer Gen36;
	ModelRenderer Gen37;
	ModelRenderer Gen38;
	ModelRenderer Gen39;
	ModelRenderer Gen40;
	ModelRenderer Gen41;
	ModelRenderer Gen42;
	ModelRenderer Gen43;
	ModelRenderer Gen44;
	ModelRenderer Gen45;
	ModelRenderer Gen46;
	ModelRenderer Gen47;
	ModelRenderer Gen48;
	ModelRenderer Gen49;
	ModelRenderer Gen50;
	ModelRenderer Gen51;
	ModelRenderer Gen52;
	ModelRenderer Gen53;
	ModelRenderer Gen54;
	ModelRenderer Gen55;
	ModelRenderer Gen56;
	ModelRenderer Gen57;
	ModelRenderer Gen58;
	ModelRenderer Gen59;
	ModelRenderer Gen60;
	ModelRenderer Gen61;
	ModelRenderer Gen62;
	ModelRenderer Gen63;
	ModelRenderer Gen64;
	ModelRenderer Gen65;
	ModelRenderer Gen66;
	ModelRenderer Gen67;
	ModelRenderer Gen68;
	ModelRenderer Gen69;
	ModelRenderer Gen70;
	ModelRenderer Gen71;
	ModelRenderer Gen72;
	ModelRenderer Gen73;
	ModelRenderer Gen74;
	ModelRenderer Gen75;
	ModelRenderer Gen76;
	ModelRenderer Gen77;
	ModelRenderer Gen78;
	ModelRenderer Gen79;
	ModelRenderer Gen80;
	ModelRenderer Gen81;
	ModelRenderer Gen82;
	ModelRenderer Gen83;
	ModelRenderer Gen84;
	ModelRenderer Gen85;
	ModelRenderer Gen86;
	ModelRenderer Gen87;
	ModelRenderer Gen88;
	ModelRenderer Gen89;
	ModelRenderer Gen90;
	ModelRenderer Gen91;
	ModelRenderer Gen92;
	ModelRenderer Gen93;
	ModelRenderer Gen94;
	ModelRenderer Gen95;
	ModelRenderer Gen96;
	ModelRenderer Gen97;
	ModelRenderer Gen98;
	ModelRenderer Gen99;
	ModelRenderer Gen100;
	ModelRenderer Gen101;
	ModelRenderer Gen102;
	ModelRenderer Gen103;
	ModelRenderer Gen104;
	ModelRenderer Gen105;
	ModelRenderer Gen106;
	ModelRenderer Gen107;
	ModelRenderer Gen108;
	ModelRenderer Gen109;
	ModelRenderer Gen110;
	ModelRenderer Gen111;
	ModelRenderer Gen112;
	ModelRenderer Gen113;
	ModelRenderer Gen114;
	ModelRenderer Gen115;
	ModelRenderer Gen116;
	ModelRenderer Gen117;
	ModelRenderer Gen118;
	ModelRenderer Gen119;
	ModelRenderer Gen120;
	ModelRenderer Gen121;
	ModelRenderer Gen122;
	ModelRenderer WindowsGlow3;
	ModelRenderer WindowsGlow2;
	ModelRenderer WindowsGlow1;
	ModelRenderer Gen123;
	ModelRenderer Gen124;
	ModelRenderer Gen125;
	ModelRenderer LampGlow;
	ModelRenderer Gen126;
	ModelRightDoor04 rd = new ModelRightDoor04();
	ModelLeftDoor04 ld = new ModelLeftDoor04();

	public ModelTardis04() {

		textureWidth = 256;
		textureHeight = 256;

		Shape1 = new ModelRenderer(this, 119, 50);
		Shape1.addBox(10.1F, -12.0F, -6.5F, 1, 1, 13);
		Shape1.setTextureSize(256, 256);
		Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Shape1, 0.0F, 0.0F, 0.0F);
		Shape1.mirror = false;

		Gen0 = new ModelRenderer(this, 13, 72);
		Gen0.addBox(6.5F, -8.5F, 8.0F, 1, 30, 1);
		Gen0.setTextureSize(256, 256);
		Gen0.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen0, 0.0F, 0.0F, 0.0F);
		Gen0.mirror = false;

		Gen1 = new ModelRenderer(this, 0, 0);
		Gen1.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen1.setTextureSize(256, 256);
		Gen1.setRotationPoint(4.9F, -4.25F, 8.7F);
		setRotation(Gen1, 0.0F, 0.7853981633974483F, 0.0F);
		Gen1.mirror = false;

		Gen2 = new ModelRenderer(this, 187, 0);
		Gen2.addBox(7.7F, -8.5F, -0.5F, 1, 31, 1);
		Gen2.setTextureSize(256, 256);
		Gen2.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen2, 0.0F, 0.0F, 0.0F);
		Gen2.mirror = false;

		Gen3 = new ModelRenderer(this, 0, 0);
		Gen3.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen3.setTextureSize(256, 256);
		Gen3.setRotationPoint(3.1F, -4.25F, 8.7F);
		setRotation(Gen3, 0.0F, 0.7853981633974483F, 0.0F);
		Gen3.mirror = false;

		Gen4 = new ModelRenderer(this, 27, 75);
		Gen4.addBox(-1.5F, -8.5F, 8.0F, 3, 30, 1);
		Gen4.setTextureSize(256, 256);
		Gen4.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen4, 0.0F, 0.0F, 0.0F);
		Gen4.mirror = false;

		Gen5 = new ModelRenderer(this, 18, 75);
		Gen5.addBox(7.5F, -8.5F, -1.5F, 1, 30, 3);
		Gen5.setTextureSize(256, 256);
		Gen5.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen5, 0.0F, 0.0F, 0.0F);
		Gen5.mirror = false;

		Gen6 = new ModelRenderer(this, 0, 134);
		Gen6.addBox(-7.5F, -1.0F, 8.0F, 15, 1, 1);
		Gen6.setTextureSize(256, 256);
		Gen6.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen6, 0.0F, 0.0F, 0.0F);
		Gen6.mirror = false;

		Gen7 = new ModelRenderer(this, 0, 134);
		Gen7.addBox(-7.5F, 6.5F, 8.0F, 15, 1, 1);
		Gen7.setTextureSize(256, 256);
		Gen7.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen7, 0.0F, 0.0F, 0.0F);
		Gen7.mirror = false;

		Gen8 = new ModelRenderer(this, 0, 134);
		Gen8.addBox(-7.5F, 14.0F, 8.0F, 15, 1, 1);
		Gen8.setTextureSize(256, 256);
		Gen8.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen8, 0.0F, 0.0F, 0.0F);
		Gen8.mirror = false;

		Gen9 = new ModelRenderer(this, 0, 134);
		Gen9.addBox(-7.5F, 21.5F, 8.0F, 15, 1, 1);
		Gen9.setTextureSize(256, 256);
		Gen9.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen9, 0.0F, 0.0F, 0.0F);
		Gen9.mirror = false;

		Gen10 = new ModelRenderer(this, 162, 81);
		Gen10.addBox(8.7F, 0.0F, -6.5F, 0, 22, 13);
		Gen10.setTextureSize(256, 256);
		Gen10.setRotationPoint(0.5F, 0.0F, 0.0F);
		setRotation(Gen10, 0.0F, 0.0F, 0.0F);
		Gen10.mirror = false;

		Gen11 = new ModelRenderer(this, 193, 0);
		Gen11.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen11.setTextureSize(256, 256);
		Gen11.setRotationPoint(8.7F, -4.25F, 4.9F);
		setRotation(Gen11, 0.0F, 0.7853981633974483F, 0.0F);
		Gen11.mirror = false;

		Gen12 = new ModelRenderer(this, 0, 0);
		Gen12.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen12.setTextureSize(256, 256);
		Gen12.setRotationPoint(-4.9F, -4.25F, 8.7F);
		setRotation(Gen12, 0.0F, 0.7853981633974483F, 0.0F);
		Gen12.mirror = false;

		Gen13 = new ModelRenderer(this, 0, 0);
		Gen13.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen13.setTextureSize(256, 256);
		Gen13.setRotationPoint(-3.1F, -4.25F, 8.7F);
		setRotation(Gen13, 0.0F, 0.7853981633974483F, 0.0F);
		Gen13.mirror = false;

		Gen14 = new ModelRenderer(this, 98, 50);
		Gen14.addBox(-6.5F, -12.0F, -11.1F, 13, 1, 1);
		Gen14.setTextureSize(256, 256);
		Gen14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen14, 0.0F, 0.0F, 0.0F);
		Gen14.mirror = false;

		Gen15 = new ModelRenderer(this, 98, 50);
		Gen15.addBox(-6.5F, -12.0F, 10.1F, 13, 1, 1);
		Gen15.setTextureSize(256, 256);
		Gen15.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen15, 0.0F, 0.0F, 0.0F);
		Gen15.mirror = false;

		Gen16 = new ModelRenderer(this, 119, 50);
		Gen16.addBox(-11.1F, -12.0F, -6.5F, 1, 1, 13);
		Gen16.setTextureSize(256, 256);
		Gen16.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen16, 0.0F, 0.0F, 0.0F);
		Gen16.mirror = false;

		Gen17 = new ModelRenderer(this, 0, 134);
		Gen17.addBox(-7.5F, -8.5F, 8.0F, 15, 1, 1);
		Gen17.setTextureSize(256, 256);
		Gen17.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen17, 0.0F, 0.0F, 0.0F);
		Gen17.mirror = false;

		Gen18 = new ModelRenderer(this, 129, 72);
		Gen18.addBox(7.5F, -1.0F, -7.5F, 1, 1, 15);
		Gen18.setTextureSize(256, 256);
		Gen18.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen18, 0.0F, 0.0F, 0.0F);
		Gen18.mirror = false;

		Gen19 = new ModelRenderer(this, 129, 72);
		Gen19.addBox(7.5F, 6.5F, -7.5F, 1, 1, 15);
		Gen19.setTextureSize(256, 256);
		Gen19.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen19, 0.0F, 0.0F, 0.0F);
		Gen19.mirror = false;

		Gen20 = new ModelRenderer(this, 129, 72);
		Gen20.addBox(7.5F, 14.0F, -7.5F, 1, 1, 15);
		Gen20.setTextureSize(256, 256);
		Gen20.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen20, 0.0F, 0.0F, 0.0F);
		Gen20.mirror = false;

		Gen21 = new ModelRenderer(this, 129, 72);
		Gen21.addBox(7.5F, 21.5F, -7.5F, 1, 1, 15);
		Gen21.setTextureSize(256, 256);
		Gen21.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen21, 0.0F, 0.0F, 0.0F);
		Gen21.mirror = false;

		Gen22 = new ModelRenderer(this, 13, 72);
		Gen22.addBox(-7.5F, -8.5F, 8.0F, 1, 30, 1);
		Gen22.setTextureSize(256, 256);
		Gen22.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen22, 0.0F, 0.0F, 0.0F);
		Gen22.mirror = false;

		Gen23 = new ModelRenderer(this, 13, 72);
		Gen23.addBox(7.5F, -8.5F, 6.5F, 1, 30, 1);
		Gen23.setTextureSize(256, 256);
		Gen23.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen23, 0.0F, 0.0F, 0.0F);
		Gen23.mirror = false;

		Gen24 = new ModelRenderer(this, 187, 0);
		Gen24.addBox(-0.5F, -8.5F, 8.2F, 1, 31, 1);
		Gen24.setTextureSize(256, 256);
		Gen24.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen24, 0.0F, 0.0F, 0.0F);
		Gen24.mirror = false;

		Gen25 = new ModelRenderer(this, 129, 72);
		Gen25.addBox(7.5F, -8.5F, -7.5F, 1, 1, 15);
		Gen25.setTextureSize(256, 256);
		Gen25.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen25, 0.0F, 0.0F, 0.0F);
		Gen25.mirror = false;

		Gen26 = new ModelRenderer(this, 13, 72);
		Gen26.addBox(7.5F, -8.5F, -7.5F, 1, 30, 1);
		Gen26.setTextureSize(256, 256);
		Gen26.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen26, 0.0F, 0.0F, 0.0F);
		Gen26.mirror = false;

		Gen27 = new ModelRenderer(this, 0, 149);
		Gen27.addBox(-6.5F, 0.0F, 8.2F, 13, 22, 0);
		Gen27.setTextureSize(256, 256);
		Gen27.setRotationPoint(0.0F, 0.0F, 1.0F);
		setRotation(Gen27, 0.0F, 0.0F, 0.0F);
		Gen27.mirror = false;

		Gen28 = new ModelRenderer(this, 0, 137);
		Gen28.addBox(-6.5F, -0.5F, -0.5F, 13, 1, 1);
		Gen28.setTextureSize(256, 256);
		Gen28.setRotationPoint(0.0F, -4.25F, 8.7F);
		setRotation(Gen28, 0.7853981633974483F, 0.0F, 0.0F);
		Gen28.mirror = false;

		Gen29 = new ModelRenderer(this, 193, 0);
		Gen29.addBox(-0.5F, -0.5F, -6.5F, 1, 1, 13);
		Gen29.setTextureSize(256, 256);
		Gen29.setRotationPoint(8.7F, -4.25F, 0.0F);
		setRotation(Gen29, 0.0F, 0.0F, 0.7853981633974483F);
		Gen29.mirror = false;

		Gen30 = new ModelRenderer(this, 193, 0);
		Gen30.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen30.setTextureSize(256, 256);
		Gen30.setRotationPoint(8.7F, -4.25F, -4.9F);
		setRotation(Gen30, 0.0F, 0.7853981633974483F, 0.0F);
		Gen30.mirror = false;

		Gen31 = new ModelRenderer(this, 193, 0);
		Gen31.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen31.setTextureSize(256, 256);
		Gen31.setRotationPoint(8.7F, -4.25F, -3.1F);
		setRotation(Gen31, 0.0F, 0.7853981633974483F, 0.0F);
		Gen31.mirror = false;

		Gen32 = new ModelRenderer(this, 193, 0);
		Gen32.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen32.setTextureSize(256, 256);
		Gen32.setRotationPoint(8.7F, -4.25F, 3.1F);
		setRotation(Gen32, 0.0F, 0.7853981633974483F, 0.0F);
		Gen32.mirror = false;

		Gen33 = new ModelRenderer(this, 13, 72);
		Gen33.addBox(-9.0F, -8.5F, 6.5F, 1, 30, 1);
		Gen33.setTextureSize(256, 256);
		Gen33.setRotationPoint(-0.5F, 0.0F, 0.0F);
		setRotation(Gen33, 0.0F, 0.0F, 0.0F);
		Gen33.mirror = false;

		Gen34 = new ModelRenderer(this, 129, 72);
		Gen34.addBox(-9.0F, -8.5F, -7.5F, 1, 1, 15);
		Gen34.setTextureSize(256, 256);
		Gen34.setRotationPoint(-0.5F, 0.0F, 0.0F);
		setRotation(Gen34, 0.0F, 0.0F, 0.0F);
		Gen34.mirror = false;

		Gen35 = new ModelRenderer(this, 18, 75);
		Gen35.addBox(-9.0F, -8.5F, -1.5F, 1, 30, 3);
		Gen35.setTextureSize(256, 256);
		Gen35.setRotationPoint(-0.5F, 0.0F, 0.0F);
		setRotation(Gen35, 0.0F, 0.0F, 0.0F);
		Gen35.mirror = false;

		Gen36 = new ModelRenderer(this, 13, 72);
		Gen36.addBox(-9.0F, -8.5F, -7.5F, 1, 30, 1);
		Gen36.setTextureSize(256, 256);
		Gen36.setRotationPoint(-0.5F, 0.0F, 0.0F);
		setRotation(Gen36, 0.0F, 0.0F, 0.0F);
		Gen36.mirror = false;

		Gen37 = new ModelRenderer(this, 129, 72);
		Gen37.addBox(-9.0F, 6.5F, -7.5F, 1, 1, 15);
		Gen37.setTextureSize(256, 256);
		Gen37.setRotationPoint(-0.5F, 0.0F, 0.0F);
		setRotation(Gen37, 0.0F, 0.0F, 0.0F);
		Gen37.mirror = false;

		Gen38 = new ModelRenderer(this, 129, 72);
		Gen38.addBox(-9.0F, 14.0F, -7.5F, 1, 1, 15);
		Gen38.setTextureSize(256, 256);
		Gen38.setRotationPoint(-0.5F, 0.0F, 0.0F);
		setRotation(Gen38, 0.0F, 0.0F, 0.0F);
		Gen38.mirror = false;

		Gen39 = new ModelRenderer(this, 129, 72);
		Gen39.addBox(-9.0F, 21.5F, -7.5F, 1, 1, 15);
		Gen39.setTextureSize(256, 256);
		Gen39.setRotationPoint(-0.5F, 0.0F, 0.0F);
		setRotation(Gen39, 0.0F, 0.0F, 0.0F);
		Gen39.mirror = false;

		Gen40 = new ModelRenderer(this, 187, 0);
		Gen40.addBox(-8.7F, -8.5F, -0.5F, 1, 31, 1);
		Gen40.setTextureSize(256, 256);
		Gen40.setRotationPoint(-1.0F, 0.0F, 0.0F);
		setRotation(Gen40, 0.0F, 0.0F, 0.0F);
		Gen40.mirror = false;

		Gen41 = new ModelRenderer(this, 162, 81);
		Gen41.addBox(-8.2F, 0.0F, -6.5F, 0, 22, 13);
		Gen41.setTextureSize(256, 256);
		Gen41.setRotationPoint(-1.0F, 0.0F, 0.0F);
		setRotation(Gen41, 0.0F, 0.0F, 0.0F);
		Gen41.mirror = false;

		Gen42 = new ModelRenderer(this, 193, 0);
		Gen42.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen42.setTextureSize(256, 256);
		Gen42.setRotationPoint(-8.7F, -4.25F, 4.9F);
		setRotation(Gen42, 0.0F, 0.7853981633974483F, 0.0F);
		Gen42.mirror = false;

		Gen43 = new ModelRenderer(this, 193, 0);
		Gen43.addBox(-0.5F, -0.5F, -6.5F, 1, 1, 13);
		Gen43.setTextureSize(256, 256);
		Gen43.setRotationPoint(-8.7F, -4.25F, 0.0F);
		setRotation(Gen43, 0.0F, 0.0F, 0.7853981633974483F);
		Gen43.mirror = false;

		Gen44 = new ModelRenderer(this, 193, 0);
		Gen44.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen44.setTextureSize(256, 256);
		Gen44.setRotationPoint(-8.7F, -4.25F, 3.1F);
		setRotation(Gen44, 0.0F, 0.7853981633974483F, 0.0F);
		Gen44.mirror = false;

		Gen45 = new ModelRenderer(this, 193, 0);
		Gen45.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen45.setTextureSize(256, 256);
		Gen45.setRotationPoint(-8.7F, -4.25F, -3.1F);
		setRotation(Gen45, 0.0F, 0.7853981633974483F, 0.0F);
		Gen45.mirror = false;

		Gen46 = new ModelRenderer(this, 193, 0);
		Gen46.addBox(-0.5F, -3.5F, -0.5F, 1, 7, 1);
		Gen46.setTextureSize(256, 256);
		Gen46.setRotationPoint(-8.7F, -4.25F, -4.9F);
		setRotation(Gen46, 0.0F, 0.7853981633974483F, 0.0F);
		Gen46.mirror = false;

		Gen47 = new ModelRenderer(this, 46, 78);
		Gen47.addBox(-7.5F, -10.5F, 8.4F, 15, 1, 1);
		Gen47.setTextureSize(256, 256);
		Gen47.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen47, 0.0F, 0.0F, 0.0F);
		Gen47.mirror = false;

		Gen48 = new ModelRenderer(this, 12, 33);
		Gen48.addBox(7.5F, -13.0F, 9.0F, 1, 36, 1);
		Gen48.setTextureSize(256, 256);
		Gen48.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen48, 0.0F, 0.0F, 0.0F);
		Gen48.mirror = false;

		Gen49 = new ModelRenderer(this, 79, 106);
		Gen49.addBox(7.9F, -10.5F, -7.5F, 1, 1, 15);
		Gen49.setTextureSize(256, 256);
		Gen49.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen49, 0.0F, 0.0F, 0.0F);
		Gen49.mirror = false;

		Gen50 = new ModelRenderer(this, 46, 75);
		Gen50.addBox(-7.5F, -10.0F, 8.3F, 15, 1, 1);
		Gen50.setTextureSize(256, 256);
		Gen50.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen50, 0.0F, 0.0F, 0.0F);
		Gen50.mirror = false;

		Gen51 = new ModelRenderer(this, 46, 72);
		Gen51.addBox(-7.5F, -9.5F, 8.2F, 15, 1, 1);
		Gen51.setTextureSize(256, 256);
		Gen51.setRotationPoint(0.0F, 0.0F, 0.5F);
		setRotation(Gen51, 0.0F, 0.0F, 0.0F);
		Gen51.mirror = false;

		Gen52 = new ModelRenderer(this, 79, 72);
		Gen52.addBox(7.7F, -9.5F, -7.5F, 1, 1, 15);
		Gen52.setTextureSize(256, 256);
		Gen52.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen52, 0.0F, 0.0F, 0.0F);
		Gen52.mirror = false;

		Gen53 = new ModelRenderer(this, 79, 89);
		Gen53.addBox(7.8F, -10.0F, -7.5F, 1, 1, 15);
		Gen53.setTextureSize(256, 256);
		Gen53.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotation(Gen53, 0.0F, 0.0F, 0.0F);
		Gen53.mirror = false;

		Gen54 = new ModelRenderer(this, 79, 72);
		Gen54.addBox(-8.7F, -9.5F, -7.5F, 1, 1, 15);
		Gen54.setTextureSize(256, 256);
		Gen54.setRotationPoint(-1.0F, 0.0F, 0.0F);
		setRotation(Gen54, 0.0F, 0.0F, 0.0F);
		Gen54.mirror = false;

		Gen55 = new ModelRenderer(this, 79, 89);
		Gen55.addBox(-8.8F, -10.0F, -7.5F, 1, 1, 15);
		Gen55.setTextureSize(256, 256);
		Gen55.setRotationPoint(-1.0F, 0.0F, 0.0F);
		setRotation(Gen55, 0.0F, 0.0F, 0.0F);
		Gen55.mirror = false;

		Gen56 = new ModelRenderer(this, 79, 106);
		Gen56.addBox(-8.9F, -10.5F, -7.5F, 1, 1, 15);
		Gen56.setTextureSize(256, 256);
		Gen56.setRotationPoint(-1.0F, 0.0F, 0.0F);
		setRotation(Gen56, 0.0F, 0.0F, 0.0F);
		Gen56.mirror = false;

		Gen57 = new ModelRenderer(this, 46, 72);
		Gen57.addBox(-7.5F, -9.5F, -8.7F, 15, 1, 1);
		Gen57.setTextureSize(256, 256);
		Gen57.setRotationPoint(0.0F, 0.0F, -1.0F);
		setRotation(Gen57, 0.0F, 0.0F, 0.0F);
		Gen57.mirror = false;

		Gen58 = new ModelRenderer(this, 46, 75);
		Gen58.addBox(-7.5F, -10.0F, -8.8F, 15, 1, 1);
		Gen58.setTextureSize(256, 256);
		Gen58.setRotationPoint(0.0F, 0.0F, -1.0F);
		setRotation(Gen58, 0.0F, 0.0F, 0.0F);
		Gen58.mirror = false;

		Gen59 = new ModelRenderer(this, 46, 78);
		Gen59.addBox(-7.5F, -10.5F, -8.9F, 15, 1, 1);
		Gen59.setTextureSize(256, 256);
		Gen59.setRotationPoint(0.0F, 0.0F, -1.0F);
		setRotation(Gen59, 0.0F, 0.0F, 0.0F);
		Gen59.mirror = false;

		Gen60 = new ModelRenderer(this, 31, 67);
		Gen60.addBox(7.75F, -14.0F, 7.75F, 2, 1, 2);
		Gen60.setTextureSize(256, 256);
		Gen60.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen60, 0.0F, 0.0F, 0.0F);
		Gen60.mirror = false;

		Gen61 = new ModelRenderer(this, 33, 145);
		Gen61.addBox(-8.0F, -16.5F, -8.0F, 16, 1, 16);
		Gen61.setTextureSize(256, 256);
		Gen61.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen61, 0.0F, 0.0F, 0.0F);
		Gen61.mirror = false;

		Gen62 = new ModelRenderer(this, 33, 163);
		Gen62.addBox(-7.5F, -16.6F, -7.5F, 15, 1, 15);
		Gen62.setTextureSize(256, 256);
		Gen62.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen62, 0.0F, 0.0F, 0.0F);
		Gen62.mirror = false;

		Gen63 = new ModelRenderer(this, 33, 180);
		Gen63.addBox(-7.0F, -16.7F, -7.0F, 14, 1, 14);
		Gen63.setTextureSize(256, 256);
		Gen63.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen63, 0.0F, 0.0F, 0.0F);
		Gen63.mirror = false;

		Gen64 = new ModelRenderer(this, 33, 196);
		Gen64.addBox(-6.5F, -16.8F, -6.5F, 13, 1, 13);
		Gen64.setTextureSize(256, 256);
		Gen64.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen64, 0.0F, 0.0F, 0.0F);
		Gen64.mirror = false;

		Gen65 = new ModelRenderer(this, 33, 211);
		Gen65.addBox(-6.0F, -16.9F, -6.0F, 12, 1, 12);
		Gen65.setTextureSize(256, 256);
		Gen65.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen65, 0.0F, 0.0F, 0.0F);
		Gen65.mirror = false;

		Gen66 = new ModelRenderer(this, 33, 225);
		Gen66.addBox(-5.5F, -17.0F, -5.5F, 11, 1, 11);
		Gen66.setTextureSize(256, 256);
		Gen66.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen66, 0.0F, 0.0F, 0.0F);
		Gen66.mirror = false;

		Gen67 = new ModelRenderer(this, 0, 0);
		Gen67.addBox(-10.5F, 22.5F, -10.5F, 21, 1, 21);
		Gen67.setTextureSize(256, 256);
		Gen67.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen67, 0.0F, 0.0F, 0.0F);
		Gen67.mirror = false;

		Gen68 = new ModelRenderer(this, 112, 218);
		Gen68.addBox(-2.0F, -17.7F, -2.0F, 4, 1, 4);
		Gen68.setTextureSize(256, 256);
		Gen68.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen68, 0.0F, 0.0F, 0.0F);
		Gen68.mirror = false;

		Gen69 = new ModelRenderer(this, 129, 72);
		Gen69.addBox(-9.0F, -0.5F, -7.5F, 1, 1, 15);
		Gen69.setTextureSize(256, 256);
		Gen69.setRotationPoint(-0.5F, -0.5F, 0.0F);
		setRotation(Gen69, 0.0F, 0.0F, 0.0F);
		Gen69.mirror = false;

		Gen70 = new ModelRenderer(this, 12, 33);
		Gen70.addBox(9.0F, -13.0F, 9.0F, 1, 36, 1);
		Gen70.setTextureSize(256, 256);
		Gen70.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen70, 0.0F, 0.0F, 0.0F);
		Gen70.mirror = false;

		Gen71 = new ModelRenderer(this, 31, 67);
		Gen71.addBox(-9.75F, -14.0F, 7.75F, 2, 1, 2);
		Gen71.setTextureSize(256, 256);
		Gen71.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen71, 0.0F, 0.0F, 0.0F);
		Gen71.mirror = false;

		Gen72 = new ModelRenderer(this, 31, 67);
		Gen72.addBox(7.75F, -14.0F, -9.75F, 2, 1, 2);
		Gen72.setTextureSize(256, 256);
		Gen72.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen72, 0.0F, 0.0F, 0.0F);
		Gen72.mirror = false;

		Gen73 = new ModelRenderer(this, 31, 67);
		Gen73.addBox(-9.75F, -14.0F, -9.75F, 2, 1, 2);
		Gen73.setTextureSize(256, 256);
		Gen73.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen73, 0.0F, 0.0F, 0.0F);
		Gen73.mirror = false;

		Gen74 = new ModelRenderer(this, 103, 197);
		Gen74.addBox(-2.0F, -20.0F, 0.0F, 4, 3, 0);
		Gen74.setTextureSize(256, 256);
		Gen74.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen74, 0.0F, -0.7853981633974483F, 0.0F);
		Gen74.mirror = false;

		Gen75 = new ModelRenderer(this, 0, 33);
		Gen75.addBox(8.5F, -13.0F, 9.2F, 1, 36, 1);
		Gen75.setTextureSize(256, 256);
		Gen75.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen75, 0.0F, 0.0F, 0.0F);
		Gen75.mirror = false;

		Gen76 = new ModelRenderer(this, 6, 33);
		Gen76.addBox(9.2F, -13.0F, 8.5F, 1, 36, 1);
		Gen76.setTextureSize(256, 256);
		Gen76.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen76, 0.0F, 0.0F, 0.0F);
		Gen76.mirror = false;

		Gen77 = new ModelRenderer(this, 12, 33);
		Gen77.addBox(9.0F, -13.0F, 7.5F, 1, 36, 1);
		Gen77.setTextureSize(256, 256);
		Gen77.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen77, 0.0F, 0.0F, 0.0F);
		Gen77.mirror = false;

		Gen78 = new ModelRenderer(this, 6, 33);
		Gen78.addBox(9.2F, -13.0F, 8.0F, 1, 36, 1);
		Gen78.setTextureSize(256, 256);
		Gen78.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen78, 0.0F, 0.0F, 0.0F);
		Gen78.mirror = false;

		Gen79 = new ModelRenderer(this, 0, 33);
		Gen79.addBox(8.0F, -13.0F, 9.2F, 1, 36, 1);
		Gen79.setTextureSize(256, 256);
		Gen79.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen79, 0.0F, 0.0F, 0.0F);
		Gen79.mirror = false;

		Gen80 = new ModelRenderer(this, 12, 33);
		Gen80.addBox(9.0F, -13.0F, -8.5F, 1, 36, 1);
		Gen80.setTextureSize(256, 256);
		Gen80.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen80, 0.0F, 0.0F, 0.0F);
		Gen80.mirror = false;

		Gen81 = new ModelRenderer(this, 12, 33);
		Gen81.addBox(-10.0F, -13.0F, 7.5F, 1, 36, 1);
		Gen81.setTextureSize(256, 256);
		Gen81.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen81, 0.0F, 0.0F, 0.0F);
		Gen81.mirror = false;

		Gen82 = new ModelRenderer(this, 12, 33);
		Gen82.addBox(-10.0F, -13.0F, -8.5F, 1, 36, 1);
		Gen82.setTextureSize(256, 256);
		Gen82.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen82, 0.0F, 0.0F, 0.0F);
		Gen82.mirror = false;

		Gen83 = new ModelRenderer(this, 12, 33);
		Gen83.addBox(-8.5F, -13.0F, 9.0F, 1, 36, 1);
		Gen83.setTextureSize(256, 256);
		Gen83.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen83, 0.0F, 0.0F, 0.0F);
		Gen83.mirror = false;

		Gen84 = new ModelRenderer(this, 12, 33);
		Gen84.addBox(7.5F, -13.0F, -10.0F, 1, 36, 1);
		Gen84.setTextureSize(256, 256);
		Gen84.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen84, 0.0F, 0.0F, 0.0F);
		Gen84.mirror = false;

		Gen85 = new ModelRenderer(this, 6, 33);
		Gen85.addBox(9.2F, -13.0F, -9.0F, 1, 36, 1);
		Gen85.setTextureSize(256, 256);
		Gen85.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen85, 0.0F, 0.0F, 0.0F);
		Gen85.mirror = false;

		Gen86 = new ModelRenderer(this, 6, 33);
		Gen86.addBox(9.2F, -13.0F, -9.5F, 1, 36, 1);
		Gen86.setTextureSize(256, 256);
		Gen86.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen86, 0.0F, 0.0F, 0.0F);
		Gen86.mirror = false;

		Gen87 = new ModelRenderer(this, 12, 33);
		Gen87.addBox(9.0F, -13.0F, -10.0F, 1, 36, 1);
		Gen87.setTextureSize(256, 256);
		Gen87.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen87, 0.0F, 0.0F, 0.0F);
		Gen87.mirror = false;

		Gen88 = new ModelRenderer(this, 0, 33);
		Gen88.addBox(8.0F, -13.0F, -10.2F, 1, 36, 1);
		Gen88.setTextureSize(256, 256);
		Gen88.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen88, 0.0F, 0.0F, 0.0F);
		Gen88.mirror = false;

		Gen89 = new ModelRenderer(this, 0, 33);
		Gen89.addBox(8.5F, -13.0F, -10.2F, 1, 36, 1);
		Gen89.setTextureSize(256, 256);
		Gen89.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen89, 0.0F, 0.0F, 0.0F);
		Gen89.mirror = false;

		Gen90 = new ModelRenderer(this, 0, 33);
		Gen90.addBox(-9.5F, -13.0F, -10.2F, 1, 36, 1);
		Gen90.setTextureSize(256, 256);
		Gen90.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen90, 0.0F, 0.0F, 0.0F);
		Gen90.mirror = false;

		Gen91 = new ModelRenderer(this, 0, 33);
		Gen91.addBox(-9.0F, -13.0F, -10.2F, 1, 36, 1);
		Gen91.setTextureSize(256, 256);
		Gen91.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen91, 0.0F, 0.0F, 0.0F);
		Gen91.mirror = false;

		Gen92 = new ModelRenderer(this, 0, 33);
		Gen92.addBox(-9.0F, -13.0F, 9.2F, 1, 36, 1);
		Gen92.setTextureSize(256, 256);
		Gen92.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen92, 0.0F, 0.0F, 0.0F);
		Gen92.mirror = false;

		Gen93 = new ModelRenderer(this, 0, 33);
		Gen93.addBox(-9.5F, -13.0F, 9.2F, 1, 36, 1);
		Gen93.setTextureSize(256, 256);
		Gen93.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen93, 0.0F, 0.0F, 0.0F);
		Gen93.mirror = false;

		Gen94 = new ModelRenderer(this, 6, 33);
		Gen94.addBox(-10.2F, -13.0F, 8.5F, 1, 36, 1);
		Gen94.setTextureSize(256, 256);
		Gen94.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen94, 0.0F, 0.0F, 0.0F);
		Gen94.mirror = false;

		Gen95 = new ModelRenderer(this, 6, 33);
		Gen95.addBox(-10.2F, -13.0F, 8.0F, 1, 36, 1);
		Gen95.setTextureSize(256, 256);
		Gen95.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen95, 0.0F, 0.0F, 0.0F);
		Gen95.mirror = false;

		Gen96 = new ModelRenderer(this, 6, 33);
		Gen96.addBox(-10.2F, -13.0F, -9.5F, 1, 36, 1);
		Gen96.setTextureSize(256, 256);
		Gen96.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen96, 0.0F, 0.0F, 0.0F);
		Gen96.mirror = false;

		Gen97 = new ModelRenderer(this, 6, 33);
		Gen97.addBox(-10.2F, -13.0F, -9.0F, 1, 36, 1);
		Gen97.setTextureSize(256, 256);
		Gen97.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen97, 0.0F, 0.0F, 0.0F);
		Gen97.mirror = false;

		Gen98 = new ModelRenderer(this, 12, 33);
		Gen98.addBox(-10.0F, -13.0F, -10.0F, 1, 36, 1);
		Gen98.setTextureSize(256, 256);
		Gen98.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen98, 0.0F, 0.0F, 0.0F);
		Gen98.mirror = false;

		Gen99 = new ModelRenderer(this, 12, 33);
		Gen99.addBox(-10.0F, -13.0F, 9.0F, 1, 36, 1);
		Gen99.setTextureSize(256, 256);
		Gen99.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen99, 0.0F, 0.0F, 0.0F);
		Gen99.mirror = false;

		Gen100 = new ModelRenderer(this, 98, 27);
		Gen100.addBox(-11.0F, -12.5F, -8.5F, 22, 2, 17);
		Gen100.setTextureSize(256, 256);
		Gen100.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen100, 0.0F, 0.0F, 0.0F);
		Gen100.mirror = false;

		Gen101 = new ModelRenderer(this, 98, 0);
		Gen101.addBox(-8.5F, -12.5F, -11.0F, 17, 2, 22);
		Gen101.setTextureSize(256, 256);
		Gen101.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen101, 0.0F, 0.0F, 0.0F);
		Gen101.mirror = false;

		Gen102 = new ModelRenderer(this, 119, 50);
		Gen102.addBox(10.1F, -11.5F, -6.5F, 1, 1, 13);
		Gen102.setTextureSize(256, 256);
		Gen102.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen102, 0.0F, 0.0F, 0.0F);
		Gen102.mirror = false;

		Gen103 = new ModelRenderer(this, 98, 0);
		Gen103.addBox(-8.5F, -12.0F, -11.0F, 17, 2, 22);
		Gen103.setTextureSize(256, 256);
		Gen103.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen103, 0.0F, 0.0F, 0.0F);
		Gen103.mirror = false;

		Gen104 = new ModelRenderer(this, 98, 50);
		Gen104.addBox(-6.5F, -11.5F, -11.1F, 13, 1, 1);
		Gen104.setTextureSize(256, 256);
		Gen104.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen104, 0.0F, 0.0F, 0.0F);
		Gen104.mirror = false;

		Gen105 = new ModelRenderer(this, 98, 50);
		Gen105.addBox(-6.5F, -11.5F, 10.1F, 13, 1, 1);
		Gen105.setTextureSize(256, 256);
		Gen105.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen105, 0.0F, 0.0F, 0.0F);
		Gen105.mirror = false;

		Gen106 = new ModelRenderer(this, 119, 50);
		Gen106.addBox(-11.1F, -11.5F, -6.5F, 1, 1, 13);
		Gen106.setTextureSize(256, 256);
		Gen106.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen106, 0.0F, 0.0F, 0.0F);
		Gen106.mirror = false;

		Gen107 = new ModelRenderer(this, 98, 27);
		Gen107.addBox(-11.0F, -12.0F, -8.5F, 22, 2, 17);
		Gen107.setTextureSize(256, 256);
		Gen107.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen107, 0.0F, 0.0F, 0.0F);
		Gen107.mirror = false;

		Gen108 = new ModelRenderer(this, 12, 33);
		Gen108.addBox(-8.5F, -13.0F, -10.0F, 1, 36, 1);
		Gen108.setTextureSize(256, 256);
		Gen108.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen108, 0.0F, 0.0F, 0.0F);
		Gen108.mirror = false;

		Gen109 = new ModelRenderer(this, 33, 238);
		Gen109.addBox(-5.0F, -17.1F, -5.0F, 10, 1, 10);
		Gen109.setTextureSize(256, 256);
		Gen109.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen109, 0.0F, 0.0F, 0.0F);
		Gen109.mirror = false;

		Gen110 = new ModelRenderer(this, 75, 238);
		Gen110.addBox(-4.5F, -17.2F, -4.5F, 9, 1, 9);
		Gen110.setTextureSize(256, 256);
		Gen110.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen110, 0.0F, 0.0F, 0.0F);
		Gen110.mirror = false;

		Gen111 = new ModelRenderer(this, 79, 228);
		Gen111.addBox(-4.0F, -17.3F, -4.0F, 8, 1, 8);
		Gen111.setTextureSize(256, 256);
		Gen111.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen111, 0.0F, 0.0F, 0.0F);
		Gen111.mirror = false;

		Gen112 = new ModelRenderer(this, 83, 219);
		Gen112.addBox(-3.5F, -17.4F, -3.5F, 7, 1, 7);
		Gen112.setTextureSize(256, 256);
		Gen112.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen112, 0.0F, 0.0F, 0.0F);
		Gen112.mirror = false;

		Gen113 = new ModelRenderer(this, 33, 123);
		Gen113.addBox(-9.0F, -15.0F, -9.0F, 18, 3, 18);
		Gen113.setTextureSize(256, 256);
		Gen113.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen113, 0.0F, 0.0F, 0.0F);
		Gen113.mirror = false;

		Gen114 = new ModelRenderer(this, 95, 185);
		Gen114.addBox(1.5F, -20.5F, -1.5F, 0, 1, 3);
		Gen114.setTextureSize(256, 256);
		Gen114.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen114, 0.0F, 0.0F, 0.0F);
		Gen114.mirror = false;

		Gen115 = new ModelRenderer(this, 103, 200);
		Gen115.addBox(-1.0F, -20.0F, -1.0F, 2, 3, 2);
		Gen115.setTextureSize(256, 256);
		Gen115.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen115, 0.0F, 0.0F, 0.0F);
		Gen115.mirror = false;

		Gen116 = new ModelRenderer(this, 96, 197);
		Gen116.addBox(-1.5F, -20.0F, 0.0F, 3, 3, 0);
		Gen116.setTextureSize(256, 256);
		Gen116.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen116, 0.0F, 1.5707963267948966F, 0.0F);
		Gen116.mirror = false;

		Gen117 = new ModelRenderer(this, 95, 205);
		Gen117.addBox(-1.5F, -17.5F, -1.5F, 3, 1, 3);
		Gen117.setTextureSize(256, 256);
		Gen117.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen117, 0.0F, 0.0F, 0.0F);
		Gen117.mirror = false;

		Gen118 = new ModelRenderer(this, 98, 192);
		Gen118.addBox(-1.5F, -20.0F, -1.5F, 3, 0, 3);
		Gen118.setTextureSize(256, 256);
		Gen118.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen118, 0.0F, 0.0F, 0.0F);
		Gen118.mirror = false;

		Gen119 = new ModelRenderer(this, 98, 192);
		Gen119.addBox(-1.5F, -20.0F, -1.5F, 3, 0, 3);
		Gen119.setTextureSize(256, 256);
		Gen119.setRotationPoint(0.0F, -0.5F, 0.0F);
		setRotation(Gen119, 0.0F, 0.0F, 0.0F);
		Gen119.mirror = false;

		Gen120 = new ModelRenderer(this, 95, 190);
		Gen120.addBox(-1.5F, -20.5F, -1.5F, 3, 1, 0);
		Gen120.setTextureSize(256, 256);
		Gen120.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen120, 0.0F, 0.0F, 0.0F);
		Gen120.mirror = false;

		Gen121 = new ModelRenderer(this, 95, 190);
		Gen121.addBox(-1.5F, -20.5F, 1.5F, 3, 1, 0);
		Gen121.setTextureSize(256, 256);
		Gen121.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen121, 0.0F, 0.0F, 0.0F);
		Gen121.mirror = false;

		Gen122 = new ModelRenderer(this, 95, 185);
		Gen122.addBox(-1.5F, -20.5F, -1.5F, 0, 1, 3);
		Gen122.setTextureSize(256, 256);
		Gen122.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen122, 0.0F, 0.0F, 0.0F);
		Gen122.mirror = false;

		WindowsGlow3 = new ModelRenderer(this, 162, 72);
		WindowsGlow3.addBox(-8.2F, -8.25F, -6.5F, 0, 8, 13);
		WindowsGlow3.setTextureSize(256, 256);
		WindowsGlow3.setRotationPoint(-1.0F, 0.0F, 0.0F);
		setRotation(WindowsGlow3, 0.0F, 0.0F, 0.0F);
		WindowsGlow3.mirror = false;

		WindowsGlow2 = new ModelRenderer(this, 0, 140);
		WindowsGlow2.addBox(-6.5F, -8.25F, 8.2F, 13, 8, 0);
		WindowsGlow2.setTextureSize(256, 256);
		WindowsGlow2.setRotationPoint(0.0F, 0.0F, 1.0F);
		setRotation(WindowsGlow2, 0.0F, 0.0F, 0.0F);
		WindowsGlow2.mirror = false;

		WindowsGlow1 = new ModelRenderer(this, 162, 72);
		WindowsGlow1.addBox(8.7F, -8.25F, -6.5F, 0, 8, 13);
		WindowsGlow1.setTextureSize(256, 256);
		WindowsGlow1.setRotationPoint(0.5F, 0.0F, 0.0F);
		setRotation(WindowsGlow1, 0.0F, 0.0F, 0.0F);
		WindowsGlow1.mirror = false;

		Gen123 = new ModelRenderer(this, 87, 211);
		Gen123.addBox(-3.0F, -17.5F, -3.0F, 6, 1, 6);
		Gen123.setTextureSize(256, 256);
		Gen123.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen123, 0.0F, 0.0F, 0.0F);
		Gen123.mirror = false;

		Gen124 = new ModelRenderer(this, 112, 211);
		Gen124.addBox(-2.5F, -17.6F, -2.5F, 5, 1, 5);
		Gen124.setTextureSize(256, 256);
		Gen124.setRotationPoint(0.0F, 0.5F, 0.0F);
		setRotation(Gen124, 0.0F, 0.0F, 0.0F);
		Gen124.mirror = false;

		Gen125 = new ModelRenderer(this, 0, 0);
		Gen125.addBox(-10.5F, 23.0F, -10.5F, 21, 1, 21);
		Gen125.setTextureSize(256, 256);
		Gen125.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen125, 0.0F, 0.0F, 0.0F);
		Gen125.mirror = false;

		LampGlow = new ModelRenderer(this, 103, 197);
		LampGlow.addBox(-2.0F, -20.0F, 0.0F, 4, 3, 0);
		LampGlow.setTextureSize(256, 256);
		LampGlow.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(LampGlow, 0.0F, 0.7853981633974483F, 0.0F);
		LampGlow.mirror = false;

		Gen126 = new ModelRenderer(this, 96, 197);
		Gen126.addBox(-1.5F, -20.0F, 0.0F, 3, 3, 0);
		Gen126.setTextureSize(256, 256);
		Gen126.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen126, 0.0F, 0.0F, 0.0F);
		Gen126.mirror = false;

	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		Shape1.render(scale);
		Gen0.render(scale);
		Gen1.render(scale);
		Gen2.render(scale);
		Gen3.render(scale);
		Gen4.render(scale);
		Gen5.render(scale);
		Gen6.render(scale);
		Gen7.render(scale);
		Gen8.render(scale);
		Gen9.render(scale);
		Gen10.render(scale);
		Gen11.render(scale);
		Gen12.render(scale);
		Gen13.render(scale);
		Gen14.render(scale);
		Gen15.render(scale);
		Gen16.render(scale);
		Gen17.render(scale);
		Gen18.render(scale);
		Gen19.render(scale);
		Gen20.render(scale);
		Gen21.render(scale);
		Gen22.render(scale);
		Gen23.render(scale);
		Gen24.render(scale);
		Gen25.render(scale);
		Gen26.render(scale);
		Gen27.render(scale);
		Gen28.render(scale);
		Gen29.render(scale);
		Gen30.render(scale);
		Gen31.render(scale);
		Gen32.render(scale);
		Gen33.render(scale);
		Gen34.render(scale);
		Gen35.render(scale);
		Gen36.render(scale);
		Gen37.render(scale);
		Gen38.render(scale);
		Gen39.render(scale);
		Gen40.render(scale);
		Gen41.render(scale);
		Gen42.render(scale);
		Gen43.render(scale);
		Gen44.render(scale);
		Gen45.render(scale);
		Gen46.render(scale);
		Gen47.render(scale);
		Gen48.render(scale);
		Gen49.render(scale);
		Gen50.render(scale);
		Gen51.render(scale);
		Gen52.render(scale);
		Gen53.render(scale);
		Gen54.render(scale);
		Gen55.render(scale);
		Gen56.render(scale);
		Gen57.render(scale);
		Gen58.render(scale);
		Gen59.render(scale);
		Gen60.render(scale);
		Gen61.render(scale);
		Gen62.render(scale);
		Gen63.render(scale);
		Gen64.render(scale);
		Gen65.render(scale);
		Gen66.render(scale);
		Gen67.render(scale);
		Gen68.render(scale);
		Gen69.render(scale);
		Gen70.render(scale);
		Gen71.render(scale);
		Gen72.render(scale);
		Gen73.render(scale);
		Gen74.render(scale);
		Gen75.render(scale);
		Gen76.render(scale);
		Gen77.render(scale);
		Gen78.render(scale);
		Gen79.render(scale);
		Gen80.render(scale);
		Gen81.render(scale);
		Gen82.render(scale);
		Gen83.render(scale);
		Gen84.render(scale);
		Gen85.render(scale);
		Gen86.render(scale);
		Gen87.render(scale);
		Gen88.render(scale);
		Gen89.render(scale);
		Gen90.render(scale);
		Gen91.render(scale);
		Gen92.render(scale);
		Gen93.render(scale);
		Gen94.render(scale);
		Gen95.render(scale);
		Gen96.render(scale);
		Gen97.render(scale);
		Gen98.render(scale);
		Gen99.render(scale);
		Gen100.render(scale);
		Gen101.render(scale);
		Gen102.render(scale);
		Gen103.render(scale);
		Gen104.render(scale);
		Gen105.render(scale);
		Gen106.render(scale);
		Gen107.render(scale);
		Gen108.render(scale);
		Gen109.render(scale);
		Gen110.render(scale);
		Gen111.render(scale);
		Gen112.render(scale);
		Gen113.render(scale);
		Gen114.render(scale);
		Gen115.render(scale);
		Gen116.render(scale);
		Gen117.render(scale);
		Gen118.render(scale);
		Gen119.render(scale);
		Gen120.render(scale);
		Gen121.render(scale);
		Gen122.render(scale);
		WindowsGlow3.render(scale);
		WindowsGlow2.render(scale);
		WindowsGlow1.render(scale);
		Gen123.render(scale);
		Gen124.render(scale);
		Gen125.render(scale);
		LampGlow.render(scale);
		Gen126.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, scaleFactor, entity);
	}

	@Override
	public void renderClosed(float scale) {
		this.render(null, 0, 0, 0, 0, 0, 0.0625F);

		GlStateManager.pushMatrix();
		GlStateManager.translate(0.46875, 0.0625, -0.5625);
		rd.render(null, 0, 0, 0, 0, 0, scale);
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.46875, 0.0625, -0.5625);
		ld.render(null, 0, 0, 0, 0, 0, scale);
		GlStateManager.popMatrix();
	}

	@Override
	public void renderOpen(float scale) {
		this.render(null, 0, 0, 0, 0, 0, 0.0625F);

		GlStateManager.pushMatrix();
		GlStateManager.translate(0.46875, 0.0625, -0.5625);
		GlStateManager.rotate(80, 0, 1, 0);
		rd.render(null, 0, 0, 0, 0, 0, scale);
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.46875, 0.0625, -0.5625);
		GlStateManager.rotate(-80, 0, 1, 0);
		ld.render(null, 0, 0, 0, 0, 0, scale);
		GlStateManager.popMatrix();
	}
}