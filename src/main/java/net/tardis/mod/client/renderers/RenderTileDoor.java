package net.tardis.mod.client.renderers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelTardis;
import net.tardis.mod.client.models.console.contols.ModelExteriorDoorL;
import net.tardis.mod.client.models.console.contols.ModelExteriorDoorR;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.util.helpers.Helper;

public class RenderTileDoor extends TileEntitySpecialRenderer {
	
	Minecraft mc;
	ModelTardis model = new ModelTardis();
	ModelExteriorDoorR door_r = new ModelExteriorDoorR();
	ModelExteriorDoorL door_l = new ModelExteriorDoorL();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/tardis.png");
	public static final ResourceLocation BLACK_DOOR = new ResourceLocation(Tardis.MODID, "textures/blocks/door.png");
	
	public RenderTileDoor() {
		mc = Minecraft.getMinecraft();
	}
	
	@Override
	public void render(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		boolean open = !((TileEntityDoor) te).isLocked();
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
			GlStateManager.rotate(180, 1, 0, 0);
			IBlockState state = mc.world.getBlockState(te.getPos());
			if (state.getBlock() == TBlocks.tardis_top) {
				EnumFacing facing = state.getValue(BlockTardisTop.FACING);
				float angle = Helper.getAngleFromFacing(facing);
				GlStateManager.rotate(angle - 180, 0, 1, 0);
			} else
				System.out.println("TARDIS: NO BLOCK AT: " + Helper.formatBlockPos(te.getPos()));
			mc.getTextureManager().bindTexture(TEXTURE);
			model.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GlStateManager.pushMatrix();
			{
				if (open) {
					Vec3d origin = Helper.convertToPixels(-6.5, 0, -8.5);
					GlStateManager.translate(origin.x, origin.y, origin.z);
					GlStateManager.rotate(-85, 0, 1, 0);
					origin = origin.scale(-1);
					GlStateManager.translate(origin.x, origin.y, origin.z);
				}
				door_l.render(null, 0, 0, 0, 0, 0, 0.0625F);
			}
			GlStateManager.popMatrix();
			
			GlStateManager.pushMatrix();
			{
				if (open) {
					Vec3d origin = Helper.convertToPixels(6.5, 0, -8.5);
					GlStateManager.translate(origin.x, origin.y, origin.z);
					GlStateManager.rotate(90, 0, 1, 0);
					origin = origin.scale(-1);
					GlStateManager.translate(origin.x, origin.y, origin.z);
				}
				door_r.render(null, 0, 0, 0, 0, 0, 0.0625F);
			}
			GlStateManager.popMatrix();
			// Renders black behind doors
			{
				GlStateManager.pushMatrix();
				GlStateManager.rotate(180, 0, 1, 0);
				GlStateManager.translate(-0.5, -0.5, 0.1625);
				mc.getTextureManager().bindTexture(BLACK_DOOR);
				Tessellator tes = Tessellator.getInstance();
				BufferBuilder buf = tes.getBuffer();
				buf.begin(7, DefaultVertexFormats.POSITION_TEX);
				GlStateManager.color(0F, 0F, 0F);
				buf.pos(1, 2, 0).tex(0, 0).endVertex();
				buf.pos(0, 2, 0).tex(1, 0).endVertex();
				buf.pos(0, 0, 0).tex(1, 1).endVertex();
				buf.pos(1, 0, 0).tex(0, 1).endVertex();
				tes.draw();
				GlStateManager.color(1F, 1F, 1F);
				GlStateManager.popMatrix();
			}
		}
		GlStateManager.popMatrix();
	}
	
	@Override
	public void renderTileEntityFast(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer) {
		// TODO Auto-generated method stub
		super.renderTileEntityFast(te, x, y, z, partialTicks, destroyStage, partial, buffer);
	}
	
}
