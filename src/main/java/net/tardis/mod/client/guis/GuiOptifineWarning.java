package net.tardis.mod.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;
import java.util.List;

public class GuiOptifineWarning extends GuiScreen {
	
	public static GuiScreen lastGui;

	public GuiOptifineWarning() {
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		this.addButton(new GuiButtonExt(0, this.width / 2 - 154, this.height - 48, 150, 20, I18n.format("Close Game")));
		this.addButton(new GuiButtonExt(1, this.width / 2 + 4, this.height - 48, 150, 20, I18n.format("Ignore (I understand)")));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		
		if (button.id == 0) {
			Minecraft.getMinecraft().shutdown();
		} else if (button.id == 1) {
			Minecraft.getMinecraft().displayGuiScreen(lastGui);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawBackground(0);
		List<String> strings = this.fontRenderer.listFormattedStringToWidth(I18n.format("Tardis Mod:\n We have noticed you have Optifine installed!\n We understand why!\n But Render bugs with optifine installed will be ignored\n As Optifine has a chance of breaking the Bigger on the inside effect!"), (int) (this.width * 0.9F));
		int i = 0;
		for (String string : strings) {
			this.drawCenteredString(this.fontRenderer, string, this.width / 2, this.height / 2 - (strings.size() * 16) / 2 + i * 16, 16777215);
			i++;
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Mod.EventBusSubscriber(value = Side.CLIENT)
	public static class EventHandler {
		
		public static boolean opened = false;
		
		@SubscribeEvent
		public static void onLoadMC(GuiScreenEvent.InitGuiEvent e) {
			if (!opened && !FMLClientHandler.instance().hasOptifine() && e.getGui() instanceof GuiMainMenu) {
				lastGui = e.getGui();
				Minecraft.getMinecraft().displayGuiScreen(new GuiOptifineWarning());
				opened = true;
			}
		}
		
	}
	
}
