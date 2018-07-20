package net.tardis.mod.client.worldshell;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.world.World;

public class FakeClientPlayer extends AbstractClientPlayer{

	public boolean isRiding = false;
	
	public FakeClientPlayer(World worldIn, GameProfile playerProfile) {
		super(worldIn, playerProfile);
	}

	@Override
	public boolean isRiding() {
		return isRiding;
	}

}
