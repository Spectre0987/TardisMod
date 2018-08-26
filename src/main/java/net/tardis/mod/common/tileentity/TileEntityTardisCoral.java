package net.tardis.mod.common.tileentity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.world.Structures;
import net.tardis.mod.util.helpers.PlayerHelper;
import net.tardis.mod.util.helpers.RiftHelper;
import net.tardis.mod.util.helpers.TardisHelper;

import java.util.UUID;

public class TileEntityTardisCoral extends TileEntity implements ITickable{

	public int time = 0;
	public UUID owner;

	public TileEntityTardisCoral() {}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		time = compound.getInteger("time");
		owner = UUID.fromString(compound.getString("owner_id"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("time", time);
		compound.setString("owner_id", owner.toString());
		return super.writeToNBT(compound);
	}

	@Override
	public void update() {
		if(!world.isRemote && this.owner != null) {
			if(world.getWorldTime() % 2400 == 0) {
				if(time > (RiftHelper.isRift(world.getChunkFromBlockCoords(getPos()).getPos(), world) ? 1 : 2)) {
					this.grow();
					time = 0;
				}
				++time;
				this.markDirty();
			}
		}
	}

	public void grow() {
		BlockPos pos = TardisHelper.getTardis(owner);
		WorldServer tardisWorld = world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
		if(tardisWorld != null && pos != null) {
			TileEntity te = tardisWorld.getTileEntity(pos);
			if(te == null || !(te instanceof TileEntityTardis)) {
				Template tem = tardisWorld.getStructureTemplateManager().get(world.getMinecraftServer(), Structures.CONSOLE_ROOM_80S);
				tem.addBlocksToWorld(tardisWorld, pos.add(-(tem.getSize().getX() / 2), -2, (-(tem.getSize().getZ() / 2)) + 1), new PlacementSettings());
				tardisWorld.setBlockState(pos, TBlocks.console_02.getDefaultState());
				TileEntityTardis tardis = (TileEntityTardis)tardisWorld.getTileEntity(pos);
				this.getWorld().setBlockState(this.getPos(), Blocks.AIR.getDefaultState());
				tardis.setDesination(getPos().up(), this.getWorld().provider.getDimension());
				tardis.startFlight();
				tardis.travel();
				ItemStack keyStack = new ItemStack(TItems.key);
				ItemKey.setPos(keyStack, pos);

				// TODO We should maybe save pending keys to json? Else people who are offline will never get a key?

				EntityPlayerMP entityPlayer = world.getMinecraftServer().getPlayerList().getPlayerByUUID(owner);
				if (entityPlayer != null) {
					entityPlayer.addItemStackToInventory(keyStack);
					PlayerHelper.sendMessage(entityPlayer, "tardis.arrived", false);
				}

			}
		}
	}

	public void setOwner(UUID id) {
		this.owner = id;
		this.markDirty();
	}

}
