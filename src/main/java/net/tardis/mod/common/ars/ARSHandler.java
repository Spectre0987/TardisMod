package net.tardis.mod.common.ars;

import java.io.File;

public class ARSHandler {

	public static String FOLDER = "./mods/Tardis Mod/Structures";

	public static void init() {
		File file = new File(FOLDER);
		file.mkdir();
		file.mkdirs();
	}
}
