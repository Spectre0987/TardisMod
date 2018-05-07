package net.tardis.mod.common.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.tardis.mod.recipes.TemporalRecipe;

public class TileEntityTemporalLab extends TileEntity implements ITickable {
	
	int ticks = 0;
	public TemporalRecipe currentRecipe;
	public ItemStack result = ItemStack.EMPTY;
	
	public TileEntityTemporalLab() {}
	
	@Override
	public void update() {
		if (currentRecipe != null) {
			++ticks;
			if (ticks >= currentRecipe.getTime()) {
				ticks = 0;
				result = currentRecipe.getResult().copy();
				currentRecipe = null;
				this.markDirty();
			}
		}
		if (!world.isRemote && !result.isEmpty()) {
			EntityItem ei = new EntityItem(world, this.getPos().getX() + 0.5, this.getPos().getY() + 1, this.getPos().getZ() + 0.5, result);
			world.spawnEntity(ei);
			this.result = ItemStack.EMPTY;
		}
	}
	
	public void setTemporalRecipe(TemporalRecipe rec) {
		this.currentRecipe = rec;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		result = new ItemStack(tag.getCompoundTag("result"));
		currentRecipe = TemporalRecipe.recipes.get(tag.getInteger("recipeID"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("result", result.writeToNBT(new NBTTagCompound()));
		tag.setInteger("recipeID", currentRecipe.id);
		return super.writeToNBT(tag);
	}
	
}
