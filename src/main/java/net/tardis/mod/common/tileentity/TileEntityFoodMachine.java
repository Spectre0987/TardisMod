package net.tardis.mod.common.tileentity;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.blocks.BlockFoodMachine;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class TileEntityFoodMachine extends TileEntity implements ITickable {

	private int ticks = 0;

	public TileEntityFoodMachine() {

	}

	private void makeFood() {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(TardisHelper.getTardisForPosition(this.getPos()));
			if(te != null && te instanceof TileEntityTardis) {
				TileEntityTardis tardis = ((TileEntityTardis)te);
				if(tardis.fuel > 0.01) {
					tardis.setFuel(tardis.fuel - 0.01F);
					EnumFacing face = world.getBlockState(getPos()).getValue(BlockFoodMachine.FACING);
					BlockPos pos = this.getPos().offset(face);
					InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BREAD));
				}
			}
		}
	}

	@Override
	public void update() {
		if(ticks > 0) {
			--ticks;
			if(ticks == 0)
				makeFood();
		}
	}

	public void start() {
		this.ticks = 100;
	}
}
