package net.tardis.mod.common.tileentity;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.tardis.mod.common.blocks.BlockFoodMachine;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.util.common.helpers.RiftHelper;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class TileEntityFoodMachine extends TileEntity implements ITickable {

	private int ticks = 0;
	private int count = 1;

	public TileEntityFoodMachine() {}

	private void makeFood() {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(TardisHelper.getTardisForPosition(this.getPos()));
			if (te != null && te instanceof TileEntityTardis) {
				TileEntityTardis tardis = ((TileEntityTardis) te);
				//TODO: Fuel Use
				if (tardis.getArtron() > 5) {
					tardis.setArtron(tardis.getArtron() - 5);
					EnumFacing face = world.getBlockState(getPos()).getValue(BlockFoodMachine.FACING);
					BlockPos pos = this.getPos().offset(face.getOpposite());
					InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.BREAD));
				}
			}
			else if(RiftHelper.isRift(world.getChunk(this.getPos()).getPos(), world)) {
				InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, new ItemStack(Items.BREAD));
			}
		}
	}

	@Override
	public void update() {
		if(ticks > 0) {
			--ticks;
			if(ticks == 0)
				for(int i = 0; i < count; ++i)
					makeFood();
		}
	}

	public void start(int count) {
		this.ticks = 160;
		this.count = count;
		world.playSound(null, this.getPos(), TSounds.FOOD_MACHINE, SoundCategory.BLOCKS, 1F, 1F);
	}
}
