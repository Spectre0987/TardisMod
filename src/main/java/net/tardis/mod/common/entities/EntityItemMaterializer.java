package net.tardis.mod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;

public class EntityItemMaterializer extends Entity{

	public static final float DELTA_ALPHA = 0.01F;
	private float alpha = 0F;

	public EntityItemMaterializer(World worldIn) {
		super(worldIn);
	}

	public static final DataParameter<NBTTagCompound> ITEM = EntityDataManager.createKey(EntityItemMaterializer.class, DataSerializers.COMPOUND_TAG);

	@Override
	protected void entityInit() {
		this.dataManager.register(ITEM, new ItemStack(Items.APPLE).serializeNBT());
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
		this.alpha += DELTA_ALPHA;
		this.setItem(new ItemStack(Items.ARROW));
		if(!world.isRemote && alpha >= 1.0F) {
			EntityItem ei = new EntityItem(world, posX, posY, posZ, new ItemStack(this.dataManager.get(ITEM)));
			world.spawnEntity(ei);
			this.setDead();
		}
		if(!world.isRemote && this.ticksExisted == 1){
			world.playSound(null, this.getPosition(), TSounds.tardis_land, SoundCategory.NEUTRAL, 1F, 1F);
		}
			
	}

	public float getAlpha() {
		return alpha;
	}

}
