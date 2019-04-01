package net.tardis.mod.common.items;

import java.util.HashMap;

import com.google.common.collect.Maps;

import net.tardis.mod.common.interfaces.IEntityFactory;

public class ItemBrakDoors extends ItemESpawn implements INeedMetadata{

	public ItemBrakDoors(IEntityFactory e) {
		super(e);
	}

	@Override
	public HashMap<Integer, String> getMeta() {
		HashMap map = Maps.newHashMap();
		map.put(0, this.getRegistryName().getNamespace());
		map.put(1, "brak_doors_open");
		return map;
	}

}
