package net.tardis.mod.common.capabilty;

public interface ITimeLord {
	
	int getRegenerationsLeft();
	
	/**
	 * Uses a Regeneration and returns remaining regenerations
	 * 
	 ****/
	int useRegeneration();
}
