package net.tardis.mod.common.entities.vehicles;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBike extends EntityLiving{

	public EntityBike(World worldIn) {
		super(worldIn);
		this.stepHeight = 1.5F;
		this.setSize(2, 1.5F);
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase) {
			EntityLivingBase base = (EntityLivingBase)this.getControllingPassenger();
			boolean motionChanged = false;
			if(!world.isRemote) {
				if(this.world.getBlockState(this.getPosition().offset(this.getHorizontalFacing())).getMaterial() != Material.AIR && base.moveForward > 0) {
					motionY += 0.15;
					motionChanged = true;
				}
				if(base.moveForward > 0) {
					Vec3d move = base.getLookVec().scale(0.5);
					this.motionX = move.x;
					this.motionZ = move.z;
					motionChanged = true;
					
				}
				for(EntityPlayerMP mp : world.getEntitiesWithinAABB(EntityPlayerMP.class, Block.FULL_BLOCK_AABB.offset(this.getPositionVector()).grow(20))) {
					mp.connection.sendPacket(new SPacketEntityVelocity(this));
				}
			}
			this.rotationYaw = base.rotationYawHead;
		}
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public boolean canBeSteered() {
		return true;
	}

	@Override
	public boolean canPassengerSteer() {
		return true;
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
		super.updateFallState(y, onGroundIn, state, pos);
	}

	@Override
	public void updatePassenger(Entity passenger) {
		super.updatePassenger(passenger);
		if(this.getPassengers().size() > 1 && this.getPassengers().get(1).getEntityId() == passenger.getEntityId()) {
			Vec3d rp = this.getLookVec().scale(-1);
			passenger.setPosition(posX + rp.x, posY + this.getMountedYOffset(), posZ + rp.z);
		}
	}

	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().size() > 0 ? this.getPassengers().get(0) : null;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		if(this.getPassengers().size() < 2) {
			player.startRiding(this, true);
		}
		return true;
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		return this.getPassengers().size() < 2;
	}

}
