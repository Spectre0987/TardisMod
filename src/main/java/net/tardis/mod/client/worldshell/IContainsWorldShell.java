package net.tardis.mod.client.worldshell;

public interface IContainsWorldShell {
	
	//implement in entity's that contain world shells to allow them to be rendered using RenderWorldShell
	
	public WorldShell getWorldShell();
	
	public void setWorldShell(WorldShell worldShell);
	
}
