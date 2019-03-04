package net.tardis.mod.util.common.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.handlers.TEventHandler;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageSyncTardises;

public class TardisHelper {

	public static final int TARDIS_SIZE = 16;
	public static Map<String, BlockPos> tardisOwners = new HashMap<String, BlockPos>();

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
			for (BlockPos pos : tardisOwners.values().toArray(new BlockPos[]{})) {
				if (pos.getX() > last.getX() && pos.getZ() > last.getZ()) {
					last = pos;
				}
			}
			return last;
		}
		return new BlockPos(8, 128, 8);
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
		} else if (otherStack.getItem() instanceof ItemKey) {
			BlockPos pos = ItemKey.getPos(otherStack);
			return pos != null && pos.equals(cPos);
		}
		return false;
	}

	public static BlockPos getTardisForPosition(Vec3i vec) {
		for (BlockPos pos : tardisOwners.values()) {
			if (pos.getDistance(vec.getX(), vec.getY(), vec.getZ()) < (TARDIS_SIZE / 2) * 16 - 8) {
				return pos;
			}
		}
		return BlockPos.ORIGIN;
	}

	//If you use this on the client, I WILL come to your house with a baseball bat :) ~Fril
	@SideOnly(Side.SERVER)
	public static TileEntityTardis getConsole(BlockPos pos) {
		WorldServer worldServer = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(TDimensions.TARDIS_ID);

		if (worldServer.getTileEntity(pos) instanceof TileEntityTardis) {
			return (TileEntityTardis) worldServer.getTileEntity(pos);
		}
		return null;
	}

	@EventBusSubscriber(modid = Tardis.MODID)
	public static class Events {

		@SubscribeEvent
		public static void sync(LivingUpdateEvent event) {
			if (event.getEntityLiving().world.getWorldTime() % 200 == 0 && !event.getEntityLiving().world.isRemote && event.getEntityLiving() instanceof EntityPlayer) {
				NetworkHandler.NETWORK.sendTo(new MessageSyncTardises(), (EntityPlayerMP) event.getEntityLiving());
			}
		}
	}
}
