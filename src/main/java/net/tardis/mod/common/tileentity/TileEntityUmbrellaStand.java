package net.tardis.mod.common.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityUmbrellaStand extends TileEntity implements ITickable {
	
	public ItemStack umbrella = ItemStack.EMPTY;
	int ticks = 20;
	
	public TileEntityUmbrellaStand() {}
	
	public void setItem(ItemStack stack) {
		if (!world.isRemote && !umbrella.isEmpty()) {
			EntityItem ei = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, umbrella);
			world.spawnEntity(ei);
		}
		umbrella = stack;
		this.markDirty();
	}
	
	public ItemStack getUmbrella() {
		return this.umbrella;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		umbrella = new ItemStack(tag.getCompoundTag("umbrella"));
		super.readFromNBT(tag);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setTag("umbrella", umbrella.writeToNBT(new NBTTagCompound()));
		return super.writeToNBT(tag);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, -1, this.getUpdateTag());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("item", umbrella.writeToNBT(new NBTTagCompound()));
		return tag;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		if (world.isRemote) {
			umbrella = new ItemStack(pkt.getNbtCompound().getCompoundTag("item"));
		}
		super.onDataPacket(net, pkt);
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		if (!world.isRemote) {
			for (EntityPlayer player : world.playerEntities) {
				((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
			}
		}
		
	}
	
	@Override
	public void update() {
		if (!world.isRemote) {
			--ticks;
			if (ticks <= 0) {
				ticks = 20;
				for (EntityPlayer player : world.playerEntities) {
					((EntityPlayerMP) player).connection.sendPacket(this.getUpdatePacket());
				}
			}
		}
	}
	
}
