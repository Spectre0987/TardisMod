package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageTelepathicCircut;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GuiTelepathicCircuts extends GuiScreen {

	public GuiTextField name;
	public BlockPos pos = BlockPos.ORIGIN;
	Minecraft mc;
	int selected = 0;

	private ArrayList<String> newStrings;

	public GuiTelepathicCircuts() {
		mc = Minecraft.getMinecraft();
		createList();
	}

	public GuiTelepathicCircuts(BlockPos pos) {
		this();
		this.pos = pos;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		name.drawTextBox();

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		super.initGui();

		int x = this.width / 2;
		int y = this.height / 2;

		int textWidth = width / 3;
		name = new GuiTextField(0, mc.fontRenderer, (width - textWidth) / 2, (height - mc.fontRenderer.FONT_HEIGHT) / 2, textWidth, mc.fontRenderer.FONT_HEIGHT * 2);
		name.setFocused(true);

		this.buttonList.add(new GuiButtonExt(0, x - 190 / 2, y + 20, 25, 18, I18n.translateToLocal("<")));
		this.buttonList.add(new GuiButtonExt(1, x - 190 / 2 + 70, y + 20, 50, 18, I18n.translateToLocal("gui.tardis.travel")));
		this.buttonList.add(new GuiButtonExt(2, x + 190 - 120, y + 20, 25, 18, I18n.translateToLocal(">")));
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 0) {
			selected = selected - 1;
			safetyCheck();
		}

		if (button.id == 1) {
			NetworkHandler.NETWORK.sendToServer(new MessageTelepathicCircut(this.pos, name.getText()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}

		if (button.id == 2) {
			selected = selected + 1;
			safetyCheck();
		}
		super.actionPerformed(button);
	}


	public void safetyCheck() {
		if (selected > newStrings.size() - 1) {
			selected = 0;
		}

		if (selected < 0) {
			selected = newStrings.size() - 1;
		}

		name.setText(newStrings.get(selected));
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		name.updateCursorCounter();

		if (Keyboard.isKeyDown(Keyboard.KEY_TAB) && name.isFocused()) {
			selected = selected + 1;
			safetyCheck();
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		if (keyCode != 28) {
			name.textboxKeyTyped(typedChar, keyCode);
		} else {
			NetworkHandler.NETWORK.sendToServer(new MessageTelepathicCircut(this.pos, name.getText()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

	private void createList() {

		newStrings = new ArrayList<>();

		String[] locations = new String[]{"Stronghold", "Monument", "Village", "Mansion", "EndCity", "Fortress", "Temple", "Mineshaft"};
		newStrings.addAll(Arrays.asList(locations));

		Collection<NetworkPlayerInfo> players = Minecraft.getMinecraft().getConnection().getPlayerInfoMap();
		players.forEach((loadedPlayer) -> {
			String loadedPlayerName = loadedPlayer.getGameProfile().getName();
			if (!Minecraft.getMinecraft().player.getName().equals(loadedPlayerName)) {
				newStrings.add(loadedPlayerName);
			}
		});

		for (ResourceLocation biomeName : ForgeRegistries.BIOMES.getKeys()) {
			newStrings.add(ForgeRegistries.BIOMES.getValue(biomeName).getBiomeName());
		}
	}


}
