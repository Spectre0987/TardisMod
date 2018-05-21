package net.tardis.mod.util.helpers;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.util.TardisTeleporter;

public class Helper {
	
	public static Random rand = new Random();
	
	public static void transferToOwnedTardis(EntityPlayerMP player, WorldServer world, BlockPos pos) {
		player.getServer().getPlayerList().transferPlayerToDimension(player, TDimensions.id, new TardisTeleporter(world));
		pos = pos.offset(EnumFacing.SOUTH, 4);
		player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}
	
	public static String formatBlockPos(BlockPos pos) {
		if (pos == null || pos.equals(BlockPos.ORIGIN)) return "None";
		return pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
	}
	
	public static void transferToWorld(EntityPlayerMP player, WorldServer world, BlockPos pos, int dim) {
		world.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, dim, new TardisTeleporter(world));
		player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
	}
	
	public static Vec3d convertToPixels(Vec3d vec) {
		return new Vec3d(vec.x / 16, vec.y / 16, vec.z / 16);
	}
	
	public static Vec3d convertToPixels(double x, double y, double z) {
		return new Vec3d(x / 16, y / 16, z / 16);
	}
	
	public static void tell(EntityPlayer playerIn, String string) {
		playerIn.sendMessage(new TextComponentString(string));
	}
	
	public static BlockPos getLowestBlock(World world, BlockPos pos) {
		pos = new BlockPos(pos.getX(), 0, pos.getZ());
		for (int i = 0; i < 256; ++i) {
			if (world.getBlockState(pos).getBlock()==Blocks.AIR && world.getBlockState(pos.up()).getBlock()==Blocks.AIR)
				return pos;
			pos = pos.up();
		}
		return null;
	}
	
	public static double clamp(double f, double f1) {
		return f > f1 ? f1 : f;
	}
	
	public static AxisAlignedBB createBB(BlockPos pos, double i) {
		return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).grow(i);
	}
	
	public static boolean isSafe(World world,BlockPos pos) {
		if(world.getBlockState(pos).getMaterial().equals(Material.AIR) && world.getBlockState(pos.down()).isTopSolid() && world.getBlockState(pos.up()).getMaterial().equals(Material.AIR))
			return true;
		else 
			return false;
	}
	
	public static boolean isDimensionBlocked(int id) {
		if(id==TDimensions.id)
			return true;
		for(int i:TardisConfig.BlockedDimensions.bDims) {
			if(id==i)
				return true;
		}
		return false;
	}

	public static float getAngleFromFacing(EnumFacing facing) {
		float angle = 0;
		if(facing.equals(EnumFacing.EAST))
			angle = 90;
		if(facing.equals(EnumFacing.SOUTH))
			angle = 180;
		if(facing.equals(EnumFacing.WEST))
			angle = 270;
		return angle;
	}

	public static Rotation getRotationFromFacing(EnumFacing facing) {
		switch(facing) {
			case NORTH:return Rotation.COUNTERCLOCKWISE_90;
			case EAST: return Rotation.CLOCKWISE_90;
			case SOUTH: return Rotation.CLOCKWISE_180;
			case WEST: return Rotation.COUNTERCLOCKWISE_90;
			default: return Rotation.NONE;
		}
	}
	
}
