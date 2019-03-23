package net.tardis.mod.client.worldshell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.network.NetworkHandler;

import java.util.ArrayList;
import java.util.Objects;

@SideOnly(Side.CLIENT)
public class FakeWorldBoti extends WorldClient {
	
	public ChunkProviderClient clientChunkProvider;
	public FakeRenderGlobal renderGlobal = new FakeRenderGlobal(Minecraft.getMinecraft());
	
	private static ArrayList<FakeWorldBoti> fakeWorlds = new ArrayList<>();
	
	public static FakeWorldBoti getFakeWorld(int dimensionID) {
		if (fakeWorlds.stream().noneMatch(world -> world.dimensionID == dimensionID)) {
			FakeWorldBoti fakeWorld = new FakeWorldBoti(dimensionID);
			fakeWorlds.add(fakeWorld);
			return fakeWorld;
		} else return fakeWorlds.stream().filter(world -> world.dimensionID == dimensionID).findAny().get();
	}
	
	public int dimensionID;
	
	public FakeWorldBoti(int dimensionID) {
		super(Objects.requireNonNull(Minecraft.getMinecraft().getConnection()), new WorldSettings(0L, GameType.SURVIVAL, true, false, WorldType.DEFAULT), dimensionID, Minecraft.getMinecraft().gameSettings.difficulty, Minecraft.getMinecraft().world.profiler);
		renderGlobal.setWorldAndLoadRenderers(this);
		this.dimensionID = dimensionID;
		
		/* If not called, there would be NPE any time blocks accessed */
		this.chunkProvider = this.createChunkProvider();
		
	}
	
	@Override
	protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
		return allowEmpty || !this.getChunkProvider().provideChunk(x, z).isEmpty();
	}
	
	@Override
	protected IChunkProvider createChunkProvider() {
		this.clientChunkProvider = new ChunkProviderClient(this);
		
		return this.clientChunkProvider;
	}
	
	public static void requestChunks(int dim) {
		if (Minecraft.getMinecraft().world.getTotalWorldTime() % 200 == 0) { //TODO optimize
			NetworkHandler.NETWORK.sendToServer(new MessageRequestChunks(0, 0, 40, dim)); //TODO origin
		}
		
	}
	
	
	/**
	 * This method fixes issues with lighting not being updated
	 * when structure template is being pasted into constructed
	 * world.
	 */
	@Override
	public boolean isAreaLoaded(BlockPos center, int radius, boolean allowEmpty) {
		return true;
	}
	
	public class FakeRenderGlobal extends RenderGlobal {
		public FakeRenderGlobal(Minecraft mcIn) {
			super(mcIn);
		}
		
		@Override
		public void renderSky(float partialTicks, int pass) {
			super.renderSky(partialTicks, pass);
		}
	}
	
}