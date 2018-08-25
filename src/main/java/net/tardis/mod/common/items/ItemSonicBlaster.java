package net.tardis.mod.common.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.tileentity.TileEntitySonicGun;

public class ItemSonicBlaster extends ItemBase {

    private AxisAlignedBB axisAlignedBB;

    public void setAxisAlignedBB(AxisAlignedBB axisAlignedBB) {
        this.axisAlignedBB = axisAlignedBB;
    }

    public AxisAlignedBB getAxisAlignedBB(EntityPlayer player, BlockPos pos) {
        return posToHelper(player, pos);
    }

    public AxisAlignedBB getAxisAlignedBB() {
        return axisAlignedBB;
    }

    public static AxisAlignedBB posToHelper(EntityPlayer player, BlockPos pos) {
    	
    	 System.out.println(player.rotationPitch);
    	
    	  if(MathHelper.ceil(player.getLookVec().y) > -20 || MathHelper.ceil(player.getLookVec().y) < 20){
              return new AxisAlignedBB(pos.north().west(), pos.south().east());
          }
    	  
        return new AxisAlignedBB(pos.west().up(), pos.east().down());
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        IBlockState state = worldIn.getBlockState(pos);
        if (state.getBlock() == Blocks.AIR) return EnumActionResult.FAIL;

        AxisAlignedBB box = posToHelper(player, pos);

        for (int x = (int) box.minX; x <= box.maxX; x++) {
            for (int y = (int) box.minY; y <= box.maxY; y++) {
                for (int z = (int) box.minZ; z <= box.maxZ; z++) {
                    BlockPos changePos = new BlockPos(x, y, z);
                    IBlockState oldState = worldIn.getBlockState(changePos);
                    if (oldState.getBlock() != Blocks.AIR) {
                        worldIn.setBlockState(changePos, TBlocks.sonic_blaster.getDefaultState());
                        TileEntitySonicGun tile = (TileEntitySonicGun) worldIn.getTileEntity(changePos);
                        tile.setBlockState(oldState);
                    }
                }
            }
        }

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
