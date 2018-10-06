package net.tardis.mod.client.models.entity.dalek;


import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class ModelDalekScaro extends ModelDalekBase {

	ModelRenderer Dome1;
	ModelRenderer Dome2;
	ModelRenderer Dome3;
	ModelRenderer Grille;
	ModelRenderer NeckRing1;
	ModelRenderer NeckRing2;
	ModelRenderer NeckRing3;
	ModelRenderer EarGlow1;
	ModelRenderer EarGlow2;
	ModelRenderer Eyestalk;
	ModelRenderer EyeRings;
	ModelRenderer Eye;
	ModelRenderer Shoulders;
	ModelRenderer Division1;
	ModelRenderer Divison2;
	ModelRenderer Division3;
	ModelRenderer Divison4;
	ModelRenderer Division5;
	ModelRenderer Division6;
	ModelRenderer Division7;
	ModelRenderer Division8;
	ModelRenderer Division9;
	ModelRenderer Division10;
	ModelRenderer Division11;
	ModelRenderer Division12;
	ModelRenderer Division13;
	ModelRenderer Division14;
	ModelRenderer GunBoxLeft;
	ModelRenderer GunBoxRight;
	ModelRenderer Gunstick;
	ModelRenderer Gun1;
	ModelRenderer Gun2;
	ModelRenderer Gun3;
	ModelRenderer Gun4;
	ModelRenderer GunBoxSecondaryLeft;
	ModelRenderer GunBoxSecondaryRight;
	ModelRenderer PlungerStick;
	ModelRenderer Plunger1;
	ModelRenderer Plunger2;
	ModelRenderer Plunger3;
	ModelRenderer Plunger4;
	ModelRenderer Skirt1;
	ModelRenderer Skirt2;
	ModelRenderer Skirt3;
	ModelRenderer Skirt4;
	ModelRenderer Skirt5;
	ModelRenderer Skirt6;
	ModelRenderer Skirt7;
	ModelRenderer Base1;
	ModelRenderer Base2;
	ModelRenderer Base3;
	ModelRenderer Base4;
	ModelRenderer Base5;
	ModelRenderer Base6;
	ModelRenderer Base7;
	ModelRenderer Base8;
	ModelRenderer Base9;
	ModelRenderer Base10;
	ModelRenderer Base11;

	public ModelDalekScaro() { 

		textureWidth = 128;
		textureHeight = 128;

		Dome1 = new ModelRenderer(this, 0, 0);
		Dome1.addBox(-3.5F, -2.0F, -3.5F, 7, 1, 7);
		Dome1.setTextureSize(128, 128);
		Dome1.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(Dome1, 0.0F, 0.0F, 0.0F);
		Dome1.mirror = false;

		Dome2 = new ModelRenderer(this, 0, 8);
		Dome2.addBox(-4.0F, -1.0F, -4.0F, 8, 2, 8);
		Dome2.setTextureSize(128, 128);
		Dome2.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(Dome2, 0.0F, 0.0F, 0.0F);
		Dome2.mirror = false;

		Dome3 = new ModelRenderer(this, 0, 18);
		Dome3.addBox(-4.5F, 0.0F, -4.5F, 9, 2, 9);
		Dome3.setTextureSize(128, 128);
		Dome3.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(Dome3, 0.0F, 0.0F, 0.0F);
		Dome3.mirror = false;

		Grille = new ModelRenderer(this, 0, 29);
		Grille.addBox(-4.0F, 2.0F, -4.0F, 8, 4, 8);
		Grille.setTextureSize(128, 128);
		Grille.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(Grille, 0.0F, 0.0F, 0.0F);
		Grille.mirror = false;

		NeckRing1 = new ModelRenderer(this, 0, 41);
		NeckRing1.addBox(-4.5F, 3.0F, -4.5F, 9, 1, 9);
		NeckRing1.setTextureSize(128, 128);
		NeckRing1.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(NeckRing1, 0.0F, 0.0F, 0.0F);
		NeckRing1.mirror = false;

		NeckRing2 = new ModelRenderer(this, 0, 41);
		NeckRing2.addBox(-4.5F, 4.5F, -4.5F, 9, 1, 9);
		NeckRing2.setTextureSize(128, 128);
		NeckRing2.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(NeckRing2, 0.0F, 0.0F, 0.0F);
		NeckRing2.mirror = false;

		NeckRing3 = new ModelRenderer(this, 0, 41);
		NeckRing3.addBox(-4.5F, 6.0F, -4.5F, 9, 1, 9);
		NeckRing3.setTextureSize(128, 128);
		NeckRing3.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(NeckRing3, 0.0F, 0.0F, 0.0F);
		NeckRing3.mirror = false;

		EarGlow1 = new ModelRenderer(this, 28, 0);
		EarGlow1.addBox(-2.5F, -4.0F, -0.5F, 1, 2, 1);
		EarGlow1.setTextureSize(128, 128);
		EarGlow1.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(EarGlow1, 0.0F, 0.0F, -0.7853981633974483F);
		EarGlow1.mirror = false;

		EarGlow2 = new ModelRenderer(this, 28, 0);
		EarGlow2.addBox(1.5F, -4.0F, -0.5F, 1, 2, 1);
		EarGlow2.setTextureSize(128, 128);
		EarGlow2.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(EarGlow2, 0.0F, 0.0F, 0.7853981633974483F);
		EarGlow2.mirror = false;

		Eyestalk = new ModelRenderer(this, 32, 0);
		Eyestalk.addBox(-0.5F, -2.0F, -8.5F, 1, 1, 5);
		Eyestalk.setTextureSize(128, 128);
		Eyestalk.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotation(Eyestalk, 0.0F, 0.0F, 0.0F);
		Eyestalk.mirror = false;

		EyeRings = new ModelRenderer(this, 44, 0);
		EyeRings.addBox(-1.0F, -2.0F, -7.0F, 2, 2, 1);
		EyeRings.setTextureSize(128, 128);
		EyeRings.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(EyeRings, 0.0F, 0.0F, 0.0F);
		EyeRings.mirror = false;

		Eye = new ModelRenderer(this, 44, 3);
		Eye.addBox(-1.0F, -2.0F, -9.5F, 2, 2, 1);
		Eye.setTextureSize(128, 128);
		Eye.setRotationPoint(0.0F, -2.5F, 0.0F);
		setRotation(Eye, 0.0F, 0.0F, 0.0F);
		Eye.mirror = false;

		Shoulders = new ModelRenderer(this, 0, 51);
		Shoulders.addBox(-5.0F, 0.0F, 0.0F, 10, 6, 10);
		Shoulders.setTextureSize(128, 128);
		Shoulders.setRotationPoint(0.0F, 4.0F, -5.0F);
		setRotation(Shoulders, 0.0F, 0.0F, 0.0F);
		Shoulders.mirror = false;

		Division1 = new ModelRenderer(this, 0, 67);
		Division1.addBox(-5.5F, 1.5F, -5.5F, 11, 1, 11);
		Division1.setTextureSize(128, 128);
		Division1.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(Division1, 0.0F, 0.0F, 0.0F);
		Division1.mirror = false;

		Divison2 = new ModelRenderer(this, 0, 79);
		Divison2.addBox(-5.5F, 2.5F, -4.0F, 11, 1, 9);
		Divison2.setTextureSize(128, 128);
		Divison2.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(Divison2, 0.0F, 0.0F, 0.0F);
		Divison2.mirror = false;

		Division3 = new ModelRenderer(this, 97, 88);
		Division3.addBox(-5.5F, 2.5F, 4.5F, 11, 1, 1);
		Division3.setTextureSize(128, 128);
		Division3.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(Division3, 0.0F, 0.0F, 0.0F);
		Division3.mirror = false;

		Divison4 = new ModelRenderer(this, 44, 29);
		Divison4.addBox(-4.5F, 6.0F, -7.0F, 9, 1, 13);
		Divison4.setTextureSize(128, 128);
		Divison4.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(Divison4, 0.0F, 0.0F, 0.0F);
		Divison4.mirror = false;

		Division5 = new ModelRenderer(this, 45, 26);
		Division5.addBox(0.0F, 6.0F, 0.0F, 1, 1, 2);
		Division5.setTextureSize(128, 128);
		Division5.setRotationPoint(-4.5F, 3.0F, -7.0F);
		setRotation(Division5, 0.0F, -0.4363323129985824F, 0.0F);
		Division5.mirror = false;

		Division6 = new ModelRenderer(this, 45, 26);
		Division6.addBox(0.0F, 6.0F, -2.0F, 1, 1, 2);
		Division6.setTextureSize(128, 128);
		Division6.setRotationPoint(-6.0F, 3.0F, -4.0F);
		setRotation(Division6, 0.0F, -0.4886921905584123F, 0.0F);
		Division6.mirror = false;

		Division7 = new ModelRenderer(this, 45, 26);
		Division7.addBox(-1.0F, 6.0F, 0.0F, 1, 1, 2);
		Division7.setTextureSize(128, 128);
		Division7.setRotationPoint(4.5F, 3.0F, -7.0F);
		setRotation(Division7, 0.0F, 0.4363323129985824F, 0.0F);
		Division7.mirror = false;

		Division8 = new ModelRenderer(this, 45, 26);
		Division8.addBox(-1.0F, 6.0F, -2.0F, 1, 1, 2);
		Division8.setTextureSize(128, 128);
		Division8.setRotationPoint(6.0F, 3.0F, -4.0F);
		setRotation(Division8, 0.0F, 0.4886921905584123F, 0.0F);
		Division8.mirror = false;

		Division9 = new ModelRenderer(this, 73, 16);
		Division9.addBox(-6.0F, 5.0F, -4.0F, 12, 2, 9);
		Division9.setTextureSize(128, 128);
		Division9.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(Division9, 0.0F, 0.0F, 0.0F);
		Division9.mirror = false;

		Division10 = new ModelRenderer(this, 32, 29);
		Division10.addBox(0.0F, 0.0F, -1.0F, 1, 2, 1);
		Division10.setTextureSize(128, 128);
		Division10.setRotationPoint(-6.0F, 8.0F, 5.0F);
		setRotation(Division10, 0.0F, -0.5235987755982988F, 0.0F);
		Division10.mirror = false;

		Division11 = new ModelRenderer(this, 32, 29);
		Division11.addBox(-1.0F, 0.0F, -1.0F, 1, 2, 1);
		Division11.setTextureSize(128, 128);
		Division11.setRotationPoint(-4.5F, 8.0F, 6.0F);
		setRotation(Division11, 0.0F, -0.6457718232379019F, 0.0F);
		Division11.mirror = false;

		Division12 = new ModelRenderer(this, 32, 29);
		Division12.addBox(-1.0F, 0.0F, -1.0F, 1, 2, 1);
		Division12.setTextureSize(128, 128);
		Division12.setRotationPoint(6.0F, 8.0F, 5.0F);
		setRotation(Division12, 0.0F, 0.5235987755982988F, 0.0F);
		Division12.mirror = false;

		Division13 = new ModelRenderer(this, 32, 29);
		Division13.addBox(0.0F, 0.0F, -1.0F, 1, 2, 1);
		Division13.setTextureSize(128, 128);
		Division13.setRotationPoint(4.5F, 8.0F, 6.0F);
		setRotation(Division13, 0.0F, 0.6457718232379019F, 0.0F);
		Division13.mirror = false;

		Division14 = new ModelRenderer(this, 73, 27);
		Division14.addBox(-4.5F, 5.0F, 5.0F, 9, 1, 1);
		Division14.setTextureSize(128, 128);
		Division14.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(Division14, 0.0F, 0.0F, 0.0F);
		Division14.mirror = false;

		GunBoxLeft = new ModelRenderer(this, 32, 6);
		GunBoxLeft.addBox(-5.0F, 3.0F, -7.0F, 3, 3, 2);
		GunBoxLeft.setTextureSize(128, 128);
		GunBoxLeft.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(GunBoxLeft, 0.0F, 0.0F, 0.0F);
		GunBoxLeft.mirror = false;

		GunBoxRight = new ModelRenderer(this, 32, 6);
		GunBoxRight.addBox(2.0F, 3.0F, -7.0F, 3, 3, 2);
		GunBoxRight.setTextureSize(128, 128);
		GunBoxRight.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(GunBoxRight, 0.0F, 0.0F, 0.0F);
		GunBoxRight.mirror = false;

		Gunstick = new ModelRenderer(this, 99, 64);
		Gunstick.addBox(-0.5F, -0.5F, -7.0F, 1, 1, 7);
		Gunstick.setTextureSize(128, 128);
		Gunstick.setRotationPoint(3.5F, 7.5F, -7.5F);
		setRotation(Gunstick, 0.0F, 0.0F, 0.0F);
		Gunstick.mirror = false;

		Gun1 = new ModelRenderer(this, 105, 56);
		Gun1.addBox(0.0F, -1.0F, -6.5F, 0, 2, 6);
		Gun1.setTextureSize(128, 128);
		Gun1.setRotationPoint(3.5F, 7.5F, -7.5F);
		setRotation(Gun1, 0.0F, 0.0F, 0.0F);
		Gun1.mirror = false;

		Gun2 = new ModelRenderer(this, 105, 56);
		Gun2.addBox(0.0F, -1.0F, -6.5F, 0, 2, 6);
		Gun2.setTextureSize(128, 128);
		Gun2.setRotationPoint(3.5F, 7.5F, -7.5F);
		setRotation(Gun2, 0.0F, 0.0F, -0.7853981633974483F);
		Gun2.mirror = false;

		Gun3 = new ModelRenderer(this, 105, 56);
		Gun3.addBox(0.0F, -1.0F, -6.5F, 0, 2, 6);
		Gun3.setTextureSize(128, 128);
		Gun3.setRotationPoint(3.5F, 7.5F, -7.5F);
		setRotation(Gun3, 0.0F, 0.0F, 1.5707963267948966F);
		Gun3.mirror = false;

		Gun4 = new ModelRenderer(this, 105, 56);
		Gun4.addBox(0.0F, -1.0F, -6.5F, 0, 2, 6);
		Gun4.setTextureSize(128, 128);
		Gun4.setRotationPoint(3.5F, 7.5F, -7.5F);
		setRotation(Gun4, 0.0F, 0.0F, 0.7853981633974483F);
		Gun4.mirror = false;

		GunBoxSecondaryLeft = new ModelRenderer(this, 89, 77);
		GunBoxSecondaryLeft.addBox(-4.5F, 3.5F, -7.5F, 2, 2, 1);
		GunBoxSecondaryLeft.setTextureSize(128, 128);
		GunBoxSecondaryLeft.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(GunBoxSecondaryLeft, 0.0F, 0.0F, 0.0F);
		GunBoxSecondaryLeft.mirror = false;

		GunBoxSecondaryRight = new ModelRenderer(this, 89, 77);
		GunBoxSecondaryRight.addBox(2.5F, 3.5F, -7.5F, 2, 2, 1);
		GunBoxSecondaryRight.setTextureSize(128, 128);
		GunBoxSecondaryRight.setRotationPoint(0.0F, 3.0F, 0.0F);
		setRotation(GunBoxSecondaryRight, 0.0F, 0.0F, 0.0F);
		GunBoxSecondaryRight.mirror = false;

		PlungerStick = new ModelRenderer(this, 99, 77);
		PlungerStick.addBox(-0.5F, -0.5F, -9.0F, 1, 1, 10);
		PlungerStick.setTextureSize(128, 128);
		PlungerStick.setRotationPoint(-3.5F, 7.5F, -7.5F);
		setRotation(PlungerStick, 0.0F, 0.0F, 0.0F);
		PlungerStick.mirror = false;

		Plunger1 = new ModelRenderer(this, 121, 77);
		Plunger1.addBox(-1.25F, -1.25F, -10.0F, 2, 2, 1);
		Plunger1.setTextureSize(128, 128);
		Plunger1.setRotationPoint(-3.5F, 7.5F, -7.5F);
		setRotation(Plunger1, 0.0F, 0.0F, 0.0F);
		Plunger1.mirror = false;

		Plunger2 = new ModelRenderer(this, 121, 77);
		Plunger2.addBox(-0.75F, -1.25F, -10.0F, 2, 2, 1);
		Plunger2.setTextureSize(128, 128);
		Plunger2.setRotationPoint(-3.5F, 7.5F, -7.5F);
		setRotation(Plunger2, 0.0F, 0.0F, 0.0F);
		Plunger2.mirror = false;

		Plunger3 = new ModelRenderer(this, 121, 77);
		Plunger3.addBox(-0.75F, -0.75F, -10.0F, 2, 2, 1);
		Plunger3.setTextureSize(128, 128);
		Plunger3.setRotationPoint(-3.5F, 7.5F, -7.5F);
		setRotation(Plunger3, 0.0F, 0.0F, 0.0F);
		Plunger3.mirror = false;

		Plunger4 = new ModelRenderer(this, 121, 77);
		Plunger4.addBox(-1.25F, -0.75F, -10.0F, 2, 2, 1);
		Plunger4.setTextureSize(128, 128);
		Plunger4.setRotationPoint(-3.5F, 7.5F, -7.5F);
		setRotation(Plunger4, 0.0F, 0.0F, 0.0F);
		Plunger4.mirror = false;

		Skirt1 = new ModelRenderer(this, 0, 89);
		Skirt1.addBox(-4.0F, 7.0F, 0.0F, 8, 13, 1);
		Skirt1.setTextureSize(128, 128);
		Skirt1.setRotationPoint(0.0F, 3.0F, -5.0F);
		setRotation(Skirt1, -0.22689280275926282F, 0.0F, 0.0F);
		Skirt1.mirror = false;

		Skirt2 = new ModelRenderer(this, 0, 103);
		Skirt2.addBox(-4.5F, 6.5F, 1.0F, 9, 13, 2);
		Skirt2.setTextureSize(128, 128);
		Skirt2.setRotationPoint(0.0F, 3.0F, -5.0F);
		setRotation(Skirt2, -0.22689280275926282F, 0.0F, 0.0F);
		Skirt2.mirror = false;

		Skirt3 = new ModelRenderer(this, 22, 112);
		Skirt3.addBox(-5.5F, 6.5F, 3.0F, 11, 13, 3);
		Skirt3.setTextureSize(128, 128);
		Skirt3.setRotationPoint(0.0F, 3.0F, -5.0F);
		setRotation(Skirt3, -0.22689280275926282F, 0.0F, 0.0F);
		Skirt3.mirror = false;

		Skirt4 = new ModelRenderer(this, 51, 0);
		Skirt4.addBox(0.0F, 7.0F, -3.5F, 2, 13, 8);
		Skirt4.setTextureSize(128, 128);
		Skirt4.setRotationPoint(-5.0F, 3.0F, 0.0F);
		setRotation(Skirt4, 0.0F, 0.0F, 0.08726646259971647F);
		Skirt4.mirror = false;

		Skirt5 = new ModelRenderer(this, 51, 0);
		Skirt5.addBox(-2.0F, 7.0F, -3.5F, 2, 13, 8);
		Skirt5.setTextureSize(128, 128);
		Skirt5.setRotationPoint(5.0F, 3.0F, 0.0F);
		setRotation(Skirt5, 0.0F, 0.0F, -0.08726646259971647F);
		Skirt5.mirror = false;

		Skirt6 = new ModelRenderer(this, 71, 0);
		Skirt6.addBox(-5.5F, -1.0F, -2.0F, 11, 14, 2);
		Skirt6.setTextureSize(128, 128);
		Skirt6.setRotationPoint(0.0F, 10.0F, 5.0F);
		setRotation(Skirt6, 0.08726646259971647F, 0.0F, 0.0F);
		Skirt6.mirror = false;

		Skirt7 = new ModelRenderer(this, 97, 0);
		Skirt7.addBox(-4.0F, -1.0F, -1.5F, 8, 14, 1);
		Skirt7.setTextureSize(128, 128);
		Skirt7.setRotationPoint(0.0F, 10.0F, 6.0F);
		setRotation(Skirt7, 0.08726646259971647F, 0.0F, 0.0F);
		Skirt7.mirror = false;

		Base1 = new ModelRenderer(this, 44, 43);
		Base1.addBox(-3.5F, 22.0F, -10.5F, 7, 2, 6);
		Base1.setTextureSize(128, 128);
		Base1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Base1, 0.0F, 0.0F, 0.0F);
		Base1.mirror = false;

		Base2 = new ModelRenderer(this, 44, 51);
		Base2.addBox(-6.0F, 0.0F, 0.0F, 6, 2, 4);
		Base2.setTextureSize(128, 128);
		Base2.setRotationPoint(-3.5F, 22.0F, -10.5F);
		setRotation(Base2, 0.0F, 1.0122909661567112F, 0.0F);
		Base2.mirror = false;

		Base3 = new ModelRenderer(this, 44, 51);
		Base3.addBox(0.0F, 0.0F, 0.0F, 6, 2, 4);
		Base3.setTextureSize(128, 128);
		Base3.setRotationPoint(-7.0F, 22.0F, -5.0F);
		setRotation(Base3, 0.0F, 0.9948376736367678F, 0.0F);
		Base3.mirror = false;

		Base4 = new ModelRenderer(this, 44, 51);
		Base4.addBox(0.0F, 0.0F, 0.0F, 6, 2, 4);
		Base4.setTextureSize(128, 128);
		Base4.setRotationPoint(3.5F, 22.0F, -10.5F);
		setRotation(Base4, 0.0F, -1.0122909661567112F, 0.0F);
		Base4.mirror = false;

		Base5 = new ModelRenderer(this, 44, 51);
		Base5.addBox(-6.0F, 0.0F, 0.0F, 6, 2, 4);
		Base5.setTextureSize(128, 128);
		Base5.setRotationPoint(7.0F, 22.0F, -5.0F);
		setRotation(Base5, 0.0F, -0.9948376736367678F, 0.0F);
		Base5.mirror = false;

		Base6 = new ModelRenderer(this, 44, 57);
		Base6.addBox(-7.0F, 22.0F, -5.0F, 14, 2, 11);
		Base6.setTextureSize(128, 128);
		Base6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Base6, 0.0F, 0.0F, 0.0F);
		Base6.mirror = false;

		Base7 = new ModelRenderer(this, 44, 70);
		Base7.addBox(-3.0F, 0.0F, 0.0F, 3, 2, 2);
		Base7.setTextureSize(128, 128);
		Base7.setRotationPoint(7.0F, 22.0F, 6.0F);
		setRotation(Base7, 0.0F, -0.9599310885968813F, 0.0F);
		Base7.mirror = false;

		Base8 = new ModelRenderer(this, 44, 70);
		Base8.addBox(-3.0F, 0.0F, -2.0F, 3, 2, 2);
		Base8.setTextureSize(128, 128);
		Base8.setRotationPoint(5.0F, 22.0F, 7.5F);
		setRotation(Base8, 0.0F, -0.9075712110370513F, 0.0F);
		Base8.mirror = false;

		Base9 = new ModelRenderer(this, 44, 70);
		Base9.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2);
		Base9.setTextureSize(128, 128);
		Base9.setRotationPoint(-7.0F, 22.0F, 6.0F);
		setRotation(Base9, 0.0F, 0.9599310885968813F, 0.0F);
		Base9.mirror = false;

		Base10 = new ModelRenderer(this, 44, 70);
		Base10.addBox(0.0F, 0.0F, -2.0F, 3, 2, 2);
		Base10.setTextureSize(128, 128);
		Base10.setRotationPoint(-5.0F, 22.0F, 7.5F);
		setRotation(Base10, 0.0F, 0.9075712110370513F, 0.0F);
		Base10.mirror = false;

		Base11 = new ModelRenderer(this, 70, 43);
		Base11.addBox(-5.0F, 22.0F, 5.5F, 10, 2, 2);
		Base11.setTextureSize(128, 128);
		Base11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(Base11, 0.0F, 0.0F, 0.0F);
		Base11.mirror = false;

	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void renderHead() {
		Dome1.render(0.0625F);
		Dome2.render(0.0625F);
		Dome3.render(0.0625F);
		NeckRing1.render(0.0625F);
		NeckRing2.render(0.0625F);
		NeckRing3.render(0.0625F);
		EarGlow1.render(0.0625F);
		EarGlow2.render(0.0625F);
		Grille.render(0.0625F);
	}
	
	@Override
	public void renderEyestalk() {
		Eyestalk.render(0.0625F);
		EyeRings.render(0.0625F);
		Eye.render(0.0625F);
	}
	
	@Override
	public void renderRightAttachment() {
		PlungerStick.render(0.0625F);
		Plunger1.render(0.0625F);
		Plunger2.render(0.0625F);
		Plunger3.render(0.0625F);
		Plunger4.render(0.0625F);
	}
	
	@Override
	public void renderLeftAttachment() {
		GunBoxLeft.render(0.0625F);
		GunBoxRight.render(0.0625F);
		Gunstick.render(0.0625F);
		Gun1.render(0.0625F);
		Gun2.render(0.0625F);
		Gun3.render(0.0625F);
		Gun4.render(0.0625F);
		GunBoxSecondaryLeft.render(0.0625F);
		GunBoxSecondaryRight.render(0.0625F);
	}
	
	@Override
	public void renderBody() {
		Skirt1.render(0.0625F);
		Skirt2.render(0.0625F);
		Skirt3.render(0.0625F);
		Skirt4.render(0.0625F);
		Skirt5.render(0.0625F);
		Skirt6.render(0.0625F);
		Skirt7.render(0.0625F);
		Base1.render(0.0625F);
		Base2.render(0.0625F);
		Base3.render(0.0625F);
		Base4.render(0.0625F);
		Base5.render(0.0625F);
		Base6.render(0.0625F);
		Base7.render(0.0625F);
		Base8.render(0.0625F);
		Base9.render(0.0625F);
		Base10.render(0.0625F);
		Base11.render(0.0625F);
		Shoulders.render(0.0625F);
		Division1.render(0.0625F);
		Divison2.render(0.0625F);
		Division3.render(0.0625F);
		Divison4.render(0.0625F);
		Division5.render(0.0625F);
		Division6.render(0.0625F);
		Division7.render(0.0625F);
		Division8.render(0.0625F);
		Division9.render(0.0625F);
		Division10.render(0.0625F);
		Division11.render(0.0625F);
		Division12.render(0.0625F);
		Division13.render(0.0625F);
		Division14.render(0.0625F);
	}
	
	@Override
	public ResourceLocation getTexture() {
		return super.getTexture();
	}
}