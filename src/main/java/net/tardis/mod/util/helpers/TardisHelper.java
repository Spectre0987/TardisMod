package net.tardis.mod.util.helpers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.handlers.TEventHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TardisHelper {
	
	public static Map<String, BlockPos> tardisOwners = new HashMap<String, BlockPos>();

	public static final int TARDIS_SIZE = 50;
	
	public static boolean isConsoleChunk(Chunk c) {
		return c.x % TARDIS_SIZE == 0 && c.z % TARDIS_SIZE == 0;
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
		if (TEventHandler.data != null) TEventHandler.data.markDirty();
		return pos;
	}
	
	public static BlockPos getLastPos() {
		int size = tardisOwners.size();
		if (size > 0) {
			BlockPos last = BlockPos.ORIGIN;
			for(BlockPos pos : tardisOwners.values().toArray(new BlockPos[] {})) {
				if(pos.getX() > last.getX() && pos.getZ() > last.getZ()) {
					last = pos;
				}
			}
			return last;
		}
		return new BlockPos(8, 6, 8);
	}
	
	public static BlockPos getNextFree() {
		return getLastPos().add(TARDIS_SIZE * 16, 0, TARDIS_SIZE * 16);
	}
	
	public static boolean hasValidKey(EntityLivingBase player, BlockPos cPos) {
		ItemStack stack = player.getHeldItemMainhand();
		ItemStack otherStack = player.getHeldItemOffhand();
		if (stack.getItem() instanceof ItemKey) {
			BlockPos pos = ItemKey.getPos(stack);
			return pos != null && pos.equals(cPos);
		}
		else if(otherStack.getItem() instanceof ItemKey) {
			BlockPos pos = ItemKey.getPos(otherStack);
			return pos != null && pos.equals(cPos);
		}
		return false;
	}
	
}
