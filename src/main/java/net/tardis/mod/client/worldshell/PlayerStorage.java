package net.tardis.mod.client.worldshell;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class PlayerStorage{
	
	public GameProfile profile;
	public double posX, posY, posZ;
	
	public PlayerStorage() {}
	
	public PlayerStorage(EntityPlayer player) {
		profile = player.getGameProfile();
		posX = player.posX;
		posY = player.posY;
		posZ = player.posZ;
	}

	
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, profile.getId().toString());
		buf.writeDouble(posX);
		buf.writeDouble(posY);
		buf.writeDouble(posZ);
	}
	
	public static PlayerStorage fromBytes(ByteBuf buf) {
		PlayerStorage stor = new PlayerStorage();
		stor.profile = new GameProfile(UUID.fromString(ByteBufUtils.readUTF8String(buf)), "name");
		stor.posX = buf.readDouble();
		stor.posY = buf.readDouble();
		stor.posZ = buf.readDouble();
		return stor;
	}
}
