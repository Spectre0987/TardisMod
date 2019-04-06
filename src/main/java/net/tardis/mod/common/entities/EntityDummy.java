package net.tardis.mod.common.entities;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.tardis.mod.capability.CapabilityTardis;
import net.tardis.mod.capability.ITardisCap;

public class EntityDummy extends Entity{

	public static final DataParameter<String> PLAYER_ID = EntityDataManager.createKey(EntityDummy.class, DataSerializers.STRING);
	public EntityPlayer player;
	
	public EntityDummy(World world, EntityPlayer player){
		this(world);
		this.dataManager.set(PLAYER_ID, player.getGameProfile().getId().toString());
		this.player = player;
	}
	
	public EntityDummy(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(PLAYER_ID, "");
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		this.dataManager.set(PLAYER_ID, compound.getString("player_id"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setString("player_id", this.dataManager.get(PLAYER_ID).toString());
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(!world.isRemote) {
			EntityPlayer player = world.getMinecraftServer().getPlayerList().getPlayerByUUID(UUID.fromString(this.dataManager.get(PLAYER_ID)));
			if(player != null) {
				ITardisCap cap = CapabilityTardis.get(player);
				if(cap.isInFlight()) {
					CapabilityTardis.endFlight(player, true);
				}
			}
			this.setDead();
		}
		return true;
	}

}
