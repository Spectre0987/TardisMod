package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.elements.ButtonRecipe;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.packets.MessageExteriorChange;

public class GuiCCircuit extends GuiScreen {
	
	Minecraft mc;
	public BlockPos pos = BlockPos.ORIGIN;
	public ButtonRecipe console1;
	private int buttonSize = 18;
	
	public GuiCCircuit() {
		mc = Minecraft.getMinecraft();
	}
	
	public GuiCCircuit(BlockPos pos) {
		this();
		this.pos = pos.toImmutable();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		console1.drawButton(mc, mouseX, mouseY, partialTicks);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button == this.console1) {
			Tardis.NETWORK.sendToServer(new MessageExteriorChange(pos,TBlocks.tardis_top.getDefaultState()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		super.actionPerformed(button);
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		console1 = new ButtonRecipe(0, (width / 2) - (buttonSize / 2), (height / 2) - (buttonSize / 2), new ItemStack(TBlocks.tardis_top));
		this.addButton(console1);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
