package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.tileentity.TileEntityTemporalLab;

public class BlockTemporalLab extends BlockTileBase {
	
	public ItemBlock item = new ItemBlock(this);
	
	public BlockTemporalLab() {
		super(Material.IRON, TileEntityTemporalLab::new);
		this.setCreativeTab(Tardis.tab);
		item.setCreativeTab(Tardis.tab);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			playerIn.openGui(Tardis.MODID, Tardis.ID_GUI_TEMPORAL_LAB, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
	
}
