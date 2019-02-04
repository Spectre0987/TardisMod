package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;

public class EntityItemMaterializer extends Entity{
	
	public static float deltaAlpha = 0.01F;
	public float alpha = 0F;
	
	public EntityItemMaterializer(World worldIn) {
		super(worldIn);
	}

	public static final DataParameter<NBTTagCompound> ITEM = EntityDataManager.createKey(EntityItemMaterializer.class, DataSerializers.COMPOUND_TAG);

	@Override
	protected void entityInit() {
		this.dataManager.register(ITEM, new ItemStack(TItems.demat_circut).serializeNBT());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.dataManager.set(ITEM, compound.getCompoundTag("item"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setTag("item", this.dataManager.get(ITEM));
	}
	
	public void setItem(ItemStack stack) {
		this.dataManager.set(ITEM, stack.serializeNBT()); 
	}

	public ItemStack getItem() {
		return new ItemStack(this.dataManager.get(ITEM));
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		this.setItem(new ItemStack(TItems.chameleon_circuit));
		this.alpha += deltaAlpha;
		if(!world.isRemote && alpha >= 1) {
			EntityItem ei = new EntityItem(world, posX, posY, posZ, new ItemStack(this.dataManager.get(ITEM)));
			world.spawnEntity(ei);
			this.setDead();
		}
	}

}
