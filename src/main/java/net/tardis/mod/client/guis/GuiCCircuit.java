package net.tardis.mod.client.guis;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
	private ResourceLocation tex = new ResourceLocation(Tardis.MODID, "textures/gui/chameleon_circuit.png");
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
		mc.getTextureManager().bindTexture(tex);
		int x = (width - 248) / 2;
		int y = (height - 167) /2;
		drawTexturedModalRect(x, y, 0, 0, 248, 167);
		drawTexturedModalRect(x + 83, y + 59, 3, 169, 12, 15);
		drawTexturedModalRect(x + 83, y + 112, 3, 169, 12, 15);
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
		this.addButton(new GuiButton(buttonSize, buttonSize, buttonSize, buttonSize, buttonSize, null));
		this.addButton(console1 = new ButtonRecipe(0, (width / 2) - (buttonSize / 2), (height / 2) - (buttonSize / 2), new ItemStack(TBlocks.tardis_top)));
		this.addButton(console2 = new ButtonRecipe(1, (width / 2) - (buttonSize / 2), ((height / 2) - (buttonSize / 2)) - buttonSize, new ItemStack(TBlocks.tardis_top_01)));
		this.addButton(console3 = new ButtonRecipe(3,(width / 2) - (buttonSize / 2), ((height / 2) - (buttonSize / 2)) - buttonSize * 2, new ItemStack(TBlocks.tardis_top_02)));
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
