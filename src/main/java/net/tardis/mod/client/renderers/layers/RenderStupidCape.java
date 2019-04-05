package net.tardis.mod.client.renderers.layers;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;

@SideOnly(Side.CLIENT)
public class RenderStupidCape implements LayerRenderer<AbstractClientPlayer> {
	private final RenderPlayer playerRenderer;
	private final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/entity/mob/cape/cape.png");
	
	public RenderStupidCape(RenderPlayer playerRendererIn) {
		this.playerRenderer = playerRendererIn;
	}
	
	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if(!entitylivingbaseIn.getUniqueID().toString().equals("3f4f2acd-9427-4917-81c3-7b8a3031fffb") && !entitylivingbaseIn.getUniqueID().toString().equals("bc8b891e-5c25-4c9f-ae61-cdfb270f1cc1")) return;
		
		if (entitylivingbaseIn.hasPlayerInfo() && !entitylivingbaseIn.isInvisible() && entitylivingbaseIn.isWearing(EnumPlayerModelParts.CAPE) && TEXTURE != null) {
			ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			
			if (itemstack.getItem() != Items.ELYTRA) {
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				this.playerRenderer.bindTexture(TEXTURE);
				GlStateManager.pushMatrix();
				GlStateManager.translate(0.0F, 0.0F, 0.125F);
				double d0 = entitylivingbaseIn.prevChasingPosX + (entitylivingbaseIn.chasingPosX - entitylivingbaseIn.prevChasingPosX) * (double) partialTicks - (entitylivingbaseIn.prevPosX + (entitylivingbaseIn.posX - entitylivingbaseIn.prevPosX) * (double) partialTicks);
				double d1 = entitylivingbaseIn.prevChasingPosY + (entitylivingbaseIn.chasingPosY - entitylivingbaseIn.prevChasingPosY) * (double) partialTicks - (entitylivingbaseIn.prevPosY + (entitylivingbaseIn.posY - entitylivingbaseIn.prevPosY) * (double) partialTicks);
				double d2 = entitylivingbaseIn.prevChasingPosZ + (entitylivingbaseIn.chasingPosZ - entitylivingbaseIn.prevChasingPosZ) * (double) partialTicks - (entitylivingbaseIn.prevPosZ + (entitylivingbaseIn.posZ - entitylivingbaseIn.prevPosZ) * (double) partialTicks);
				float f = entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks;
				double d3 = (double) MathHelper.sin(f * 0.017453292F);
				double d4 = (double) (-MathHelper.cos(f * 0.017453292F));
				float f1 = (float) d1 * 10.0F;
				f1 = MathHelper.clamp(f1, -6.0F, 32.0F);
				float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
				float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;
				
				if (f2 < 0.0F) {
					f2 = 0.0F;
				}
				
				float f4 = entitylivingbaseIn.prevCameraYaw + (entitylivingbaseIn.cameraYaw - entitylivingbaseIn.prevCameraYaw) * partialTicks;
				f1 = f1 + MathHelper.sin((entitylivingbaseIn.prevDistanceWalkedModified + (entitylivingbaseIn.distanceWalkedModified - entitylivingbaseIn.prevDistanceWalkedModified) * partialTicks) * 6.0F) * 32.0F * f4;
				
				if (entitylivingbaseIn.isSneaking()) {
					f1 += 25.0F;
				}
				
				GlStateManager.rotate(6.0F + f2 / 2.0F + f1, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(f3 / 2.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotate(-f3 / 2.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				this.playerRenderer.getMainModel().renderCape(0.0625F);
				GlStateManager.popMatrix();
			}
		}
	}
	
	public boolean shouldCombineTextures() {
		return false;
	}
}