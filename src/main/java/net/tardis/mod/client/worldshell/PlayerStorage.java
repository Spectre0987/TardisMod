package net.tardis.mod.client.worldshell;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class PlayerStorage{
	
	public GameProfile profile;
	public double posX, posY, posZ, rotationYaw;
	public NBTTagCompound tag = new NBTTagCompound();
	
	public PlayerStorage() {}
	
	public PlayerStorage(EntityPlayer player) {
		profile = player.getGameProfile();
		posX = player.posX;
		posY = player.posY;
		posZ = player.posZ;
		rotationYaw = player.rotationYawHead;
		player.writeToNBT(tag);
		tag.setBoolean("sneak", player.isSneaking());
		tag.setBoolean("riding", player.isRiding());
		tag.setFloat("limbSwing", player.limbSwing);
		tag.setFloat("limbSwingAmount", player.limbSwingAmount);
		tag.setInteger("ageInTicks", player.ticksExisted);
		tag.setFloat("swingProgress", player.swingProgress);
		tag.setDouble("motionX", player.motionX);
		tag.setDouble("motionY", player.motionY);
		tag.setDouble("motionZ", player.motionZ);
		tag.setFloat("rotationYaw", player.rotationYaw);
		tag.setFloat("rotationYawHead", player.rotationYawHead);
	}

	
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, profile.getId().toString());
		buf.writeDouble(posX);
		buf.writeDouble(posY);
		buf.writeDouble(posZ);
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static PlayerStorage fromBytes(ByteBuf buf) {
		PlayerStorage stor = new PlayerStorage();
		stor.profile = new GameProfile(UUID.fromString(ByteBufUtils.readUTF8String(buf)), "name");
		stor.posX = buf.readDouble();
		stor.posY = buf.readDouble();
		stor.posZ = buf.readDouble();
		stor.tag = ByteBufUtils.readTag(buf);
		return stor;
	}
}
