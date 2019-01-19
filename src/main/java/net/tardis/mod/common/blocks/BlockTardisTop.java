package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.systems.SystemDimension;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;

import java.util.function.Supplier;

public class BlockTardisTop extends BlockTileBase implements INoBox {


	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public ItemBlock item = new ItemBlockTardis(this);

	public BlockTardisTop(Supplier<TileEntity> tileEntity) {
		super(Material.WOOD, tileEntity);
		this.setBlockUnbreakable();
		this.setResistance(9999);
		this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
	}

	public ItemBlock getItem() {
		return item;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntityDoor door = (TileEntityDoor) worldIn.getTileEntity(pos);
			door.toggleLocked(playerIn);
			WorldServer ws = worldIn.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
			TileEntity te = ws.getTileEntity(door.getConsolePos());
			if (te != null && te instanceof TileEntityTardis) {
				ItemStack held = playerIn.getHeldItem(hand);
				SystemDimension dim = null;
				if (held.getItem() == TItems.time_vector_generator) {
					for (BaseSystem s : ((TileEntityTardis) te).systems) {
						if (s.getClass() == SystemDimension.class) {
							dim = (SystemDimension) s;
						}
					}
				}
				if (dim != null) {
					dim.repair(held);
					held.shrink(1);
				}
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (!worldIn.isRemote) {
			WorldServer tWorld = worldIn.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
			TileEntityTardis tardis = (TileEntityTardis) tWorld.getTileEntity(((TileEntityDoor) worldIn.getTileEntity(pos)).getConsolePos());
			if (tardis != null) {
				tWorld.playSound(null, tardis.getPos(), TSounds.knocking, SoundCategory.BLOCKS, 1F, 1F);
				worldIn.playSound(null, tardis.getPos(), TSounds.knocking, SoundCategory.BLOCKS, 1F, 1F);
			}
		}
		super.onBlockClicked(worldIn, pos, playerIn);
	}

	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
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
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		try {
			if (!worldIn.isRemote) {
				worldIn.playSound(null, pos, TSounds.tardis_land, SoundCategory.BLOCKS, 1F, 1F);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	@Override
	public boolean causesSuffocation(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		return false;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntityDoor door = (TileEntityDoor) world.getTileEntity(pos);
		if (door != null && !door.isLocked()) {
			return door.getLightLevel();
		}
		return super.getLightValue(state, world, pos);
	}


	public static class ItemBlockTardis extends ItemBlock {

		public ItemBlockTardis(Block block) {
			super(block);
		}

		@Override
		public String getItemStackDisplayName(ItemStack stack) {
			return new TextComponentTranslation(TBlocks.tardis_top.getTranslationKey() + ".name").getFormattedText();
		}

	}

}
