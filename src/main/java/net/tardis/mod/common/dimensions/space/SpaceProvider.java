package net.tardis.mod.common.dimensions.space;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.renderers.sky.RenderVoid;
import net.tardis.mod.common.dimensions.TDimensions;

public class SpaceProvider extends WorldProvider {

	public Map<BlockPos, SpaceEvent> space = new HashMap<>();
	
	public SpaceProvider() {
	}

	@Override
	public DimensionType getDimensionType() {
		return TDimensions.spaceType;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorSpace(this.world);
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.5F;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public float getCloudHeight() {
		return 1000;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getCloudRenderer() {
		return new RenderVoid();
	}

	@Override
	public boolean shouldMapSpin(String entity, double x, double z, double rotation) {
		return true;
	}

	@Override
	public void calculateInitialWeather() {
	}

	@Override
	public void updateWeather() {
	}

	@Override
	public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
		return false;
	}

	@Override
	public void onPlayerAdded(EntityPlayerMP player) {
		super.onPlayerAdded(player);
		player.setNoGravity(true);
	}

	@Override
	public void onPlayerRemoved(EntityPlayerMP player) {
		super.onPlayerRemoved(player);
		player.setNoGravity(false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer() {
		return new RenderSpace();
	}

	@Override
	public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
		return WorldSleepResult.DENY;
	}
	
	public static class SpaceEvent{
		
		public World world;
		private BlockPos pos;
		
		/**Also adds it to the space world**/
		public SpaceEvent(World world, BlockPos pos) {
			this.world = world;
			this.pos = pos;
			if(world.provider instanceof SpaceProvider) {
				((SpaceProvider)world.provider).space.put(pos, this);
			}
		}
		
		public void update() {}
		
		public NBTTagCompound writeNBT(NBTTagCompound tag) {
			tag.setLong("pos", this.pos.toLong());
			return tag;
		}
		
		public void readFromNBT(NBTTagCompound tag) {
			this.pos = BlockPos.fromLong(tag.getLong("pos"));
		}
		
		public BlockPos getPos() {
			return this.pos;
		}
	}
	
	@EventBusSubscriber(modid = Tardis.MODID)
	public static class Events{
		
		@SubscribeEvent
		public static void update(WorldTickEvent event) {
			if(event.world.provider instanceof SpaceProvider) {
				for(Entry<BlockPos, SpaceEvent> entry : ((SpaceProvider)event.world.provider).space.entrySet()) {
					if(event.world.isBlockLoaded(entry.getKey())) {
						entry.getValue().update();
					}
				}
			}
		}
	}

}
