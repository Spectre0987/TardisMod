package net.tardis.mod.common.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.client.worldshell.BlockStorage;

public class EntityShip extends Entity{

	private Map<BlockPos, BlockStorage> blocks = new HashMap<BlockPos, BlockStorage>();
	
	public EntityShip(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void entityInit() {
		
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}
	
	public void land() {
		for(Entry<BlockPos, BlockStorage> entry : blocks.entrySet()) {
			world.setBlockState(entry.getKey(), entry.getValue().blockstate);
			TileEntity te = world.getTileEntity(entry.getKey());
			if(te != null) {
				te.readFromNBT(entry.getValue().tileentity);
			}
		}
	}
	
	public void move() {
		this.move(MoverType.SELF, motionX, motionY, motionZ);
		motionX = motionY = motionZ = 0;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase) {
			EntityLivingBase base = (EntityLivingBase)this.getControllingPassenger();
			if(base.moveForward > 0) {
				Vec3d look = base.getLookVec().scale(0.25);
				motionX = look.x;
				motionY = look.y;
				motionZ = look.z;
			}
		}
		move();
	}

	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().size() > 0 ? this.getPassengers().get(0) : null;
	}

	public Map<BlockPos, BlockStorage> getBlocks(){
		return this.blocks;
	}
	
	public void setBlocks(Map<BlockPos, BlockStorage> map) {
		this.blocks = map;
	}
	
	public void addBlock(BlockPos pos, BlockStorage stor) {
		this.blocks.put(pos, stor);
	}
}
