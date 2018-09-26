package net.tardis.mod.common.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.blocks.BlockFoodMachine;
import net.tardis.mod.common.sounds.TSounds;

public class TileEntityFoodMachine extends TileEntity implements ITickable {

	private boolean active = false;
	private int ticks = 0;

	public TileEntityFoodMachine() {
		
	}
	
	public void makeFood() {
		if(!world.isRemote) {
			for(TileEntity te : world.getChunk(getPos()).getTileEntityMap().values()) {
				if(te != null && te instanceof TileEntityTardis) {
					TileEntityTardis tardis = (TileEntityTardis)te;
					if(tardis.fuel >= 0.01) {
						tardis.fuel -= 0.01;
						tardis.markDirty();
						active = true;
						world.playSound(null, getPos().getX(), getPos().getY(), getPos().getZ(), TSounds.FOOD_MACHINE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					}
				}
			}
		}
	}

	@Override
	public void update() {
		if (active) {
			ticks++;
		}

		if (ticks == 140) {
			BlockPos food = this.getPos().offset(world.getBlockState(getPos()).getValue(BlockFoodMachine.FACING).getOpposite());
			EntityItem ei = new EntityItem(world, food.getX(), food.getY(), food.getZ(), new ItemStack(Items.BREAD));
			world.spawnEntity(ei);
			ticks = 0;
			active = false;
		}
	}
}
