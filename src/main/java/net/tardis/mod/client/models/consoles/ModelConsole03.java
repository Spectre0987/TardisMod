package net.tardis.mod.client.models.consoles;


import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.tardis.mod.util.common.helpers.Helper;

public class ModelConsole03 extends ModelBase {

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
	ModelRenderer Shape3;
	ModelRenderer Gen16;
	ModelRenderer Shape5;
	ModelRenderer Gen17;
	ModelRenderer Gen18;
	ModelRenderer Gen19;
	ModelRenderer Gen20;
	ModelRenderer Gen21;
	ModelRenderer Gen22;
	ModelRenderer Rotor14;
	ModelRenderer Rotor22;
	ModelRenderer Rotor20;
	ModelRenderer Glow4;
	ModelRenderer Rotor8;
	ModelRenderer Glow2;
	ModelRenderer Glow6;
	ModelRenderer Rotor3;
	ModelRenderer Rotor6;
	ModelRenderer Rotor2;
	ModelRenderer Rotor10;
	ModelRenderer Rotor4;
	ModelRenderer Rotor1;
	ModelRenderer Glow1;
	ModelRenderer Glow3;
	ModelRenderer Rotor21;
	ModelRenderer Rotor12;
	ModelRenderer Rotor16;
	ModelRenderer Rotor9;
	ModelRenderer Rotor5;
	ModelRenderer Rotor7;
	ModelRenderer Gen23;
	ModelRenderer Glow5;
	ModelRenderer Gen24;
	ModelRenderer Gen25;
	ModelRenderer Gen26;
	ModelRenderer Gen27;
	ModelRenderer Gen28;
	ModelRenderer Gen29;
	ModelRenderer Gen30;
	ModelRenderer Rotor13;
	ModelRenderer Rotor11;
	ModelRenderer Rotor15;
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

	public ModelConsole03() { 

		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 0, 33);
		Shape1.addBox(-0.5F, 11.0F, -18.5F, 1, 1, 37);
		Shape1.setTextureSize(textureWidth, textureHeight);
		Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Shape1, 0.0F, -0.5235987755982988F, 0.0F);
		Shape1.mirror = false;

		Gen0 = new ModelRenderer(this, 0, 71);
		Gen0.addBox(-0.5F, 12.0F, -9.5F, 1, 12, 19);
		Gen0.setTextureSize(textureWidth, textureHeight);
		Gen0.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen0, 0.0F, -0.5235987755982988F, 0.0F);
		Gen0.mirror = false;

		Gen1 = new ModelRenderer(this, 0, 102);
		Gen1.addBox(-4.0F, 23.0F, -8.0F, 8, 1, 16);
		Gen1.setTextureSize(textureWidth, textureHeight);
		Gen1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen1, 0.0F, -1.0471975511965976F, 0.0F);
		Gen1.mirror = false;

		Gen2 = new ModelRenderer(this, 0, 102);
		Gen2.addBox(-4.0F, 23.0F, -8.0F, 8, 1, 16);
		Gen2.setTextureSize(textureWidth, textureHeight);
		Gen2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen2, 0.0F, 1.0471975511965976F, 0.0F);
		Gen2.mirror = false;

		Gen3 = new ModelRenderer(this, 0, 33);
		Gen3.addBox(-0.5F, 11.0F, -18.5F, 1, 1, 37);
		Gen3.setTextureSize(textureWidth, textureHeight);
		Gen3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen3, 0.0F, 1.5707963267948966F, 0.0F);
		Gen3.mirror = false;

		Gen4 = new ModelRenderer(this, 68, 46);
		Gen4.addBox(-0.5F, 6.0F, -7.0F, 1, 1, 1);
		Gen4.setTextureSize(textureWidth, textureHeight);
		Gen4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen4, 0.0F, -0.5235987755982988F, 0.0F);
		Gen4.mirror = false;

		Gen5 = new ModelRenderer(this, 0, 71);
		Gen5.addBox(-0.5F, 12.0F, -9.5F, 1, 12, 19);
		Gen5.setTextureSize(textureWidth, textureHeight);
		Gen5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen5, 0.0F, 1.5707963267948966F, 0.0F);
		Gen5.mirror = false;

		Gen6 = new ModelRenderer(this, 0, 71);
		Gen6.addBox(-0.5F, 12.0F, -9.5F, 1, 12, 19);
		Gen6.setTextureSize(textureWidth, textureHeight);
		Gen6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen6, 0.0F, 0.5235987755982988F, 0.0F);
		Gen6.mirror = false;

		Gen7 = new ModelRenderer(this, 68, 46);
		Gen7.addBox(-0.5F, 6.0F, -7.0F, 1, 1, 1);
		Gen7.setTextureSize(textureWidth, textureHeight);
		Gen7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen7, 0.0F, 1.5707963267948966F, 0.0F);
		Gen7.mirror = false;

		Gen8 = new ModelRenderer(this, 68, 28);
		Gen8.addBox(-3.5F, 6.0F, -6.0F, 7, 17, 1);
		Gen8.setTextureSize(textureWidth, textureHeight);
		Gen8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen8, 0.0F, -1.0471975511965976F, 0.0F);
		Gen8.mirror = false;

		Gen9 = new ModelRenderer(this, 28, 95);
		Gen9.addBox(-9.0F, 11.0F, -16.0F, 18, 1, 32);
		Gen9.setTextureSize(textureWidth, textureHeight);
		Gen9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen9, 0.0F, -1.0471975511965976F, 0.0F);
		Gen9.mirror = false;

		Gen10 = new ModelRenderer(this, 28, 95);
		Gen10.addBox(-9.0F, 11.0F, -16.0F, 18, 1, 32);
		Gen10.setTextureSize(textureWidth, textureHeight);
		Gen10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen10, 0.0F, 0.0F, 0.0F);
		Gen10.mirror = false;

		Gen11 = new ModelRenderer(this, 28, 95);
		Gen11.addBox(-9.0F, 11.0F, -16.0F, 18, 1, 32);
		Gen11.setTextureSize(textureWidth, textureHeight);
		Gen11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen11, 0.0F, 1.0471975511965976F, 0.0F);
		Gen11.mirror = false;

		Gen12 = new ModelRenderer(this, 0, 102);
		Gen12.addBox(-4.0F, 23.0F, -8.0F, 8, 1, 16);
		Gen12.setTextureSize(textureWidth, textureHeight);
		Gen12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen12, 0.0F, 0.0F, 0.0F);
		Gen12.mirror = false;

		Gen13 = new ModelRenderer(this, 68, 46);
		Gen13.addBox(-0.5F, 6.0F, -7.0F, 1, 1, 1);
		Gen13.setTextureSize(textureWidth, textureHeight);
		Gen13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen13, 0.0F, -1.5707963267948966F, 0.0F);
		Gen13.mirror = false;

		Gen14 = new ModelRenderer(this, 68, 48);
		Gen14.addBox(-1.0F, 13.0F, -6.5F, 2, 1, 1);
		Gen14.setTextureSize(textureWidth, textureHeight);
		Gen14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen14, 0.0F, -1.0471975511965976F, 0.0F);
		Gen14.mirror = false;

		Gen15 = new ModelRenderer(this, 68, 28);
		Gen15.addBox(-3.5F, 6.0F, -6.0F, 7, 17, 1);
		Gen15.setTextureSize(textureWidth, textureHeight);
		Gen15.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen15, 0.0F, -2.0943951023931953F, 0.0F);
		Gen15.mirror = false;

		Shape3 = new ModelRenderer(this, 0, 33);
		Shape3.addBox(-0.5F, -16.0F, -3.0F, 1, 7, 1);
		Shape3.setTextureSize(textureWidth, textureHeight);
		Shape3.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Shape3, 1.3613568165555772F, -1.5707963267948966F, 0.0F);
		Shape3.mirror = false;

		Gen16 = new ModelRenderer(this, 92, 0);
		Gen16.addBox(-0.5F, 4.3F, -7.4F, 1, 13, 1);
		Gen16.setTextureSize(textureWidth, textureHeight);
		Gen16.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen16, -1.1519173063162573F, -1.5707963267948966F, 0.0F);
		Gen16.mirror = false;

		Shape5 = new ModelRenderer(this, 46, 0);
		Shape5.addBox(-4.0F, -6.0F, -5.0F, 8, 1, 3);
		Shape5.setTextureSize(textureWidth, textureHeight);
		Shape5.setRotationPoint(0.0F, 23.0F, 0.0F);
		setRotation(Shape5, 0.8726646259971648F, 1.0471975511965976F, 0.0F);
		Shape5.mirror = false;

		Gen17 = new ModelRenderer(this, 46, 0);
		Gen17.addBox(-4.0F, -6.0F, -5.0F, 8, 1, 3);
		Gen17.setTextureSize(textureWidth, textureHeight);
		Gen17.setRotationPoint(0.0F, 23.0F, 0.0F);
		setRotation(Gen17, 0.8726646259971648F, 0.0F, 0.0F);
		Gen17.mirror = false;

		Gen18 = new ModelRenderer(this, 46, 0);
		Gen18.addBox(-4.0F, -6.0F, -5.0F, 8, 1, 3);
		Gen18.setTextureSize(textureWidth, textureHeight);
		Gen18.setRotationPoint(0.0F, 23.0F, 0.0F);
		setRotation(Gen18, 0.8726646259971648F, -1.0471975511965976F, 0.0F);
		Gen18.mirror = false;

		Gen19 = new ModelRenderer(this, 46, 0);
		Gen19.addBox(-4.0F, -6.0F, -5.0F, 8, 1, 3);
		Gen19.setTextureSize(textureWidth, textureHeight);
		Gen19.setRotationPoint(0.0F, 23.0F, 0.0F);
		setRotation(Gen19, 0.8726646259971648F, -2.0943951023931953F, 0.0F);
		Gen19.mirror = false;

		Gen20 = new ModelRenderer(this, 0, 33);
		Gen20.addBox(-0.5F, -18.0F, -4.0F, 1, 10, 1);
		Gen20.setTextureSize(textureWidth, textureHeight);
		Gen20.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen20, 1.3613568165555772F, -1.5707963267948966F, 0.0F);
		Gen20.mirror = false;

		Gen21 = new ModelRenderer(this, 46, 0);
		Gen21.addBox(-4.0F, -6.0F, -5.0F, 8, 1, 3);
		Gen21.setTextureSize(textureWidth, textureHeight);
		Gen21.setRotationPoint(0.0F, 23.0F, 0.0F);
		setRotation(Gen21, 0.8726646259971648F, 2.0943951023931953F, 0.0F);
		Gen21.mirror = false;

		Gen22 = new ModelRenderer(this, 46, 0);
		Gen22.addBox(-4.0F, -6.0F, -5.0F, 8, 1, 3);
		Gen22.setTextureSize(textureWidth, textureHeight);
		Gen22.setRotationPoint(0.0F, 23.0F, 0.0F);
		setRotation(Gen22, 0.8726646259971648F, 3.141592653589793F, 0.0F);
		Gen22.mirror = false;

		Rotor14 = new ModelRenderer(this, 0, 54);
		Rotor14.addBox(-0.5F, 5.0F, -4.5F, 1, 2, 9);
		Rotor14.setTextureSize(textureWidth, textureHeight);
		Rotor14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor14, 0.0F, -0.5235987755982988F, 0.0F);
		Rotor14.mirror = false;

		Rotor22 = new ModelRenderer(this, 0, 22);
		Rotor22.addBox(-3.0F, 6.5F, -5.0F, 6, 1, 10);
		Rotor22.setTextureSize(textureWidth, textureHeight);
		Rotor22.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor22, 0.0F, 2.0943951023931953F, 0.0F);
		Rotor22.mirror = false;

		Rotor20 = new ModelRenderer(this, 0, 22);
		Rotor20.addBox(-3.0F, 6.5F, -5.0F, 6, 1, 10);
		Rotor20.setTextureSize(textureWidth, textureHeight);
		Rotor20.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor20, 0.0F, 1.0471975511965976F, 0.0F);
		Rotor20.mirror = false;

		Glow4 = new ModelRenderer(this, 108, 0);
		Glow4.addBox(-0.5F, -3.0F, -2.5F, 1, 0, 2);
		Glow4.setTextureSize(textureWidth, textureHeight);
		Glow4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Glow4, 0.0F, 3.141592653589793F, 0.0F);
		Glow4.mirror = false;

		Rotor8 = new ModelRenderer(this, 96, 0);
		Rotor8.addBox(-2.0F, 3.5F, -2.0F, 4, 1, 0);
		Rotor8.setTextureSize(textureWidth, textureHeight);
		Rotor8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor8, 0.0F, -2.0943951023931953F, 0.0F);
		Rotor8.mirror = false;

		Glow2 = new ModelRenderer(this, 108, 0);
		Glow2.addBox(-0.5F, -3.0F, -2.5F, 1, 0, 2);
		Glow2.setTextureSize(textureWidth, textureHeight);
		Glow2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Glow2, 0.0F, -1.0471975511965976F, 0.0F);
		Glow2.mirror = false;

		Glow6 = new ModelRenderer(this, 108, 0);
		Glow6.addBox(-0.5F, -3.0F, -2.5F, 1, 0, 2);
		Glow6.setTextureSize(textureWidth, textureHeight);
		Glow6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Glow6, 0.0F, 1.0471975511965976F, 0.0F);
		Glow6.mirror = false;

		Rotor3 = new ModelRenderer(this, 104, 0);
		Rotor3.addBox(-1.0F, -3.0F, -1.5F, 2, 9, 0);
		Rotor3.setTextureSize(textureWidth, textureHeight);
		Rotor3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor3, 0.0F, -2.0943951023931953F, 0.0F);
		Rotor3.mirror = false;

		Rotor6 = new ModelRenderer(this, 96, 0);
		Rotor6.addBox(-2.0F, 3.5F, -2.0F, 4, 1, 0);
		Rotor6.setTextureSize(textureWidth, textureHeight);
		Rotor6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor6, 0.0F, 0.0F, 0.0F);
		Rotor6.mirror = false;

		Rotor2 = new ModelRenderer(this, 104, 0);
		Rotor2.addBox(-1.0F, -3.0F, -1.5F, 2, 9, 0);
		Rotor2.setTextureSize(textureWidth, textureHeight);
		Rotor2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor2, 0.0F, 0.0F, 0.0F);
		Rotor2.mirror = false;

		Rotor10 = new ModelRenderer(this, 96, 0);
		Rotor10.addBox(-2.0F, 3.5F, -2.0F, 4, 1, 0);
		Rotor10.setTextureSize(textureWidth, textureHeight);
		Rotor10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor10, 0.0F, 2.0943951023931953F, 0.0F);
		Rotor10.mirror = false;

		Rotor4 = new ModelRenderer(this, 104, 0);
		Rotor4.addBox(-1.0F, -3.0F, -1.5F, 2, 9, 0);
		Rotor4.setTextureSize(textureWidth, textureHeight);
		Rotor4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor4, 0.0F, 2.0943951023931953F, 0.0F);
		Rotor4.mirror = false;

		Rotor1 = new ModelRenderer(this, 72, 3);
		Rotor1.addBox(-0.5F, -2.9F, -0.5F, 1, 8, 1);
		Rotor1.setTextureSize(textureWidth, textureHeight);
		Rotor1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor1, 0.0F, 0.7853981633974483F, 0.0F);
		Rotor1.mirror = false;

		Glow1 = new ModelRenderer(this, 68, 3);
		Glow1.addBox(-0.5F, -3.0F, -2.5F, 1, 8, 1);
		Glow1.setTextureSize(textureWidth, textureHeight);
		Glow1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Glow1, 0.0F, -1.0471975511965976F, 0.0F);
		Glow1.mirror = false;

		Glow3 = new ModelRenderer(this, 68, 3);
		Glow3.addBox(-0.5F, -3.0F, -2.5F, 1, 8, 1);
		Glow3.setTextureSize(textureWidth, textureHeight);
		Glow3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Glow3, 0.0F, 3.141592653589793F, 0.0F);
		Glow3.mirror = false;

		Rotor21 = new ModelRenderer(this, 0, 22);
		Rotor21.addBox(-3.0F, 6.5F, -5.0F, 6, 1, 10);
		Rotor21.setTextureSize(textureWidth, textureHeight);
		Rotor21.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor21, 0.0F, 0.0F, 0.0F);
		Rotor21.mirror = false;

		Rotor12 = new ModelRenderer(this, 0, 54);
		Rotor12.addBox(-0.5F, 5.0F, -4.5F, 1, 2, 9);
		Rotor12.setTextureSize(textureWidth, textureHeight);
		Rotor12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor12, 0.0F, 0.5235987755982988F, 0.0F);
		Rotor12.mirror = false;

		Rotor16 = new ModelRenderer(this, 0, 54);
		Rotor16.addBox(-0.5F, 5.0F, -4.5F, 1, 2, 9);
		Rotor16.setTextureSize(textureWidth, textureHeight);
		Rotor16.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor16, 0.0F, 1.5707963267948966F, 0.0F);
		Rotor16.mirror = false;

		Rotor9 = new ModelRenderer(this, 96, 0);
		Rotor9.addBox(-2.0F, -2.5F, -2.0F, 4, 1, 0);
		Rotor9.setTextureSize(textureWidth, textureHeight);
		Rotor9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor9, 0.0F, 2.0943951023931953F, 0.0F);
		Rotor9.mirror = false;

		Rotor5 = new ModelRenderer(this, 96, 0);
		Rotor5.addBox(-2.0F, -2.5F, -2.0F, 4, 1, 0);
		Rotor5.setTextureSize(textureWidth, textureHeight);
		Rotor5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor5, 0.0F, 0.0F, 0.0F);
		Rotor5.mirror = false;

		Rotor7 = new ModelRenderer(this, 96, 0);
		Rotor7.addBox(-2.0F, -2.5F, -2.0F, 4, 1, 0);
		Rotor7.setTextureSize(textureWidth, textureHeight);
		Rotor7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor7, 0.0F, -2.0943951023931953F, 0.0F);
		Rotor7.mirror = false;

		Gen23 = new ModelRenderer(this, 0, 0);
		Gen23.addBox(-8.5F, 3.0F, -6.5F, 17, 11, 0);
		Gen23.setTextureSize(textureWidth, textureHeight);
		Gen23.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen23, -1.1344640137963142F, -1.0471975511965976F, 0.0F);
		Gen23.mirror = false;

		Glow5 = new ModelRenderer(this, 68, 3);
		Glow5.addBox(-0.5F, -3.0F, -2.5F, 1, 8, 1);
		Glow5.setTextureSize(textureWidth, textureHeight);
		Glow5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Glow5, 0.0F, 1.0471975511965976F, 0.0F);
		Glow5.mirror = false;

		Gen24 = new ModelRenderer(this, 68, 46);
		Gen24.addBox(-0.5F, 6.0F, -7.0F, 1, 1, 1);
		Gen24.setTextureSize(textureWidth, textureHeight);
		Gen24.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen24, 0.0F, 0.5235987755982988F, 0.0F);
		Gen24.mirror = false;

		Gen25 = new ModelRenderer(this, 68, 46);
		Gen25.addBox(-0.5F, 6.0F, -7.0F, 1, 1, 1);
		Gen25.setTextureSize(textureWidth, textureHeight);
		Gen25.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen25, 0.0F, 2.6179938779914944F, 0.0F);
		Gen25.mirror = false;

		Gen26 = new ModelRenderer(this, 68, 46);
		Gen26.addBox(-0.5F, 6.0F, -7.0F, 1, 1, 1);
		Gen26.setTextureSize(textureWidth, textureHeight);
		Gen26.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen26, 0.0F, -2.6179938779914944F, 0.0F);
		Gen26.mirror = false;

		Gen27 = new ModelRenderer(this, 68, 28);
		Gen27.addBox(-3.5F, 6.0F, -6.0F, 7, 17, 1);
		Gen27.setTextureSize(textureWidth, textureHeight);
		Gen27.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen27, 0.0F, 1.0471975511965976F, 0.0F);
		Gen27.mirror = false;

		Gen28 = new ModelRenderer(this, 68, 28);
		Gen28.addBox(-3.5F, 6.0F, -6.0F, 7, 17, 1);
		Gen28.setTextureSize(textureWidth, textureHeight);
		Gen28.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen28, 0.0F, 2.0943951023931953F, 0.0F);
		Gen28.mirror = false;

		Gen29 = new ModelRenderer(this, 68, 28);
		Gen29.addBox(-3.5F, 6.0F, -6.0F, 7, 17, 1);
		Gen29.setTextureSize(textureWidth, textureHeight);
		Gen29.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen29, 0.0F, 3.141592653589793F, 0.0F);
		Gen29.mirror = false;

		Gen30 = new ModelRenderer(this, 0, 33);
		Gen30.addBox(-0.5F, 11.0F, -18.5F, 1, 1, 37);
		Gen30.setTextureSize(textureWidth, textureHeight);
		Gen30.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen30, 0.0F, 0.5235987755982988F, 0.0F);
		Gen30.mirror = false;

		Rotor13 = new ModelRenderer(this, 0, 44);
		Rotor13.addBox(-2.0F, 5.0F, -4.0F, 4, 2, 8);
		Rotor13.setTextureSize(textureWidth, textureHeight);
		Rotor13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor13, 0.0F, 0.0F, 0.0F);
		Rotor13.mirror = false;

		Rotor11 = new ModelRenderer(this, 0, 44);
		Rotor11.addBox(-2.0F, 5.0F, -4.0F, 4, 2, 8);
		Rotor11.setTextureSize(textureWidth, textureHeight);
		Rotor11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor11, 0.0F, 1.0471975511965976F, 0.0F);
		Rotor11.mirror = false;

		Rotor15 = new ModelRenderer(this, 0, 44);
		Rotor15.addBox(-2.0F, 5.0F, -4.0F, 4, 2, 8);
		Rotor15.setTextureSize(textureWidth, textureHeight);
		Rotor15.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Rotor15, 0.0F, 2.0943951023931953F, 0.0F);
		Rotor15.mirror = false;

		Gen31 = new ModelRenderer(this, 68, 25);
		Gen31.addBox(-3.5F, 8.0F, -2.9F, 7, 2, 1);
		Gen31.setTextureSize(textureWidth, textureHeight);
		Gen31.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen31, -0.4363323129985824F, -1.0471975511965976F, 0.0F);
		Gen31.mirror = false;

		Gen32 = new ModelRenderer(this, 68, 48);
		Gen32.addBox(-1.0F, 13.0F, -6.5F, 2, 1, 1);
		Gen32.setTextureSize(textureWidth, textureHeight);
		Gen32.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen32, 0.0F, 0.0F, 0.0F);
		Gen32.mirror = false;

		Gen33 = new ModelRenderer(this, 68, 48);
		Gen33.addBox(-1.0F, 13.0F, -6.5F, 2, 1, 1);
		Gen33.setTextureSize(textureWidth, textureHeight);
		Gen33.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen33, 0.0F, 1.0471975511965976F, 0.0F);
		Gen33.mirror = false;

		Gen34 = new ModelRenderer(this, 68, 48);
		Gen34.addBox(-1.0F, 13.0F, -6.5F, 2, 1, 1);
		Gen34.setTextureSize(textureWidth, textureHeight);
		Gen34.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen34, 0.0F, 2.0943951023931953F, 0.0F);
		Gen34.mirror = false;

		Gen35 = new ModelRenderer(this, 68, 48);
		Gen35.addBox(-1.0F, 13.0F, -6.5F, 2, 1, 1);
		Gen35.setTextureSize(textureWidth, textureHeight);
		Gen35.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen35, 0.0F, 3.141592653589793F, 0.0F);
		Gen35.mirror = false;

		Gen36 = new ModelRenderer(this, 68, 48);
		Gen36.addBox(-1.0F, 13.0F, -6.5F, 2, 1, 1);
		Gen36.setTextureSize(textureWidth, textureHeight);
		Gen36.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen36, 0.0F, -2.0943951023931953F, 0.0F);
		Gen36.mirror = false;

		Gen37 = new ModelRenderer(this, 0, 0);
		Gen37.addBox(-8.5F, 3.0F, -6.5F, 17, 11, 0);
		Gen37.setTextureSize(textureWidth, textureHeight);
		Gen37.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen37, -1.1344640137963142F, 0.0F, 0.0F);
		Gen37.mirror = false;

		Gen38 = new ModelRenderer(this, 0, 0);
		Gen38.addBox(-8.5F, 3.0F, -6.5F, 17, 11, 0);
		Gen38.setTextureSize(textureWidth, textureHeight);
		Gen38.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen38, -1.1344640137963142F, 1.0471975511965976F, 0.0F);
		Gen38.mirror = false;

		Gen39 = new ModelRenderer(this, 0, 0);
		Gen39.addBox(-8.5F, 3.0F, -6.5F, 17, 11, 0);
		Gen39.setTextureSize(textureWidth, textureHeight);
		Gen39.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen39, -1.1344640137963142F, 2.0943951023931953F, 0.0F);
		Gen39.mirror = false;

		Gen40 = new ModelRenderer(this, 0, 0);
		Gen40.addBox(-8.5F, 3.0F, -6.5F, 17, 11, 0);
		Gen40.setTextureSize(textureWidth, textureHeight);
		Gen40.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen40, -1.1344640137963142F, 3.141592653589793F, 0.0F);
		Gen40.mirror = false;

		Gen41 = new ModelRenderer(this, 0, 0);
		Gen41.addBox(-8.5F, 3.0F, -6.5F, 17, 11, 0);
		Gen41.setTextureSize(textureWidth, textureHeight);
		Gen41.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen41, -1.1344640137963142F, -2.0943951023931953F, 0.0F);
		Gen41.mirror = false;

		Gen42 = new ModelRenderer(this, 68, 25);
		Gen42.addBox(-3.5F, 8.0F, -2.9F, 7, 2, 1);
		Gen42.setTextureSize(textureWidth, textureHeight);
		Gen42.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen42, -0.4363323129985824F, 0.0F, 0.0F);
		Gen42.mirror = false;

		Gen43 = new ModelRenderer(this, 68, 25);
		Gen43.addBox(-3.5F, 8.0F, -2.9F, 7, 2, 1);
		Gen43.setTextureSize(textureWidth, textureHeight);
		Gen43.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen43, -0.4363323129985824F, 1.0471975511965976F, 0.0F);
		Gen43.mirror = false;

		Gen44 = new ModelRenderer(this, 68, 25);
		Gen44.addBox(-3.5F, 8.0F, -2.9F, 7, 2, 1);
		Gen44.setTextureSize(textureWidth, textureHeight);
		Gen44.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen44, -0.4363323129985824F, 2.0943951023931953F, 0.0F);
		Gen44.mirror = false;

		Gen45 = new ModelRenderer(this, 68, 25);
		Gen45.addBox(-3.5F, 8.0F, -2.9F, 7, 2, 1);
		Gen45.setTextureSize(textureWidth, textureHeight);
		Gen45.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen45, -0.4363323129985824F, 3.141592653589793F, 0.0F);
		Gen45.mirror = false;

		Gen46 = new ModelRenderer(this, 68, 25);
		Gen46.addBox(-3.5F, 8.0F, -2.9F, 7, 2, 1);
		Gen46.setTextureSize(textureWidth, textureHeight);
		Gen46.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen46, -0.4363323129985824F, -2.0943951023931953F, 0.0F);
		Gen46.mirror = false;

		Gen47 = new ModelRenderer(this, 68, 28);
		Gen47.addBox(-3.5F, 6.0F, -6.0F, 7, 17, 1);
		Gen47.setTextureSize(textureWidth, textureHeight);
		Gen47.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen47, 0.0F, 0.0F, 0.0F);
		Gen47.mirror = false;

		Gen48 = new ModelRenderer(this, 82, 14);
		Gen48.addBox(-2.0F, 6.0F, -6.0F, 4, 0, 12);
		Gen48.setTextureSize(textureWidth, textureHeight);
		Gen48.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen48, 0.0F, 0.0F, 0.0F);
		Gen48.mirror = false;

		Gen49 = new ModelRenderer(this, 82, 14);
		Gen49.addBox(-2.0F, 6.0F, -6.0F, 4, 0, 12);
		Gen49.setTextureSize(textureWidth, textureHeight);
		Gen49.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen49, 0.0F, 0.5235987755982988F, 0.0F);
		Gen49.mirror = false;

		Gen50 = new ModelRenderer(this, 82, 14);
		Gen50.addBox(-2.0F, 6.0F, -6.0F, 4, 0, 12);
		Gen50.setTextureSize(textureWidth, textureHeight);
		Gen50.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen50, 0.0F, 1.0471975511965976F, 0.0F);
		Gen50.mirror = false;

		Gen51 = new ModelRenderer(this, 82, 14);
		Gen51.addBox(-2.0F, 6.0F, -6.0F, 4, 0, 12);
		Gen51.setTextureSize(textureWidth, textureHeight);
		Gen51.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen51, 0.0F, 2.6179938779914944F, 0.0F);
		Gen51.mirror = false;

		Gen52 = new ModelRenderer(this, 82, 14);
		Gen52.addBox(-2.0F, 6.0F, -6.0F, 4, 0, 12);
		Gen52.setTextureSize(textureWidth, textureHeight);
		Gen52.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen52, 0.0F, 1.5707963267948966F, 0.0F);
		Gen52.mirror = false;

		Gen53 = new ModelRenderer(this, 82, 14);
		Gen53.addBox(-2.0F, 6.0F, -6.0F, 4, 0, 12);
		Gen53.setTextureSize(textureWidth, textureHeight);
		Gen53.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Gen53, 0.0F, 2.0943951023931953F, 0.0F);
		Gen53.mirror = false;

		Gen54 = new ModelRenderer(this, 0, 33);
		Gen54.addBox(-0.5F, -16.0F, -3.0F, 1, 7, 1);
		Gen54.setTextureSize(textureWidth, textureHeight);
		Gen54.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen54, 1.3613568165555772F, -0.5235987755982988F, 0.0F);
		Gen54.mirror = false;

		Gen55 = new ModelRenderer(this, 0, 33);
		Gen55.addBox(-0.5F, -18.0F, -4.0F, 1, 10, 1);
		Gen55.setTextureSize(textureWidth, textureHeight);
		Gen55.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen55, 1.3613568165555772F, -0.5235987755982988F, 0.0F);
		Gen55.mirror = false;

		Gen56 = new ModelRenderer(this, 0, 33);
		Gen56.addBox(-0.5F, -16.0F, -3.0F, 1, 7, 1);
		Gen56.setTextureSize(textureWidth, textureHeight);
		Gen56.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen56, 1.3613568165555772F, 0.5235987755982988F, 0.0F);
		Gen56.mirror = false;

		Gen57 = new ModelRenderer(this, 0, 33);
		Gen57.addBox(-0.5F, -18.0F, -4.0F, 1, 10, 1);
		Gen57.setTextureSize(textureWidth, textureHeight);
		Gen57.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen57, 1.3613568165555772F, 0.5235987755982988F, 0.0F);
		Gen57.mirror = false;

		Gen58 = new ModelRenderer(this, 0, 33);
		Gen58.addBox(-0.5F, -16.0F, -3.0F, 1, 7, 1);
		Gen58.setTextureSize(textureWidth, textureHeight);
		Gen58.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen58, 1.3613568165555772F, 1.5707963267948966F, 0.0F);
		Gen58.mirror = false;

		Gen59 = new ModelRenderer(this, 0, 33);
		Gen59.addBox(-0.5F, -18.0F, -4.0F, 1, 10, 1);
		Gen59.setTextureSize(textureWidth, textureHeight);
		Gen59.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen59, 1.3613568165555772F, 1.5707963267948966F, 0.0F);
		Gen59.mirror = false;

		Gen60 = new ModelRenderer(this, 0, 33);
		Gen60.addBox(-0.5F, -16.0F, -3.0F, 1, 7, 1);
		Gen60.setTextureSize(textureWidth, textureHeight);
		Gen60.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen60, 1.3613568165555772F, 2.6179938779914944F, 0.0F);
		Gen60.mirror = false;

		Gen61 = new ModelRenderer(this, 0, 33);
		Gen61.addBox(-0.5F, -18.0F, -4.0F, 1, 10, 1);
		Gen61.setTextureSize(textureWidth, textureHeight);
		Gen61.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen61, 1.3613568165555772F, 2.6179938779914944F, 0.0F);
		Gen61.mirror = false;

		Gen62 = new ModelRenderer(this, 0, 33);
		Gen62.addBox(-0.5F, -16.0F, -3.0F, 1, 7, 1);
		Gen62.setTextureSize(textureWidth, textureHeight);
		Gen62.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen62, 1.3613568165555772F, -2.6179938779914944F, 0.0F);
		Gen62.mirror = false;

		Gen63 = new ModelRenderer(this, 0, 33);
		Gen63.addBox(-0.5F, -18.0F, -4.0F, 1, 10, 1);
		Gen63.setTextureSize(textureWidth, textureHeight);
		Gen63.setRotationPoint(0.0F, 12.0F, 0.0F);
		setRotation(Gen63, 1.3613568165555772F, -2.6179938779914944F, 0.0F);
		Gen63.mirror = false;

		Gen64 = new ModelRenderer(this, 92, 0);
		Gen64.addBox(-0.5F, 4.3F, -7.4F, 1, 13, 1);
		Gen64.setTextureSize(textureWidth, textureHeight);
		Gen64.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen64, -1.1519173063162573F, -0.5235987755982988F, 0.0F);
		Gen64.mirror = false;

		Gen65 = new ModelRenderer(this, 92, 0);
		Gen65.addBox(-0.5F, 4.3F, -7.4F, 1, 13, 1);
		Gen65.setTextureSize(textureWidth, textureHeight);
		Gen65.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen65, -1.1519173063162573F, 0.5235987755982988F, 0.0F);
		Gen65.mirror = false;

		Gen66 = new ModelRenderer(this, 92, 0);
		Gen66.addBox(-0.5F, 4.3F, -7.4F, 1, 13, 1);
		Gen66.setTextureSize(textureWidth, textureHeight);
		Gen66.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen66, -1.1519173063162573F, 1.5707963267948966F, 0.0F);
		Gen66.mirror = false;

		Gen67 = new ModelRenderer(this, 92, 0);
		Gen67.addBox(-0.5F, 4.3F, -7.4F, 1, 13, 1);
		Gen67.setTextureSize(textureWidth, textureHeight);
		Gen67.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen67, -1.1519173063162573F, 2.6179938779914944F, 0.0F);
		Gen67.mirror = false;

		Gen68 = new ModelRenderer(this, 92, 0);
		Gen68.addBox(-0.5F, 4.3F, -7.4F, 1, 13, 1);
		Gen68.setTextureSize(textureWidth, textureHeight);
		Gen68.setRotationPoint(0.0F, 11.0F, 0.0F);
		setRotation(Gen68, -1.1519173063162573F, -2.6179938779914944F, 0.0F);
		Gen68.mirror = false;

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
		Shape3.render(scale);
		Gen16.render(scale);
		Shape5.render(scale);
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
		
		GlStateManager.pushMatrix();
		float time = (float) ageInTicks % 60;
		float percent = (time % 30) / 30;
		GlStateManager.translate(0, ((float)Helper.precentToPixels(4) * (time < 30 ? (percent) : (1F - percent))), 0);
		Rotor14.render(scale);
		Rotor22.render(scale);
		Rotor20.render(scale);
		Rotor8.render(scale);
		Rotor3.render(scale);
		Rotor6.render(scale);
		Rotor2.render(scale);
		Rotor10.render(scale);
		Rotor4.render(scale);
		Rotor1.render(scale);
		Rotor21.render(scale);
		Rotor12.render(scale);
		Rotor16.render(scale);
		Rotor9.render(scale);
		Rotor5.render(scale);
		Rotor7.render(scale);
		Rotor13.render(scale);
		Rotor11.render(scale);
		Rotor15.render(scale);
		
		Minecraft.getMinecraft().entityRenderer.disableLightmap();
		Glow4.render(scale);
		Glow2.render(scale);
		Glow6.render(scale);
		Glow1.render(scale);
		Glow3.render(scale);
		Glow5.render(scale);
		Minecraft.getMinecraft().entityRenderer.enableLightmap();
		
		GlStateManager.popMatrix();
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
}