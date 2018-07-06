package net.tardis.mod.common.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.blocks.BlockFoodMachine;

public class TileEntityFoodMachine extends TileEntity {
	
	public TileEntityFoodMachine() {
		
	}
	
	public void makeFood() {
		if(!world.isRemote) {
			for(TileEntity te : world.getChunkFromBlockCoords(getPos()).getTileEntityMap().values()) {
				if(te != null && te instanceof TileEntityTardis) {
					TileEntityTardis tardis = (TileEntityTardis)te;
					if(tardis.fuel >= 0.01) {
						tardis.fuel -= 0.01;
						tardis.markDirty();
						BlockPos food = this.getPos().offset(world.getBlockState(getPos()).getValue(BlockFoodMachine.FACING).getOpposite());
						EntityItem ei = new EntityItem(world, food.getX(), food.getY(), food.getZ(), new ItemStack(Items.BREAD));
						world.spawnEntity(ei);
					}
				}
			}
		}
	}
}
