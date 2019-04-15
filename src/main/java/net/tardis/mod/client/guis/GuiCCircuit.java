package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.guis.elements.ButtonMonitor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageExteriorChange;

public class GuiCCircuit extends GuiScreen{
	
	public static EnumExterior[] exteriors = new EnumExterior[]{EnumExterior.FIRST, EnumExterior.SECOND, EnumExterior.THIRD, EnumExterior.FIFTH, EnumExterior.FOURTH, EnumExterior.CLOCK, EnumExterior.TT, EnumExterior.WOOD_DOOR, EnumExterior.CC, EnumExterior.WARDROBE};
	private TileEntityTardis tardis;
	private int index = 0;
	
	public GuiCCircuit(TileEntityTardis tardis) {
		this.tardis = tardis;
	}
	
	public GuiCCircuit(BlockPos pos) {
		TileEntity te = Minecraft.getMinecraft().world.getTileEntity(pos);
		if(te instanceof TileEntityTardis) {
			this.tardis = (TileEntityTardis)te;
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		GuiMonitor.drawMonitorBackground(this, width, height);
		this.drawCenteredString(Minecraft.getMinecraft().fontRenderer, "Chameleon Circuit", width / 2, height / 2 - 50, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
		GlStateManager.pushMatrix();
		GlStateManager.color(1F, 1, 1, 1F);
		GlStateManager.translate(width / 2 + 50, height / 2, 200);
		GlStateManager.scale(30, 30, 30);
		GlStateManager.rotate(-22.5F, 1, 0, 0);
		GlStateManager.rotate((Minecraft.getMinecraft().world.getTotalWorldTime() % 360) + partialTicks, 0, 1, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(exteriors[index].tex);
		exteriors[index].model.renderClosed(0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button instanceof ButtonMonitor) {
			((ButtonMonitor)button).doAction();
		}
	}

	@Override
	public void initGui() {
		super.initGui();
		int bWidth = width / 2 - 105;
		int bHeight = height / 2 + 50;
		this.addButton(new ButtonMonitor(0, bWidth, bHeight - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 0), "> Next Disguise")).addAction(()->{
			if(index + 1 > exteriors.length - 1)
				index = 0;
			++index;
		});
		this.addButton(new ButtonMonitor(1, bWidth, bHeight - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 1), "> Select Exterior")).addAction(()->{
			NetworkHandler.NETWORK.sendToServer(new MessageExteriorChange(tardis.getPos(), exteriors[index].block.getDefaultState()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		});
		this.addButton(new ButtonMonitor(2, bWidth, bHeight - (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * 2), "> Prev Disguise")).addAction(()->{
			if(index - 1 < 0)
				index = exteriors.length - 1;
			else --index;
		});
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}