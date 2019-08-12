package net.tardis.mod.client.guis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.tardis.mod.client.guis.elements.ButtonSaves;

public class GuiWorldJump extends GuiScreen {

	private int index = 0;
	private List<WorldSummary> saves = new ArrayList<WorldSummary>();
	
	public GuiWorldJump() {
		
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.getAllWorlds();
		int index = 0;
		for(WorldSummary file : this.saves) {
			this.addButton(new ButtonSaves(index, (this.width / 2) - 75, 
					((height / 2) + 50) - Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT * index,
					file));
			++index;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GuiMonitor.drawMonitorBackground(this, this.width, this.height);
		this.index = 0;
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	public void getAllWorlds() {
		try {
			for(WorldSummary sum : this.mc.getSaveLoader().getSaveList()) {
				this.saves.add(sum);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if(button instanceof ButtonSaves) {
			
			try {
				WorldSummary sum = ((ButtonSaves)button).getFile();
				
				File oldPlayer = Paths.get(
						FMLClientHandler.instance().getSavesDirectory().getPath(),
						FMLClientHandler.instance().getServer().getWorldName(),
						"playerdata",
						Minecraft.getMinecraft().player.getUniqueID() + ".dat").toFile();
				
				System.out.println(oldPlayer);
				if(oldPlayer.exists()) {
					File newPlayer = Paths.get(FMLClientHandler.instance().getSavesDirectory().getPath(),
									sum.getFileName(),
									"playerdata",
									Minecraft.getMinecraft().player.getUniqueID() + ".dat").toFile();
					if(newPlayer.exists())
						newPlayer.delete();
					System.out.print(oldPlayer.renameTo(newPlayer));
				}
				FMLClientHandler.instance().tryLoadExistingWorld(null, sum);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
