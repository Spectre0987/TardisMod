package net.tardis.mod.common.ars;

import net.minecraft.util.ResourceLocation;

public class ARS {

	
	public static enum EnumDirection{
		RIGHT,
		LEFT,
		STRIAGHT;
	}
	
	public static class CorridorPiece{
		
		private ResourceLocation template;
		private EnumDirection[] directions;
	}
}
