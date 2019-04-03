package net.tardis.mod.client.guis.manual;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.capability.ITardisCap.Vec2d;

public class GuiManual extends GuiScreen {
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/manual.png");
	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
	private static List<Page> PAGES = new ArrayList<>();
	public int gui_width = 281, gui_height = 208;
	private int index = 0;
	
	static {
		IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
		if(manager instanceof IReloadableResourceManager) {
			((IReloadableResourceManager)manager).registerReloadListener(new IResourceManagerReloadListener() {
				@Override
				public void onResourceManagerReload(IResourceManager resourceManager) {
					GuiManual.loadPages();
				}});
		}
	}

	public GuiManual() {
		mc = Minecraft.getMinecraft();
	}

	public static void loadPages() {
		GuiManual.PAGES.clear();
		List<ResourceLocation> pagelocations = GuiManual.getPages();
		for(ResourceLocation loc : pagelocations) {
			GuiManual.PAGES.add(Page.read(loc));
		}
		
	}
	
	public static List<ResourceLocation> getPages(){
		List<ResourceLocation> locations = new ArrayList<>();
		try {
			InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(Tardis.MODID, "manual/index.json")).getInputStream();
			JsonReader reader = new GsonBuilder().create().newJsonReader(new InputStreamReader(is));
			reader.beginArray();
			while(reader.hasNext()) {
				locations.add(new ResourceLocation(reader.nextString()));
			}
			reader.endArray();
			reader.close();
			is.close();
		}
		catch(Exception e) {e.printStackTrace();}
		return locations;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.drawModalRectWithCustomSizedTexture(width / 2 - this.gui_width / 2, height / 2 - this.gui_height / 2, 0, 0, this.gui_width, this.gui_height, 512, 512);
		if(this.index < this.PAGES.size()) {
			this.PAGES.get(index).draw(width / 2 - 125, height / 2 - 80);
		}
		if(this.index + 1 < this.PAGES.size()) {
			this.PAGES.get(index + 1).draw(width / 2 + 15, height / 2 - 80);
		}
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	public static class Page{
		
		private List<String> lines = new ArrayList<>();
		private Map<Vec2d, ResourceLocation> images = new HashMap<>(); 
		
		public static Page read(ResourceLocation loc) {
			Page page = new Page();
			try {
				BufferedReader read = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream()));
				Iterator<String> iter = read.lines().iterator();
				while(iter.hasNext()) {
					String[] lines = iter.next().split("<br />");
					for(String line : lines) {
						page.addLine(line);
					}
				}
				read.close();
				page.readImages();
			}
			catch(Exception e) {}
			return page;
		}
		
		public void addLine(String line) {
			this.lines.add(line);
		}
		
		public void readImages() {
			List<ResourceLocation> IMAGE = new ArrayList<>();
			for(String s : lines) {
				if(s.contains("<img")){
					images.put(new Vec2d(0, 0), new ResourceLocation(s.substring(s.indexOf("src=\"") + 5, s.indexOf(">") - 1)));
				}
			}
			
		}
		
		public void drawImage(int x, int y) {
			this.images.forEach((Vec2d vec, ResourceLocation image) -> {
				Minecraft.getMinecraft().getTextureManager().bindTexture(image);
				BufferBuilder bb = Tessellator.getInstance().getBuffer();
				bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				
				bb.pos(x, y, 10).tex(0, 0).endVertex();
				bb.pos(x, y + 100, 10).tex(0, 1).endVertex();
				bb.pos(x + 100, y + 100, 10).tex(1, 1).endVertex();
				bb.pos(x + 100, y, 10).tex(1, 0).endVertex();
				
				Tessellator.getInstance().draw();
			});
		}
		
		public void draw(double x, double y) {
			FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
			int index = 0;
			for(String line : lines) {
				fr.drawString(line, (int)x, (int)y + (index * fr.FONT_HEIGHT), 0x000000);
				++index;
			}
			this.drawImage((int)x, (int)y);
		}
	}

}
