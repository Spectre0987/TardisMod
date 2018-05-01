package net.tardis.mod.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.blocks.TBlocks;
import net.tardis.mod.tileentity.TileEntitySonicGun;

public class SonicBlaster extends Item {
	
	public SonicBlaster() {
		this.setCreativeTab(Tardis.tab);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!worldIn.isRemote) {
			RayTraceResult ray = worldIn.rayTraceBlocks(playerIn.getPositionVector().addVector(0, playerIn.getEyeHeight(), 0), playerIn.getPositionVector().add(playerIn.getForward().scale(120)),true);
			if(ray!=null&&ray.typeOfHit.equals(RayTraceResult.Type.BLOCK)&&ray.getBlockPos()!=null) {
				BlockPos pos=ray.getBlockPos().down().west().north();
				for(int x=0;x<3;x++) {
					for(int y=0;y<3;y++) {
						for(int z=0;z<3;z++) {
							BlockPos cPos=pos.add(x,y,z);
							IBlockState oldState=worldIn.getBlockState(cPos);
							if(oldState.getBlockHardness(worldIn, cPos)>-1&&oldState.getBlock()!=TBlocks.sonic_block) {
								worldIn.setBlockState(cPos, TBlocks.sonic_block.getDefaultState());
								((TileEntitySonicGun)worldIn.getTileEntity(cPos)).setBlockState(oldState);
							}
						}
					}
				}
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
}
