package net.tardis.mod.client.renderers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.ModelInteriorDoors;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.blocks.BlockInteriorDoor;
import net.tardis.mod.common.tileentity.interiors.TileEntityInteriorDoor;

public class RenderTileInteriorDoor extends TileEntitySpecialRenderer<TileEntityInteriorDoor> {
	
	Minecraft mc;
	ModelInteriorDoors model = new ModelInteriorDoors();
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/doors.png");
	
	public RenderTileInteriorDoor() {
		mc = Minecraft.getMinecraft();
	}

	@Override
	public void render(TileEntityInteriorDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		//mc.getTextureManager().bindTexture(TEXTURE);
		//model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		IBlockState state = mc.world.getBlockState(te.getPos());
		Vec3d offset = new Vec3d(0, 0, 0);
		if(state.getBlock() instanceof BlockInteriorDoor) {
			EnumFacing facing = state.getValue(BlockInteriorDoor.FACING);
			switch(facing) {
			case EAST:{
				GlStateManager.translate(0, 0, 0);
				GlStateManager.rotate(0,0,1,0);
			};
			case SOUTH:{};
			case WEST:{};
			default:{};
			}
		}
		RenderHelper.renderPortal(new RenderWorldShell(), te, partialTicks, 0, null, new Vec3d(1.5, 2.5, 0));
		GlStateManager.popMatrix();
	}

}
