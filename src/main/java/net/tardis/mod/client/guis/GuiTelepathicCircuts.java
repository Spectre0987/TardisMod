package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.Tardis;
import net.tardis.mod.packets.MessageTelepathicCircut;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class GuiTelepathicCircuts extends GuiScreen {
	
	public GuiTextField name;
	public BlockPos pos = BlockPos.ORIGIN;
	Minecraft mc;
	int selected = 0;

	private ArrayList<String> newStrings;
	
	public GuiTelepathicCircuts() {
		mc = Minecraft.getMinecraft();

	}
	
	public GuiTelepathicCircuts(BlockPos pos) {
		this();
		this.pos = pos;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawDefaultBackground();
		name.drawTextBox();
	}

	@Override
	public void initGui() {
		super.initGui();

		createList();
    	
		int textWidth = width / 3;
		name = new GuiTextField(0, mc.fontRenderer, (width - textWidth) / 2, (height - mc.fontRenderer.FONT_HEIGHT) / 2, textWidth, mc.fontRenderer.FONT_HEIGHT*2);
		name.setFocused(true);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		name.updateCursorCounter();

		if (Keyboard.isKeyDown(Keyboard.KEY_TAB) && name.isFocused()) {
			selected = selected + 1;
			if (selected > newStrings.size() - 1) {
				selected = 0;
			}
			name.setText(newStrings.get(selected));
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		if(keyCode != 28) {
			name.textboxKeyTyped(typedChar, keyCode);
		}
		else {
			Tardis.NETWORK.sendToServer(new MessageTelepathicCircut(this.pos, name.getText()));
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

	private void createList() {

		newStrings = new ArrayList();

		String[] locations = new String[]{"Stronghold", "Monument", "Village", "Mansion", "EndCity", "Fortress", "Temple", "Mineshaft"};

		for (String loc : locations) {
			newStrings.add(loc);
		}

		Collection<NetworkPlayerInfo> players = Minecraft.getMinecraft().getConnection().getPlayerInfoMap();
		players.forEach((loadedPlayer) -> {
			String loadedPlayerName = loadedPlayer.getGameProfile().getName();
			if (!Minecraft.getMinecraft().player.getName().equals(loadedPlayerName)) {
				newStrings.add(loadedPlayerName);
			}
		});

	}

}
