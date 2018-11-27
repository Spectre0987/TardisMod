package net.tardis.mod.common.blocks;

import com.google.common.base.Supplier;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.api.blocks.IBlock;
import net.tardis.mod.common.blocks.interfaces.IRenderBox;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class BlockConsole extends BlockTileBase implements IRenderBox, IBlock {
	
	public static final AxisAlignedBB BB = new AxisAlignedBB(-1, 0, -1, 2, 0.7, 2);
	public ItemBlock item = new ItemBlock(this);
	
	public BlockConsole(Supplier<TileEntity> tile) {
		super(Material.ROCK, tile);
		this.setBlockUnbreakable();
		this.setResistance(9999F);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 15;
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
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		try {
			TileEntityTardis tardis = (TileEntityTardis) worldIn.getTileEntity(pos);
			tardis.createControls();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			ItemStack held = playerIn.getHeldItem(hand);
			if(playerIn.isSneaking()) {
				TileEntityTardis tardis = (TileEntityTardis)worldIn.getTileEntity(pos);
				if(tardis.controls != null) {
					for(EntityControl c : tardis.controls) {
						c.setDead();
					}
				}
				tardis.controls = null;
				tardis.createControls();
			}
			else if(!held.isEmpty()) {
				TileEntityTardis tardis = (TileEntityTardis)worldIn.getTileEntity(pos);
				if(tardis != null) {
					for(BaseSystem sys : tardis.systems) {
						if(held.getItem() == sys.getRepairItem()) {
							if(sys.repair(held)) {
								playerIn.getHeldItem(hand).shrink(1);
							}
						}
					}
				}
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}


	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		return false;
	}

    /**
     * This interface determines whether
     * the bounding box the player sees when their mouse is over a block
     * should be rendered on the block this is implemented to
     */
    @Override
    public boolean shouldRenderBox() {
        return false;
    }

	@Override
	public boolean doesDelete() {
		return false;
	}
}
