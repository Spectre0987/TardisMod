package net.tardis.mod.util.helpers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.api.dimensions.IBlockedDimension;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.util.TardisTeleporter;

public class Helper {
	
	public static Random rand = new Random();
	
	public static void transferToOwnedTardis(EntityPlayerMP player, WorldServer world, BlockPos pos) {
        player.getServer().getPlayerList().transferPlayerToDimension(player, TDimensions.TARDIS_ID, new TardisTeleporter(world));
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
			if (world.getBlockState(pos).getBlock() == Blocks.AIR && world.getBlockState(pos.up()).getBlock() == Blocks.AIR) return pos;
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
	
	public static boolean isSafe(World world, BlockPos pos, EnumFacing facing) {
		if (world.getBlockState(pos).getMaterial().equals(Material.AIR) && world.getBlockState(pos.down()).isTopSolid() && world.getBlockState(pos.up()).getMaterial().equals(Material.AIR)) {
            return world.getBlockState(pos.offset(facing)).getMaterial().equals(Material.AIR) && world.getBlockState(pos.offset(facing).up()).getMaterial().equals(Material.AIR);
		}
		return false;
	}
	
	public static boolean isDimensionBlocked(int id) {
       if (id == TDimensions.TARDIS_ID) return true;
        if(DimensionManager.createProviderFor(id) instanceof IBlockedDimension)return true;
        boolean isW = TardisConfig.Dimensions.USE_WHITELIST;
		for (int i : TardisConfig.Dimensions.bDims) {
			if(isW) {
				if (id == i) return false;
			}
			else {
				if (id == i) return true;
			}
		}
		if(isW) return true;
		return false;
	}
	
	public static float getAngleFromFacing(EnumFacing facing) {
		float angle = 0;
		if (facing.equals(EnumFacing.EAST)) angle = 90;
		if (facing.equals(EnumFacing.SOUTH)) angle = 180;
		if (facing.equals(EnumFacing.WEST)) angle = 270;
		return angle;
	}
	
	public static Rotation getRotationFromFacing(EnumFacing facing) {
		switch (facing) {
			case NORTH:
				return Rotation.COUNTERCLOCKWISE_90;
			case EAST:
				return Rotation.CLOCKWISE_90;
			case SOUTH:
				return Rotation.CLOCKWISE_180;
			case WEST:
				return Rotation.COUNTERCLOCKWISE_90;
			default:
				return Rotation.NONE;
		}
	}
	
	public static float get360FromFacing(EnumFacing facing) {
		switch (facing) {
			case NORTH:
				return 180;
			case EAST:
				return -90;
			case SOUTH:
				return 0;
			default:
				return 90;
		}
	}
	
	public static boolean isIntInRange(int min, int max, int num) {
		return num < max && num > min;
	}

	public static int getSlotForItem(EntityPlayer player, Item item) {
		for(int i = 0; i < player.inventory.mainInventory.size(); ++i) {
			if(player.inventory.getStackInSlot(i).getItem() == item) {
				return i;
			}
		}
		return -1;
	}

	public static Biome findBiomeByName(String string) {
		List<Biome> biomes = EntityHelper.biomes;
		try {
			Field field; 
			if((Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
				field = Biome.class.getDeclaredField("biomeName");
			}
			else {
				field = Biome.class.getDeclaredField("field_76791_y");
			}
			field.setAccessible(true);
			for(Biome b : biomes) {
				Object obj = field.get(b);
				if(((String)obj).trim().toLowerCase().equals(string)) {
					return b;
				}
			}
		}
		catch(Exception e) {}
		return null;
	}

	public static Vec3d blockPosToVec3d(Vec3i pos) {
		return new Vec3d(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public static BlockPos getSafePosLower(BlockPos pos, World world, EnumFacing facing) {
		for(int y = pos.getY(); y > 0; --y) {
			BlockPos lPos = new BlockPos(pos.getX(), y, pos.getZ());
			if(Helper.isSafe(world, lPos, facing)) {
				return lPos;
			}
		}
		return world.getTopSolidOrLiquidBlock(pos);
	}
	
	public static BlockPos getSafeHigherPos(World world, BlockPos pos, EnumFacing facing) {
		for(int y = pos.getY(); y < world.getHeight(); ++y) {
			BlockPos lPos = new BlockPos(pos.getX(), y, pos.getZ());
			if(Helper.isSafe(world, lPos, facing)) {
				return lPos;
			}
		}
		return world.getTopSolidOrLiquidBlock(pos);
	}

	public static float precentToPixels(float f) {
		return f / 16.0F;
	}
	
	public static String formatDimensionName(String name) {
		name = name.replace("_", " ");
		char[] nameChars = name.toCharArray();
		for(int index = 0; index < nameChars.length; ++index) {
			if(nameChars[index] == ' ' && index + 1 < nameChars.length) {
				char c = nameChars[index + 1];
				nameChars[index + 1] = Character.toUpperCase(c);
			}
		}
		nameChars[0] = Character.toUpperCase(nameChars[0]);
		return new String(nameChars);
	}
}
