package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.items.ItemSonic;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class ControlSonicSlot extends EntityControl {
	
	public static final DataParameter<NBTTagCompound> ITEM = EntityDataManager.createKey(ControlSonicSlot.class, DataSerializers.COMPOUND_TAG);
	
	public ControlSonicSlot(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlSonicSlot(World world) {
		super(world);
		this.setSize(0.0625F, 0.0625F);
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		return Helper.convertToPixels(11.25, -1.5, 6.25);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if(player.getHeldItemMainhand().getItem() instanceof ItemSonic && this.getItemStack().isEmpty()) {
			this.setItemStack(player.getHeldItemMainhand());
			player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
		}
		else if(player.getHeldItemMainhand().isEmpty() && !this.getItemStack().isEmpty()){
			player.setHeldItem(EnumHand.MAIN_HAND, this.getItemStack());
			this.setItemStack(ItemStack.EMPTY);
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(ITEM, ItemStack.EMPTY.writeToNBT(new NBTTagCompound()));
	}
	
	public void setItemStack(ItemStack stack) {
		this.getDataManager().set(ITEM, stack.writeToNBT(new NBTTagCompound()));
	}
	
	public ItemStack getItemStack() {
		return new ItemStack(this.getDataManager().get(ITEM));
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(world.getWorldTime() % 20 == 0) {
			ItemStack stack = this.getItemStack();
			if(!stack.isEmpty() && stack.getItem() instanceof ItemSonic) {
				ItemSonic.setCharge(getItemStack(), ItemSonic.getCharge(this.getItemStack()) + 1);
			}
			this.rotationYaw = 65;
		}
	}
	
}
