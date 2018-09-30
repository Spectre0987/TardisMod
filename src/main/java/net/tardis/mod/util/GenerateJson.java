package net.tardis.mod.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.tardis.mod.Tardis;
import net.tardis.mod.util.common.helpers.Helper;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class GenerateJson {
	
	/**
	 * Author Spectre0987
	 * 
	 * Mostly adapted from Sub's code.
	 * **/
	
	public static void generateFacingBlockstate(String name) {
		
		try{
			File f = new File(Minecraft.getMinecraft().gameDir.getAbsolutePath() + "\\..\\src\\main\\resources\\assets\\tardis\\blockstates\\" + name + ".json");
			if(!f.exists())f.createNewFile();
            Writer fw = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonWriter jw = gson.newJsonWriter(fw);

			String model = Tardis.MODID + ":" + name;
			jw.beginObject();
			jw.name("forge_marker").value(1);
			jw.name("defaults");
			jw.beginObject();
			jw.name("model").value(model);
			jw.endObject();
			jw.name("variants");
			jw.beginObject();
			for(EnumFacing face : EnumFacing.HORIZONTALS) {
				jw.name("facing=" + face.getName2());
				jw.beginObject();
				jw.name("model").value(model);
				jw.name("y").value(Helper.getAngleFromFacing(face));
				jw.endObject();
			}
			jw.name("inventory");
			writeJSONVarient(jw, model);
			
			jw.name("normal");
			writeJSONVarient(jw, model);
			
			jw.endObject();
			jw.endObject();
			
			fw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void genCubeAll(String name) {
		try {
			File f = new File(Minecraft.getMinecraft().gameDir.getAbsolutePath() + "\\..\\src\\main\\resources\\assets\\tardis\\blockstates\\" + name + ".json");
			if(!f.exists())f.createNewFile();
            Writer writer = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonWriter jw = gson.newJsonWriter(writer);
			
			jw.beginObject();
			jw.name("forge_marker").value(1);
			jw.name("defaults");
			jw.beginObject();
			jw.name("model").value("cube_all");
			jw.name("textures");
			jw.beginObject();
			jw.name("all").value(Tardis.MODID + ":blocks/hellbent/" + name);
			jw.endObject();
			jw.endObject();
			
			jw.name("variants");
			jw.beginObject();
			
			jw.name("inventory");
			jw.beginArray();
			jw.beginObject();
			
			jw.endObject();
			jw.endArray();
			
			jw.name("normal");
			jw.beginArray();
			jw.beginObject();
			
			jw.endObject();
			jw.endArray();
			
			jw.endObject();
			jw.endObject();
			
			writer.close();
		}
		catch(Exception e){}
	}
	
	public static void genHalfSlab(String name) {
		try {
			File f = new File(Minecraft.getMinecraft().gameDir.getAbsolutePath() + "\\..\\src\\main\\resources\\assets\\tardis\\blockstates\\" + name + ".json");
			if(!f.exists())f.createNewFile();
            Writer writer = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonWriter jw = gson.newJsonWriter(writer);
			
			jw.beginObject();
			jw.name("forge_marker").value(1);
			jw.name("defaults");
			jw.beginObject();
			jw.name("model").value("half_slab");
			jw.name("textures");
			jw.beginObject();
			jw.name("bottom").value(Tardis.MODID + ":blocks/" +name);
			jw.name("top").value(Tardis.MODID + ":blocks/" + name);
			jw.name("side").value(Tardis.MODID + ":blocks/" + name);
			jw.endObject();
			jw.endObject();
			jw.name("variants");
			jw.beginObject();
			
			jw.name("top=true");
			jw.beginArray();
			jw.beginObject();
			jw.endObject();
			jw.endArray();
			
			jw.name("top=false");
			jw.beginArray();
			jw.beginObject();
			jw.name("x").value(180);
			jw.endObject();
			jw.endArray();
			
			jw.endObject();
			jw.endObject();
			writer.close();
		}
		catch(Exception e) {}
	}
	public static void writeJSONVarient(JsonWriter jw, String model) throws IOException {
		jw.beginObject();
		jw.name("model").value(model);
		jw.endObject();
	}
}
