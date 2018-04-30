package net.tardis.mod.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.Tardis;
import net.tardis.mod.dimensions.TDimensions;
import net.tardis.mod.items.Key;
import net.tardis.mod.items.TItems;
import net.tardis.mod.tileentity.TileEntityTardis;

public class HomingBeacon extends Block {

	public HomingBeacon() {
		super(Material.IRON);
		this.setCreativeTab(Tardis.tab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			ItemStack stack=playerIn.getHeldItem(hand);
			if(stack.getItem()==TItems.key) {
				BlockPos tPos=Key.getPos(stack);
				if(tPos!=null&&!tPos.equals(BlockPos.ORIGIN)) {
					TileEntityTardis tardis=(TileEntityTardis)((WorldServer)world).getMinecraftServer().getWorld(TDimensions.id).getTileEntity(tPos);
					tardis.setDesination(pos.north(), playerIn.dimension);
					tardis.travel();
				}
			}
		}
		return true;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list=new ArrayList<ItemStack>();
		list.add(new ItemStack(TBlocks.homing_beacon));
		return list;
	}
	

}
