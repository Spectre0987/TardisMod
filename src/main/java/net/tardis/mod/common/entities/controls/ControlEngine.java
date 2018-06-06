package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.info.CrashType;
import net.tardis.mod.common.items.components.ItemComponent;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ControlEngine extends EntityControl {
	
	private NonNullList<ItemStack> inv = NonNullList.create().withSize(9, ItemStack.EMPTY);
	
	public ControlEngine(World world) {
		super(world);
		this.setSize(1F, 1F);
	}
	
	public ControlEngine(TileEntityTardis tardis) {
		super(tardis);
	}
	
	@Override
	public Vec3d getOffset() {
		return new Vec3d(0, -1, 0);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {}
	
	public void onCrash(CrashType type) {
		for (ItemStack stack : inv) {
			if (stack.getItem() instanceof ItemComponent) {
				((ItemComponent) stack.getItem()).damageItem(type, stack);
			}
		}
	}
	
}
