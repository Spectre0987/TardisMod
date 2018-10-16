package net.tardis.mod.common.entities;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityRayCyberman extends EntityThrowable implements IEntityAdditionalSpawnData {

	public float damage;
	public Vec3d color;

	public EntityRayCyberman(World worldIn) {
		super(worldIn);
	}

	public EntityRayCyberman(World worldIn, EntityLivingBase throwerIn, float damage, Vec3d color) {
		super(worldIn, throwerIn);
		this.damage = damage;
		this.color = color;
	}

	@Override
	public void onEntityUpdate() {
		double speed = new Vec3d(posX, posY, posZ).distanceTo(new Vec3d(prevPosX, prevPosY, prevPosZ));
		if (!this.world.isRemote && (ticksExisted > 30 * 20 || speed < 0.01)) {
			this.setDead();
		}
		super.onEntityUpdate();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result == null || isDead)
			return;

		if (result.typeOfHit == Type.ENTITY) {
			if (result.entityHit == this.thrower) return;
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), damage);
		} else if (result.typeOfHit == Type.BLOCK) {

			IBlockState block = world.getBlockState(result.getBlockPos());

			if (block.getBlock() == Blocks.TNT) {
				//	BlockTNT tnt = (BlockTNT) block;
				//tnt.explode(world, result.getBlockPos(), block, getThrower());
			}
		}

		if (!this.world.isRemote)
			this.setDead();
	}

	@Override
	protected float getGravityVelocity() {
		return 0.00001F;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setFloat("Damage", damage);
		compound.setDouble("Color_R", color.x);
		compound.setDouble("Color_G", color.y);
		compound.setDouble("Color_B", color.z);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.damage = compound.getFloat("Damage");
		this.color = new Vec3d(compound.getDouble("Color_R"), compound.getDouble("Color_G"), compound.getDouble("Color_B"));
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeEntityToNBT(nbt);
		ByteBufUtils.writeTag(buffer, nbt);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		this.readEntityFromNBT(ByteBufUtils.readTag(additionalData));
	}

	@Override
	public boolean isInWater() {
		return false;
	}
}
