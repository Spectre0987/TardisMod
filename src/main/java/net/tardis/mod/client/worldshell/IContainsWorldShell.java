package net.tardis.mod.client.worldshell;

import javax.annotation.Nullable;

import net.minecraft.world.World;

public interface IContainsWorldShell {

	//implement in entity's that contain world shells to allow them to be rendered using RenderWorldShell

	WorldShell getWorldShell();

	void setWorldShell(WorldShell worldShell);

	/**
	 * Gets the dimension for the sky renderers
	 */
	int getDimension();
	
	boolean requiresUpdate();
	void setRequiresUpdate(boolean bool);
	
	/**Incase you're thicker than some, this should NOT be called on the server.**/
	@Nullable
	World getRenderWorld();

}
