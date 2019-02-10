package net.tardis.mod.common.ars;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.tileentity.TileEntityTardis;

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
	
	public void generate(WorldServer world, BlockPos pos) {
		for(Entity entity : world.getEntitiesWithinAABB(Entity.class, Block.FULL_BLOCK_AABB.offset(pos).grow(20))) {
			if(!(entity instanceof EntityControl) || !(entity instanceof EntityLivingBase)) {
				entity.setDead();
				world.updateEntities(); 
				world.updateEntityWithOptionalForce(entity, true);
			}
		}
		Template temp = world.getStructureTemplateManager().get(world.getMinecraftServer(), filePath);
		PlacementSettings ps = new PlacementSettings();
		IBlockState consoleState = world.getBlockState(pos);
		TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(pos);
		temp.addBlocksToWorld(world, pos.subtract(consolePos), ps);
		world.setBlockState(pos, consoleState);
		if(tardis != null)
			((TileEntityTardis)world.getTileEntity(pos)).readFromNBT(tardis.writeToNBT(new NBTTagCompound()));
	}
	
	public BlockPos getConsolePos(){
		return this.consolePos;
	}
}
