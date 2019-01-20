package net.tardis.mod.client.models.entity.dalek;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public abstract class ModelDalekBase extends ModelBase {

	public ModelDalekBase() {

	}

	public void renderHead() {
	}

	public void renderEyestalk() {
	}

	public void renderRightAttachment() {
	}

	public void renderLeftAttachment() {
	}

	public void renderBody() {
	}

	public ResourceLocation getTexture() {
		return null;
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		GlStateManager.pushMatrix();
		if (getTexture() != null) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
		}

		//Render the head related things
		GlStateManager.pushMatrix();
		GlStateManager.rotate(netHeadYaw, 0, 1, 0);
		renderHead();

		GlStateManager.rotate(headPitch, 1, 0, 0);
		renderEyestalk();
		GlStateManager.popMatrix();

		//Render Body related things
		GlStateManager.pushMatrix();
		GlStateManager.rotate(entityIn.rotationYaw, 0, 1, 0);
		renderBody();
		renderLeftAttachment();
		renderRightAttachment();
		GlStateManager.popMatrix();


		GlStateManager.popMatrix();
	}
}
