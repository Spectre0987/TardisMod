package net.tardis.mod.util.common.helpers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.tardis.mod.Tardis;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
			Tardis.LOG.info(e.getMessage());
		}
		return usernames;
	}
}
