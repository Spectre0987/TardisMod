package net.tardis.mod.common.entities.vehicles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;

public class EntityBessie extends Entity{

	public static final DataParameter<Integer> DAMAGE_TICKS = EntityDataManager.createKey(EntityBessie.class, DataSerializers.VARINT);
	
	public static int MAX_PASSENGERS = 3;
	public float health = 40F;
	
	public EntityBessie(World worldIn) {
		super(worldIn);
		this.setSize(1.9F, 1.5F);
		this.stepHeight = 1.3F;
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		return this.getPassengers().size() < MAX_PASSENGERS;
	}

	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().size() > 0 ? this.getPassengers().get(0) : null;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(world.isRemote) {
			if(this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityLivingBase) {
				EntityLivingBase base = (EntityLivingBase) this.getControllingPassenger();
				Vec3d look = this.getLookVec().scale(0.75);
				boolean move = false;
				this.prevRotationYaw = this.rotationYaw;
				if(base.moveForward > 0) {
					this.motionX = look.x;
					this.motionZ = look.z;
					move = true;
				}
				else if (base.moveForward < 0) {
					look = look.scale(-1);
					this.motionX = look.x;
					this.motionZ = look.z;
					move = true;
				}
				if(base.moveStrafing > 0) {
					this.rotationYaw -= 10;
					move = true;
				}
				else if (base.moveStrafing < 0) {
					this.rotationYaw += 10;
					move = true;
				}
				if(move) world.sendPacketToServer(new CPacketVehicleMove(this));
			}
		}
		this.move(MoverType.SELF, motionX, motionY, motionZ);
		motionX = 0; motionY = 0; motionZ = 0;
		if(!this.hasNoGravity() && !this.onGround) {
			motionY -= 0.5D;
		}
		if(!world.isRemote) {
			if(this.health <= 0.0F) {
				InventoryHelper.spawnItemStack(world, posX, posY, posZ, new ItemStack(TItems.bessie));
				this.setDead();
			}
			else if(this.dataManager.get(DAMAGE_TICKS) > 0) {
				this.dataManager.set(DAMAGE_TICKS, this.getDataManager().get(DAMAGE_TICKS) - 1);
			}
		}
	}

	@Override
	public void updatePassenger(Entity pass) {
		super.updatePassenger(pass);
		if(this.getPassengers().size() > 0 && this.getPassengers().get(0) == pass) {
			Vec3d pos = (this.getLookVec().rotateYaw(-90).scale(0.5)).add(this.getPositionVector());
			pass.setPosition(pos.x, pos.y , pos.z);
		}
		if(this.getPassengers().size() > 1 && this.getPassengers().get(1) == pass) {
			Vec3d pos = (this.getLookVec().rotateYaw(90).scale(0.5)).add(this.getPositionVector());
			pass.setPosition(pos.x, pos.y , pos.z);
		}
		if(this.getPassengers().size() > 2 && this.getPassengers().get(2) == pass) {
			Vec3d pos = (this.getLookVec().scale(-1)).add(this.getPositionVector());
			pass.setPosition(pos.x, pos.y, pos.z);
		}
		
		if(pass instanceof EntityLivingBase) {
			((EntityLivingBase)pass).rotationYaw = this.rotationYaw;
		}
	}

	@Override
	public double getMountedYOffset() {
		return 0.5;
	}

	@Override
	public void applyEntityCollision(Entity entityIn) {
		if(!(entityIn instanceof EntityPlayer) && this.getPassengers().size() < MAX_PASSENGERS && world.getWorldTime() % 20 == 0 && entityIn.getRidingEntity() == null) {
			entityIn.startRiding(this);
		}
		super.applyEntityCollision(entityIn);
	}

	@Override
	protected void entityInit() {
		this.getDataManager().register(DAMAGE_TICKS, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		health = compound.getFloat("health");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setFloat("health", health);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
		if(player.isSneaking() && this.getPassengers().size() > 0) {
			for(Entity e : this.getPassengers()) {
				e.dismountRidingEntity();
			}
		}
		else if(!player.isSneaking() && this.getPassengers().size() < MAX_PASSENGERS){
			player.startRiding(this);
		}
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	@Override
	public boolean canRiderInteract() {
		return false;
	}

	@Override
	public boolean canPassengerSteer() {
		return super.canPassengerSteer();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		this.health -= amount;
		this.getDataManager().set(DAMAGE_TICKS, 20);
		return true;
	}

	public void playHorn() {
		this.playSound(TSounds.bessieHorn, 1, 1);
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}

	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}
}
