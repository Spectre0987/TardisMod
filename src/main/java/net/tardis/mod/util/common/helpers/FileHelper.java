package net.tardis.mod.util.common.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tardis.mod.common.tileentity.TileEntityEgg;

public class FileHelper {

	public static Map<UUID, String> getPlayersFromServerFile() {
		File usernameCache = FMLCommonHandler.instance().getMinecraftServerInstance().getFile("usernamecache.json");
		Map<UUID, String> usernames = new HashMap<>();
		try {
			JsonObject object = new JsonParser().parse(new FileReader(usernameCache)).getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entrySet = object.entrySet();
			for (Map.Entry<String, JsonElement> elem : entrySet) {
				usernames.put(UUID.fromString(elem.getKey()), object.get(elem.getKey()).getAsString());
			}
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
		return usernames;
	}

	public static void readOrWriteARS(File dir) {
		try {
			dir = new File(dir.getPath() + File.separator + "tardis_ars.json");
			if(dir.exists()) {
				JsonReader reader = new GsonBuilder().create().newJsonReader(new FileReader(dir));
				reader.beginArray();
				while(reader.hasNext()) {
					ItemStack stack = new ItemStack(Item.getByNameOrId(reader.nextString()), 64);
					TileEntityEgg.register(stack);
				}
				reader.endArray();
				reader.close();
			}
			else {
				dir.createNewFile();
				JsonWriter writer = new GsonBuilder().setPrettyPrinting().create().newJsonWriter(new FileWriter(dir));
				writer.beginArray();
				writer.value("tardis:toyota_hexagon_1");
				writer.endArray();
				writer.close();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
