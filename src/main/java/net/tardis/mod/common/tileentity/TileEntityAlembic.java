package net.tardis.mod.common.tileentity;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;

public class TileEntityAlembic extends TileEntity implements ITickable {
	
	private boolean water = false;
	private ItemStack stack = ItemStack.EMPTY;
	private ItemStack result = ItemStack.EMPTY;
	private int timeLeft = 0;
	
	public TileEntityAlembic() {
		
	}
	
	public void setWater(boolean bool) {
		this.water = bool;
		this.markDirty();
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
		this.markDirty();
	}
	
	public void setResult(ItemStack stack) {
		this.result = stack;
		this.markDirty();
	}
	
	public boolean hasWater() {
		return this.water;
	}
	
	public ItemStack getResult() {
		return this.result;
	}
	
	public ItemStack getStack() {
		return this.stack;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.water = compound.getBoolean("water");
		this.stack = new ItemStack((NBTTagCompound)compound.getTag("stack"));
		this.result = new ItemStack((NBTTagCompound)compound.getTag("result"));
		this.timeLeft = compound.getInteger("time");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("water", water);
		compound.setTag("stack", this.stack.writeToNBT(new NBTTagCompound()));
		compound.setTag("result", this.result.writeToNBT(new NBTTagCompound()));
		compound.setInteger("time", this.timeLeft);
		return super.writeToNBT(compound);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, -1, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		if(world.isRemote) {
			readFromNBT(pkt.getNbtCompound());
		}
	}

	@Override
	public void update() {
		if(!this.stack.isEmpty()) {
			if(AlembicRecipe.getItemResult(this.stack.getItem()) != null) {
				if(this.timeLeft <= 0) {
					System.out.println("Time");
					ItemStack add = new ItemStack(AlembicRecipe.getItemResult(this.stack.getItem()));
					if(result.isEmpty()) {
						result = add;
						stack.shrink(1);
						this.markDirty();
					}
					else if(add.getItem() == this.stack.getItem()) {
						if(add.getCount() + result.getCount() <= result.getMaxStackSize()) {
							result.setCount(add.getCount() + result.getCount());
							stack.shrink(add.getCount());
						}
						else {
							int amtToAdd = result.getMaxStackSize() - result.getCount();
							result.setCount(result.getMaxStackSize());
							stack.shrink(amtToAdd);
						}
						this.markDirty();
					}
				}
			}
			--timeLeft;
			if(this.timeLeft < 0)
				this.timeLeft = 200;
			if(!world.isRemote) {
				for(EntityPlayerMP players : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(getPos()).grow(10D))) {
					players.connection.sendPacket(this.getUpdatePacket());
				}
			}
		}
		if(world.isRemote && world.getWorldTime() % 10 == 0 && !this.getStack().isEmpty()) {
			world.spawnParticle(this.getResult().isEmpty() ? EnumParticleTypes.SMOKE_NORMAL : EnumParticleTypes.CLOUD, getPos().getX() + 0.5, getPos().getY() + 1, getPos().getZ() + 0.5, 0, 0.1, 0, 0);
		}
	}
	
	public static class AlembicRecipe{
		
		private static HashMap<Item, Item> ITEMS = new HashMap<>();
		
		public static Item getItemResult(Item item) {
			if(ITEMS.containsKey(item)) {
				return ITEMS.get(item);
			}
			return null;
		}
		
		public static void registerRecipe(Item item, Item item1) {
			ITEMS.put(item, item1);
		}
	}
}
