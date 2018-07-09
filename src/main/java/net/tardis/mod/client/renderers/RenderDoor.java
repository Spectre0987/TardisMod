package net.tardis.mod.client.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.profiler.Profiler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.worldshell.RenderWorldShell;
import net.tardis.mod.common.entities.controls.ControlDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.proxy.ClientProxy;
import net.tardis.mod.util.helpers.Helper;

public class RenderDoor extends Render<ControlDoor> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/door.png");
	public static final ResourceLocation BLACK_HOLE_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/blocks/black_hole.png");
	RenderWorldShell shellRender;
	Minecraft mc;
	
	public RenderDoor() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
		shellRender = new RenderWorldShell();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(ControlDoor entity) {
		return TEXTURE;
	}
	
	@Override
	public void doRender(ControlDoor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x - 0.5, y, z + 0.41);
		mc.getTextureManager().bindTexture(TEXTURE);
		boolean open = entity.isOpen();
		if(open) {
			try {
				GlStateManager.pushMatrix();
				GlStateManager.translate(0,0, 1);
				GL11.glEnable(GL11.GL_STENCIL_TEST);
				
				// Always write to stencil buffer
				GL11.glStencilFunc(GL11.GL_NEVER, 1, 0xFF);
				GL11.glStencilOp(GL11.GL_REPLACE, GL11.GL_KEEP, GL11.GL_KEEP);
				GL11.glStencilMask(0xFF);
				GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
		
				this.drawDoorShape();
		
				//GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				// Only pass stencil test if equal to 1
				GL11.glStencilMask(0x00);
				GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);
		
				// Draw scene from portal view
				GlStateManager.pushMatrix();
				mc.entityRenderer.disableLightmap();
				GlStateManager.rotate(180,0,1,0);
				GlStateManager.translate(0, 0, -3);
				GlStateManager.rotate(Helper.getAngleFromFacing(entity.getFacing()), 0, 1, 0);
				shellRender.doRender(entity, 0,0,0, 0, partialTicks);
				mc.entityRenderer.enableLightmap();
				GlStateManager.popMatrix();
				try {
					TileEntityTardis te = (TileEntityTardis)mc.world.getTileEntity(entity.getConsolePos());
					Class<? extends IRenderHandler> renderer = ClientProxy.skyRenderers.get(te.dimension);
					if(renderer != null) {
						renderer.newInstance().render(partialTicks, mc.world, mc);
					}
					else {
						WorldClient wc = mc.world;
						
						GlStateManager.pushMatrix();
						mc.world = new WorldClient(mc.getConnection(), new WorldSettings(wc.getSeed(), GameType.NOT_SET, true, false, wc.getWorldType()), te.dimension, EnumDifficulty.EASY, new Profiler());
						mc.world.setWorldTime(entity.getTime());
						WorldProvider wp = DimensionManager.createProviderFor(te.dimension);
						Vec3d fog = mc.world.getSkyColorBody(mc.player, partialTicks);
						if(fog != null) {
							GlStateManager.color((float)fog.x, (float)fog.y, (float)fog.z);
						}
						mc.renderGlobal.renderSky(partialTicks, 0);
						mc.renderGlobal.renderSky(partialTicks, 1);
						mc.renderGlobal.renderSky(partialTicks, 2);
						GlStateManager.color(1F,1F,1F);
						GlStateManager.popMatrix();
						
						mc.world = wc;
					}
				}
				catch(Exception e) {}
		
				GL11.glDisable(GL11.GL_STENCIL_TEST);
				GL11.glPopMatrix();
				GlStateManager.pushMatrix();
				GlStateManager.popMatrix();
			}
			catch(Exception e) {
				System.out.println("BOTI Rendering Failed!");
			}
			
		// Draw portal stencils so portals wont be drawn over
		GL11.glColorMask(false, false, false, false);
		this.drawDoorShape();
		
		//Set things back
		GL11.glColorMask(true, true, true, true);
		}
		else {
			Tessellator tes = Tessellator.getInstance();
			BufferBuilder buf = tes.getBuffer();
			buf.begin(7, DefaultVertexFormats.POSITION_TEX);
			mc.getTextureManager().bindTexture(TEXTURE);
			buf.pos(0, 0, 0).tex(0, 0).endVertex();
			buf.pos(0, 2, 0).tex(0, 2).endVertex();
			buf.pos(1, 2, 0).tex(1, 2).endVertex();
			buf.pos(1, 0, 0).tex(1, 0).endVertex();
			tes.draw();
		}
		GlStateManager.popMatrix();
	}
	
	public void drawDoorShape() {
		GlStateManager.translate(0, 0, -1);
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		
		buf.pos(0, 0, 0).tex(0, 0).endVertex();
		buf.pos(0, 2, 0).tex(0, 1).endVertex();
		buf.pos(1, 2, 0).tex(1, 1).endVertex();
		buf.pos(1, 0, 0).tex(1, 0).endVertex();
		
		tes.draw();
	}
}
