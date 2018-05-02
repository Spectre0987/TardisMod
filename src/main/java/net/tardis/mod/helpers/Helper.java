package net.tardis.mod.helpers;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.Tardis;
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.util.TardisTeleporter;

public class Helper {

	public static Random rand=new Random();
	
	public static void transferToOwnedTardis(EntityPlayerMP player, WorldServer world,BlockPos pos) {
		player.getServer().getPlayerList().transferPlayerToDimension(player, TDimensions.id, new TardisTeleporter(world));
		pos=pos.offset(EnumFacing.SOUTH, 4);
		player.setPositionAndUpdate(pos.getX()+0.5, pos.getY(),pos.getZ()+0.5);
	}

	public static String formatBlockPos(BlockPos pos) {
		if(pos==null||pos.equals(BlockPos.ORIGIN))return "None";
		return pos.getX()+", "+pos.getY()+", "+pos.getZ();
	}

	public static void transferToWorld(EntityPlayerMP player,WorldServer world,BlockPos pos,int dim) {
		world.getMinecraftServer().getPlayerList().transferPlayerToDimension(player, dim, new TardisTeleporter(world));
		player.setPositionAndUpdate(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5);
	}

	public static Vec3d convertToPixels(Vec3d vec) {
		return new Vec3d(vec.x/16,vec.y/16,vec.z/16);
	}
	
	public static Vec3d convertToPixels(double x,double y,double z) {
		return new Vec3d(x/16,y/16,z/16);
	}

	public static void say(String string) {
		System.out.println(string);
	}

	public static void tell(EntityPlayer playerIn, String string) {
		playerIn.sendMessage(new TextComponentString(string));
	}

	public static BlockPos findTardisLandingSpot(World world,BlockPos pos) {
		if(getLowestBlock(world,pos)!=null) {
			if(!world.getBlockState(pos.north()).causesSuffocation()&&!world.getBlockState(pos.north().up()).causesSuffocation()) {
				return pos;
			}
		}
		return findTardisLandingSpot(world, pos.add(rand.nextInt(6)-3,0,rand.nextInt(6)-3));
	}
	public static BlockPos getLowestBlock(World world,BlockPos pos) {
		pos=new BlockPos(pos.getX(),0,pos.getZ());
		for(int i=0;i<256;++i) {
			if(!world.getBlockState(pos).causesSuffocation()&&!world.getBlockState(pos.up()).causesSuffocation())return pos;
			pos=pos.up();
		}
		return null;
	}
	public static double clamp(double f,double f1) {
		return f>f1?f1:f;
	}

	public static AxisAlignedBB createBB(BlockPos pos, double i) {
		return new AxisAlignedBB(pos.getX(),pos.getY(),pos.getZ(),pos.getX()+1,pos.getY()+1,pos.getZ()+1).grow(i);
	}
}
