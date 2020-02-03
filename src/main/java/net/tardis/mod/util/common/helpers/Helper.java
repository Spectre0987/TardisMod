package net.tardis.mod.util.common.helpers;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.tardis.mod.api.dimensions.IBlockedDimension;
import net.tardis.mod.client.worldshell.WorldShell;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.util.TardisTeleporter;

public class Helper {

	public static Random rand = new Random();
	
	public static <T extends Enum<?>> T randomEnum(Class<T> clazz, Random random) {
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}
	
	public static void transferToOwnedTardis(EntityPlayerMP player, WorldServer world, BlockPos pos) {

	}

	public static String formatBlockPos(BlockPos pos) {
		if (pos == null || pos.equals(BlockPos.ORIGIN)) return "None";
		return pos.getX() + ", " + pos.getY() + ", " + pos.getZ();
	}

	public static void transferToWorld(EntityPlayerMP player, WorldServer world, BlockPos pos, int dim) {
		world.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, dim, new TardisTeleporter(new BlockPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5)));
	}

	public static Vec3d convertToPixels(Vec3d vec) {
		return new Vec3d(vec.x / 16, vec.y / 16, vec.z / 16);
	}

	public static Vec3d convertToPixels(double x, double y, double z) {
		return new Vec3d(x / 16, y / 16, z / 16);
	}

	public static BlockPos getLowestBlock(World world, BlockPos pos) {
		pos = new BlockPos(pos.getX(), 0, pos.getZ());
		for (int i = 0; i < 256; ++i) {
			if (isStateOverwritable(world.getBlockState(pos)) && isStateOverwritable(world.getBlockState(pos.up())))
				return pos;
			pos = pos.up();
		}
		return null;
	}
	
	public static boolean isStateOverwritable(IBlockState state) {
		return state.getMaterial() == Material.PLANTS ||
				state.getMaterial() == Material.AIR ||
				state.getMaterial() == Material.SNOW ||
				state.getMaterial() == Material.WATER||
				state.getMaterial() == Material.CARPET ||
				state.getMaterial().isLiquid() || 
				state.getMaterial().isReplaceable();
	}

	public static double clamp(double f, double f1) {
		return f > f1 ? f1 : f;
	}

	public static AxisAlignedBB createBB(BlockPos pos, double i) {
		return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1).grow(i);
	}

	public static boolean isSafe(World world, BlockPos pos, EnumFacing facing) {
		return isStateOverwritable(world.getBlockState(pos)) && isStateOverwritable(world.getBlockState(pos.up())) && !isStateOverwritable(world.getBlockState(pos.down()));
	}
	
	public static boolean isDimensionBlocked(int id) {
		if (id == TDimensions.TARDIS_ID) return true;
		if (DimensionManager.createProviderFor(id) instanceof IBlockedDimension) return true;
		boolean isW = TardisConfig.Dimensions.USE_WHITELIST;
		for (int i : TardisConfig.Dimensions.bDims) {
			if (isW) {
				if (id == i)
					return false;
			}
			else {
				if (id == i)
					return true;
			}
		}
		return isW;
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

	public static float get180Rot(EnumFacing facing) {
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
		for (int i = 0; i < player.inventory.mainInventory.size(); ++i) {
			if (player.inventory.getStackInSlot(i).getItem() == item) {
				return i;
			}
		}
		return -1;
	}

	public static Biome findBiomeByName(String string) {
		List<Biome> biomes= ForgeRegistries.BIOMES.getValues();
		for (Biome biome : biomes) {
			if(biome.biomeName.trim().toLowerCase().equals(string)){
				return biome;
			}
		}
		return null;
	}

	public static Vec3d blockPosToVec3d(Vec3i pos) {
		return new Vec3d(pos.getX(), pos.getY(), pos.getZ());
	}

	public static BlockPos getSafePosLower(BlockPos pos, World world, EnumFacing facing) {
		for (int y = pos.getY(); y > 0; --y) {
			BlockPos lPos = new BlockPos(pos.getX(), y, pos.getZ());
			if (Helper.isSafe(world, lPos, facing)) {
				return lPos;
			}
		}
		return BlockPos.ORIGIN;
	}

	public static BlockPos getSafeHigherPos(World world, BlockPos pos, EnumFacing facing) {
		for (int y = pos.getY(); y < world.getHeight(); ++y) {
			BlockPos lPos = new BlockPos(pos.getX(), y, pos.getZ());
			if (Helper.isSafe(world, lPos, facing)) {
				return lPos;
			}
		}
		return BlockPos.ORIGIN;
	}

	public static float precentToPixels(float f) {
		return f / 16.0F;
	}

	public static String formatDimensionName(String name) {
		if(name == null) return "UNKNOWN!";
		name = name.replace("_", " ");
		char[] nameChars = name.toCharArray();
		for (int index = 0; index < nameChars.length; ++index) {
			if (nameChars[index] == ' ' && index + 1 < nameChars.length) {
				char c = nameChars[index + 1];
				nameChars[index + 1] = Character.toUpperCase(c);
			}
		}
		nameChars[0] = Character.toUpperCase(nameChars[0]);
		return new String(nameChars);
	}

	/**
	 * Stops those nasty null pointers
	 **/
	public static NBTTagCompound getStackTag(ItemStack stack) {
		if (stack.getTagCompound() == null)
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}

	public static BlockPos scaleBP(BlockPos dist, double i) {
		return new BlockPos(dist.getX() * i, dist.getY() * i, dist.getZ() * i);
	}

	public static TileEntityTardis getTardis(TileEntity te) {
		return te != null && te instanceof TileEntityTardis ? ((TileEntityTardis) te) : null;
	}

	public static boolean isThisBlockBehindTheWorldBorder(BlockPos pos, int dim) {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim).getWorldBorder().contains(pos);
	}

	public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
		for (Map.Entry<T, E> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}
	
	
	public static int getLight(World world, BlockPos pos) {
		int light = 0;
		for(EnumFacing face : EnumFacing.VALUES) {
			int newLight = world.getLight(pos.offset(face), true);
			if(newLight > light)
				light = newLight;
		}
		return light;
	}
	
	public static int getDaylight(World world, BlockPos pos) {
		int light = 0;
		for(EnumFacing face : EnumFacing.VALUES) {
			int newLight = world.getLightFor(EnumSkyBlock.SKY, pos);
			if(newLight > light)
				light = newLight;
		}
		return light;
	}
	
	public static boolean areWorldShellsEqual(WorldShell shell, WorldShell shell1) {
		if(!shell.getOffset().equals(shell1.getOffset()))
			return false;
		if(shell.blockMap.size() != shell1.blockMap.size())
			return false;
		for(BlockPos pos : shell.blockMap.keySet()) {
			if(!shell.blockMap.get(pos).equals(shell1.blockMap.get(pos)))
				return false;
		}
		return true;
	}
	
	public static boolean canRenderInBOTI(IBlockState state) {
		for(String blocked : TardisConfig.BOTI.modids) {
			if(state.getBlock().getRegistryName().toString().equals(blocked))
				return false;
			if(blocked.endsWith("*")) {
				String modid = blocked.substring(0, blocked.indexOf(':'));
				if(state.getBlock().getRegistryName().getNamespace().equals(modid))
					return false;
			}
		}
		return true;
	}
}
