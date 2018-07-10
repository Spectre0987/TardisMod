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
	public ButtonRecipe console2;
	public ButtonRecipe console3;
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
		console2.drawButton(mc, mouseX, mouseY, partialTicks);
		console3.drawButton(mc, mouseX, mouseY, partialTicks);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button instanceof ButtonRecipe) {
			Tardis.NETWORK.sendToServer(new MessageExteriorChange(pos, ((ButtonRecipe)button).getBlock()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
		super.actionPerformed(button);
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		this.addButton(console1 = new ButtonRecipe(0, (width / 2) - (buttonSize / 2), (height / 2) - (buttonSize / 2), new ItemStack(TBlocks.tardis_top)));
		this.addButton(console2 = new ButtonRecipe(1, (width / 2) - (buttonSize / 2), ((height / 2) - (buttonSize / 2)) - buttonSize, new ItemStack(TBlocks.tardis_top_01)));
		this.addButton(console3 = new ButtonRecipe(3,(width / 2) - (buttonSize / 2), ((height / 2) - (buttonSize / 2)) - buttonSize * 2, new ItemStack(TBlocks.tardis_top_02)));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
