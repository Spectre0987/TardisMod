package net.tardis.mod.proxy;

import net.minecraft.util.math.BlockPos;

public class ServerProxy {

	public void preInit() {
	}

	public void init() {
	}

	public void postInit() {
	}

	public void openGui(int id, GuiContext context) {}
	
	public static class GuiContext{
		
		public BlockPos pos = BlockPos.ORIGIN;
		public int dimension = 0;
		public int entityID = 0;
		
		public GuiContext(int dimension, int entityID, BlockPos pos) {
			this.dimension = dimension;
			this.entityID = entityID;
			this.pos = pos;
		}
	}
}
