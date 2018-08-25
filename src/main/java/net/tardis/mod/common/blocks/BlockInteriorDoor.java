package net.tardis.mod.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityInteriorDoor;

public class BlockInteriorDoor extends BlockFacingDecoration {

    private DoorTypes type;

    public BlockInteriorDoor(DoorTypes type) {
        super(TileEntityInteriorDoor::new);
        this.type = type;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        TileEntityInteriorDoor door = (TileEntityInteriorDoor) worldIn.getTileEntity(pos);
        door.setDoorType(type);
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
            TileEntityInteriorDoor door = (TileEntityInteriorDoor) worldIn.getTileEntity(pos);
            door.setOpen(!door.getOpen());
            worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), door.getOpen() ? type.getOpenSound() : type.getClosedSound(), SoundCategory.PLAYERS, 1.0F, 1.0F);
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
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
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return true;
	}
	
	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

    public enum DoorTypes {
        DEFAULT(TSounds.door_open, TSounds.door_closed);

        private final SoundEvent doorOpen, doorClosed;

        DoorTypes(SoundEvent doorOpen, SoundEvent doorClosed) {
            this.doorOpen = doorOpen;
            this.doorClosed = doorClosed;
        }

        public SoundEvent getOpenSound() {
            return doorOpen;
        }

        public SoundEvent getClosedSound() {
            return doorClosed;
        }
    }

}
