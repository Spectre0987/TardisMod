package net.tardis.mod.common.ars;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.IDoor;
import net.tardis.mod.common.entities.controls.ControlDoor;

public class ConsoleRoom {
	
	public static List<ConsoleRoom> CONSOLE_ROOMS = new ArrayList<>();
	
	private ResourceLocation preview;
	private ResourceLocation filePath;
	private BlockPos consolePos = BlockPos.ORIGIN;
	
	public ConsoleRoom(ResourceLocation preview, ResourceLocation filePath, BlockPos cp) {
		this.preview = preview;
		this.filePath = filePath;
		this.consolePos = cp;
	}
	
	public ResourceLocation getPreview() {
		return this.preview;
	}
	
	public ResourceLocation getFilePath() {
		return this.filePath;
	}
	
	AxisAlignedBB BB = new AxisAlignedBB(-20, -20, -20, 20, 20, 20);
	
	public void generate(WorldServer world, BlockPos pos) {
		for(Entity ent : world.getEntitiesWithinAABB(Entity.class, BB)) {
			if(ent instanceof IDoor || ent instanceof ControlDoor)
				ent.setDead();
		}
		Template temp = world.getStructureTemplateManager().get(world.getMinecraftServer(), filePath);
		PlacementSettings ps = new PlacementSettings();
		temp.addBlocksToWorld(world, pos.subtract(consolePos), ps);
	}
	
	public BlockPos getConsolePos() {
		return this.consolePos;
	}
	
	public static void registerConsoleRoom(String previewPath, String interiorName, BlockPos pos) {
		CONSOLE_ROOMS.add(new ConsoleRoom(new ResourceLocation(Tardis.MODID, previewPath), new ResourceLocation(Tardis.MODID, interiorName), pos));
	}
}
