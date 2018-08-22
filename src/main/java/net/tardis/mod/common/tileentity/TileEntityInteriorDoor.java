package net.tardis.mod.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.client.worldshell.BlockStorage;
import net.tardis.mod.client.worldshell.IContainsWorldShell;
import net.tardis.mod.client.worldshell.MessageSyncWorldShell;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.enums.EnumInteriorDoor;
import net.tardis.mod.packets.NetworkHandler;

public class TileEntityInteriorDoor extends TileEntity implements ITickable, IContainsWorldShell {
	
	private WorldShell shell = new WorldShell(BlockPos.ORIGIN);
	private Vec3i rad = new Vec3i(10, 10, 10);

	private boolean open;
	private EnumInteriorDoor doorType;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		setOpen(compound.getBoolean("open"));
		setDoorType(EnumInteriorDoor.valueOf(compound.getString("type")));
	}

	public EnumInteriorDoor getDoorType() {
		return doorType;
	}

	public void setDoorType(EnumInteriorDoor doorType) {
		this.doorType = doorType;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("open", open);
		compound.setString("type", doorType.toString());
		return compound;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public TileEntityInteriorDoor() {}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(-1, 0, 0, 2, 4, 1).offset(getPos());
	}

	@Override
	public WorldShell getWorldShell() {
		return shell;
	}

	@Override
	public void setWorldShell(WorldShell worldShell) {
		this.shell = worldShell;
	}
	
	@Override
	public void update() {
		if(!world.isRemote) {
			TileEntityTardis tardis = null;
			for(TileEntity te : this.world.getChunkFromBlockCoords(this.getPos()).getTileEntityMap().values()) {
				if(te instanceof TileEntityTardis) {
					tardis = (TileEntityTardis)te;
				}
			}
			if(tardis != null) {
				WorldServer ws = world.getMinecraftServer().getWorld(tardis.dimension);
				shell = new WorldShell(tardis.getLocation());
				for(BlockPos pos : BlockPos.getAllInBox(tardis.getLocation().subtract(rad), tardis.getLocation().add(rad))) {
					this.shell.blockMap.put(pos, new BlockStorage(ws.getBlockState(pos), ws.getTileEntity(pos), ws.getLight(pos)));
				}
			}
			NetworkHandler.NETWORK.sendToAllAround(new MessageSyncWorldShell(this.shell, this.getPos()), new TargetPoint(world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 16));
		}
	}

}
