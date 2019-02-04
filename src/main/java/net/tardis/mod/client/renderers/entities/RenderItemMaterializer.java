package net.tardis.mod.client.renderers.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.tardis.mod.common.entities.EntityItemMaterializer;

public class RenderItemMaterializer extends Render<EntityItemMaterializer>{

	public RenderItemMaterializer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityItemMaterializer entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(entity.getItem(), Minecraft.getMinecraft().world, null);
		model = ForgeHooksClient.handleCameraTransforms(model, TransformType.GROUND, false);
		BufferBuilder bb = Tessellator.getInstance().getBuffer();
		bb.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.color(1F, 1, 1, 0.1F);
		for(EnumFacing face : EnumFacing.VALUES) {
			for(BakedQuad quad : model.getQuads(null, face, 0)) {
				bb.addVertexData(quad.getVertexData());
				bb.putColor4(0x2BAAFF);
			}
		}
		for(BakedQuad quad : model.getQuads(null, null, 0)) {
			bb.addVertexData(quad.getVertexData());
			bb.putColor4(0x2BAAFF);
		}
		Tessellator.getInstance().draw();
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityItemMaterializer entity) {
		return null;
	}

}
