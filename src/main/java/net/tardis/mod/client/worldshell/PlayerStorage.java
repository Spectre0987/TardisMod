package net.tardis.mod.client.worldshell;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerStorage {

	public GameProfile profile;
	public double posX, posY, posZ, rotationYaw;
	public NBTTagCompound tag = new NBTTagCompound();

	public PlayerStorage() {
	}

	public PlayerStorage(EntityPlayer player) {
		profile = player.getGameProfile();
		posX = player.posX;
		posY = player.posY;
		posZ = player.posZ;
		rotationYaw = player.rotationYawHead;
		//player.writeToNBT(tag);
		tag.setBoolean("sneak", player.isSneaking());
		tag.setBoolean("riding", player.isRiding());
		tag.setFloat("limbSwing", player.limbSwing);
		tag.setFloat("limbSwingAmount", player.limbSwingAmount);
		tag.setInteger("ageInTicks", player.ticksExisted);
		tag.setFloat("rotationYaw", player.rotationYaw);
		tag.setFloat("rotationYawHead", player.rotationYawHead);
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

	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, profile.getId().toString());
		buf.writeDouble(posX);
		buf.writeDouble(posY);
		buf.writeDouble(posZ);
		ByteBufUtils.writeTag(buf, tag);
	}

	@SideOnly(Side.CLIENT)
	public FakeClientPlayer getPlayer(World world) {
		FakeClientPlayer player = new FakeClientPlayer(world, this.profile);
		player.setPosition(posX, posY, posZ);
		player.setSneaking(tag.getBoolean("sneak"));
		player.isRiding = tag.getBoolean("riding");
		player.limbSwing = tag.getFloat("limbSwing");
		player.limbSwingAmount = tag.getFloat("limbSwingAmount");
		player.ticksExisted = tag.getInteger("ageInTicks");
		player.rotationYaw = tag.getFloat("rotationYaw");
		player.rotationYawHead = tag.getFloat("rotationYawHead");
		return player;
	}
}
