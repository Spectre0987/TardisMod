package net.tardis.mod.client.guis.manual;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
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
import org.lwjgl.opengl.GL11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GuiManual extends GuiScreen {
	public static final ResourceLocation TEXTURE = new ResourceLocation(Tardis.MODID, "textures/gui/manual.png");
	private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
	public ButtonBook next, prev;
	private static List<Page> PAGES = new ArrayList<>();;
	public int gui_width = 281, gui_height = 208;
	private int index = 0;
	
	static {
		PAGES =  new ArrayList<>();
		IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
		if (manager instanceof IReloadableResourceManager) {
			((IReloadableResourceManager) manager).registerReloadListener(new IResourceManagerReloadListener() {
				@Override
				public void onResourceManagerReload(IResourceManager resourceManager) {
					GuiManual.loadPages();
				}
			});
		}
	}
	private static List<Page> PAGES = new ArrayList<>();
	public int gui_width = 281, gui_height = 208;
	private int index = 0;
	
	public ButtonBook next, prev;

	public GuiManual() {
		mc = Minecraft.getMinecraft();
	}
	
	public static void loadPages() {
		GuiManual.PAGES.clear();
		List<ResourceLocation> pagelocations = GuiManual.getPages();
		for (ResourceLocation loc : pagelocations) {
			GuiManual.PAGES.add(Page.read(loc));
		}
		
	}
	
	public static List<ResourceLocation> getPages() {
		List<ResourceLocation> locations = new ArrayList<>();
		try {
			InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation(Tardis.MODID, "manual/index.json")).getInputStream();
			JsonReader reader = new GsonBuilder().create().newJsonReader(new InputStreamReader(is));
			reader.beginArray();
			while (reader.hasNext()) {
				locations.add(new ResourceLocation(reader.nextString()));
			}
			reader.endArray();
			reader.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locations;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(TEXTURE);
		drawModalRectWithCustomSizedTexture(width / 2 - this.gui_width / 2, height / 2 - this.gui_height / 2, 0, 0, this.gui_width, this.gui_height, 512, 512);
		if (this.index < PAGES.size()) {
			PAGES.get(index).draw(width / 2 - 125, height / 2 - 80);
		}
		if (this.index + 1 < PAGES.size()) {
			PAGES.get(index + 1).draw(width / 2 + 15, height / 2 - 80);
		}
		GlStateManager.color(1F, 1F, 1F);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		this.addButton(this.next = new ButtonBook(0, width / 2 + 105, height / 2 + 55, 28, 10, false));
		this.addButton(this.prev = new ButtonBook(1, width / 2 - 120, height / 2 + 55, 28, 10, true));
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if (mouseX < width / 2) {
			//Page page = this.PAGES.get(this.index);
			//for(Element line : page.lines) {
			
			//}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if (button == next && this.index < PAGES.size() - 2)
			index += 2;
		else if (button == prev && this.index > 1)
			index -= 2;
		System.out.println(index);
	}

	public static class Page{
		
		private List<Element> lines = new ArrayList<>();
		private int yLevel;
		
		public static Page read(ResourceLocation loc) {
			Page page = new Page();
			try {
				BufferedReader read = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream()));
				Iterator<String> iter = read.lines().iterator();
				while(iter.hasNext()) {
					String[] lines = iter.next().split("<br />");
					int index = 0;
					for(String line : lines) {
						Element element = Element.read(line);
						if (element != null) {
							line = line.substring(0, line.indexOf("<")) + line.substring(line.indexOf(">") + 1);
							page.addElement(element);
						}
						if (element == null && !line.isEmpty()) {
							element = new Element();
							element.type = "text";
							element.mod.put("text", line);
							page.lines.add(element);
						}
					}
				}
				read.close();
			}
			catch(Exception e) {}
			return page;
		}
		
		public void addElement(Element ele) {
			this.lines.add(ele);
		}
		
		public void draw(double x, double y) {
			FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
			this.yLevel = 0;
			for (Element line : lines) {
				if (line.type.equals("text"))
					fr.drawString(line.mod.get("text"), (int) x, (int) y + yLevel, 0x000000);
				else if (line.type.equals("img")) {
					int height = 50, width = 100;
					try {
						width = Integer.parseInt(line.mod.get("width"));
						height = Integer.parseInt(line.mod.get("height"));
					} catch (Exception e) {
					}
					int baseX = (int) x, baseY = (int) y + this.yLevel;
					Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(line.mod.get("src")));
					BufferBuilder bb = Tessellator.getInstance().getBuffer();
					GlStateManager.color(1F, 1, 1);
					bb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
					
					bb.pos(baseX, baseY, 10).tex(0, 0).endVertex();
					bb.pos(baseX, baseY + height, 10).tex(0, 1).endVertex();
					bb.pos(baseX + width, baseY + height, 10).tex(1, 1).endVertex();
					bb.pos(baseX + width, baseY, 10).tex(1, 0).endVertex();
					
					Tessellator.getInstance().draw();
					if (line.getHeight() == fr.FONT_HEIGHT)
						yLevel += height;
				} else if (line.type.equals("h1")) {
					GlStateManager.pushMatrix();
					GlStateManager.translate(x, y + this.yLevel, 10);
					GlStateManager.scale(1.5, 1.5, 0);
					fr.drawString(line.mod.get("text"), 0, 0, 0x000000);
					GlStateManager.popMatrix();
					yLevel += fr.FONT_HEIGHT * 1.5;
				}
				yLevel += line.getHeight();
			}
		}
	}
	
	public static class Element {
		
		public String type;
		public Map<String, String> mod = new HashMap<>();
		private int height;
		
		public static Element read(String line) {
			try {
				Element ele = new Element();
				String parsed = line.substring(line.indexOf('<') + 1, line.indexOf('>'));
				String[] div = parsed.split(" ");
				ele.type = div[0];
				for (String string : div) {
					if (string.contains("=")) {
						String[] split = string.split("=");
						ele.mod.put(split[0].replaceAll("\"", "").trim(), split[1].replaceAll("\"", ""));
					}
				}
				String rest = line.replaceAll(line.substring(line.indexOf("<"), line.indexOf(">")), "").replaceAll("<", "").replaceAll(">", "").trim();
				ele.mod.put("text", rest);
				return ele;
			} catch (Exception e) {
				return null;
			}
		}
		
		public int getHeight() {
			try {
				return mod.containsKey("height") ? Integer.parseInt(mod.get("height")) : Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
			} catch (Exception e) {
			}
			return 0;
		}
	}
	
	public static class ButtonBook extends GuiButton {
		
		private boolean isPrev;
		
		public ButtonBook(int buttonId, int x, int y, int widthIn, int heightIn, boolean isPrev) {
			super(buttonId, x, y, widthIn, heightIn, "");
			this.isPrev = isPrev;
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
			if (isPrev)
				this.drawTexturedModalRect(x, y, 2, 206, 18, 10);
			else this.drawTexturedModalRect(x, y, 3, 194, 18, 10);
		}
		
	}

}
