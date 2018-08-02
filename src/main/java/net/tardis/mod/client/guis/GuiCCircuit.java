package net.tardis.mod.client.guis;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.packets.MessageExteriorChange;

public class GuiCCircuit extends GuiScreen {
	
	Minecraft mc;
	public BlockPos pos = BlockPos.ORIGIN;
	public static EnumExterior[] exteriors = new EnumExterior[] {EnumExterior.FIRST, EnumExterior.SECOND, EnumExterior.THIRD};
	private ResourceLocation tex = new ResourceLocation(Tardis.MODID, "textures/gui/chameleon_circuit.png");
	private GuiButton selectButton;
	private GuiButton nextArrow;
	private GuiButton prevArrow;
	private static final int WIDTH = 248;
	private static final int HEIGHT = 166;
	private int index = 0;
	
	public GuiCCircuit() {
		mc = Minecraft.getMinecraft();
	}
	
	public GuiCCircuit(BlockPos pos) {
		this();
		this.pos = pos.toImmutable();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawDefaultBackground();
		mc.getTextureManager().bindTexture(tex);
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		this.drawTexturedModalRect(res.getScaledWidth() / 2 - WIDTH / 2, res.getScaledHeight() / 2 - HEIGHT / 2, 0, 0, WIDTH, HEIGHT);
		GlStateManager.pushMatrix();
		String title = "Chameleon Circuit";
		mc.fontRenderer.drawStringWithShadow(title, res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(title) / 2, (res.getScaledHeight() / 2 - HEIGHT / 2) + mc.fontRenderer.FONT_HEIGHT, Color.lightGray.getRGB());
		GlStateManager.color(1F, 1F, 1F);
		GlStateManager.popMatrix();
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate(res.getScaledWidth() / 2, res.getScaledHeight() / 2, -20);
			GlStateManager.scale(25, 25, 25);
			GlStateManager.rotate(mc.world.getTotalWorldTime() % 360, 0, 1, 0);
			GL11.glDepthFunc(GL11.GL_GREATER);
			RenderHelper.disableStandardItemLighting();
			EnumExterior ext = exteriors[index];
			mc.getTextureManager().bindTexture(ext.tex);
			ext.model.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
			GlStateManager.popMatrix();
		}
		for(GuiButton b : this.buttonList) {
			b.drawButton(mc, mouseX, mouseY, partialTicks);
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button == this.selectButton) {
			Tardis.NETWORK.sendToServer(new MessageExteriorChange(pos, exteriors[index].block.getDefaultState()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		else if(button == this.nextArrow) {
			int i = index + 1;
			if(i > this.exteriors.length - 1) {
				index = 0;
			}
			else index++;
		}
		else {
			int i = index - 1;
			if(i < 0) {
				index = this.exteriors.length - 1;
			}
			else --index;
		}
		super.actionPerformed(button);
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		String select = "Select";
		ScaledResolution res = new ScaledResolution(mc);
		this.selectButton = this.addButton(new GuiButton(0, res.getScaledWidth() / 2 - (mc.fontRenderer.getStringWidth(select) * 2) / 2, (res.getScaledHeight() / 2 - mc.fontRenderer.FONT_HEIGHT / 2) + 60, mc.fontRenderer.getStringWidth(select) * 2, mc.fontRenderer.FONT_HEIGHT * 2, select));
		String nArrow = ">";
		this.nextArrow = this.addButton(new GuiButton(1, (res.getScaledWidth() / 2 - (mc.fontRenderer.getStringWidth(nArrow) * 2) / 2) + 40, (res.getScaledHeight() / 2 - mc.fontRenderer.FONT_HEIGHT * 2) + 74, mc.fontRenderer.getStringWidth(nArrow) * 2, mc.fontRenderer.FONT_HEIGHT * 2, nArrow));
		String pArrow = "<";
		this.prevArrow = this.addButton(new GuiButton(2, (res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(pArrow)) - 40, (res.getScaledHeight() / 2 - mc.fontRenderer.FONT_HEIGHT) + 65, mc.fontRenderer.getStringWidth(pArrow) * 2, mc.fontRenderer.FONT_HEIGHT * 2, pArrow));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
