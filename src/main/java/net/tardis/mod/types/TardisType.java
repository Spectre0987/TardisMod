package net.tardis.mod.types;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TardisType {

	public abstract class Common{
		
	}
	
	@SideOnly(Side.CLIENT)
	public abstract class Client{
		
		public abstract void renderExterior();
		
		public abstract void renderConsole();
	}	
}
