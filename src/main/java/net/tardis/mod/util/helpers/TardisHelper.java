package net.tardis.mod.util.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.handlers.TEventHandler;

public class TardisHelper {

	public static Map<String, BlockPos> tardisOwners = new HashMap<String, BlockPos>();
	
	public static final int TARDIS_SIZE = 16;
	
	public static boolean isConsoleChunk(Chunk c) {
		if (c.x % TARDIS_SIZE == 0 && c.z % TARDIS_SIZE == 0)
			return true;
		return false;
	}
	
	public static boolean hasTardis(UUID id) {
		return tardisOwners.containsKey(id.toString());
	}
	
	public static BlockPos getTardis(UUID id) {
		if (hasTardis(id)) {
			return tardisOwners.get(id.toString());
		}
		return addTardis(id);
	}
	
	public static BlockPos addTardis(UUID id) {
		BlockPos pos = getNextFree();
		tardisOwners.put(id.toString(), pos.toImmutable());
		if (TEventHandler.data != null)
			TEventHandler.data.markDirty();
		return pos;
	}
	
	public static BlockPos getLastPos() {
		int size = tardisOwners.size();
		if (size > 0)
			return tardisOwners.values().toArray(new BlockPos[1])[size - 1];
		return new BlockPos(8, 6, 8);
	}
	
	public static BlockPos getNextFree() {
		return getLastPos().add(TARDIS_SIZE >> 4, 0, TARDIS_SIZE >> 4);
	}
	
	public static boolean hasValidKey(EntityPlayer player, BlockPos cPos) {
		ItemStack stack = player.getHeldItemMainhand();
		if (stack.getItem() == TItems.key) {
			BlockPos pos = ItemKey.getPos(stack);
			if (pos != null && pos.equals(cPos))
				return true;
		}
		return false;
	}
	
}
