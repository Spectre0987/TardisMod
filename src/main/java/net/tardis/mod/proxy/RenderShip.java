package net.tardis.mod.proxy;

import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.common.entities.EntityShip;
import net.tardis.mod.util.client.RenderHelper;

public class RenderShip extends Render<EntityShip>{

	public RenderShip(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityShip entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}

	@Override
	public void doRender(EntityShip entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		RenderHelper.renderTransformAxis();
		for(Entry<BlockPos, BlockStorage> entry : entity.getBlocks().entrySet()) {
			System.out.println("Rendering");
			BufferBuilder bb = Tessellator.getInstance().getBuffer();
			Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlock(entry.getValue().blockstate, entry.getKey().subtract(new BlockPos(x, y, z)), Minecraft.getMinecraft().world, bb);
		}
		GlStateManager.popMatrix();
	}

}
