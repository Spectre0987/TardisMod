package net.tardis.mod.common.entities;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDalekCasing extends EntityLiving{

	public EntityDalekCasing(World worldIn) {
		super(worldIn);
		this.stepHeight = 1.2F;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getControllingPassenger() != null) {
			Entity e = this.getControllingPassenger();
			if(e != null && e instanceof EntityLivingBase){
				if(((EntityLivingBase)e).moveForward > 0) {
					Vec3d look = ((EntityLivingBase)e).getLookVec().normalize().scale(0.15);
					this.motionX = look.x;
					this.motionZ = look.z;
				}
				this.rotationYaw = ((EntityLivingBase)e).rotationYawHead;
			}
		}
	}

	@Override
	protected void despawnEntity() {
		
	}

	@Override
	public void dismountEntity(Entity entityIn) {
		super.dismountEntity(entityIn);
		if(world.isRemote && entityIn instanceof EntityPlayer) {
			this.setCamera(0);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void setCamera(int i) {
		Minecraft.getMinecraft().gameSettings.thirdPersonView = i;
	}

	@Override
	public boolean canTrample(World world, Block block, BlockPos pos, float fallDistance) {
		return false;
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return this.getPassengers().size() <= 0;
	}

	@Override
	protected boolean canFitPassenger(Entity passenger) {
		return this.getPassengers().size() == 0;
	}

	@Override
	public double getYOffset() {
		return super.getYOffset();
	}

	@Override
	public boolean canRiderInteract() {
		return false;
	}

	@Override
	public double getMountedYOffset() {
		return 0.1D;
	}

	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		player.startRiding(this);
		if(world.isRemote) {
			this.setCamera(1);
		}
		return super.applyPlayerInteraction(player, vec, hand);
	}

	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().size() >= 1 ? this.getPassengers().get(0) : null;
	}
}
