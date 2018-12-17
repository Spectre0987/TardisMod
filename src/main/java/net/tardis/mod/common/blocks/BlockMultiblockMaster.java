package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.blocks.interfaces.INeedItem;
import net.tardis.mod.common.tileentity.TileEntityMultiblockMaster;

public class BlockMultiblockMaster extends BlockMultiblock implements INeedItem{

	public static final BlockMultiblockMaster DUMMY = new BlockMultiblockMaster(Material.AIR);
	
	public BlockMultiblockMaster(Material material) {
		super(material);
		this.setCreativeTab(TardisTabs.BLOCKS);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMultiblockMaster();
	}

	@Override
	public ItemBlock getItem() {
		return new ItemBlock(this);
	}
	
	public static BlockMultiblockMaster getMasterBlock(World world, BlockPos pos) {
		if(world.getBlockState(pos).getBlock() instanceof BlockMultiblockMaster)
			return (BlockMultiblockMaster)world.getBlockState(pos).getBlock();
		return DUMMY;
	}

	@Override
	public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
		System.out.println("Hardness: " + this.blockHardness);
		return this.blockHardness;
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
		return this.blockHardness;
	}
}
