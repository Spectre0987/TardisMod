package net.tardis.mod.common.tileentity;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.UUID;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.ars.ConsoleRoom;
import net.tardis.mod.common.blocks.BlockTardisCoral;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.util.common.helpers.RiftHelper;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class TileEntityTardisCoral extends TileEntity implements ITickable {

	public int time = 0;
	public int growStage = 0;
	public UUID owner;

	public TileEntityTardisCoral() {}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		time = compound.getInteger("time");
		this.growStage = compound.getInteger("grow");
		if(compound.hasKey("owner_id"))
			owner = UUID.fromString(compound.getString("owner_id"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("time", time);
		compound.setInteger("grow", growStage);
		if(owner != null)
			compound.setString("owner_id", owner.toString());
		return super.writeToNBT(compound);
	}

	@Override
	public void update() {
		if (!world.isRemote && this.owner != null) {
			if (world.getWorldTime() % 2400 == 0) {
				if (time > (RiftHelper.isRift(world.getChunk(getPos()).getPos(), world) ? 1 : 2)) {
					this.grow();
					time = 0;
				}
				++time;
				this.markDirty();
			}
			if(world.getWorldTime() % 600 == 0) {
				++this.growStage;
				if(growStage > 3) growStage = 3;
				world.setBlockState(this.getPos(), TBlocks.tardis_coral.getDefaultState().withProperty(BlockTardisCoral.GROW_STAGE, this.growStage));
			}
		}
	}

	public void grow() {
		if(world.isRemote) return;
		BlockPos pos = TardisHelper.getTardis(owner);
		WorldServer tardisWorld = world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
		if (tardisWorld != null && pos != null) {
			TileEntity te = tardisWorld.getTileEntity(pos);
			if (te == null || !(te instanceof TileEntityTardis)) {
				ConsoleRoom.CONSOLE_ROOMS.get(0).generate((world).getMinecraftServer().getWorld(TDimensions.TARDIS_ID), pos);
				tardisWorld.setBlockState(pos, TBlocks.console_02.getDefaultState());
				TileEntityTardis tardis = (TileEntityTardis) tardisWorld.getTileEntity(pos);
				this.getWorld().setBlockState(this.getPos(), Blocks.AIR.getDefaultState());
				tardis.setAbsoluteDesination(getPos(), this.getWorld().provider.getDimension());
				tardis.startFlight();
				tardis.travel();
				ItemStack keyStack = new ItemStack(TItems.key);
				ItemKey.setPos(keyStack, pos);

				EntityPlayerMP entityPlayer = world.getMinecraftServer().getPlayerList().getPlayerByUUID(owner);
				if (entityPlayer != null) {
					InventoryHelper.spawnItemStack(entityPlayer.world, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, keyStack);
				} else {
					try {
						File f = new File(world.getMinecraftServer().getDataDirectory() + "/pending_keys.json");
						HashMap<String, Long> map = new HashMap<>();
						if (f.exists()) {
							JsonReader jr = new JsonReader(new FileReader(f));
							jr.beginObject();
							while (jr.hasNext()) {
								map.put(jr.nextName(), Long.parseLong(jr.nextString()));
							}
							jr.endArray();
							jr.close();
						} else f.createNewFile();
						map.put(owner.toString(), pos.toLong());
						GsonBuilder gb = new GsonBuilder();
						gb.setPrettyPrinting();
						JsonWriter jw = gb.create().newJsonWriter(new FileWriter(f));
						jw.beginObject();
						for (String name : map.keySet()) {
							jw.name(name).value(map.get(name).toString());
						}
						jw.endObject();
						jw.close();
					} catch (Exception e) {
					}
				}
			}
		}
		getWorld().setBlockToAir(getPos()); //Just to make sure Coral goes bye bye
	}

	public void setOwner(UUID id) {
		this.owner = id;
		this.markDirty();
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return oldState.getBlock() != newSate.getBlock();
	}

}
