package net.tardis.mod.client.models.clothing;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.entity.BodyPartHook;

public class ModelThirteenCoat extends ModelBiped {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/clothing/thirteen_coat.png");

	public ModelThirteenCoat() {
		textureWidth = 64;
		textureHeight = 64;
		ModelRenderer buttonBack2 = new ModelRenderer(this, 52, 39);
		buttonBack2.setRotationPoint(-2.0F, 8.800000381469728F, 1.7000000476837158F);
		buttonBack2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		ModelRenderer rACoatArmTop = new ModelRenderer(this, 0, 24);
		rACoatArmTop.setRotationPoint(-3.0F, -2.1F, -2.0F);
		rACoatArmTop.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
		ModelRenderer buttonBack1 = new ModelRenderer(this, 52, 36);
		buttonBack1.setRotationPoint(1.0F, 8.800000381469728F, 1.7000000476837158F);
		buttonBack1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		ModelRenderer coatBaseLeft = new ModelRenderer(this, 22, 0);
		coatBaseLeft.setRotationPoint(3.0999999046325684F, -0.40000000149011616F, -2.5F);
		coatBaseLeft.addBox(0.0F, 0.0F, 0.0F, 1, 15, 5, 0.0F);
		ModelRenderer coatBaseRight = new ModelRenderer(this, 22, 21);
		coatBaseRight.setRotationPoint(-4.099999904632568F, -0.40000000149011616F, -2.5F);
		coatBaseRight.addBox(0.0F, 0.0F, 0.0F, 1, 15, 5, 0.0F);
		ModelRenderer coatBaseRight2 = new ModelRenderer(this, 36, 19);
		coatBaseRight2.setRotationPoint(-3.0999999046325684F, -0.40000000149011616F, -2.5F);
		coatBaseRight2.addBox(0.0F, 0.0F, 0.0F, 1, 14, 4, 0.0F);
		ModelRenderer rACoatArmLeft = new ModelRenderer(this, 0, 42);
		rACoatArmLeft.setRotationPoint(-3.1F, -2.1F, -2.5F);
		rACoatArmLeft.addBox(0.0F, 0.0F, 0.0F, 1, 10, 5, 0.0F);
		ModelRenderer lACoatArmFront = new ModelRenderer(this, 47, 0);
		lACoatArmFront.setRotationPoint(-1.0F, -2.1F, -2.5F);
		lACoatArmFront.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
		ModelRenderer lapelRight1 = new ModelRenderer(this, 47, 24);
		lapelRight1.setRotationPoint(-3.200000047683716F, -0.30000000000000004F, -2.799999952316284F);
		lapelRight1.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
		ModelRenderer button3 = new ModelRenderer(this, 58, 6);
		button3.setRotationPoint(-4.199999809265137F, 7.200000000000001F, -0.699999988079071F);
		button3.addBox(0.0F, 0.0F, -2.0F, 1, 1, 1, 0.0F);
		ModelRenderer button6 = new ModelRenderer(this, 58, 15);
		button6.setRotationPoint(3.200000047683716F, 7.200000000000001F, -0.699999988079071F);
		button6.addBox(0.0F, 0.0F, -2.0F, 1, 1, 1, 0.0F);
		ModelRenderer coatBaseBack = new ModelRenderer(this, 0, 0);
		coatBaseBack.setRotationPoint(-4.0F, -0.40000000149011616F, 1.5F);
		coatBaseBack.addBox(0.0F, 0.0F, 0.0F, 8, 15, 1, 0.0F);
		ModelRenderer rACoatArmBack = new ModelRenderer(this, 10, 30);
		rACoatArmBack.setRotationPoint(-3.0F, -2.1F, 1.5F);
		rACoatArmBack.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
		ModelRenderer coatBaseLeft2 = new ModelRenderer(this, 36, 0);
		coatBaseLeft2.setRotationPoint(2.0999999046325684F, -0.40000000149011616F, -2.5F);
		coatBaseLeft2.addBox(0.0F, 0.0F, 0.0F, 1, 14, 4, 0.0F);
		ModelRenderer lACoatArmLeft = new ModelRenderer(this, 27, 42);
		lACoatArmLeft.setRotationPoint(2.1F, -2.1F, -2.5F);
		lACoatArmLeft.addBox(0.0F, 0.0F, 0.0F, 1, 10, 5, 0.0F);
		ModelRenderer rACoatArmFront = new ModelRenderer(this, 0, 30);
		rACoatArmFront.setRotationPoint(-3.0F, -2.1F, -2.5F);
		rACoatArmFront.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);
		ModelRenderer lACoatArmRight = new ModelRenderer(this, 41, 42);
		lACoatArmRight.setRotationPoint(-1.1F, -2.1F, -2.5F);
		lACoatArmRight.addBox(0.0F, 0.0F, 0.0F, 1, 10, 5, 0.0F);
		ModelRenderer button2 = new ModelRenderer(this, 58, 3);
		button2.setRotationPoint(-4.199999809265137F, 9.200000000000001F, -0.699999988079071F);
		button2.addBox(0.0F, 0.0F, -2.0F, 1, 1, 1, 0.0F);
		ModelRenderer lapelRight2 = new ModelRenderer(this, 52, 24);
		lapelRight2.setRotationPoint(-3.9000000953674316F, 0.7000000000000001F, -2.700000047683716F);
		lapelRight2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		ModelRenderer button4 = new ModelRenderer(this, 58, 9);
		button4.setRotationPoint(3.200000047683716F, 11.200000000000001F, -0.699999988079071F);
		button4.addBox(0.0F, 0.0F, -2.0F, 1, 1, 1, 0.0F);
		ModelRenderer button1 = new ModelRenderer(this, 58, 0);
		button1.setRotationPoint(-4.199999809265137F, 11.200000000000001F, -0.699999988079071F);
		button1.addBox(0.0F, 0.0F, -2.0F, 1, 1, 1, 0.0F);
		ModelRenderer rACoatArmRight = new ModelRenderer(this, 14, 42);
		rACoatArmRight.setRotationPoint(0.1F, -2.1F, -2.5F);
		rACoatArmRight.addBox(0.0F, 0.0F, 0.0F, 1, 10, 5, 0.0F);
		ModelRenderer laCoatArmTop = new ModelRenderer(this, 0, 18);
		laCoatArmTop.setRotationPoint(-1.0F, -2.1F, -2.0F);
		laCoatArmTop.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
		ModelRenderer lapelLeft2 = new ModelRenderer(this, 52, 30);
		lapelLeft2.setRotationPoint(2.9000000953674316F, 0.7000000000000001F, -2.700000047683716F);
		lapelLeft2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		ModelRenderer lapelLeft1 = new ModelRenderer(this, 47, 33);
		lapelLeft1.setRotationPoint(2.200000047683716F, -0.30000000000000004F, -2.799999952316284F);
		lapelLeft1.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
		ModelRenderer button5 = new ModelRenderer(this, 58, 12);
		button5.setRotationPoint(3.200000047683716F, 9.200000000000001F, -0.699999988079071F);
		button5.addBox(0.0F, 0.0F, -2.0F, 1, 1, 1, 0.0F);
		ModelRenderer lACoatArmBack = new ModelRenderer(this, 47, 12);
		lACoatArmBack.setRotationPoint(-1.0F, -2.1F, 1.5F);
		lACoatArmBack.addBox(0.0F, 0.0F, 0.0F, 4, 10, 1, 0.0F);

		bipedRightArm.addChild(rACoatArmTop);
		bipedRightArm.addChild(rACoatArmLeft);
		bipedLeftArm.addChild(lACoatArmFront);
		bipedRightArm.addChild(rACoatArmBack);
		bipedLeftArm.addChild(lACoatArmLeft);
		bipedRightArm.addChild(rACoatArmFront);
		bipedLeftArm.addChild(lACoatArmRight);
		bipedRightArm.addChild(rACoatArmRight);
		bipedLeftArm.addChild(laCoatArmTop);
		bipedLeftArm.addChild(lACoatArmBack);

		bipedBody = new BodyPartHook(this, 16, 16);
		bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0);
		bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

		((BodyPartHook) bipedBody).setRender(scale -> {
			GlStateManager.pushMatrix();
			Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
			coatBaseLeft.render(scale);
			coatBaseLeft2.render(scale);
			coatBaseRight.render(scale);
			coatBaseRight2.render(scale);
			coatBaseBack.render(scale);
			button1.render(scale);
			button2.render(scale);
			button3.render(scale);
			button4.render(scale);
			button5.render(scale);
			button6.render(scale);
			lapelRight1.render(scale);
			lapelRight2.render(scale);
			lapelLeft1.render(scale);
			lapelLeft2.render(scale);
			buttonBack1.render(scale);
			buttonBack2.render(scale);
			GlStateManager.popMatrix();
		});

	}

}
